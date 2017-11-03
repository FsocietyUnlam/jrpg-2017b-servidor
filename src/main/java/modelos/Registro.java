package modelos;

public class Registro {
	private int idPersonaje;
	private String usuario;
	private String password;
	
	
	
	public int getIdPersonaje() {
		return idPersonaje;
	}



	public void setIdPersonaje(int idPersonaje) {
		this.idPersonaje = idPersonaje;
	}



	public String getUsuario() {
		return usuario;
	}



	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	@Override
	public String toString() {
		return "Registro [idPj=" + idPersonaje + ", username=" + usuario + ", password=" + password + "]";
	}
}
