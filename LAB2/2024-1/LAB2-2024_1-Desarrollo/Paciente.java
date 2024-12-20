import java.util.ArrayList;

public class Paciente extends Persona {
	private int numHistClinica;
	private ArrayList<AtencionMedica> atenciones;
	
	public Paciente(String dni, String nombre, String apellidop, String apellidom, int numHistClinica) {
		super(dni, nombre, apellidop, apellidom);
		this.numHistClinica = numHistClinica;
	}
	
	public void setAtencionesMedicas(ArrayList<AtencionMedica> atenciones) {
		this.atenciones = atenciones;
	}
	
	public ArrayList<AtencionMedica> getAtencionesMedicas() {
		return atenciones;
	}
	
	public void listarCitasMedicasProgramadas() {
		for(AtencionMedica atencion : atenciones) {
			if(atencion instanceof CitaMedica) {
				System.out.println(atencion.consultarDatos());
			}
		}
	}
	
	@Override
	public String devolverInformacion() {
		return "PACIENTE: " + dni + " - " + nombre + " " + apellidop + " " + apellidom + "\n";
	}
}
