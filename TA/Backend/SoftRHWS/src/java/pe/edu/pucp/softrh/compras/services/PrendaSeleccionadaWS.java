package pe.edu.pucp.softrh.compras.services;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import java.util.ArrayList;
import jakarta.jws.WebParam;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Date;
import pe.edu.pucp.softrh.rmi.servidor.SoftRhRMI_Servidor;
import pe.edu.pucp.softrh.compras.model.PrendaSeleccionada;
import pe.edu.pucp.softrh.rmi.interfaces.PrendaSeleccionadaBO;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebService(serviceName = "PrendaSeleccionadaWS", targetNamespace = "softrh.services")
public class PrendaSeleccionadaWS {

	private static final String ARCHIVO_CONFIGURACION = "datosConexion.txt";
    private static String IP;
    private static Integer puerto;

    private PrendaSeleccionadaBO psBO;

    public PrendaSeleccionadaWS() {
		SoftRhRMI_Servidor.leerArchivoYCrearCadena();
        String nombreServicio = SoftRhRMI_Servidor.retornarNombreDelServicio("prendaSeleccionadaBO");
        try {
            this.psBO = (PrendaSeleccionadaBO) Naming.lookup(nombreServicio);
        } catch (NotBoundException | MalformedURLException | RemoteException ex) {
            Logger.getLogger(PrendaSeleccionadaWS.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @WebMethod(operationName = "insertarPrendaSeleccionada")
    public Integer insertarPrendaSeleccionada(Integer idPrenda, Integer fidCarrito, Integer cantidad, Double precio) {
        Integer retorno = -1;
        try {
            retorno = psBO.insertar(idPrenda, fidCarrito, cantidad, precio);
        } catch (RemoteException ex) {
            Logger.getLogger(PrendaSeleccionadaWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    @WebMethod(operationName = "modificarPrendaSeleccionada")
    public Integer modificarPrendaSeleccionada(PrendaSeleccionada prenda) {
        Integer retorno = -1;
        try {
            retorno = psBO.modificar(prenda);
        } catch (RemoteException ex) {
            Logger.getLogger(PrendaSeleccionadaWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    @WebMethod(operationName = "eliminarPrendaSeleccionada")
    public Integer eliminarPrendaSeleccionada(Integer idPrenda, Integer idCarrito) {
        Integer retorno = -1;
        try {
            retorno = psBO.eliminar(idPrenda, idCarrito);
        } catch (RemoteException ex) {
            Logger.getLogger(PrendaSeleccionadaWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    @WebMethod(operationName = "obtenerPrendaSeleccionadaPorId")
    public PrendaSeleccionada obtenerPrendaSeleccionadaPorId(Integer idPrenda, Integer idCarrito) {
        PrendaSeleccionada retorno = null;
        try {
            retorno = psBO.obtenerPorIdPrenda(idPrenda, idCarrito);
        } catch (RemoteException ex) {
            Logger.getLogger(PrendaSeleccionadaWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    @WebMethod(operationName = "listarPrendasIdCarrito")
    public ArrayList<PrendaSeleccionada> listarPrendasIdCarrito(Integer idCarrito) {
        ArrayList<PrendaSeleccionada> retorno = new ArrayList<>();
        try {
            retorno = psBO.obtenerPorIdCarrito(idCarrito);
        } catch (RemoteException ex) {
            Logger.getLogger(PrendaSeleccionadaWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    @WebMethod(operationName = "listarPrendasIdOrden")
    public ArrayList<PrendaSeleccionada> listarPrendasIdOrden(Integer idOrden) {
        ArrayList<PrendaSeleccionada> retorno = new ArrayList<>();
        try {
            retorno = psBO.obtenerPorIdOrden(idOrden);
        } catch (RemoteException ex) {
            Logger.getLogger(PrendaSeleccionadaWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    @WebMethod(operationName = "aplicarCuponLista")
    public Integer aplicarCuponLista(Integer idCarrito, Double descuento) {
        Integer retorno = -1;
        try {
            retorno = psBO.aplicarCuponLista(idCarrito, descuento);
        } catch (RemoteException ex) {
            Logger.getLogger(PrendaSeleccionadaWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
}

