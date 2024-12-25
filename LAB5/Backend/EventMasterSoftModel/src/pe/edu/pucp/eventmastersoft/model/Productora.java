package pe.edu.pucp.eventmastersoft.model;
public class Productora {
    private int idProductora;
    private String nombre;
    private boolean activa;

    public Productora(){}
    public Productora(String nombre){
        this.nombre = nombre;
    }
    public Productora(int idProductora, String nombre, boolean activa){
        this.idProductora = idProductora;
        this.nombre = nombre;
        this.activa = activa;
    }
    
    public int getIdProductora() {
        return idProductora;
    }

    public void setIdProductora(int idProductora) {
        this.idProductora = idProductora;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isActiva() {
        return activa;
    }

    public void setActiva(boolean activa) {
        this.activa = activa;
    }
    
    
}
