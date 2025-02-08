package pe.edu.pucp.softrh.compras.bo;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import pe.edu.pucp.softrh.compras.dao.OrdenCompraDAO;
import pe.edu.pucp.softrh.compras.daoimp.OrdenCompraDAOImp;
import pe.edu.pucp.softrh.compras.model.Carrito;
import pe.edu.pucp.softrh.compras.model.Estado;
import pe.edu.pucp.softrh.compras.model.OrdenCompra;
import pe.edu.pucp.softrh.database.config.PayPalAPI;
import pe.edu.pucp.softrh.usuarios.model.Cliente;
import pe.edu.pucp.softrh.usuarios.model.Cupon;

public class OrdenCompraBO {
    private OrdenCompraDAO ordenCompraDAO;

    public OrdenCompraBO() {
        this.ordenCompraDAO = new OrdenCompraDAOImp();
    }

    public Integer insertar(Date fechaRegistro, Estado estado, String dni, String correo, Double subtotal, Cliente cliente, Cupon cupon, Carrito carrito) {
        OrdenCompra orden = new OrdenCompra();
        orden.setFechaRegistro(fechaRegistro);
        orden.setEstado(estado);
        orden.setDni(dni);
        orden.setCorreo(correo);
        orden.setSubtotal(subtotal);
        orden.setCliente(cliente);
        orden.setCupon(cupon);
        orden.setCarrito(carrito);
        orden.setIdOrden(ordenCompraDAO.insertar(orden));
        return orden.getIdOrden();
    }

    public Integer modificar(Integer IdOrdenCompra, Date fechaRegistro, Date fechaProcesado, Date fechaEntregado, Date fechaAnulado, Estado estado, String dni, String correo, Double subtotal, Cliente cliente, String paypal_id) {
        OrdenCompra orden = new OrdenCompra(fechaRegistro, fechaProcesado, fechaEntregado, fechaAnulado, estado, dni, correo, subtotal, cliente);
        orden.setIdOrden(IdOrdenCompra);
        orden.setPaypal_id(paypal_id);
        return ordenCompraDAO.modificar(orden);
    }

    public Integer eliminar(Integer idOrden) {
        return this.ordenCompraDAO.eliminar(idOrden);
    }

    public ArrayList<OrdenCompra> listarTodos() {
        return this.ordenCompraDAO.listarTodos();
    }

    public OrdenCompra obtenerPorId(Integer idOrden) {
        return this.ordenCompraDAO.obtenerPorId(idOrden);
    }

    public ArrayList<OrdenCompra> listarPorEstado(String cadena) {
        return this.ordenCompraDAO.listarPorEstado(cadena);
    }

    public ArrayList<OrdenCompra> listarPorIdCliente(Integer idCliente) {
        return this.ordenCompraDAO.obtenerPorIdCliente(idCliente);
    }

    public Integer cambiarEstado(Integer idOrden, String cadena) {
        return this.ordenCompraDAO.cambiarEstado(idOrden, cadena);
    }

    public Integer insertarPrendaSeleccionada(int idPrenda, int fidOrden, int cantidad, double precio) {
        return this.ordenCompraDAO.insertarPrendaSeleccionada(idPrenda, fidOrden, cantidad, precio);
    }

    public String pagarConPaypal(Integer idOrden, String url_confirmacion,
            String url_cancelacion) {
        PayPalAPI api = new PayPalAPI();

        OrdenCompra orden = this.ordenCompraDAO.obtenerPorId(idOrden);
        String url_return = "";
        String id = orden.getIdOrden().toString();
        String subtotal = orden.getSubtotal().toString();
        try {
            url_return = api.sendOrderRequest(id, id, "PEN", subtotal, url_confirmacion, url_cancelacion, "RHStore");
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(OrdenCompraBO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return url_return;
    }

    public Boolean capturarPagoConPayPal(String url_return) {
        PayPalAPI api = new PayPalAPI();

        try {
            return api.verificate_pay(url_return);
        } catch (URISyntaxException | IOException | InterruptedException ex) {
            Logger.getLogger(OrdenCompraBO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }
}
