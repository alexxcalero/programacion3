public abstract class Empresa implements IConsultable {
	// Declaro el atributo como protected para que las clases hijas puedan
	// acceder a este dato sin la necesidad de implementar getters o setters
	protected String nombre;
	
	public Empresa(String nombre) {
		this.nombre = nombre;
	}
}
