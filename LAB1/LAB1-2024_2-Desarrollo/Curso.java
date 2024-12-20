import java.util.Date;
import java.time.LocalTime;

public class Curso extends ProgramaAcademico {
	private int horasPorSemana;
	private double creditos;
	private Date fechaInicio;
	private Date fechaFin;
	private DiaSemana diaDictado;
	private LocalTime horaInicio;
	private LocalTime horaFin;
	
	public Curso(String nombre, String clave, char modalidad, double precio, int horasPorSemana, double creditos, Date fechaInicio,
		Date fechaFin, DiaSemana diaDictado, LocalTime horaInicio, LocalTime horaFin) {
		super(nombre, clave, modalidad, precio);
		this.horasPorSemana = horasPorSemana;
		this.creditos = creditos;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.diaDictado = diaDictado;
		this.horaInicio = horaInicio;
		this.horaFin = horaFin;
	}
	
	@Override
	public String consultarDatos() {
		return "CURSO: " + getClave() + " - " + getNombre() + " - S/. " + getPrecio() +
			   " - CRED: " + creditos;
	}
}
