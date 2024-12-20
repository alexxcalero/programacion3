import java.util.ArrayList;

public class InstitucionEducativa {
	private int id;
	private String ruc;
	private String nombre;
	private ArrayList<Sede> sedes;
	
	public InstitucionEducativa(int id, String ruc, String nombre) {
		this.id = id;
		this.ruc = ruc;
		this.nombre = nombre;
	}
	
	public ArrayList<Sede> getSedes() {
		return sedes;
	}
	
	public void setSedes(ArrayList<Sede> sedes) {
		this.sedes = sedes;
	}
	
	public String consultarProgramasDeSede(int idSede) {
		String cadena = "Programas disponibles para " + sedes.get(idSede).getNombre() + "\n";
		cadena += sedes.get(idSede).consultarProgramasAcademicos();
		return cadena;
	}
}
