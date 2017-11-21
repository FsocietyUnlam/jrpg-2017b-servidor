package modelos;
/**
 * Clase Inventario.
 * @author Hector
 *
 */
public class Inventario {
    private int idInventario;
    private int manos1;
    private int manos2;
    private int pie;
    private int cabeza;
    private int pecho;
    private int accesorio;
/**
 * Getter de IdInventario.
 * @return idInventario
 */
    public int getIdInventario() {
        return idInventario;
    }
/**
 * Setter de IdInventario.
 * @param idInventario del tipo int
 */
    public void setIdInventario(final int idInventario) {
        this.idInventario = idInventario;
    }
/**
 * Getter de Manos1.
 * @return manos1
 */
    public int getManos1() {
        return manos1;
    }
/**
 * Setter de Manos1.
 * @param manos1 del tipo int
 */
    public void setManos1(final int manos1) {
        this.manos1 = manos1;
    }
/**
 * Getter de Manos2.
 * @return manos2
 */
    public int getManos2() {
        return manos2;
    }
/**
 * Setter de Manos2.
 * @param manos2 del tipo int
 */
    public void setManos2(final int manos2) {
        this.manos2 = manos2;
    }
/**
 * Getter de Pie.
 * @return pie
 */
    public int getPie() {
        return pie;
    }
/**
 * Setter de Pie.
 * @param pie del tipo int
 */
    public void setPie(final int pie) {
        this.pie = pie;
    }
/**
 * Getter de Cabeza.
 * @return cabeza
 */
    public int getCabeza() {
        return cabeza;
    }
/**
 * Setter de Cabeza.
 * @param cabeza del tipo int
 */
    public void setCabeza(final int cabeza) {
        this.cabeza = cabeza;
    }
/**
 * Getter de Pecho.
 * @return pecho
 */
    public int getPecho() {
        return pecho;
    }
/**
 * Setter de Pecho.
 * @param pecho del tipo int
 */
    public void setPecho(final int pecho) {
        this.pecho = pecho;
    }
/**
 * Getter de Accesorio.
 * @return accesorio
 */
    public int getAccesorio() {
        return accesorio;
    }
/**
 * Setter de Accesorio.
 * @param accesorio del tipo int
 */
    public void setAccesorio(final int accesorio) {
        this.accesorio = accesorio;
    }
/**
 * Metodo toString.
 */
    @Override
    public String toString() {
        return "Inventario [idInventario=" + idInventario + ", manos1=" + manos1 + ", manos2=" + manos2 + ", pie="
    + pie + ", cabeza=" + cabeza + ", pecho=" + pecho + ", accesorio=" + accesorio + "]";
    }

}
