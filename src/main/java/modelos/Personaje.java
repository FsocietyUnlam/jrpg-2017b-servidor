package modelos;

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
	
	public int getIdPersonaje() {
		return idPersonaje;
	}
	public void setIdPersonaje(int idPersonaje) {
		this.idPersonaje = idPersonaje;
	}
	public int getIdInventario() {
		return idInventario;
	}
	public void setIdInventario(int idInventario) {
		this.idInventario = idInventario;
	}
	public int getIdMochila() {
		return idMochila;
	}
	public void setIdMochila(int idMochila) {
		this.idMochila = idMochila;
	}
	public String getCasta() {
		return casta;
	}
	public void setCasta(String casta) {
		this.casta = casta;
	}
	public String getRaza() {
		return raza;
	}
	public void setRaza(String raza) {
		this.raza = raza;
	}
	public int getFuerza() {
		return fuerza;
	}
	public void setFuerza(int fuerza) {
		this.fuerza = fuerza;
	}
	public int getDestreza() {
		return destreza;
	}
	public void setDestreza(int destreza) {
		this.destreza = destreza;
	}
	public int getInteligencia() {
		return inteligencia;
	}
	public void setInteligencia(int inteligencia) {
		this.inteligencia = inteligencia;
	}
	public int getSaludTope() {
		return saludTope;
	}
	public void setSaludTope(int saludTope) {
		this.saludTope = saludTope;
	}
	public int getEnergiaTope() {
		return energiaTope;
	}
	public void setEnergiaTope(int energiaTope) {
		this.energiaTope = energiaTope;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getExperiencia() {
		return experiencia;
	}
	public void setExperiencia(int experiencia) {
		this.experiencia = experiencia;
	}
	public int getNivel() {
		return nivel;
	}
	public void setNivel(int nivel) {
		this.nivel = nivel;
	}
	public int getIdAlianza() {
		return idAlianza;
	}
	public void setIdAlianza(int idAlianza) {
		this.idAlianza = idAlianza;
	}
	
	@Override
	public String toString() {
		return "Personaje [idPersonaje=" + idPersonaje + ", idInventario=" + idInventario + ", idMochila=" + idMochila
				+ ", casta=" + casta + ", raza=" + raza + ", fuerza=" + fuerza + ", destreza=" + destreza
				+ ", inteligencia=" + inteligencia + ", saludTope=" + saludTope + ", energiaTope=" + energiaTope
				+ ", nombre=" + nombre + ", experiencia=" + experiencia + ", nivel=" + nivel + ", idAlianza="
				+ idAlianza + "]";
	}
}
