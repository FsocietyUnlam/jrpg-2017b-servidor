package comandos;

import java.io.IOException;

import estados.Estado;
import mensajeria.PaqueteFinalizarBatalla;
import servidor.EscuchaCliente;
import servidor.Servidor;
/**
 *Clase FinalizarBatalla.
 */
public class FinalizarBatalla extends ComandosServer {

    @Override
    public final void ejecutar() {
        PaqueteFinalizarBatalla paqueteFinalizarBatalla = (PaqueteFinalizarBatalla) gson
                .fromJson(cadenaLeida, PaqueteFinalizarBatalla.class);
        escuchaCliente.setPaqueteFinalizarBatalla(paqueteFinalizarBatalla);
        Servidor.getConector().actualizarInventario(paqueteFinalizarBatalla.getGanadorBatalla());
        Servidor.getPersonajesConectados()
                .get(escuchaCliente.getPaqueteFinalizarBatalla().getId()).setEstado(Estado.getEstadoJuego());
        Servidor.getPersonajesConectados().get(escuchaCliente.getPaqueteFinalizarBatalla()
                .getIdEnemigo()).setEstado(Estado.getEstadoJuego());
        for (EscuchaCliente conectado : Servidor.getClientesConectados()) {
            if (conectado.getIdPersonaje() == escuchaCliente.getPaqueteFinalizarBatalla().getIdEnemigo()) {
                try {
                    conectado.getSalida().writeObject(gson
                             .toJson(escuchaCliente.getPaqueteFinalizarBatalla()));
                } catch (IOException e) {
                // TODO Auto-generated catch block
                  Servidor.log.append("Falló al intentar enviar finalizarBatalla a:"
                          + conectado.getPaquetePersonaje().getId() + "\n");
                }
            }
        }
        synchronized (Servidor.atencionConexiones) {
            Servidor.atencionConexiones.notify();
        }

    }

}
