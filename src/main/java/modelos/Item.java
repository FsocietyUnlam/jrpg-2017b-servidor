package modelos;

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
	
	public int getIdItem() {
		return idItem;
	}
	public void setIdItem(int idItem) {
		this.idItem = idItem;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getWereable() {
		return wereable;
	}
	public void setWereable(int wereable) {
		this.wereable = wereable;
	}
	public int getBonusSalud() {
		return bonusSalud;
	}
	public void setBonusSalud(int bonusSalud) {
		this.bonusSalud = bonusSalud;
	}
	public int getBonusEnergia() {
		return bonusEnergia;
	}
	public void setBonusEnergia(int bonusEnergia) {
		this.bonusEnergia = bonusEnergia;
	}
	public int getBonusFuerza() {
		return bonusFuerza;
	}
	public void setBonusFuerza(int bonusFuerza) {
		this.bonusFuerza = bonusFuerza;
	}
	public int getBonusDestreza() {
		return bonusDestreza;
	}
	public void setBonusDestreza(int bonusDestreza) {
		this.bonusDestreza = bonusDestreza;
	}
	public int getBonusInteligencia() {
		return bonusInteligencia;
	}
	public void setBonusInteligencia(int bonusInteligencia) {
		this.bonusInteligencia = bonusInteligencia;
	}
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}
	public String getFotoEquipado() {
		return fotoEquipado;
	}
	public void setFotoEquipado(String fotoEquipado) {
		this.fotoEquipado = fotoEquipado;
	}
	public int getFuerzaRequerida() {
		return fuerzaRequerida;
	}
	public void setFuerzaRequerida(int fuerzaRequerida) {
		this.fuerzaRequerida = fuerzaRequerida;
	}
	public int getDestrezaRequerida() {
		return destrezaRequerida;
	}
	public void setDestrezaRequerida(int destrezaRequerida) {
		this.destrezaRequerida = destrezaRequerida;
	}
	public int getInteligenciaRequerida() {
		return inteligenciaRequerida;
	}
	public void setInteligenciaRequerida(int inteligenciaRequerida) {
		this.inteligenciaRequerida = inteligenciaRequerida;
	}
	
	@Override
	public String toString() {
		return "Item [idItem=" + idItem + ", nombre=" + nombre + ", wereable=" + wereable + ", bonusSalud=" + bonusSalud
				+ ", bonusEnergia=" + bonusEnergia + ", bonusFuerza=" + bonusFuerza + ", bonusDestreza=" + bonusDestreza
				+ ", bonusInteligencia=" + bonusInteligencia + ", foto=" + foto + ", fotoEquipado=" + fotoEquipado
				+ ", fuerzaRequerida=" + fuerzaRequerida + ", destrezaRequerida=" + destrezaRequerida
				+ ", inteligenciaRequerida=" + inteligenciaRequerida + "]";
	}
}
