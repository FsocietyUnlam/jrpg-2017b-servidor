package modelos;
/**
 * Clase Item.
 * @author Hector
 *
 */
public class Item {
    private int idItem;
    private String nombre;
    private int wereable;
    private int bonusSalud;
    private int bonusEnergia;
    private int bonusFuerza;
    private int bonusDestreza;
    private int bonusInteligencia;
    private String foto;
    private String fotoEquipado;
    private int fuerzaRequerida;
    private int destrezaRequerida;
    private int inteligenciaRequerida;
/**
 * Getter de IdItem.
 * @return idItem
 */
    public int getIdItem() {
        return idItem;
    }
/**
 * Setter de IdItem.
 * @param idItem del tipo int
 */
    public void setIdItem(final int idItem) {
        this.idItem = idItem;
    }
/**
 * Getter de Nombre.
 * @return nombre
 */
    public String getNombre() {
        return nombre;
    }
/**
 * Setter de Nombre.
 * @param nombre del tipo string
 */
    public void setNombre(final String nombre) {
        this.nombre = nombre;
    }
/**
 * Getter de Wereable.
 * @return wereable
 */
    public int getWereable() {
        return wereable;
    }
/**
 * Setter de Wereable.
 * @param wereable del tipo int
 */
    public void setWereable(final int wereable) {
        this.wereable = wereable;
    }
/**
 * Getter de BonusSalud.
 * @return bonusSalud
 */
    public int getBonusSalud() {
        return bonusSalud;
    }
/**
 * Setter de BonusSalud.
 * @param bonusSalud del tipo int
 */
    public void setBonusSalud(final int bonusSalud) {
        this.bonusSalud = bonusSalud;
    }
/**
 * Getter de BonusEnergia.
 * @return bonus energia
 */
    public int getBonusEnergia() {
        return bonusEnergia;
    }
/**
 * Setter de BonusEnergia.
 * @param bonusEnergia del tipo int
 */
    public void setBonusEnergia(final int bonusEnergia) {
        this.bonusEnergia = bonusEnergia;
    }
/**
 * Getter de BonusFuerza.
 * @return bonusFuerza
 */
    public int getBonusFuerza() {
        return bonusFuerza;
    }
/**
 * Setter de BonusFuerza.
 * @param bonusFuerza del tipo int
 */
    public void setBonusFuerza(final int bonusFuerza) {
        this.bonusFuerza = bonusFuerza;
    }
/**
 * Getter de BonusDestreza.
 * @return bonusDestreza
 */
    public int getBonusDestreza() {
        return bonusDestreza;
    }
/**
 * Setter de BonusDestreza.
 * @param bonusDestreza del tipo int
 */
    public void setBonusDestreza(final int bonusDestreza) {
        this.bonusDestreza = bonusDestreza;
    }
/**
 * Getter de BonusInteligencia.
 * @return bonusInteligencia
 */
    public int getBonusInteligencia() {
        return bonusInteligencia;
    }
/**
 * Setter de BonusInteligencia.
 * @param bonusInteligencia del tipo int
 */
    public void setBonusInteligencia(final int bonusInteligencia) {
        this.bonusInteligencia = bonusInteligencia;
    }
/**
 * Getter de Foto.
 * @return foto
 */
    public String getFoto() {
        return foto;
    }
/**
 * Setter de Foto.
 * @param foto del tipo string
 */
    public void setFoto(final String foto) {
        this.foto = foto;
    }
/**
 * Getter de FotoEquipado.
 * @return fotoEquipado
 */
    public String getFotoEquipado() {
        return fotoEquipado;
    }
/**
 * Setter de FotoEquipado.
 * @param fotoEquipado del tipo string
 */
    public void setFotoEquipado(final String fotoEquipado) {
        this.fotoEquipado = fotoEquipado;
    }
/**
 * Getter de FuerzaRequerida.
 * @return fuerzaRequerida
 */
    public int getFuerzaRequerida() {
        return fuerzaRequerida;
    }
/**
 * Setter de FuerzaRequerida.
 * @param fuerzaRequerida del tipo int
 */
    public void setFuerzaRequerida(final int fuerzaRequerida) {
        this.fuerzaRequerida = fuerzaRequerida;
    }
/**
 * Getter de DestrezaRequerida.
 * @return destrezaRequerida
 */
    public int getDestrezaRequerida() {
        return destrezaRequerida;
    }
/**
 * Setter de DestrezaRequerida.
 * @param destrezaRequerida del tipo int
 */
    public void setDestrezaRequerida(final int destrezaRequerida) {
        this.destrezaRequerida = destrezaRequerida;
    }
/**
 * Getter de InteligenciaRequerida.
 * @return inteligenciaRequerida
 */
    public int getInteligenciaRequerida() {
        return inteligenciaRequerida;
    }
/**
 * Setter de InteligenciaRequerida.
 * @param inteligenciaRequerida del tipo int
 */
    public void setInteligenciaRequerida(final int inteligenciaRequerida) {
        this.inteligenciaRequerida = inteligenciaRequerida;
    }

    @Override
    /**
     * Metodo ToString.
     */
    public String toString() {
        return "Item [idItem=" + idItem + ", nombre=" + nombre + ", wereable=" + wereable
                + ", bonusSalud=" + bonusSalud + ", bonusEnergia=" + bonusEnergia + ", bonusFuerza=" + bonusFuerza
                + ", bonusDestreza=" + bonusDestreza + ", bonusInteligencia=" + bonusInteligencia
                + ", foto=" + foto + ", fotoEquipado=" + fotoEquipado + ", fuerzaRequerida=" + fuerzaRequerida
                + ", destrezaRequerida=" + destrezaRequerida + ", inteligenciaRequerida=" + inteligenciaRequerida + "]";
    }
}