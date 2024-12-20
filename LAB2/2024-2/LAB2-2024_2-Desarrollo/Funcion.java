import java.util.Date;
import java.time.LocalTime;
import java.text.SimpleDateFormat;

public class Funcion implements InfoProvider {
	private Date fecha;
	private LocalTime hora;
	
	public Funcion(Date fecha, LocalTime hora) {
		this.fecha = fecha;
		this.hora = hora;
	}
	
	public String devolverDatos() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		return "FECHA: " + sdf.format(fecha) + " " + hora + "\n";
	}
}
