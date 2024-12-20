public class Medico extends Persona {
	private String numColegiatura;
	private Especialidad especialidad;
	private String numEspecialidad;
	
	public Medico(String dni, String nombre, String apellidop, String apellidom, Especialidad especialidad, String numColegiatura, String numEspecialidad) {
		super(dni, nombre, apellidop, apellidom);
		this.especialidad = especialidad;
		this.numColegiatura = numColegiatura;
		this.numEspecialidad = numEspecialidad;
	}
	
	@Override
	public String devolverInformacion() {
		return "MEDICO: " + nombre + " " + apellidop + " " + apellidom + " - CMP:" + numColegiatura + " - ESPECIALIDAD:" + especialidad.getNombre() + "\n";
	}
}
