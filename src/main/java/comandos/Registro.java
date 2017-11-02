package comandos;

import java.io.IOException;

import mensajeria.Comando;
import mensajeria.Paquete;
import mensajeria.PaqueteUsuario;
import servidor.Servidor;
/**
 *Clase Registro.
 */
public class Registro extends ComandosServer {

    @Override
    public final void ejecutar() {
        Paquete paqueteSv = new Paquete(null, 0);
        paqueteSv.setComando(Comando.REGISTRO);
        getEscuchaCliente().setPaqueteUsuario((PaqueteUsuario) (gson
                 .fromJson(cadenaLeida, PaqueteUsuario.class)).clone());

        // Si el usuario se pudo registrar le envio un msj de exito
        try {
            if (Servidor.getConector().registrarUsuario(getEscuchaCliente().getPaqueteUsuario())) {
                paqueteSv.setMensaje(Paquete.msjExito);
                getEscuchaCliente().getSalida().writeObject(gson.toJson(paqueteSv));

                // Si el usuario no se pudo registrar le envio un msj de fracaso
            } else {
                paqueteSv.setMensaje(Paquete.msjFracaso);
                getEscuchaCliente().getSalida().writeObject(gson.toJson(paqueteSv));
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            Servidor.getLog().append("Falló al intentar enviar registro\n");
        }

    }

}
