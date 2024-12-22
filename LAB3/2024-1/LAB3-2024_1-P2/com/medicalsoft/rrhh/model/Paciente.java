package com.medicalsoft.rrhh.model;

import java.util.ArrayList;

public class Paciente extends Persona {
	private int numeroHistorioClinica;
	private ArrayList<AtencionMedica> atencionesMedicas;
	
	public Paciente(String DNI, String nombre, String apellidoPaterno, String apellidoMaterno, int numeroHistorioClinica) {
		super(DNI, nombre, apellidoPaterno, apellidoMaterno);
		this.numeroHistorioClinica = numeroHistorioClinica;
	}
	
	public void setAtencionesMedicas(ArrayList<AtencionMedica> atencionesMedicas) {
		this.atencionesMedicas = atencionesMedicas;
	}
	
	public ArrayList<AtencionMedica> getAtencionesMedicas() {
		return atencionesMedicas;
	}
	
	@Override
	public String devolverInformacion() {
		return "PACIENTE: " + getDNI() + " - " + getNombre() + " " + getApellidoPaterno() + " " + getApellidoMaterno() + "\n";
	}
	
	public void listarCitasMedicasProgramadas() {
		for(AtencionMedica at: atencionesMedicas) {
			if(at.getTipoAtencion() == TipoAtencion.CITA_MEDICA)
				System.out.println(at.consultarDatos());
		}
	}
}
