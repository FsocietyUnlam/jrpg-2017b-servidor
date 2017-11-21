package modelos;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
/**
 * Clase Personaje.
 * @author Hector
 *
 */
public class Personaje {
    private int idPersonaje;
    private int idInventario;
    private int idMochila;
    private String casta;
    private String raza;
    private int fuerza;
    private int destreza;
    private int inteligencia;
    private int saludTope;
    private int energiaTope;
    private String nombre;
    private int experiencia;
    private int nivel;
    private int idAlianza;
/**
 * Id.
 * @return id
 */
    @Id
    @GeneratedValue
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
 * Getter de IdMochila.
 * @return idMochila
 */
    public int getIdMochila() {
        return idMochila;
    }
/**
 * Setter de IdMochila.
 * @param idMochila del tipo int
 */
    public void setIdMochila(final int idMochila) {
        this.idMochila = idMochila;
    }
/**
 * Getter de Casta.
 * @return casta
 */
    public String getCasta() {
        return casta;
    }
/**
 * Setter de Casta.
 * @param casta del tipo string
 */
    public void setCasta(final String casta) {
        this.casta = casta;
    }
/**
 * Getter de Raza.
 * @return raza
 */
    public String getRaza() {
        return raza;
    }
/**
 * Setter de Raza.
 * @param raza del tipo string
 */
    public void setRaza(final String raza) {
        this.raza = raza;
    }
/**
 * Getter de Fuerza.
 * @return fuerza
 */
    public int getFuerza() {
        return fuerza;
    }
/**
 * Setter de Fuerza.
 * @param fuerza del tipo int
 */
    public void setFuerza(final int fuerza) {
        this.fuerza = fuerza;
    }
/**
 * Getter de Destreza.
 * @return destreza
 */
    public int getDestreza() {
        return destreza;
    }
/**
 * Setter de destreza.
 * @param destreza del tipo int
 */
    public void setDestreza(final int destreza) {
        this.destreza = destreza;
    }
/**
 * Getter de Inteligencia.
 * @return inteligencia
 */
    public int getInteligencia() {
        return inteligencia;
    }
/**
 * Setter de Inteligencia.
 * @param inteligencia del tipo int
 */
    public void setInteligencia(final int inteligencia) {
        this.inteligencia = inteligencia;
    }
/**
 * Getter de SaludTope.
 * @return saludTope
 */
    public int getSaludTope() {
        return saludTope;
    }
/**
 * Setter de SaludTope.
 * @param saludTope del tipo int
 */
    public void setSaludTope(final int saludTope) {
        this.saludTope = saludTope;
    }
/**
 * Getter de EnergiaTope.
 * @return energiaTope
 */
    public int getEnergiaTope() {
        return energiaTope;
    }
/**
 * Setter de EnergiaTope.
 * @param energiaTope del tipo int
 */
    public void setEnergiaTope(final int energiaTope) {
        this.energiaTope = energiaTope;
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
 * Getter de Experiencia.
 * @return experiencia
 */
    public int getExperiencia() {
        return experiencia;
    }
/**
 * Setter de Experiencia.
 * @param experiencia del tipo int
 */
    public void setExperiencia(final int experiencia) {
        this.experiencia = experiencia;
    }
/**
 * Getter de Nivel.
 * @return nivel
 */
    public int getNivel() {
        return nivel;
    }
/**
 * Setter de Nivel.
 * @param nivel del tipo int
 */
    public void setNivel(final int nivel) {
        this.nivel = nivel;
    }
/**
 * Getter de IdAlianza.
 * @return idAlianza
 */
    public int getIdAlianza() {
        return idAlianza;
    }
/**
 * Setter de IdAlianza.
 * @param idAlianza del tipo int
 */
    public void setIdAlianza(final int idAlianza) {
        this.idAlianza = idAlianza;
    }
/**
 * Meotod toString.
 */
    @Override
    public String toString() {
        return "Personaje [idPersonaje=" + idPersonaje + ", idInventario=" + idInventario + ", idMochila=" + idMochila
                + ", casta=" + casta + ", raza=" + raza + ", fuerza=" + fuerza + ", destreza="
                + destreza + ", inteligencia=" + inteligencia + ", saludTope=" + saludTope + ", energiaTope="
                + energiaTope + ", nombre=" + nombre + ", experiencia=" + experiencia + ", nivel=" + nivel
                + ", idAlianza=" + idAlianza + "]";
    }
}