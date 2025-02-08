package pe.edu.pucp.softplantilla.model;

import java.io.Serializable;
import java.util.Date;

public class Persona implements Serializable {
	private Integer idPersona;
	private String nombre;
	private String apellido;
	private Double altura;
	private Tipo tipo;
	private Date fechaNacimiento;
	private Boolean activo;
	// private byte[] foto;  // Por si tiene foto, de tipo LONGBLOB en MySQL

	public Persona() {}

	public Persona(Integer idPersona, String nombre, String apellido, Double altura, Tipo tipo, Date fechaNacimiento) {
		this.idPersona = idPersona;
		this.nombre = nombre;
		this.apellido = apellido;
		this.altura = altura;
		this.tipo = tipo;
		this.fechaNacimiento = fechaNacimiento;
		this.activo = true;
	}

	public Persona(String nombre, String apellido, Double altura, Tipo tipo, Date fechaNacimiento) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.altura = altura;
		this.tipo = tipo;
		this.fechaNacimiento = fechaNacimiento;
		this.activo = true;
	}

	public Integer getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(Integer idPersona) {
		this.idPersona = idPersona;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public Double getAltura() {
		return altura;
	}

	public void setAltura(Double altura) {
		this.altura = altura;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	@Override
	public String toString() {
		return "idPersona=" + idPersona + ", nombre=" + nombre + ", apellido=" + apellido + ", altura=" + altura + ", tipo=" + tipo + ", fechaNacimiento=" + fechaNacimiento + ", activo=" + activo;
	}
}
