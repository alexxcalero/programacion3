/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pe.edu.pucp.softrh.rmi.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import pe.edu.pucp.softrh.compras.model.Carrito;
import pe.edu.pucp.softrh.compras.model.Estado;
import pe.edu.pucp.softrh.compras.model.OrdenCompra;
import pe.edu.pucp.softrh.usuarios.model.Cliente;
import pe.edu.pucp.softrh.usuarios.model.Cupon;

public interface OrdenCompraBO extends Remote{
    Integer insertar(Date fechaRegistro, Estado estado, String dni,
            String correo, Double subtotal, Cliente cliente, Cupon cupon,
            Carrito carrito) throws RemoteException;

    Integer modificar(Integer IdOrdenCompra, Date fechaRegistro, Date fechaProcesado,
            Date fechaEntregado, Date fechaAnulado, Estado estado, String dni,
            String correo, Double subtotal, Cliente cliente, String paypal_id) throws RemoteException;

    Integer eliminar(Integer idOrden) throws RemoteException;

    ArrayList<OrdenCompra> listarTodos() throws RemoteException;

    OrdenCompra obtenerPorId(Integer idOrden) throws RemoteException;

    ArrayList<OrdenCompra> listarPorEstado(String cadena) throws RemoteException;

    ArrayList<OrdenCompra> listarPorIdCliente(Integer idCliente) throws RemoteException;

    Integer cambiarEstado(Integer idOrden, String cadena) throws RemoteException;

    Integer insertarPrendaSeleccionada(int idPrenda, int fidOrden, int cantidad, double precio) throws RemoteException;

    String pagarConPaypal(Integer idOrden, String url_confirmacion,
            String url_cancelacion) throws RemoteException;

    Boolean capturarPagoConPayPal(String url_return) throws RemoteException;
}
