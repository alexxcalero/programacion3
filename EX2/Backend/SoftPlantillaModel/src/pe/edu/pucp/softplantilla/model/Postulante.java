package pe.edu.pucp.softplantilla.model;

import java.io.Serializable;

public class Postulante implements Serializable {
    private Integer idPostulante;
    private Universidad universidad;
    private String dni;
    private String nombre;
    private String apellidoPaterno;
    private Double promedioAcumuladoPregrado;
    private Boolean fueTercioSuperior;
    private Boolean fueMiembroGrupoInvestigacion;
    private Boolean fueDeportistaCalificado;
    private Modalidad modalidadPreferida;
    private Boolean activo;

    public Postulante() {}
    
    public Postulante(Universidad universidad, String dni, String nombre, String apellidoPaterno, Double promedioAcumuladoPregrado, Boolean fueTercioSuperior, Boolean fueMiembroGrupoInvestigacion, Boolean fueDeportistaCalificado, Modalidad modalidadPreferida) {
        this.idPostulante = null;
        this.universidad = universidad;
        this.dni = dni;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.promedioAcumuladoPregrado = promedioAcumuladoPregrado;
        this.fueTercioSuperior = fueTercioSuperior;
        this.fueMiembroGrupoInvestigacion = fueMiembroGrupoInvestigacion;
        this.fueDeportistaCalificado = fueDeportistaCalificado;
        this.modalidadPreferida = modalidadPreferida;
        this.activo = true;
    }
    
    public Postulante(Integer idPostulante, Universidad universidad, String dni, String nombre, String apellidoPaterno, Double promedioAcumuladoPregrado, Boolean fueTercioSuperior, Boolean fueMiembroGrupoInvestigacion, Boolean fueDeportistaCalificado, Modalidad modalidadPreferida) {
        this.idPostulante = idPostulante;
        this.universidad = universidad;
        this.dni = dni;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.promedioAcumuladoPregrado = promedioAcumuladoPregrado;
        this.fueTercioSuperior = fueTercioSuperior;
        this.fueMiembroGrupoInvestigacion = fueMiembroGrupoInvestigacion;
        this.fueDeportistaCalificado = fueDeportistaCalificado;
        this.modalidadPreferida = modalidadPreferida;
        this.activo = true;
    }

    public Integer getIdPostulante() {
        return idPostulante;
    }

    public void setIdPostulante(Integer idPostulante) {
        this.idPostulante = idPostulante;
    }

    public Universidad getUniversidad() {
        return universidad;
    }

    public void setUniversidad(Universidad universidad) {
        this.universidad = universidad;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public Double getPromedioAcumuladoPregrado() {
        return promedioAcumuladoPregrado;
    }

    public void setPromedioAcumuladoPregrado(Double promedioAcumuladoPregrado) {
        this.promedioAcumuladoPregrado = promedioAcumuladoPregrado;
    }

    public Boolean getFueTercioSuperior() {
        return fueTercioSuperior;
    }

    public void setFueTercioSuperior(Boolean fueTercioSuperior) {
        this.fueTercioSuperior = fueTercioSuperior;
    }

    public Boolean getFueMiembroGrupoInvestigacion() {
        return fueMiembroGrupoInvestigacion;
    }

    public void setFueMiembroGrupoInvestigacion(Boolean fueMiembroGrupoInvestigacion) {
        this.fueMiembroGrupoInvestigacion = fueMiembroGrupoInvestigacion;
    }

    public Boolean getFueDeportistaCalificado() {
        return fueDeportistaCalificado;
    }

    public void setFueDeportistaCalificado(Boolean fueDeportistaCalificado) {
        this.fueDeportistaCalificado = fueDeportistaCalificado;
    }

    public Modalidad getModalidadPreferida() {
        return modalidadPreferida;
    }

    public void setModalidadPreferida(Modalidad modalidadPreferida) {
        this.modalidadPreferida = modalidadPreferida;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }
}
