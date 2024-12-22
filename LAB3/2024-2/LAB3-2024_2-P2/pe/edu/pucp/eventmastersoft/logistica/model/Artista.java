package pe.edu.pucp.eventmastersoft.logistica.model;

import java.util.ArrayList;

public class Artista implements InfoProvider{
	private int idArtista;
	private String nombre;
	private String nacionalidad;
	private ArrayList<Artista> artistas;
	public Artista(){
		this.artistas = new ArrayList<>();
	}
	public Artista(String nombre, String nacionalidad){
		this.nombre = nombre;
		this.nacionalidad = nacionalidad;
		this.artistas = new ArrayList<>();
	}
	public int getIdArtista(){
		return idArtista;
	}
	public void setIdArtista(int idArtista){
		this.idArtista = idArtista;
	}
	public String getNombre(){
		return nombre;
	}
	public void setNombre(String nombre){
		this.nombre = nombre;
	}
	public String getNacionalidad(){
		return nacionalidad;
	}
	public void setNacionalidad(String nacionalidad){
		this.nacionalidad = nacionalidad;
	}
	public String devolverDatos(){
		return "ARTISTA: " + nombre + "\n";
	}
	public void agregarArtista(Artista artista){
		artistas.add(artista);
	}
	public void setArtistas(ArrayList<Artista> artistas){
		this.artistas = new ArrayList<>(artistas);
	}
	public ArrayList<Artista> getArtistas(){
		return new ArrayList<>(artistas);
	}
}