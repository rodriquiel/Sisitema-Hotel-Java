package modelo;

public class Usuario {
	private Integer id;
	private String usuario;
	private String password;
	private String nombre;
	
	public Usuario(String usuario, String password, String nombre) {
		this.usuario = usuario;
		this.password = password;
		this.nombre = nombre;
	}
	
	public Usuario(Integer id, String usuario, String password, String nombre) {
		this.id = id;
		this.usuario = usuario;
		this.password = password;
		this.nombre = nombre;
	}
	
	public String getUsuario() {
		return usuario;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	
	
}
