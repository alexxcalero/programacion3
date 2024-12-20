public abstract class Vehiculo implements IConsultable {
	// Declaro los atributos como protected para que las clases hijas puedan
	// acceder a estos datos sin la necesidad de implementar getters o setters
	protected Aerolinea aerolinea;
	protected String modelo;
	protected double velocidadMaxima;
	
	public Vehiculo(Aerolinea aerolinea, String modelo, double velocidadMaxima) {
		this.aerolinea = aerolinea;
		this.modelo = modelo;
		this.velocidadMaxima = velocidadMaxima;
	}
}
