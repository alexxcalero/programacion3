public class Avion extends Vehiculo {
	private int pasajerosMaximos;
	
	public Avion(Aerolinea aerolinea, String modelo, double velocidadMaxima, int pasajerosMaximos) {
		super(aerolinea, modelo, velocidadMaxima);
		this.pasajerosMaximos = pasajerosMaximos;
	}
	
	@Override
	public String consultarDatos() {
		String cadena = "Avion: " + modelo + " - Capacidad Max. Pasajeros:" + pasajerosMaximos + "\n";
		cadena += aerolinea.consultarDatos();
		return cadena;
	}
}
