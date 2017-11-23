package servidor;

import com.google.gson.Gson;

import estados.Estado;
import mensajeria.Comando;
import mensajeria.PaqueteDeEnemigos;
import mensajeria.PaqueteDePersonajes;

/**
 * Clase AtencionConcexiones.
 */
public class AtencionConexiones extends Thread {
    /**
     * Variable gson del tipo Gson.
     */
    private final Gson gson = new Gson();
    /**
     *Metodo AtencionConexiones.
     */
    public AtencionConexiones() {
    }
    /**
     *Metodo run.
     */
    public void run() {

        synchronized (this) {
            try {

                while (true) {

                    // Espero a que se conecte alguien
                    wait();

                    // Le reenvio la conexion a todos
                    for (EscuchaCliente conectado : Servidor.getClientesConectados()) {

                        if (conectado.getPaquetePersonaje().getEstado() != Estado.getEstadoOffline()) {

                            PaqueteDePersonajes pdp = (PaqueteDePersonajes)
                                    new PaqueteDePersonajes(Servidor.getPersonajesConectados()).clone();
                            pdp.setComando(Comando.CONEXION);
                            synchronized (conectado) {
                                conectado.getSalida().writeObject(gson.toJson(pdp));
                            }
                            
                            PaqueteDeEnemigos pde = (PaqueteDeEnemigos)new PaqueteDeEnemigos(Servidor.getEnemigos()).clone();
                            pde.setComando(Comando.SETENEMIGOS);
                            synchronized (conectado) {
                                 conectado.getSalida().writeObject(
                                      gson.toJson(pde));
                                 }
                        }
                    }
                    
                    
                }
            } catch (Exception e) {
                Servidor.log.append("Falló al intentar enviar paqueteDePersonajes\n");
            }
        }
    }
}