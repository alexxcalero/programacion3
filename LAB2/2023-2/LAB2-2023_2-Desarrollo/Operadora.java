public class Operadora extends Empresa {
	private boolean ofreceSoporteLogistico;
	private boolean ofreceAsistNavegacion;
	
	public Operadora(String nombre, boolean ofreceSoporteLogistico, boolean ofreceAsistNavegacion) {
		super(nombre);
		this.ofreceSoporteLogistico = ofreceSoporteLogistico;
		this.ofreceAsistNavegacion = ofreceAsistNavegacion;
	}
	
	@Override
	public String consultarDatos() {
		return "Operadora: " + nombre + " - Soporte Logistico:" + ofreceSoporteLogistico + " - Asistencia Navegacion:" + ofreceAsistNavegacion + "\n";
	}
}
