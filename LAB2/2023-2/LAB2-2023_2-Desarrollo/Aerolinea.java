public class Aerolinea extends Empresa {
	private String callsign;
	private boolean poseeFidelidad;
	
	public Aerolinea(String nombre, String callsign, boolean poseeFidelidad) {
		super(nombre);
		this.callsign = callsign;
		this.poseeFidelidad = poseeFidelidad;
	}
	
	@Override
	public String consultarDatos() {
		return "Aerolinea: " + nombre + " - " + callsign + " - Programa Fidelidad:" + poseeFidelidad + "\n";
	}
}
