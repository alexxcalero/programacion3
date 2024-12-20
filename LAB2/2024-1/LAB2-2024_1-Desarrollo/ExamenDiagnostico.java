import java.util.Date;
import java.text.SimpleDateFormat;

public class ExamenDiagnostico extends AtencionMedica {
	private TipoExamen tipo;
	private SalaEspecializada sala;
	
	public ExamenDiagnostico(Paciente paciente, Medico medico, Date fechaAtencion, SalaEspecializada sala, TipoExamen tipo) {
		super(paciente, medico, fechaAtencion);
		this.sala = sala;
		this.tipo = tipo;
	}
	
	@Override
	public String consultarDatos() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
		String cadena = "ID:" + id + " - CREACION:" + sdf.format(fechaCreacion) + " - ATENCION:" + sdf.format(fechaAtencion) + " - " + estado + "\n";
		cadena += medico.devolverInformacion();
		cadena += paciente.devolverInformacion();
		cadena += sala.devolverInformacion();
		cadena += "TIPO EXAMEN:" + tipo + "\n";
		return cadena;
	}
}
