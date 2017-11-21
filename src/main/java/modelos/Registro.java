package modelos;

/**
 * Clase Registro.
 * @author Hector
 *
 */
public class Registro {
    private int idPersonaje;
    private String usuario;
    private String password;
/**
 * Getter de IdPersonaje.
 * @return idPersonaje
 */
    public int getIdPersonaje() {
        return idPersonaje;
    }
/**
 * Setter de IdPersonaje.
 * @param idPersonaje del tipo int
 */
    public void setIdPersonaje(final int idPersonaje) {
        this.idPersonaje = idPersonaje;
    }
/**
 * Getter de Usuario.
 * @return usuario
 */
    public String getUsuario() {
        return usuario;
    }
/**
 * Setter de Usuario.
 * @param usuario del tipo string
 */
    public void setUsuario(final String usuario) {
        this.usuario = usuario;
    }
/**
 * Getter de Password.
 * @return password
 */
    public String getPassword() {
        return password;
    }
/**
 * Setter de Password.
 * @param password del tipo string
 */
    public void setPassword(final String password) {
        this.password = password;
    }
/**
 * Metodo toString.
 */
    @Override
    public String toString() {
        return "Registro [idPj=" + idPersonaje + ", username=" + usuario + ", password=" + password + "]";
    }
}