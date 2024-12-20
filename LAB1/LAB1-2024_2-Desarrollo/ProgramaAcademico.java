public abstract class ProgramaAcademico implements IConsultable {
	private static int indice = 1;
	private int id;
	private String nombre;
	private String clave;
	private char modalidad;
	private double precio;
	
	public ProgramaAcademico(String nombre, String clave, char modalidad, double precio) {
		this.id = indice;
		this.nombre = nombre;
		this.clave = clave;
		this.modalidad = modalidad;
		this.precio = precio;
		indice++;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public String getClave() {
		return clave;
	}
	
	public double getPrecio() {
		return precio;
	}
}
