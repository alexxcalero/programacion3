package pe.edu.pucp.eventmastersoft.model;
import java.util.Date;
public class Evento {
    private int idEvento;
    private Productora productora;
    private char clasificacion;
    private String nombre;
    private double costoRealizacion;
    private TipoEvento tipoEvento;
    private Date fechaRealizacion;
    private String descripcion;
    private boolean permiteReingreso;
    private boolean permiteGrabacion;
    private byte[] bannerPromocional;
    private boolean activo;

    public Evento(){}
    public Evento(Productora productora, char clasificacion, String nombre, double costoRealizacion, TipoEvento tipoEvento, Date fechaRealizacion, String descripcion, boolean permiteReingreso, boolean permiteGrabacion, byte[] bannerPromocional){
        this.productora = productora;
        this.clasificacion = clasificacion;
        this.nombre = nombre;
        this.costoRealizacion = costoRealizacion;
        this.tipoEvento = tipoEvento;
        this.fechaRealizacion = fechaRealizacion;
        this.descripcion = descripcion;
        this.permiteGrabacion = permiteGrabacion;
        this.permiteReingreso = permiteReingreso;
        this.bannerPromocional = bannerPromocional;
    }
    
    public Evento(Productora productora, char clasificacion, String nombre, double costoRealizacion, TipoEvento tipoEvento, Date fechaRealizacion, String descripcion, boolean permiteReingreso, boolean permiteGrabacion, byte[] bannerPromocional, boolean activo){
        this.productora = productora;
        this.clasificacion = clasificacion;
        this.nombre = nombre;
        this.costoRealizacion = costoRealizacion;
        this.tipoEvento = tipoEvento;
        this.fechaRealizacion = fechaRealizacion;
        this.descripcion = descripcion;
        this.permiteGrabacion = permiteGrabacion;
        this.permiteReingreso = permiteReingreso;
        this.bannerPromocional = bannerPromocional;
        this.activo = activo;
    }
    
    public int getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(int idEvento) {
        this.idEvento = idEvento;
    }

    public Productora getProductora() {
        return productora;
    }

    public void setProductora(Productora productora) {
        this.productora = productora;
    }

    public char getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(char clasificacion) {
        this.clasificacion = clasificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getCostoRealizacion() {
        return costoRealizacion;
    }

    public void setCostoRealizacion(double costoRealizacion) {
        this.costoRealizacion = costoRealizacion;
    }

    public TipoEvento getTipoEvento() {
        return tipoEvento;
    }

    public void setTipoEvento(TipoEvento tipoEvento) {
        this.tipoEvento = tipoEvento;
    }

    public Date getFechaRealizacion() {
        return fechaRealizacion;
    }

    public void setFechaRealizacion(Date fechaRealizacion) {
        this.fechaRealizacion = fechaRealizacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isPermiteReingreso() {
        return permiteReingreso;
    }

    public void setPermiteReingreso(boolean permiteReingreso) {
        this.permiteReingreso = permiteReingreso;
    }

    public boolean isPermiteGrabacion() {
        return permiteGrabacion;
    }

    public void setPermiteGrabacion(boolean permiteGrabacion) {
        this.permiteGrabacion = permiteGrabacion;
    }

    public byte[] getBannerPromocional() {
        return bannerPromocional;
    }

    public void setBannerPromocional(byte[] bannerPromocional) {
        this.bannerPromocional = bannerPromocional;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
    
}