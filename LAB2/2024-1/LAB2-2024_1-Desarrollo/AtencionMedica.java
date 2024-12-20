import java.util.Date;

public abstract class AtencionMedica {
	// Declaro los atributos como protected para que las clases hijas puedan
	// acceder a estos datos sin la necesidad de implementar getters o setters
	protected static int indice = 1;
	protected int id;
	protected Date fechaCreacion;
	protected Date fechaAtencion;
	protected Estado estado;
	protected Paciente paciente;
	protected Medico medico;
	
	public AtencionMedica(Paciente paciente, Medico medico, Date fechaAtencion) {
		id = indice++;
		this.fechaCreacion = new Date();
		this.fechaAtencion = fechaAtencion;
		this.estado = Estado.PROGRAMADA;
		this.paciente = paciente;
		this.medico = medico;
	}
	
	public abstract String consultarDatos();
}
