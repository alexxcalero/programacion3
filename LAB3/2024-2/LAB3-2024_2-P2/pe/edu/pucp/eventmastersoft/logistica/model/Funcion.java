package pe.edu.pucp.eventmastersoft.logistica.model;

import java.util.Date;
import java.time.LocalTime;
import java.text.SimpleDateFormat;

public class Funcion implements InfoProvider{
	private int idFuncion;
	private Date fecha;
	private LocalTime hora;
	public Funcion(){}
	public Funcion(Date fecha, LocalTime hora){
		this.fecha = fecha;
		this.hora = hora;
	}
	public int getIdFuncion(){
		return idFuncion;
	}
	public void setIdFuncion(int idFuncion){
		this.idFuncion = idFuncion;
	}
	public Date getFecha(){
		return fecha;
	}
	public void setFecha(Date fecha){
		this.fecha = fecha;
	}
	public LocalTime getHora(){
		return hora;
	}
	public void setHora(LocalTime hora){
		this.hora = hora;
	}
	public String devolverDatos(){
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		return "FECHA:" + sdf.format(fecha) + " " + hora + "\n"; 
	}
}