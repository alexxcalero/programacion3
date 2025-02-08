package pe.edu.pucp.softrh.compras.model;

import java.util.Date;
import java.io.Serializable;

public class Totales implements Serializable{
    private Integer idTotal;
    private Integer totalPrendas;
    private Integer promocionesActivas;
    private Integer cuponesActivos;
    private Integer clientesActivos;
    private Date fechaRegistro;

    public Totales(){
        this.totalPrendas = null;
        this.promocionesActivas = null;
        this.cuponesActivos = null;
        this.clientesActivos = null;
        this.fechaRegistro = null;
    }
    public Totales(Integer totalPrendas, Integer promocionesActivas,
            Integer cuponesActivos, Integer clientesActivos, Date fechaRegistro) {
        this.totalPrendas = totalPrendas;
        this.promocionesActivas = promocionesActivas;
        this.cuponesActivos = cuponesActivos;
        this.clientesActivos = clientesActivos;
        this.fechaRegistro = fechaRegistro;
    }

    public Integer getIdTotal() {
        return idTotal;
    }

    public void setIdTotal(Integer idTotal) {
        this.idTotal = idTotal;
    }

    public Integer getTotalPrendas() {
        return totalPrendas;
    }

    public void setTotalPrendas(Integer totalPrendas) {
        this.totalPrendas = totalPrendas;
    }

    public Integer getPromocionesActivas() {
        return promocionesActivas;
    }

    public void setPromocionesActivas(Integer promocionesActivas) {
        this.promocionesActivas = promocionesActivas;
    }

    public Integer getCuponesActivos() {
        return cuponesActivos;
    }

    public void setCuponesActivos(Integer cuponesActivos) {
        this.cuponesActivos = cuponesActivos;
    }

    public Integer getClientesActivos() {
        return clientesActivos;
    }

    public void setClientesActivos(Integer clientesActivos) {
        this.clientesActivos = clientesActivos;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
}
