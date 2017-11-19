package servidor;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import mensajeria.PaqueteMensaje;
import mensajeria.PaqueteMovimiento;
import mensajeria.PaquetePersonaje;

public class Servidor extends Thread {
/**
 * ArrayList clientesConectados del tipo EscuchaCliente.
 */
    private static ArrayList<EscuchaCliente> clientesConectados = new ArrayList<>();
/**
 * HashMap ubicacionPersonajes.
 */
    private static Map<Integer, PaqueteMovimiento> ubicacionPersonajes = new HashMap<>();
/**
 * HashMap personajesConectados.
 */
    private static Map<Integer, PaquetePersonaje> personajesConectados = new HashMap<>();
/**
 * Variable server del tipo Thread.
 */
    private static Thread server;
/**
 * Variable serverSocket del tipo ServerSocket.
 */
    private static ServerSocket serverSocket;
/**
 * Variable conexionDB del tipo Conector.
 */
    private static Conector conexionDB;
/**
 * Variable PUERTO del tipo final int.
 */
    private final int PUERTO = 55050;
/**
 * Constante ANCHO del tipo int.
 */
    private static final int ANCHO = 700;
/**
 * Constante ALTO del tipo int.
 */
    private static final int ALTO = 640;
/**
 * Constante ALTO_LOG del tipo int.
 */
    private static final int ALTO_LOG = 520;
/**
 * Constante ANCHO_LOG del tipo int.
 */
    private static final int ANCHO_LOG = ANCHO - 25;
/**
 * Variable log del tipo JTextArea.
 */
    public static JTextArea log;
/**
 * Variable atencionConexiones del tipo AtencionConexiones.
 */
    public static AtencionConexiones atencionConexiones;
/**
 * Varible atencionMovimientos del tipo AtencionMovimientos.
 */
    public static AtencionMovimientos atencionMovimientos;
/**
 * Main.
 * @param args del tipo string
 */
    public static void main(final String[] args) {
        cargarInterfaz();
    }
/**
 * Metodo cargarInterfaz.
 */
    private static void cargarInterfaz() {
        JFrame ventana = new JFrame("Servidor WOME");
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setSize(ANCHO, ALTO);
        ventana.setResizable(false);
        ventana.setLocationRelativeTo(null);
        ventana.setLayout(null);
        ventana.setIconImage(Toolkit.getDefaultToolkit().getImage("src/main/java/servidor/server.png"));
        JLabel titulo = new JLabel("Log del servidor...");
        titulo.setFont(new Font("Courier New", Font.BOLD, 16));
        titulo.setBounds(10, 0, 200, 30);
        ventana.add(titulo);

        log = new JTextArea();
        log.setEditable(false);
        log.setFont(new Font("Times New Roman", Font.PLAIN, 13));
        JScrollPane scroll = new JScrollPane(log, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setBounds(10, 40, ANCHO_LOG, ALTO_LOG);
        ventana.add(scroll);

        final JButton botonIniciar = new JButton();
        final JButton botonDetener = new JButton();
        botonIniciar.setText("Iniciar");
        botonIniciar.setBounds(220, ALTO - 70, 100, 30);
        botonIniciar.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                server = new Thread(new Servidor());
                server.start();
                botonIniciar.setEnabled(false);
                botonDetener.setEnabled(true);
            }
        });

        ventana.add(botonIniciar);

        botonDetener.setText("Detener");
        botonDetener.setBounds(360, ALTO - 70, 100, 30);
        botonDetener.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                try {
                    server.stop();
                    atencionConexiones.stop();
                    atencionMovimientos.stop();
                    for (EscuchaCliente cliente : clientesConectados) {
                        cliente.getSalida().close();
                        cliente.getEntrada().close();
                        cliente.getSocket().close();
                    }
                    serverSocket.close();
                    log.append("El servidor se ha detenido." + System.lineSeparator());
                } catch (IOException e1) {
                    log.append("Fallo al intentar detener el servidor." + System.lineSeparator());
                }
                if (conexionDB != null) {
                    conexionDB.close();
                }
                botonDetener.setEnabled(false);
                botonIniciar.setEnabled(true);
            }
        });
        botonDetener.setEnabled(false);
        ventana.add(botonDetener);

        ventana.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        ventana.addWindowListener(new WindowAdapter() {
            public void windowClosing(final WindowEvent evt) {
                if (serverSocket != null) {
                    try {
                        server.stop();
                        atencionConexiones.stop();
                        atencionMovimientos.stop();
                        for (EscuchaCliente cliente : clientesConectados) {
                            cliente.getSalida().close();
                            cliente.getEntrada().close();
                            cliente.getSocket().close();
                        }
                        serverSocket.close();
                        log.append("El servidor se ha detenido." + System.lineSeparator());
                    } catch (IOException e) {
                        log.append("Fallo al intentar detener el servidor." + System.lineSeparator());
                        System.exit(1);
                    }
                }
                if (conexionDB != null) {
                    conexionDB.close();
                }
                System.exit(0);
            }
        });

        ventana.setVisible(true);
    }
/**
 * Metodo run.
 */
    public void run() {
        try {

            conexionDB = new Conector();
            conexionDB.connect();

            log.append("Iniciando el servidor..." + System.lineSeparator());
            serverSocket = new ServerSocket(PUERTO);
            log.append("Servidor esperando conexiones..." + System.lineSeparator());
            String ipRemota;

            atencionConexiones = new AtencionConexiones();
            atencionMovimientos = new AtencionMovimientos();

            atencionConexiones.start();
            atencionMovimientos.start();

            while (true) {
                Socket cliente = serverSocket.accept();
                ipRemota = cliente.getInetAddress().getHostAddress();
                log.append(ipRemota + " se ha conectado" + System.lineSeparator());

                ObjectOutputStream salida = new ObjectOutputStream(cliente.getOutputStream());
                ObjectInputStream entrada = new ObjectInputStream(cliente.getInputStream());

                EscuchaCliente atencion = new EscuchaCliente(ipRemota, cliente, entrada, salida);
                atencion.start();
                clientesConectados.add(atencion);
            }
        } catch (Exception e) {
            log.append("Fallo la conexión." + System.lineSeparator());
        }
    }
/**
 * Metodo mensajeUsuario.
 * @param pqm envia el paquete mensaje
 * @return booelan
 */
    public static boolean mensajeAUsuario(final PaqueteMensaje pqm) {
        // boolean result = true;
        // boolean noEncontro = true;
        //
        // for (Map.Entry<Integer, PaquetePersonaje> personaje :
        // personajesConectados.entrySet()) {
        // if (noEncontro &&
        // (!personaje.getValue().getNombre().equals(pqm.getUserReceptor()))) {
        // result = false;
        // } else {
        // result = true;
        // noEncontro = false;
        // }
        // }
        // // Si existe inicio sesion
        // if (result) {
        // Servidor.log
        // .append(pqm.getUserEmisor() + " envió mensaje a " + pqm.getUserReceptor() +
        // System.lineSeparator());
        // return true;
        // } else {
        // // Si no existe informo y devuelvo false
        // Servidor.log.append("El mensaje para " + pqm.getUserReceptor()
        // + " no se envió, ya que se encuentra desconectado." +
        // System.lineSeparator());
        // return false;
        // }

        boolean encontro = false;

        Iterator<Entry<Integer, PaquetePersonaje>> it = personajesConectados.entrySet().iterator();

        while (it.hasNext() && !encontro) {
            Map.Entry<Integer, PaquetePersonaje> e = (Map.Entry<Integer, PaquetePersonaje>) it.next();

            if (e.getValue().getNombre().equals(pqm.getUserReceptor())) {
                encontro = true;
            }
        }

        if (encontro) { // Si existe inicio sesion
            Servidor.log.append(pqm.getUserEmisor() + " envió mensaje a "
                    + pqm.getUserReceptor() + System.lineSeparator());
        } else {
            Servidor.log.append("El mensaje para " + pqm.getUserReceptor()
                    + " no se envió, ya que se encuentra desconectado." + System.lineSeparator());
        }

        return encontro;
    }
/**
 * Metodo mensajeAALL.
 * @param contador envia el contador
 * @return boolean
 */
    public static boolean mensajeAAll(final int contador) {
        /*
         * boolean result = true; if(personajesConectados.size() != contador+1) {
         * result= false; } // Si existe inicio sesion if (result) {
         * Servidor.log.append("Se ha enviado un mensaje a todos los usuarios" +
         * System.lineSeparator()); return true; }else { // Si no existe informo y
         * devuelvo false Servidor.log.
         * append("Uno o más de todos los usuarios se ha desconectado, se ha mandado el mensaje a los demas."
         * + System.lineSeparator()); return false; }
         */

        boolean existeInicioSesion = personajesConectados.size() == contador + 1;

        if (existeInicioSesion) {
            Servidor.log.append("Se ha enviado un mensaje a todos los usuarios" + System.lineSeparator());
        } else {
            Servidor.log.append("Uno o más de todos los usuarios se ha desconectado,"
                    + "se ha mandado el mensaje a los demas." + System.lineSeparator());
        }

        return existeInicioSesion;
    }
/**
 * Getter de los clientes conectados.
 * @return clientesConectados
 */
    public static ArrayList<EscuchaCliente> getClientesConectados() {
        return clientesConectados;
    }
/**
 * Getter de la ubicacion de los personajes.
 * @return ubicacionPersonajes
 */
    public static Map<Integer, PaqueteMovimiento> getUbicacionPersonajes() {
        return ubicacionPersonajes;
    }
/**
 * Getter de los personajes conectados.
 * @return personajesConectados
 */
    public static Map<Integer, PaquetePersonaje> getPersonajesConectados() {
        return personajesConectados;
    }
/**
 * Getter del conector.
 * @return conexionBD
 */
    public static Conector getConector() {
        return conexionDB;
    }
}