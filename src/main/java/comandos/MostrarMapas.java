package comandos;

import mensajeria.PaquetePersonaje;
import servidor.Servidor;
/**
 *Clase MostrarMapas.
 */
public class MostrarMapas extends ComandosServer {

    @Override
    public final void ejecutar() {
        getEscuchaCliente().setPaquetePersonaje((PaquetePersonaje) gson
                .fromJson(cadenaLeida, PaquetePersonaje.class));
        Servidor.getLog().append(getEscuchaCliente().getSocket()
                         .getInetAddress().getHostAddress() + " ha elegido el mapa "
                         + getEscuchaCliente().getPaquetePersonaje().getMapa() + System.lineSeparator());
    }

}
