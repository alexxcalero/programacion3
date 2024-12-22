package pe.edu.pucp.eventmastersoft.gestion.model;

import pe.edu.pucp.eventmastersoft.contratos.model.TipoConcierto;

public class Concierto extends Evento{
	private boolean existePermisoGrabacion;
	private TipoConcierto tipoConcierto;
	public Concierto(){}
	public Concierto(String nombre, double costoRealizacion, char tipoPublico, TipoConcierto tipoConcierto, boolean existePermisoGrabacion){
		super(nombre, costoRealizacion, tipoPublico);
		this.tipoConcierto = tipoConcierto;
		this.existePermisoGrabacion = existePermisoGrabacion;
	}
	public boolean isExistePermisoGrabacion(){
		return existePermisoGrabacion;
	}
	public void setExistePermisoGrabacion(boolean existePermisoGrabacion){
		this.existePermisoGrabacion = existePermisoGrabacion;
	}
	public TipoConcierto getTipoConcierto(){
		return tipoConcierto;
	}
	public void setTipoConcierto(TipoConcierto tipoConcierto){
		this.tipoConcierto = tipoConcierto;
	}
	@Override
	public String devolverDatos(){
		String cadena = "";
		cadena += devolverCabecera();
		cadena += "CONCIERTO - TIPO:" + tipoConcierto + "\n";
		cadena += "PERMISO GRABACION: " + (existePermisoGrabacion?"SI":"NO") + "\n";
		cadena += "-----------------------------------------------------------" + "\n";
		return cadena;
	}
}