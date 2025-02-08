package pe.edu.pucp.softrh.compras.model;

import java.io.Serializable;

public class Factura extends Comprobante implements Serializable{
    private String ruc;
    private String razonSocial;
    private String repreLegal;

    public Factura(Double totalPagar, OrdenCompra orden, String ruc, String razonSocial, String repreLegal) {
        super(totalPagar, orden);
        this.ruc = ruc;
        this.razonSocial = razonSocial;
        this.repreLegal = repreLegal;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getRepreLegal() {
        return repreLegal;
    }

    public void setRepreLegal(String repreLegal) {
        this.repreLegal = repreLegal;
    }
}
