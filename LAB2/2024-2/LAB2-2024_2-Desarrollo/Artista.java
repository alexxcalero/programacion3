import java.util.ArrayList;

public class Artista implements InfoProvider {
	private String nombre;
	private String nacionalidad;
	private ArrayList<Artista> integrantes;  // En caso sea una banda
	
	public Artista(String nombre, String nacionalidad) {
		this.nombre = nombre;
		this.nacionalidad = nacionalidad;
		this.integrantes = new ArrayList<Artista>();
	}
	
	public void agregarArtista(Artista artista) {
		integrantes.add(artista);
	}
	
	public String devolverDatos() {
		return "ARTISTA: " + nombre + "\n";
	}
}
