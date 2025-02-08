package pe.edu.pucp.softrh.usuarios.services;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.util.ArrayList;
import java.util.Date;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import pe.edu.pucp.softrh.rmi.interfaces.CuponBO;
import pe.edu.pucp.softrh.rmi.servidor.SoftRhRMI_Servidor;
import pe.edu.pucp.softrh.usuarios.model.Cupon;
import pe.edu.pucp.softrh.usuarios.model.Trabajador;

@WebService(serviceName = "CuponWS", targetNamespace = "softrh.services")
public class CuponWS {

    private static final String ARCHIVO_CONFIGURACION = "datosConexion.txt";
    private static String IP;
    private static Integer puerto;

    private CuponBO cuponBO;

    public CuponWS() {
		SoftRhRMI_Servidor.leerArchivoYCrearCadena();
        String nombreServicio = SoftRhRMI_Servidor.retornarNombreDelServicio("cuponBO");
        try {
            this.cuponBO = (CuponBO) Naming.lookup(nombreServicio);
        } catch (NotBoundException | MalformedURLException | RemoteException ex) {
            Logger.getLogger(CuponWS.class.getName()).log(Level.SEVERE, null, ex);
            this.cuponBO = null; // Inicializa como null en caso de error
        }
    }

    @WebMethod(operationName = "insertarCupon")
    public Integer insertarCupon(String codigo, String descripcion, Double valorDescuento, Date fechaInicio, Date fechaFin, Trabajador trabajador) {
        Integer retorno = -1;
        try {
            retorno = cuponBO.insertar(codigo, descripcion, valorDescuento, fechaInicio, fechaFin, trabajador);
        } catch (RemoteException ex) {
            Logger.getLogger(CuponWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    @WebMethod(operationName = "modificarCupon")
    public Integer modificarCupon(Integer idCupon, String codigo, String descripcion, Double valorDescuento, Date fechaInicio, Date fechaFin, Trabajador trabajador) {
        Integer retorno = -1;
        try {
            retorno = cuponBO.modificar(idCupon, codigo, descripcion, valorDescuento, fechaInicio, fechaFin, trabajador);
        } catch (RemoteException ex) {
            Logger.getLogger(CuponWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    @WebMethod(operationName = "eliminarCupon")
    public Integer eliminarCupon(Integer idCupon) {
        Integer retorno = -1;
        try {
            retorno = cuponBO.eliminar(idCupon);
        } catch (RemoteException ex) {
            Logger.getLogger(CuponWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    @WebMethod(operationName = "listarCuponesTodos")
    public ArrayList<Cupon> listarCuponesTodos() {
        ArrayList<Cupon> retorno = new ArrayList<>();
        try {
            retorno = cuponBO.listarTodos();
        } catch (RemoteException ex) {
            Logger.getLogger(CuponWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    @WebMethod(operationName = "obtenerCuponPorId")
    public Cupon obtenerCuponPorId(Integer idCupon) {
        Cupon retorno = null;
        try {
            retorno = cuponBO.obtenerPorId(idCupon);
        } catch (RemoteException ex) {
            Logger.getLogger(CuponWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    @WebMethod(operationName = "listarCuponesPorCodigoDescripcion")
    public ArrayList<Cupon> listarCuponesPorCodigoDescripcion(String cadena) {
        ArrayList<Cupon> retorno = new ArrayList<>();
        try {
            retorno = cuponBO.listarPorCodigoDescripcion(cadena);
        } catch (RemoteException ex) {
            Logger.getLogger(CuponWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    @WebMethod(operationName = "insertarCuponUsado")
    public Integer insertarCuponUsado(Integer idCliente, Integer idCupon, Date fecha) {
        Integer retorno = -1;
        try {
            retorno = cuponBO.insertarCuponUsado(idCliente, idCupon, fecha);
        } catch (RemoteException ex) {
            Logger.getLogger(CuponWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
}

