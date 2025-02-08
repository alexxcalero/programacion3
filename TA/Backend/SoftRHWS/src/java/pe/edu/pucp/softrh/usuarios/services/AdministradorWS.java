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
import pe.edu.pucp.softrh.rmi.interfaces.AdministradorBO;
import pe.edu.pucp.softrh.rmi.servidor.SoftRhRMI_Servidor;
import pe.edu.pucp.softrh.usuarios.model.Administrador;

@WebService(serviceName = "AdministradorWS", targetNamespace = "softrh.services")
public class AdministradorWS {

    private static final String ARCHIVO_CONFIGURACION = "datosConexion.txt";
    private static String IP;
    private static Integer puerto;

    private AdministradorBO administradorBO;

    public AdministradorWS() {
		SoftRhRMI_Servidor.leerArchivoYCrearCadena();
        String nombreServicio = SoftRhRMI_Servidor.retornarNombreDelServicio("administradorBO");
        try {
            this.administradorBO = (AdministradorBO) Naming.lookup(nombreServicio);
        } catch (NotBoundException | MalformedURLException | RemoteException ex) {
            Logger.getLogger(AdministradorWS.class.getName()).log(Level.SEVERE, null, ex);
            this.administradorBO = null; // Inicializa como null en caso de error
        }
    }

    @WebMethod(operationName = "insertarAdministrador")
    public Integer insertarAdministrador(String dni, String nombres, String apellidos, String correo, String contrasenha, Date fechaCreacion) {
        Integer retorno = -1;
        try {
            retorno = administradorBO.insertar(dni, nombres, apellidos, correo, contrasenha, fechaCreacion);
        } catch (RemoteException ex) {
            Logger.getLogger(AdministradorWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    @WebMethod(operationName = "modificarAdministrador")
    public Integer modificarAdministrador(Integer idUsuario, String dni, String nombres, String apellidos, String correo, String contrasenha, Date fechaCreacion) {
        Integer retorno = -1;
        try {
            retorno = administradorBO.modificar(idUsuario, dni, nombres, apellidos, correo, contrasenha, fechaCreacion);
        } catch (RemoteException ex) {
            Logger.getLogger(AdministradorWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    @WebMethod(operationName = "eliminarAdministrador")
    public Integer eliminarAdministrador(Integer idAdministrador) {
        Integer retorno = -1;
        try {
            retorno = administradorBO.eliminar(idAdministrador);
        } catch (RemoteException ex) {
            Logger.getLogger(AdministradorWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    @WebMethod(operationName = "listarAdministradoresTodos")
    public ArrayList<Administrador> listarAdministradoresTodos() {
        ArrayList<Administrador> retorno = new ArrayList<>();
        try {
            retorno = administradorBO.listarTodos();
        } catch (RemoteException ex) {
            Logger.getLogger(AdministradorWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    @WebMethod(operationName = "obtenerAdministradorPorId")
    public Administrador obtenerAdministradorPorId(Integer idAdministrador) {
        Administrador retorno = null;
        try {
            retorno = administradorBO.obtenerPorId(idAdministrador);
        } catch (RemoteException ex) {
            Logger.getLogger(AdministradorWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    @WebMethod(operationName = "listarAdministradoresPorDniNombre")
    public ArrayList<Administrador> listarAdministradoresPorDniNombre(String cadena) {
        ArrayList<Administrador> retorno = new ArrayList<>();
        try {
            retorno = administradorBO.listarPorDniNombre(cadena);
        } catch (RemoteException ex) {
            Logger.getLogger(AdministradorWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
}

