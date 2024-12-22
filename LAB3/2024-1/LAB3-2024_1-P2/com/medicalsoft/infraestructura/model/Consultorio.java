package com.medicalsoft.infraestructura.model;

public class Consultorio extends AmbienteClinico {
	private String numero;
	
	public Consultorio(){}
	
	public Consultorio(double especioEnMetrosCuadrados, char torre, int piso, String numero) {
		super(especioEnMetrosCuadrados, torre, piso);
		this.numero = numero;
	}
	
	public Consultorio(int idConsultorio, double espacioEnMetrosCuadrados, char torre, int piso, String numero) {
		super(idConsultorio, espacioEnMetrosCuadrados, torre, piso);
		this.numero = numero;
	}
	
	public void setNumero(String numero) {
		this.numero = numero;
	}
	
	public String getNumero() {
		return numero;
	}
	
	@Override
	public String devolverInformacion() {
		return "CONSULTORIO:" + numero + " - TORRE:" + getTorre() + " - PISO:" + getPiso() + "\n";
	}
}
