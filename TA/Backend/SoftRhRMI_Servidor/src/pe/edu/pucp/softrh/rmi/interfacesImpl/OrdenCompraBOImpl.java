package pe.edu.pucp.softrh.rmi.interfacesImpl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Date;
import pe.edu.pucp.softrh.compras.model.Carrito;
import pe.edu.pucp.softrh.compras.model.Estado;
import pe.edu.pucp.softrh.compras.model.OrdenCompra;
import pe.edu.pucp.softrh.rmi.interfaces.OrdenCompraBO;
import pe.edu.pucp.softrh.usuarios.model.Cliente;
import pe.edu.pucp.softrh.usuarios.model.Cupon;

public class OrdenCompraBOImpl extends UnicastRemoteObject implements OrdenCompraBO{
    private pe.edu.pucp.softrh.compras.bo.OrdenCompraBO ordenCompraBO;

    public OrdenCompraBOImpl(Integer puerto) throws RemoteException{
        super(puerto);
        this.ordenCompraBO = new pe.edu.pucp.softrh.compras.bo.OrdenCompraBO();
    }

    @Override
    public Integer insertar(Date fechaRegistro, Estado estado, String dni, String correo, Double subtotal, Cliente cliente, Cupon cupon, Carrito carrito) throws RemoteException {
        return this.ordenCompraBO.insertar(fechaRegistro, estado, dni, correo, subtotal, cliente, cupon, carrito);
    }

    @Override
    public Integer modificar(Integer IdOrdenCompra, Date fechaRegistro, Date fechaProcesado, Date fechaEntregado, Date fechaAnulado, Estado estado, String dni, String correo, Double subtotal, Cliente cliente, String paypal_id) throws RemoteException {
        return this.ordenCompraBO.modificar(IdOrdenCompra, fechaRegistro, fechaProcesado, fechaEntregado, fechaAnulado, estado, dni, correo, subtotal, cliente, paypal_id);
    }

    @Override
    public Integer eliminar(Integer idOrden) throws RemoteException {
        return this.ordenCompraBO.eliminar(idOrden);
    }

    @Override
    public ArrayList<OrdenCompra> listarTodos() throws RemoteException {
        return this.ordenCompraBO.listarTodos();
    }

    @Override
    public OrdenCompra obtenerPorId(Integer idOrden) throws RemoteException {
        return this.ordenCompraBO.obtenerPorId(idOrden);
    }

    @Override
    public ArrayList<OrdenCompra> listarPorEstado(String cadena) throws RemoteException {
        return this.ordenCompraBO.listarPorEstado(cadena);
    }

    @Override
    public ArrayList<OrdenCompra> listarPorIdCliente(Integer idCliente) throws RemoteException {
        return this.ordenCompraBO.listarPorIdCliente(idCliente);
    }

    @Override
    public Integer cambiarEstado(Integer idOrden, String cadena) throws RemoteException {
        return this.ordenCompraBO.cambiarEstado(idOrden, cadena);
    }

    @Override
    public Integer insertarPrendaSeleccionada(int idPrenda, int fidOrden, int cantidad, double precio) throws RemoteException {
        return this.ordenCompraBO.insertarPrendaSeleccionada(idPrenda, fidOrden, cantidad, precio);
    }

    @Override
    public String pagarConPaypal(Integer idOrden, String url_confirmacion, String url_cancelacion) throws RemoteException {
        return this.ordenCompraBO.pagarConPaypal(idOrden, url_confirmacion, url_cancelacion);
    }

    @Override
    public Boolean capturarPagoConPayPal(String url_return) throws RemoteException {
        return this.ordenCompraBO.capturarPagoConPayPal(url_return);
    }

}
