package pe.edu.pucp.softrh.compras.model;

import java.util.ArrayList;
import java.io.Serializable;

public class PrendaSeleccionada implements Serializable{
    private Integer idPrendaSeleccionada;
    private Integer idCarrito;
    private Integer cantidad;
    private Double precio;
    private Carrito carrito;
    private ArrayList<OrdenCompra> ordenes;

    public PrendaSeleccionada() {
    }

    public PrendaSeleccionada(Integer cantidad, Double precio) {
        this.cantidad = cantidad;
        this.precio = precio;
    }

    public PrendaSeleccionada(Integer cantidad, Double precio, Carrito carrito) {
        this.cantidad = cantidad;
        this.precio = precio;
        this.carrito = carrito;
        ordenes = new ArrayList<>();
    }

    public Integer getIdCarrito() {
        return idCarrito;
    }

    public void setIdCarrito(Integer idCarrito) {
        this.idCarrito = idCarrito;
    }

    public Integer getIdPrendaSeleccionada() {
        return idPrendaSeleccionada;
    }

    public void setIdPrendaSeleccionada(Integer idPrendaSeleccionada) {
        this.idPrendaSeleccionada = idPrendaSeleccionada;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Carrito getCarrito() {
        return carrito;
    }

    public void setCarrito(Carrito carrito) {
        this.carrito = carrito;
    }

    public void agregarOrden(OrdenCompra orden) {
        ordenes.add(orden);
    }

    public void show(){
        System.out.println("id prenda: " + this.idPrendaSeleccionada);
        System.out.println("id carrito: " + this.idCarrito);
        System.out.println("cantidad: " + this.cantidad);
        System.out.println("total: " + this.precio);
    }
}
