package com.medicalsoft.infraestructura.model;

import com.medicalsoft.rrhh.model.IConsultable;

public abstract class AmbienteClinico implements IConsultable {
	private int idAmbienteClinico;
	private double espacioEnMetrosCuadrados;
	private char torre;
	private int piso;
	
	public AmbienteClinico(){}
	
	public AmbienteClinico(int idAmbienteClinico, double espacioEnMetrosCuadrados, char torre, int piso) {
		this.idAmbienteClinico = idAmbienteClinico;
		this.espacioEnMetrosCuadrados = espacioEnMetrosCuadrados;
		this.torre = torre;
		this.piso = piso;
	}
	
	public AmbienteClinico(double espacioEnMetrosCuadrados, char torre, int piso) {
		this.espacioEnMetrosCuadrados = espacioEnMetrosCuadrados;
		this.torre = torre;
		this.piso = piso;
	}
	
	public int getIdAmbienteClinico() {
		return idAmbienteClinico;
	}
	
	public void setIdAmbienteClinico(int idAmbienteClinico) {
		this.idAmbienteClinico = idAmbienteClinico;
	}
	
	public double getEspacioEnMetrosCuadrados() {
		return espacioEnMetrosCuadrados;
	}
	
	public void setEspacioEnMetrosCuadrados(double espacioEnMetrosCuadrados) {
		this.espacioEnMetrosCuadrados = espacioEnMetrosCuadrados;
	}
	
	public char getTorre() {
		return torre;
	}
	
	public void setTorre(char torre) {
		this.torre = torre;
	}
	
	public int getPiso() {
		return piso;
	}
	
	public void setPiso(int piso) {
		this.piso = piso;
	}
	
	public abstract String devolverInformacion();
}
