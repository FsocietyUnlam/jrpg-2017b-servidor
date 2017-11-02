package comandos;

import java.io.IOException;

import mensajeria.PaquetePersonaje;
import servidor.EscuchaCliente;
import servidor.Servidor;
/**
 *Clase ActualizarTrueque.
 */
public class ActualizarTrueque extends ComandosServer {

    @Override
    public final void ejecutar() {
        getEscuchaCliente().setPaquetePersonaje((PaquetePersonaje) gson
                .fromJson(cadenaLeida, PaquetePersonaje.class));
        Servidor.getConector().actualizarInventario(getEscuchaCliente().getPaquetePersonaje());
        Servidor.getConector().actualizarPersonaje(getEscuchaCliente().getPaquetePersonaje());
        Servidor.getPersonajesConectados().remove(getEscuchaCliente().getPaquetePersonaje().getId());
        Servidor.getPersonajesConectados()
                .put(getEscuchaCliente().getPaquetePersonaje().getId(), getEscuchaCliente().getPaquetePersonaje());

        for (EscuchaCliente conectado : Servidor.getClientesConectados()) {
            try {
                conectado.getSalida().writeObject(gson.toJson(getEscuchaCliente().getPaquetePersonaje()));
            } catch (IOException e) {
                // TODO Auto-generated catch block
                Servidor.getLog().append("Falló al intentar enviar actualizacion de trueque a:"
                       + conectado.getPaquetePersonaje().getId() + "\n");
            }
        }

    }

}
