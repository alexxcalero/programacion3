package pe.edu.pucp.eventmastersoft.gestion.model;

import java.util.ArrayList;

public class Productora{
	private int idProductora;
	private String nombre;
	private ArrayList<Evento> eventos;
	public Productora(){
		this.eventos = new ArrayList<>();
	}
	public Productora(String nombre){
		this.nombre = nombre;
		this.eventos = new ArrayList<>();
	}
	public int getIdProductora(){
		return idProductora;
	}
	public void setIdProductora(int idProductora){
		this.idProductora = idProductora;
	}
	public void setEventos(ArrayList<Evento> eventos){
		this.eventos = new ArrayList<>(eventos);
	}
	public ArrayList<Evento> getEventos(){
		return new ArrayList<>(eventos);
	}
	public void agregarEvento(Evento evento){
		eventos.add(evento);
	}
	public String consultarDatosEvento(int indice){
		return eventos.get(indice).devolverDatos();
	}
	public String consultarObrasTeatrales(){
		String cadena = "";
		for(Evento e : eventos){
			if(e instanceof ObraTeatral){
				cadena = cadena + e.devolverDatos();
			}
		}
		return cadena;
	}
}