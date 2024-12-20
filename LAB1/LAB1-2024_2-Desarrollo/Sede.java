import java.util.ArrayList;

public class Sede {
	private int id;
	private String nombre;
	private String direccion;
	private ArrayList<ProgramaAcademico> programasAcademicos;
	
	public Sede(int id, String nombre, String direccion) {
		this.id = id;
		this.nombre = nombre;
		this.direccion = direccion;
	}

	public String getNombre() {
		return nombre;
	}
	
	public ArrayList<ProgramaAcademico> getProgramasAcademicos() {
		return programasAcademicos;
	}
	
	public void setProgramasAcademicos(ArrayList<ProgramaAcademico> programasAcademicos) {
		this.programasAcademicos = programasAcademicos;
	}
	
	public String consultarProgramasAcademicos() {
		String cadena = "";
		for(ProgramaAcademico programa : programasAcademicos) {
			cadena += programa.consultarDatos() + "\n";
		}
		return cadena;
	}
}
