import java.util.Date;
import java.text.SimpleDateFormat;

public class ConsultaPresencial extends CitaMedica {
	private boolean requiereAsistencia;
	private Consultorio consultorio;
	
	public ConsultaPresencial(Paciente paciente, Medico medico, Date fechaAtencion, String motivo, Consultorio consultorio, boolean requiereAsistencia) {
		super(paciente, medico, fechaAtencion, motivo);
		this.requiereAsistencia = requiereAsistencia;
		this.consultorio = consultorio;
	}
	
	@Override
	public String consultarDatos() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
		String cadena = "ID:" + id + " - CREACION:" + sdf.format(fechaCreacion) + " - ATENCION:" + sdf.format(fechaAtencion) + " - " + estado + "\n";
		cadena += medico.devolverInformacion();
		cadena += paciente.devolverInformacion();
		cadena += consultorio.devolverInformacion();
		cadena += "REQ. ASISTENCIA:";
		if(requiereAsistencia == false)
			cadena += "NO\n";
		else
			cadena += "SI\n";
		return cadena;
	}
}
