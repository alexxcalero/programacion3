package pe.edu.pucp.softplantilla.model;

import java.io.Serializable;

public class Universidad implements Serializable {
    private Integer idUniversidad;
    private String nombre;
    private String siglas;
    private Boolean activa;

    public Universidad() {}
    
    public Universidad(Integer idUniversidad, String nombre, String siglas) {
        this.idUniversidad = idUniversidad;
        this.nombre = nombre;
        this.siglas = siglas;
        this.activa = true;
    }

    public Integer getIdUniversidad() {
        return idUniversidad;
    }

    public void setIdUniversidad(Integer idUniversidad) {
        this.idUniversidad = idUniversidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSiglas() {
        return siglas;
    }

    public void setSiglas(String siglas) {
        this.siglas = siglas;
    }

    public Boolean getActiva() {
        return activa;
    }

    public void setActiva(Boolean activa) {
        this.activa = activa;
    }
}
