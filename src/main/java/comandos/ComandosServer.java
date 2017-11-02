package comandos;

import mensajeria.Comando;
import servidor.EscuchaCliente;
/**
 *Clase ComandosServer.
 */
public abstract class ComandosServer extends Comando {
/**
*Variable escuchaCliente del tipo EscuchaCliente.
**/
    private EscuchaCliente escuchaCliente;
/**
 *Setter de la variable escuchaCliente.
 *@param escuchaCliente envia el escuchaCliente
 */
    public void setEscuchaCliente(final EscuchaCliente escuchaCliente) {
        this.escuchaCliente = escuchaCliente;
    }
/**
 * Getter del escucha cliente.
 * @return escuchaCliente
 */
    public EscuchaCliente getEscuchaCliente() {
        return escuchaCliente;
    }
}
