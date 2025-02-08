package pe.edu.pucp.softrh.prendas.services;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.util.ArrayList;
import java.util.Date;
import pe.edu.pucp.softrh.prendas.model.Prenda;
import pe.edu.pucp.softrh.prendas.model.Promocion;
import pe.edu.pucp.softrh.prendas.model.TipoPromocion;
import pe.edu.pucp.softrh.rmi.interfaces.PromocionBO;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import pe.edu.pucp.softrh.rmi.servidor.SoftRhRMI_Servidor;
import pe.edu.pucp.softrh.usuarios.model.Trabajador;

@WebService(serviceName = "PromocionWS", targetNamespace = "softrh.services")
public class PromocionWS {

	private static final String ARCHIVO_CONFIGURACION = "datosConexion.txt";
    private static String IP;
    private static Integer puerto;

    private PromocionBO promocionBO;

    public PromocionWS() {
		SoftRhRMI_Servidor.leerArchivoYCrearCadena();
        String nombreServicio = SoftRhRMI_Servidor.retornarNombreDelServicio("promocionBO");
        try {
            this.promocionBO = (PromocionBO) Naming.lookup(nombreServicio);
        } catch (NotBoundException | MalformedURLException | RemoteException ex) {
            Logger.getLogger(PromocionWS.class.getName()).log(Level.SEVERE, null, ex);
            this.promocionBO = null; // Inicializa como null en caso de error
        }
    }

    @WebMethod(operationName = "insertarPromocion")
    public Integer insertarPromocion(String nombre, String descripcion, Double valorDescuento, TipoPromocion tipo, Date fechaInicio, Date fechaFin, ArrayList<Prenda> prendasSeleccionadas, Trabajador trabajador) {
        Integer retorno = -1;
        try {
            retorno = promocionBO.insertar(nombre, descripcion, valorDescuento, tipo, fechaInicio, fechaFin, prendasSeleccionadas, trabajador);
        } catch (RemoteException ex) {
            Logger.getLogger(PromocionWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    @WebMethod(operationName = "modificarPromocion")
    public Integer modificarPromocion(Integer idPromocion, String nombre, String descripcion, Double valorDescuento, TipoPromocion tipo, Date fechaInicio, Date fechaFin, ArrayList<Prenda> prendasSeleccionadas, Trabajador trabajador) {
        Integer retorno = -1;
        try {
            retorno = promocionBO.modificar(idPromocion, nombre, descripcion, valorDescuento, tipo, fechaInicio, fechaFin, prendasSeleccionadas, trabajador);
        } catch (RemoteException ex) {
            Logger.getLogger(PromocionWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    @WebMethod(operationName = "eliminarPromocion")
    public Integer eliminarPromocion(Integer idPromocion) {
        Integer retorno = -1;
        try {
            retorno = promocionBO.eliminar(idPromocion);
        } catch (RemoteException ex) {
            Logger.getLogger(PromocionWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    @WebMethod(operationName = "listarPromocionesTodas")
    public ArrayList<Promocion> listarPromocionesTodas() {
        ArrayList<Promocion> retorno = new ArrayList<>();
        try {
            retorno = promocionBO.listarTodos();
        } catch (RemoteException ex) {
            Logger.getLogger(PromocionWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    @WebMethod(operationName = "obtenerPromocionPorId")
    public Promocion obtenerPromocionPorId(Integer idPromocion) {
        Promocion retorno = null;
        try {
            retorno = promocionBO.obtenerPorId(idPromocion);
        } catch (RemoteException ex) {
            Logger.getLogger(PromocionWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    @WebMethod(operationName = "listarPromocionesPorNombreDescripcion")
    public ArrayList<Promocion> listarPromocionesPorNombreDescripcion(String cadena) {
        ArrayList<Promocion> retorno = new ArrayList<>();
        try {
            retorno = promocionBO.listarPorNombreDescripcion(cadena);
        } catch (RemoteException ex) {
            Logger.getLogger(PromocionWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    @WebMethod(operationName = "listarPrendasAgregadas")
    public ArrayList<Prenda> listarPrendasAgregadas(int idPromocion) {
        ArrayList<Prenda> retorno = new ArrayList<>();
        try {
            retorno = promocionBO.listarPrendasAgregadas(idPromocion);
        } catch (RemoteException ex) {
            Logger.getLogger(PromocionWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
}

