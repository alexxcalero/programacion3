package com.medicalsoft.rrhh.model;

public class Medico extends Persona {
	private Especialidad especialidad;
	private String numeroColegiatura;
	private String numeroRegistroEspecialidad;
	
	public Medico(String DNI, String nombre, String apellidoPaterno, String apellidoMaterno, Especialidad especialidad, String numeroColegiatura, String numeroRegistroEspecialidad) {
		super(DNI, nombre, apellidoPaterno, apellidoMaterno);
		this.especialidad = especialidad;
		this.numeroColegiatura = numeroColegiatura;
		this.numeroRegistroEspecialidad = numeroRegistroEspecialidad;
	}
	
	@Override
	public String devolverInformacion() {
		return "MEDICO: " + getNombre() + " " + getApellidoPaterno() + " " + getApellidoMaterno() + " - CMP:" + numeroColegiatura + " - ESPECIALIDAD:" + especialidad.getNombre() + "\n";
	}
}
