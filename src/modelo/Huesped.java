package modelo;

import java.sql.Date;

public class Huesped {
	private Integer id;
	private String nombre;
	private String apellido;
	private Date fechaNac;
	private String nacionalidad;
	private String telefono;
	private Integer idReserva;
	
	
	
	public Huesped(Integer id, String nombre, String apellido, Date fechaNac, String nacionalidad, String telefono,
			Integer idReserva) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaNac = fechaNac;
		this.nacionalidad = nacionalidad;
		this.telefono = telefono;
		this.idReserva = idReserva;
	}

	public Huesped(String nombre, String apellido, Date fechaNac, String nacionalidad, String telefono,
			Integer idReserva) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaNac = fechaNac;
		this.nacionalidad = nacionalidad;
		this.telefono = telefono;
		this.idReserva = idReserva;
	}



	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public String getApellido() {
		return apellido;
	}
	
	public Date getFechaNac() {
		return fechaNac;
	}
	
	public String getNacionalidad() {
		return nacionalidad;
	}
	
	public String getTelefono() {
		return telefono;
	}
	
	public Integer getIdReserva() {
		return idReserva;
	}
	
	
	
}
