public class Local implements InfoProvider {
	private String nombre;
	private String direccion;
	private int capacidad;
	private TipoLocal tipo;
	
	public Local(String nombre, String direccion, int capacidad, TipoLocal tipo) {
		this.nombre = nombre;
		this.direccion = direccion;
		this.capacidad = capacidad;
		this.tipo = tipo;
	}
	
	public String devolverDatos() {
		return "LOCAL: " + nombre + " - CAPACIDAD: " + capacidad + "\n";
	}
}
