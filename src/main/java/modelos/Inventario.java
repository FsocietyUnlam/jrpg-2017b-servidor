package modelos;

public class Inventario {
	private int idInventario;
	private int manos1;
	private int manos2;
	private int pie;
	private int cabeza;
	private int pecho;
	private int accesorio;
	
	
	public int getIdInventario() {
		return idInventario;
	}
	public void setIdInventario(int idInventario) {
		this.idInventario = idInventario;
	}
	public int getManos1() {
		return manos1;
	}
	public void setManos1(int manos1) {
		this.manos1 = manos1;
	}
	public int getManos2() {
		return manos2;
	}
	public void setManos2(int manos2) {
		this.manos2 = manos2;
	}
	public int getPie() {
		return pie;
	}
	public void setPie(int pie) {
		this.pie = pie;
	}
	public int getCabeza() {
		return cabeza;
	}
	public void setCabeza(int cabeza) {
		this.cabeza = cabeza;
	}
	public int getPecho() {
		return pecho;
	}
	public void setPecho(int pecho) {
		this.pecho = pecho;
	}
	public int getAccesorio() {
		return accesorio;
	}
	public void setAccesorio(int accesorio) {
		this.accesorio = accesorio;
	}
	
	@Override
	public String toString() {
		return "Inventario [idInventario=" + idInventario + ", manos1=" + manos1 + ", manos2=" + manos2 + ", pie=" + pie
				+ ", cabeza=" + cabeza + ", pecho=" + pecho + ", accesorio=" + accesorio + "]";
	}
	
}
