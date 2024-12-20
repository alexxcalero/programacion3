public class Aeropuerto implements IConsultable {
	private String nombre;
	private String direccion;
	private TipoAeropuerto tipo;
	private Ciudad ciudad;
	private Operadora operadora;
	
	public Aeropuerto(String nombre, String direccion, TipoAeropuerto tipo, Ciudad ciudad, Operadora operadora) {
		this.nombre = nombre;
		this.direccion = direccion;
		this.tipo = tipo;
		this.ciudad = ciudad;
		this.operadora = operadora;
	}
	
	public String consultarDatos() {
		String cadena = "Aeropuerto: " + nombre + " - " + ciudad.getNombre() + " - " + tipo + "\n";
		cadena += operadora.consultarDatos();
		return cadena;
	}
}
