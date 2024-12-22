package pe.edu.pucp.eventmastersoft.logistica.model;

public class Local implements InfoProvider{
	private int idLocal;
	private String nombre;
	private String direccion;
	private int capacidad;
	private double espacioMetrosCuadrados;
	private TipoLocal tipoLocal;
	private boolean activo;
	public Local(){}
	public Local(int idLocal, String nombre, String direccion, int capacidad, double espacioMetrosCuadrados, TipoLocal tipoLocal){
		this.idLocal = idLocal;
		this.nombre = nombre;
		this.direccion = direccion;
		this.capacidad = capacidad;
		this.espacioMetrosCuadrados = espacioMetrosCuadrados;
		this.tipoLocal = tipoLocal;
	}
	public Local(int idLocal, String nombre, String direccion, int capacidad, double espacioMetrosCuadrados, TipoLocal tipoLocal, boolean activo){
		this.idLocal = idLocal;
		this.nombre = nombre;
		this.direccion = direccion;
		this.capacidad = capacidad;
		this.espacioMetrosCuadrados = espacioMetrosCuadrados;
		this.tipoLocal = tipoLocal;
		this.activo = activo;
	}
	public Local(String nombre, String direccion, int capacidad, double espacioMetrosCuadrados, TipoLocal tipoLocal){
		this.nombre = nombre;
		this.direccion = direccion;
		this.capacidad = capacidad;
		this.espacioMetrosCuadrados = espacioMetrosCuadrados;
		this.tipoLocal = tipoLocal;
	}
	public Local(String nombre, String direccion, int capacidad, double espacioMetrosCuadrados, TipoLocal tipoLocal, boolean activo){
		this.nombre = nombre;
		this.direccion = direccion;
		this.capacidad = capacidad;
		this.espacioMetrosCuadrados = espacioMetrosCuadrados;
		this.tipoLocal = tipoLocal;
		this.activo = activo;
	}
	public int getIdLocal(){
		return idLocal;
	}
	public void setIdLocal(int idLocal){
		this.idLocal = idLocal;
	}
	public String getNombre(){
		return nombre;
	}
	public void setNombre(String nombre){
		this.nombre = nombre;
	}
	public String getDireccion(){
		return direccion;
	}
	public void setDireccion(String direccion){
		this.direccion = direccion;
	}
	public int getCapacidad(){
		return capacidad;
	}
	public void setCapacidad(int capacidad){
		this.capacidad = capacidad;
	}
	public double getEspacioMetrosCuadrados(){
		return espacioMetrosCuadrados;
	}
	public void setEspacioMetrosCuadrados(double espacioMetrosCuadrados){
		this.espacioMetrosCuadrados = espacioMetrosCuadrados;
	}
	public TipoLocal getTipoLocal(){
		return tipoLocal;
	}
	public void setTipoLocal(TipoLocal tipoLocal){
		this.tipoLocal = tipoLocal;
	}
	public String devolverDatos(){
		return "LOCAL: " + nombre + " - CAPACIDAD:" + capacidad + "\n";
	}
}