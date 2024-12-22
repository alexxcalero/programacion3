package com.medicalsoft.rrhh.model;

public abstract class Persona implements IConsultable {
	private String DNI;
	private String nombre;
	private String apellidoPaterno;
	private String apellidoMaterno;
	
	public Persona(String DNI, String nombre, String apellidoPaterno, String apellidoMaterno) {
		this.DNI = DNI;
		this.nombre = nombre;
		this.apellidoPaterno = apellidoPaterno;
		this.apellidoMaterno = apellidoMaterno;
	}
	
	public String getDNI() {
		return DNI;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public String getApellidoPaterno() {
		return apellidoPaterno;
	}
	
	public String getApellidoMaterno() {
		return apellidoMaterno;
	}
	
	public abstract String devolverInformacion(); 
}
