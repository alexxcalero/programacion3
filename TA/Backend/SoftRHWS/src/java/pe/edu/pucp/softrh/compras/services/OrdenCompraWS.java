package pe.edu.pucp.softrh.compras.services;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Date;
import pe.edu.pucp.softrh.compras.model.Carrito;
import pe.edu.pucp.softrh.usuarios.model.Cupon;
import pe.edu.pucp.softrh.compras.model.OrdenCompra;
import pe.edu.pucp.softrh.compras.model.Estado;
import pe.edu.pucp.softrh.rmi.interfaces.OrdenCompraBO;
import pe.edu.pucp.softrh.rmi.servidor.SoftRhRMI_Servidor;
import pe.edu.pucp.softrh.usuarios.model.Cliente;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebService(serviceName = "OrdenCompraWS", targetNamespace = "softrh.services")
public class OrdenCompraWS {
	private static final String ARCHIVO_CONFIGURACION = "datosConexion.txt";
    private static String IP;
    private static Integer puerto;

    private OrdenCompraBO ordenCompraBO;

    public OrdenCompraWS() {
		SoftRhRMI_Servidor.leerArchivoYCrearCadena();
        String nombreServicio = SoftRhRMI_Servidor.retornarNombreDelServicio("ordenCompraBO");
        try {
            this.ordenCompraBO = (OrdenCompraBO) Naming.lookup(nombreServicio);
        } catch (NotBoundException | MalformedURLException | RemoteException ex) {
            Logger.getLogger(OrdenCompraWS.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @WebMethod(operationName = "insertarOrdenCompraConCupon")
    public Integer insertarOrdenCompraConCupon(Date fechaRegistro, Estado estado, String dni,
            String correo, Double subtotal, Cliente cliente, Cupon cupon,
            Carrito carrito) {

        Integer retorno = -1;
        try {
            retorno = ordenCompraBO.insertar(fechaRegistro, estado, dni, correo, subtotal,
                    cliente, cupon, carrito);
        } catch (RemoteException ex) {
            Logger.getLogger(OrdenCompraWS.class.getName()).log(Level.SEVERE, null, ex);
        }

        return retorno;
    }

    ////////////////////////////////////

    @WebMethod(operationName = "insertarOrdenCompraSinCupon")
    public Integer insertarOrdenCompraSinCupon(Date fechaRegistro, Estado estado, String dni,
            String correo, Double subtotal, Cliente cliente, Carrito carrito) {
        Integer retorno = -1;
        try {
            Cupon cupon = new Cupon();
            retorno = ordenCompraBO.insertar(fechaRegistro, estado, dni, correo, subtotal, cliente, cupon, carrito);
        } catch (RemoteException ex) {
            Logger.getLogger(OrdenCompraWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    @WebMethod(operationName = "modificarOrdenCompra")
    public Integer modificarOrdenCompra(Integer idOrdenCompra, Date fechaRegistro,
            Date fechaProcesado, Date fechaEntregado, Date fechaAnulado, Estado estado,
            String dni, String correo, Double subtotal, Cliente cliente, String paypal_id) {
        Integer retorno = -1;
        try {
            retorno = ordenCompraBO.modificar(idOrdenCompra, fechaRegistro, fechaProcesado, fechaEntregado,
                    fechaAnulado, estado, dni, correo, subtotal, cliente, paypal_id);
        } catch (RemoteException ex) {
            Logger.getLogger(OrdenCompraWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    @WebMethod(operationName = "eliminarOrdenCompra")
    public Integer eliminarOrdenCompra(Integer idOrdenCompra) {
        Integer retorno = -1;
        try {
            retorno = ordenCompraBO.eliminar(idOrdenCompra);
        } catch (RemoteException ex) {
            Logger.getLogger(OrdenCompraWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    @WebMethod(operationName = "listarOrdenCompraTodos")
    public ArrayList<OrdenCompra> listarOrdenCompraTodos() {
        ArrayList<OrdenCompra> retorno = new ArrayList<>();
        try {
            retorno = ordenCompraBO.listarTodos();
        } catch (RemoteException ex) {
            Logger.getLogger(OrdenCompraWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    @WebMethod(operationName = "listarPorIdCliente")
    public ArrayList<OrdenCompra> listarPorIdCliente(int idCliente) {
        ArrayList<OrdenCompra> retorno = new ArrayList<>();
        try {
            retorno = ordenCompraBO.listarPorIdCliente(idCliente);
        } catch (RemoteException ex) {
            Logger.getLogger(OrdenCompraWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    @WebMethod(operationName = "obtenerOrdenCompraPorId")
    public OrdenCompra obtenerOrdenCompraPorId(Integer idOrdenCompra) {
        OrdenCompra retorno = null;
        try {
            retorno = ordenCompraBO.obtenerPorId(idOrdenCompra);
        } catch (RemoteException ex) {
            Logger.getLogger(OrdenCompraWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    @WebMethod(operationName = "listarOrdenPorEstado")
    public ArrayList<OrdenCompra> listarOrdenPorEstado(String cadena) {
        ArrayList<OrdenCompra> retorno = new ArrayList<>();
        try {
            retorno = ordenCompraBO.listarPorEstado(cadena);
        } catch (RemoteException ex) {
            Logger.getLogger(OrdenCompraWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    @WebMethod(operationName = "cambiarEstado")
    public Integer cambiarEstado(Integer idOrdenCompra, String cadena) {
        Integer retorno = -1;
        try {
            retorno = ordenCompraBO.cambiarEstado(idOrdenCompra, cadena);
        } catch (RemoteException ex) {
            Logger.getLogger(OrdenCompraWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    @WebMethod(operationName = "insertarPrendaSeleccionada")
    public Integer insertarPrendaSeleccionada(int idPrenda, int fidOrden, int cantidad, double precio) {
        Integer retorno = -1;
        try {
            retorno = ordenCompraBO.insertarPrendaSeleccionada(idPrenda, fidOrden, cantidad, precio);
        } catch (RemoteException ex) {
            Logger.getLogger(OrdenCompraWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    @WebMethod(operationName = "pagarConPaypal")
    public String pagarConPaypal(Integer idOrden, String url_confirmacion, String url_cancelacion) {
        String retorno = null;
        try {
            retorno = ordenCompraBO.pagarConPaypal(idOrden, url_confirmacion, url_cancelacion);
        } catch (RemoteException ex) {
            Logger.getLogger(OrdenCompraWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    @WebMethod(operationName = "capturarPagoConPayPal")
    public Boolean capturarPagoConPayPal(String url_retorno) {
        Boolean retorno = false;
        try {
            retorno = ordenCompraBO.capturarPagoConPayPal(url_retorno);
        } catch (RemoteException ex) {
            Logger.getLogger(OrdenCompraWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
}
