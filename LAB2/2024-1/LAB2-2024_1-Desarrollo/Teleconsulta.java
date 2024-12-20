import java.util.Date;
import java.text.SimpleDateFormat;

public class Teleconsulta extends CitaMedica {
	private Plataforma plataforma;
	private String enlace;
	
	public Teleconsulta(Paciente paciente, Medico medico, Date fechaAtencion, String motivo, Plataforma plataforma, String enlace) {
		super(paciente, medico, fechaAtencion, motivo);
		this.plataforma = plataforma;
		this.enlace = enlace;
	}
	
	@Override
	public String consultarDatos() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
		String cadena = "ID:" + id + " - CREACION:" + sdf.format(fechaCreacion) + " - ATENCION:" + sdf.format(fechaAtencion) + " - " + estado + "\n";
		cadena += medico.devolverInformacion();
		cadena += paciente.devolverInformacion();
		cadena += "PLATAFORMA:" + plataforma + " - ENLACE:" + enlace + "\n";
		return cadena;
	}
}
