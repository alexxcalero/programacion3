import java.util.Date;
import java.time.LocalTime;
import java.text.SimpleDateFormat;

public class Taller extends ProgramaAcademico {
	private Date fecha;
	private LocalTime horaInicio;
	private LocalTime horaFin;
	
	public Taller(String nombre, String clave, char modalidad, double precio, Date fecha,
				  LocalTime horaInicio, LocalTime horaFin) {
		super(nombre, clave, modalidad, precio);
		this.fecha = fecha;
		this.horaInicio = horaInicio;
		this.horaFin = horaFin;
	}
	
	@Override
	public String consultarDatos() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		return "TALLER: " + getClave() + " - " + getNombre() + " - S/. " + getPrecio() +
			   " - FECHA: " + sdf.format(fecha);
	}
}
