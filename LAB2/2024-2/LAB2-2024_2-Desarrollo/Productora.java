import java.util.ArrayList;

public class Productora {
	private String nombre;
	private ArrayList<Evento> eventos;
	
	public Productora(String nombre) {
		this.nombre = nombre;
		this.eventos = new ArrayList<Evento>();
	}
	
	public void agregarEvento(Evento evento) {
		eventos.add(evento);
	}
	
	public String consultarDatosEvento(int indice) {
		if(indice < 0 || indice >= eventos.size())
			return "NO HAY EVENTO DISPONIBLE\n";
		else
			return eventos.get(indice).devolverDatos();
	}
	
	public String consultarObrasTeatrales() {
		String cadena = "";
		for(Evento evento : eventos) {
			if(evento instanceof ObraTeatral) {
				cadena += evento.devolverDatos();
			}
		}
		return cadena;
	}
}
