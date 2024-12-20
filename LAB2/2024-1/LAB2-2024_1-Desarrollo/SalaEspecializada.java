public class SalaEspecializada extends AmbienteClinico {
	private String nombre;
	private boolean poseeEquipamiento;
	
	public SalaEspecializada(double area, char torre, int piso, String nombre, boolean poseeEquipamiento) {
		super(area, torre, piso);
		this.nombre = nombre;
		this.poseeEquipamiento = poseeEquipamiento;
	}
	
	@Override
	public String devolverInformacion() {
		String cadena = "SALA ESPECIALIZADA:" + nombre + " - TORRE:" + torre + " - PISO:" + piso + " - EQ. IMAGENOLOGIA:";
		if(poseeEquipamiento == false)
			cadena += "NO\n";
		else
			cadena += "SI\n";
		return cadena;
	}
}
