package pe.edu.pucp.eventmastersoft.gestion.model;

import java.util.ArrayList;
import pe.edu.pucp.eventmastersoft.logistica.model.InfoProvider;
import pe.edu.pucp.eventmastersoft.logistica.model.Artista;
import pe.edu.pucp.eventmastersoft.logistica.model.Local;
import pe.edu.pucp.eventmastersoft.logistica.model.Funcion;
import pe.edu.pucp.eventmastersoft.contratos.model.IDataProvider;

public abstract class Evento implements InfoProvider, IDataProvider{
	private static int correlativo = 1;
	private int idEvento;
	private String nombre;
	private double costoRealizacion;
	private char tipoPublico;
	private ArrayList<Artista> artistas;
	private Local local;
	private ArrayList<Funcion> funciones;
	public Evento(){
		this.artistas = new ArrayList<>();
		this.funciones = new ArrayList<>();
	}
	public Evento(String nombre, double costoRealizacion, char tipoPublico){
		this.idEvento = correlativo;
		this.nombre = nombre;
		this.costoRealizacion = costoRealizacion;
		this.tipoPublico = tipoPublico;
		this.artistas = new ArrayList<>();
		this.funciones = new ArrayList<>();
		correlativo++;
	}
	public int getIdEvento(){
		return idEvento;
	}
	public void setIdEvento(int idEvento){
		this.idEvento = idEvento;
	}
	public String getNombre(){
		return nombre;
	}
	public void setNombre(String nombre){
		this.nombre = nombre;
	}
	public double getCostoRealizacion(){
		return costoRealizacion;
	}
	public void setCostoRealizacion(double costoRealizacion){
		this.costoRealizacion = costoRealizacion;
	}
	public char getTipoPublico(){
		return tipoPublico;
	}
	public void setTipoPublico(char tipoPublico){
		this.tipoPublico = tipoPublico;
	}
	public Local getLocal(){
		return local;
	}
	public void setLocal(Local local){
		this.local = local;
	}
	public void setArtistas(ArrayList<Artista> artistas){
		this.artistas = new ArrayList<>(artistas);
	}
	public ArrayList<Artista> getArtistas(){
		return new ArrayList<>(artistas);
	}
	public void agregarArtista(Artista artista){
		artistas.add(artista);
	}
	public void setFunciones(ArrayList<Funcion> funciones){
		this.funciones = new ArrayList<>(funciones);
	}
	public ArrayList<Funcion> getFunciones(){
		return new ArrayList<>(funciones);
	}
	public void agregarFuncion(Funcion funcion){
		funciones.add(funcion);
	}
	public String devolverCabecera(){
		String cadena = "EVENTO Nro. " + idEvento + "\n";
		cadena += "-----------------------------------------------------------" + "\n";
		cadena += "NOMBRE: " + nombre + " - TIPO PUBLICO: " + tipoPublico + "\n";
		for(int i=0; i<artistas.size(); i++){
			cadena += "ARTISTA " + (int)(i+1) + ": " + artistas.get(i).devolverDatos();
		}
		cadena += local.devolverDatos();
		for(int i=0; i<funciones.size(); i++){
			cadena += "FUNCION " + (int)(i+1) + ": " + funciones.get(i).devolverDatos();
		}
		return cadena;
	}
}