public abstract class Persona implements IConsultable {
	// Declaro los atributos como protected para que las clases hijas puedan
	// acceder a estos datos sin la necesidad de implementar getters o setters
	protected String dni;
	protected String nombre;
	protected String apellidop;
	protected String apellidom;
	
	public Persona(String dni, String nombre, String apellidop, String apellidom) {
		this.dni = dni;
		this.nombre = nombre;
		this.apellidop = apellidop;
		this.apellidom = apellidom;
	}
}
