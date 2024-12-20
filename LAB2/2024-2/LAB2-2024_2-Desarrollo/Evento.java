import java.util.ArrayList;

public abstract class Evento implements InfoProvider, IDataProvider {
	// Declaro los atributos como protected para que las clases hijas puedan
	// acceder a estos datos sin la necesidad de implementar getters o setters
	protected static int correlativo = 1;
	protected int indice;
	protected String nombre;
	protected double costoRealizacion;
	protected char tipoPublico;
	protected ArrayList<Artista> artistas;
	protected Local local;
	protected ArrayList<Funcion> funciones;
	
	public Evento(String nombre, double costoRealizacion, char tipoPublico) {
		this.indice = correlativo++;
		this.nombre = nombre;
		this.costoRealizacion = costoRealizacion;
		this.tipoPublico = tipoPublico;
		this.artistas = new ArrayList<Artista>();
		this.funciones = new ArrayList<Funcion>();
	}
	
	public void agregarArtista(Artista artista) {
		artistas.add(artista);
	}
	
	public void setLocal(Local local) {
		this.local = local;
	}
	
	public void agregarFuncion(Funcion funcion) {
		funciones.add(funcion);
	}
	
	public String devolverCabecera() {
		String cadena = "EVENTO Nro. " + indice + "\n";
		cadena += "--------------------------------------------------------\n";
		cadena += "NOMBRE: " + nombre + " - TIPO PUBLICO: " + tipoPublico + "\n";
		int cantArt = 1;
		for(Artista artista : artistas) {
			cadena += "ARTISTA " + cantArt + ": " + artista.devolverDatos();
			cantArt++;
		}
		cadena += local.devolverDatos();
		int cantFun = 1;
		for(Funcion funcion : funciones) {
			cadena += "FUNCION " + cantFun + ": " + funcion.devolverDatos();
			cantFun++;
		}
		return cadena;
	}
}
