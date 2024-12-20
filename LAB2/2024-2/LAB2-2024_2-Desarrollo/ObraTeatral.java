public class ObraTeatral extends Evento {
	private int numeroActos;
	private boolean requiereEscenografia;
	
	public ObraTeatral(String nombre, double costoRealizacion, char tipoPublico, int numeroActos, boolean requiereEscenografia) {
		super(nombre, costoRealizacion, tipoPublico);
		this.numeroActos = numeroActos;
		this.requiereEscenografia = requiereEscenografia;
	}
	
	@Override
	public String devolverDatos() {
		String cadena = devolverCabecera();
		cadena += "OBRA TEATRAL - NUM. ACTOS: " + numeroActos + "\n";
		cadena += "REQ. ESCENOGRAFIA: ";
		if(requiereEscenografia == true)
			cadena += "SI\n";
		else
			cadena += "NO\n";
		cadena += "--------------------------------------------------------\n";
		return cadena;
	}
}
