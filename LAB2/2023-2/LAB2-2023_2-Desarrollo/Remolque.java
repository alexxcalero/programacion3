public class Remolque extends Vehiculo {
	private double capacidadMaxima;
	
	public Remolque(Aerolinea aerolinea, String modelo, double velocidadMaxima, double capacidadMaxima) {
		super(aerolinea, modelo, velocidadMaxima);
		this.capacidadMaxima = capacidadMaxima;
	}
	
	@Override
	public String consultarDatos() {
		return "Remolque: " + modelo + " - Capacidad de Carga:" + capacidadMaxima + "Kg.\n";
	}
}
