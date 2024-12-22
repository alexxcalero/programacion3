package com.medicalsoft.infraestructura.model;

public class SalaEspecializada extends AmbienteClinico {
	private String nombre;
	private boolean poseeEquipamientoImagenologia;
	
	public SalaEspecializada(){}
	
	public SalaEspecializada(double especioEnMetrosCuadrados, char torre, int piso, String nombre, boolean poseeEquipamientoImagenologia) {
		super(especioEnMetrosCuadrados, torre, piso);
		this.nombre = nombre;
		this.poseeEquipamientoImagenologia = poseeEquipamientoImagenologia;
	}
	
	public SalaEspecializada(int idSalaEspecializada, String nombre, double especioEnMetrosCuadrados, char torre, int piso,  boolean poseeEquipamientoImagenologia) {
		super(idSalaEspecializada, especioEnMetrosCuadrados, torre, piso);
		this.nombre = nombre;
		this.poseeEquipamientoImagenologia = poseeEquipamientoImagenologia;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public boolean isPoseeEquipamientoImagenologia() {
        return poseeEquipamientoImagenologia;
    }
    
	public void setPoseeEquipamientoImagenologia(boolean poseeEquipamientoImagenologia) {
        this.poseeEquipamientoImagenologia = poseeEquipamientoImagenologia;
    }
	
	@Override
	public String devolverInformacion() {
		return "SALA ESPECIALIZADA:" + nombre + " - TORRE:" + getTorre() + " - PISO:" + getPiso() + " - EQ. IMAGENOLOGIA:" + (poseeEquipamientoImagenologia?"SI":"NO") + "\n";  
	}
}
