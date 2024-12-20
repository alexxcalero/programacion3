public abstract class AmbienteClinico implements IConsultable {
	// Declaro los atributos como protected para que las clases hijas puedan
	// acceder a estos datos sin la necesidad de implementar getters o setters
	protected double area;
	protected char torre;
	protected int piso;
	
	public AmbienteClinico(double area, char torre, int piso) {
		this.area = area;
		this.torre = torre;
		this.piso = piso;
	}
}
