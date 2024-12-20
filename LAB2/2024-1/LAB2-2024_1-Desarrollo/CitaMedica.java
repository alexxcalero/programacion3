import java.util.Date;

public abstract class CitaMedica extends AtencionMedica {
	// Declaro el atributo como protected para que las clases hijas puedan
	// acceder a este dato sin la necesidad de implementar getters o setters
	protected String motivo;
	
	public CitaMedica(Paciente paciente, Medico medico, Date fechaAtencion, String motivo) {
		super(paciente, medico, fechaAtencion);
		this.motivo = motivo;
	}
	
	@Override
	public abstract String consultarDatos();
}
