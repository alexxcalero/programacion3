package pe.edu.pucp.eventmastersoft.gestion.model;

public class ObraTeatral extends Evento{
	private int numeroActos;
	private boolean requiereEscenografia;
	public ObraTeatral(){}
	public ObraTeatral(String nombre, double costoRealizacion, char tipoPublico, int numeroActos, boolean requiereEscenografia){
		super(nombre, costoRealizacion, tipoPublico);
		this.numeroActos = numeroActos;
		this.requiereEscenografia = requiereEscenografia;
	}
	public int getNumeroActos(){
		return numeroActos;
	}
	public void setNumeroActos(int numeroActos){
		this.numeroActos = numeroActos;
	}
	public boolean isRequiereEscenografia(){
		return requiereEscenografia;
	}
	public void setRequiereEscenografia(boolean requiereEscenografia){
		this.requiereEscenografia = requiereEscenografia;
	}
	@Override
	public String devolverDatos(){
		String cadena = "";
		cadena += devolverCabecera();
		cadena += "OBRA TEATRAL - NUM. ACTOS: " + numeroActos + "\n";
		cadena += "REQ. ESCENOGRAFIA: " + (requiereEscenografia?"SI":"NO") + "\n";
		cadena += "-----------------------------------------------------------" + "\n";
		return cadena;
	}
}