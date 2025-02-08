package pe.edu.pucp.softrh.compras.services;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Date;
import pe.edu.pucp.softrh.compras.model.Carrito;
import pe.edu.pucp.softrh.rmi.interfaces.CarritoBO;
import pe.edu.pucp.softrh.rmi.servidor.SoftRhRMI_Servidor;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebService(serviceName = "CarritoWS", targetNamespace = "softrh.services")
public class CarritoWS {

	private static final String ARCHIVO_CONFIGURACION = "datosConexion.txt";
    private static String IP;
    private static Integer puerto;

    private CarritoBO carritoBO;

    public CarritoWS() {
		SoftRhRMI_Servidor.leerArchivoYCrearCadena();
        String nombreServicio = SoftRhRMI_Servidor.retornarNombreDelServicio("carritoBO");
        try {
            this.carritoBO = (CarritoBO) Naming.lookup(nombreServicio);
        } catch (NotBoundException | MalformedURLException | RemoteException ex) {
            Logger.getLogger(CarritoWS.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @WebMethod(operationName = "bug")
    public Double bug(Integer numero) {
        return (numero * 2.0);
    }

    /*
    @WebMethod(operationName = "insertarCarritoBD")
    public Integer insertarCarrito(Integer idCliente) {
        return this.carritoBO.insertar(idCliente);
    }
    */

    @WebMethod(operationName = "insertarCarrito")
    public Integer insertarCarrito(Integer idCliente) {
        Integer retorno = -1;

        try {
            retorno = this.carritoBO.insertar(idCliente);
        } catch (RemoteException ex) {
            Logger.getLogger(CarritoWS.class.getName()).log(Level.SEVERE, null, ex);
        }

        return retorno;
    }

    @WebMethod(operationName = "modificarCarrito")
    public Integer modificarCarrito(Carrito carrito) {
        Integer retorno = -1;

        try {
            retorno = this.carritoBO.modificar(carrito);
        } catch (RemoteException ex) {
            Logger.getLogger(CarritoWS.class.getName()).log(Level.SEVERE, null, ex);
        }

        return retorno;

    }

    @WebMethod(operationName = "obtenerCarritoPorIdCliente")
    public Carrito obtenerCarritoPorId(Integer idCarrito) {
        Carrito retorno = null;

        try {
            retorno = this.carritoBO.obtenerPorId(idCarrito);
        } catch (RemoteException ex) {
            Logger.getLogger(CarritoWS.class.getName()).log(Level.SEVERE, null, ex);
        }

        return retorno;
    }

    @WebMethod(operationName = "limpiarCarrito")
    public Integer limpiarCarrito(Integer fidCliente) {
        Integer retorno = null;

        try {
            retorno = this.carritoBO.limpiar_carrito(fidCliente);
        } catch (RemoteException ex) {
            Logger.getLogger(CarritoWS.class.getName()).log(Level.SEVERE, null, ex);
        }

        return retorno;
    }

}
