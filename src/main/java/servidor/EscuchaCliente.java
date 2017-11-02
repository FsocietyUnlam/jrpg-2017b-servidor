package servidor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import com.google.gson.Gson;

import comandos.ComandosServer;
import mensajeria.Comando;
import mensajeria.Paquete;
import mensajeria.PaqueteAtacar;
import mensajeria.PaqueteBatalla;
import mensajeria.PaqueteDeMovimientos;
import mensajeria.PaqueteDePersonajes;
import mensajeria.PaqueteFinalizarBatalla;
import mensajeria.PaqueteMovimiento;
import mensajeria.PaquetePersonaje;
import mensajeria.PaqueteUsuario;

/**
 * Clase EscuchaCliente.
 */
public class EscuchaCliente extends Thread {
    /**
     * Variable socket del tipo Socket.
     */
    private final Socket socket;
    /**
     * Variable entrada del tipo ObjectInputStream.
     */
    private final ObjectInputStream entrada;
    /**
     * Variable salida del tipo ObjectOutputStream.
     */
    private final ObjectOutputStream salida;
    /**
     * Variable idPersonaje del tipo int.
     */
    private int idPersonaje;
    /**
     * Variable gson del tipo Gson.
     */
    private final Gson gson = new Gson();
    /**
     * Variable paquetePersonaje del tipo PaquetePersonaje.
     */
    private PaquetePersonaje paquetePersonaje;
    /**
     * Variable paqueteMovimiento del tipo PaqueteMovimiento.
     */
    private PaqueteMovimiento paqueteMovimiento;
    /**
     * Variable paqueteBatalla del tipo PaqueteBatalla.
     */
    private PaqueteBatalla paqueteBatalla;
    /**
     * Variable paqueteAtacar del tipo PaqueteAtacar.
     */
    private PaqueteAtacar paqueteAtacar;
    /**
     * Variable paqueteFinalizarBatalla del tipo PaqueteFinalizarBatalla.
     */
    private PaqueteFinalizarBatalla paqueteFinalizarBatalla;
    /**
     * Variable paqueteUsuario del tipo PaqueteUsuario.
     */
    private PaqueteUsuario paqueteUsuario;
    /**
     * Variable paqueteDeMovimiento del tipo PaqueteDeMovimientos.
     */
    private PaqueteDeMovimientos paqueteDeMovimiento;
    /**
     * Variable paqueteDePersonajes del tipo PaqueteDePersonajes.
     */
    private PaqueteDePersonajes paqueteDePersonajes;
/**
 *
 * @param ip envia el ip.
 * @param socket envia el socket
 * @param entrada envia la entrada
 * @param salida envia la salida
 * @throws IOException excepcion
 */
    public EscuchaCliente(final String ip, final Socket socket, final ObjectInputStream entrada,
            final ObjectOutputStream salida) throws IOException {
        this.socket = socket;
        this.entrada = entrada;
        this.salida = salida;
        paquetePersonaje = new PaquetePersonaje();
    }
/**
 * Metodo run.
 */
    public void run() {
        try {
            ComandosServer comand;
            Paquete paquete;
            Paquete paqueteSv = new Paquete(null, 0);
            paqueteUsuario = new PaqueteUsuario();

            String cadenaLeida = (String) entrada.readObject();
            paquete = gson.fromJson(cadenaLeida, Paquete.class);
            while (!(paquete.getComando() == Comando.DESCONECTAR)) {

                comand = (ComandosServer) paquete.getObjeto(Comando.NOMBREPAQUETE);
                comand.setCadena(cadenaLeida);
                comand.setEscuchaCliente(this);
                comand.ejecutar();
                cadenaLeida = (String) entrada.readObject();
            }

            entrada.close();
            salida.close();
            socket.close();

            Servidor.getPersonajesConectados().remove(paquetePersonaje.getId());
            Servidor.getUbicacionPersonajes().remove(paquetePersonaje.getId());
            Servidor.getClientesConectados().remove(this);

            for (EscuchaCliente conectado : Servidor.getClientesConectados()) {
                paqueteDePersonajes = new PaqueteDePersonajes(Servidor.getPersonajesConectados());
                paqueteDePersonajes.setComando(Comando.CONEXION);
                conectado.salida.writeObject(gson.toJson(paqueteDePersonajes, PaqueteDePersonajes.class));
            }

            Servidor.getLog().append(paquete.getIp() + " se ha desconectado." + System.lineSeparator());

        } catch (IOException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            Servidor.getLog().append("Error de conexion: " + e.getMessage() + System.lineSeparator());
        }
    }
/**
 * Getter del socket.
 * @return socket
 */
    public Socket getSocket() {
        return socket;
    }
/**
 * Getter de la entrada.
 * @return entrada
 */
    public ObjectInputStream getEntrada() {
        return entrada;
    }
/**
 * Getter de la salida.
 * @return salida
 */
    public ObjectOutputStream getSalida() {
        return salida;
    }
/**
 * Getter del paquete personaje.
 * @return paquetePersonaje
 */
    public PaquetePersonaje getPaquetePersonaje() {
        return paquetePersonaje;
    }
/**
 * Getter del id del personaje.
 * @return idPersonaje
 */
    public int getIdPersonaje() {
        return idPersonaje;
    }
/**
 * Getter del paquete de movimiento.
 * @return paqueteMovimiento
 */
    public PaqueteMovimiento getPaqueteMovimiento() {
        return paqueteMovimiento;
    }
/**
 * Setter del paquete movimiento.
 * @param paqueteMovimiento envia el paquete de movimientos
 */
    public void setPaqueteMovimiento(final PaqueteMovimiento paqueteMovimiento) {
        this.paqueteMovimiento = paqueteMovimiento;
    }
/**
 * Getter del paquete de batalla.
 * @return paqueteBatalla
 */
    public PaqueteBatalla getPaqueteBatalla() {
        return paqueteBatalla;
    }
/**
 * Setter del paquete de batalla.
 * @param paqueteBatalla envia el paquete de batalla
 */
    public void setPaqueteBatalla(final PaqueteBatalla paqueteBatalla) {
        this.paqueteBatalla = paqueteBatalla;
    }
/**
 * Getter del paquete atacar.
 * @return paqueteAtacar
 */
    public PaqueteAtacar getPaqueteAtacar() {
        return paqueteAtacar;
    }
/**
 * Setter del paquete atacar.
 * @param paqueteAtacar envia el paquete atacar
 */
    public void setPaqueteAtacar(final PaqueteAtacar paqueteAtacar) {
        this.paqueteAtacar = paqueteAtacar;
    }
/**
 * Getter del paquete finalizar batalla.
 * @return paqueteFinalizarBatalla
 */
    public PaqueteFinalizarBatalla getPaqueteFinalizarBatalla() {
        return paqueteFinalizarBatalla;
    }
/**
 * Setter del paquete finalizar batalla.
 * @param paqueteFinalizarBatalla envia el paquete finalizar batalla
 */
    public void setPaqueteFinalizarBatalla(final PaqueteFinalizarBatalla paqueteFinalizarBatalla) {
        this.paqueteFinalizarBatalla = paqueteFinalizarBatalla;
    }
/**
 * Getter del paquete de movimiento.
 * @return paquete de movimiento
 */
    public PaqueteDeMovimientos getPaqueteDeMovimiento() {
        return paqueteDeMovimiento;
    }
/**
 * Setter del paquete de movimiento.
 * @param paqueteDeMovimiento envia el paquete de movimiento
 */
    public void setPaqueteDeMovimiento(final PaqueteDeMovimientos paqueteDeMovimiento) {
        this.paqueteDeMovimiento = paqueteDeMovimiento;
    }
/**
 * Getter del paquete de personajes.
 * @return paqueteDePersonajes
 */
    public PaqueteDePersonajes getPaqueteDePersonajes() {
        return paqueteDePersonajes;
    }
/**
 * Setter del paquete de personajes.
 * @param paqueteDePersonajes envia el paquete de personajes
 */
    public void setPaqueteDePersonajes(final PaqueteDePersonajes paqueteDePersonajes) {
        this.paqueteDePersonajes = paqueteDePersonajes;
    }
/**
 * Setter del id del personaje.
 * @param idPersonaje envia el id del personaje
 */
    public void setIdPersonaje(final int idPersonaje) {
        this.idPersonaje = idPersonaje;
    }
/**
 * Setter del paquete del personaje.
 * @param paquetePersonaje envia el paquete del personaje
 */
    public void setPaquetePersonaje(final PaquetePersonaje paquetePersonaje) {
        this.paquetePersonaje = paquetePersonaje;
    }
/**
 * Getter del paquete de usuarios.
 * @return paqueteUsuario
 */
    public PaqueteUsuario getPaqueteUsuario() {
        return paqueteUsuario;
    }
/**
 * Setter del paquete de usuario.
 * @param paqueteUsuario envia el paquete de usuario
 */
    public void setPaqueteUsuario(final PaqueteUsuario paqueteUsuario) {
        this.paqueteUsuario = paqueteUsuario;
    }
}
