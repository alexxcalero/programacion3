package pe.edu.pucp.softrh.usuarios.services;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.util.ArrayList;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import pe.edu.pucp.softrh.rmi.interfaces.DireccionBO;
import pe.edu.pucp.softrh.rmi.servidor.SoftRhRMI_Servidor;
import pe.edu.pucp.softrh.usuarios.model.Cliente;
import pe.edu.pucp.softrh.usuarios.model.Direccion;

@WebService(serviceName = "DireccionWS", targetNamespace = "softrh.services")
public class DireccionWS {

    private static final String ARCHIVO_CONFIGURACION = "datosConexion.txt";
    private static String IP;
    private static Integer puerto;

    private DireccionBO direccionBO;

    public DireccionWS() {
		SoftRhRMI_Servidor.leerArchivoYCrearCadena();
        String nombreServicio = SoftRhRMI_Servidor.retornarNombreDelServicio("direccionBO");
        try {
            this.direccionBO = (DireccionBO) Naming.lookup(nombreServicio);
        } catch (NotBoundException | MalformedURLException | RemoteException ex) {
            Logger.getLogger(DireccionWS.class.getName()).log(Level.SEVERE, null, ex);
            this.direccionBO = null; // Inicializa como null en caso de error
        }
    }

    @WebMethod(operationName = "insertarDireccion")
    public Integer insertarDireccion(String direc, String distrito, String provincia, String departamento, String codigoPostal, String referencia, Cliente cliente) {
        Integer retorno = -1;
        try {
            retorno = direccionBO.insertar(direc, distrito, provincia, departamento, codigoPostal, referencia, cliente);
        } catch (RemoteException ex) {
            Logger.getLogger(DireccionWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    @WebMethod(operationName = "modificarDireccion")
    public Integer modificarDireccion(Integer idDireccion, String direc, String distrito, String provincia, String departamento, String codigoPostal, String referencia, Cliente cliente) {
        Integer retorno = -1;
        try {
            retorno = direccionBO.modificar(idDireccion, direc, distrito, provincia, departamento, codigoPostal, referencia, cliente);
        } catch (RemoteException ex) {
            Logger.getLogger(DireccionWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    @WebMethod(operationName = "eliminarDireccion")
    public Integer eliminarDireccion(Integer idDireccion) {
        Integer retorno = -1;
        try {
            retorno = direccionBO.eliminar(idDireccion);
        } catch (RemoteException ex) {
            Logger.getLogger(DireccionWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    @WebMethod(operationName = "listarDireccionesTodas")
    public ArrayList<Direccion> listarDireccionesTodas() {
        ArrayList<Direccion> retorno = new ArrayList<>();
        try {
            retorno = direccionBO.listarTodos();
        } catch (RemoteException ex) {
            Logger.getLogger(DireccionWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    @WebMethod(operationName = "obtenerDireccionPorId")
    public Direccion obtenerDireccionPorId(Integer idCliente) {
        Direccion retorno = null;
        try {
            retorno = direccionBO.obtenerPorId(idCliente);
        } catch (RemoteException ex) {
            Logger.getLogger(DireccionWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    @WebMethod(operationName = "listarDireccionesPorIdCliente")
    public ArrayList<Direccion> listarDireccionesPorIdCliente(Integer idCliente) {
        ArrayList<Direccion> retorno = new ArrayList<>();
        try {
            retorno = direccionBO.listarPorIdCliente(idCliente);
        } catch (RemoteException ex) {
            Logger.getLogger(DireccionWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
}


