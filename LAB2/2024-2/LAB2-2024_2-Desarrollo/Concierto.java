public class Concierto extends Evento {
	private TipoConcierto tipo;
	private boolean permiteGrabacion;
	
	public Concierto(String nombre, double costoRealizacion, char tipoPublico, TipoConcierto tipo, boolean permiteGrabacion) {
		super(nombre, costoRealizacion, tipoPublico);
		this.tipo = tipo;
		this.permiteGrabacion = permiteGrabacion;
	}
	
	@Override
	public String devolverDatos() {
		String cadena = devolverCabecera();
		cadena += "CONCIERTO - TIPO: " + tipo + "\n";
		cadena += "PERMISO GRABACION: ";
		if(permiteGrabacion == true)
			cadena += "SI\n";
		else
			cadena += "NO\n";
		cadena += "--------------------------------------------------------\n";
		return cadena;
	}
}
