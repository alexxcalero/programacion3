public class Consultorio extends AmbienteClinico {
	private String numero;
	
	public Consultorio(double area, char torre, int piso, String numero) {
		super(area, torre, piso);
		this.numero = numero;
	}
	
	@Override
	public String devolverInformacion() {
		return "CONSULTORIO:" + numero + " - TORRE:" + torre + " - PISO:" + piso + "\n";
	}
}
