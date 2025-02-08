package pe.edu.pucp.softrh.usuarios.services;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import pe.edu.pucp.softrh.rmi.interfaces.UsuarioBO;
import pe.edu.pucp.softrh.rmi.servidor.SoftRhRMI_Servidor;

@WebService(serviceName = "UsuarioWS", targetNamespace = "softrh.services")
public class UsuarioWS {

    private static final String ARCHIVO_CONFIGURACION = "datosConexion.txt";
    private static String IP;
    private static Integer puerto;

    private UsuarioBO usuarioBO;

    public UsuarioWS() {
		SoftRhRMI_Servidor.leerArchivoYCrearCadena();
        String nombreServicio = SoftRhRMI_Servidor.retornarNombreDelServicio("usuarioBO");
        try {
            this.usuarioBO = (UsuarioBO) Naming.lookup(nombreServicio);
        } catch (NotBoundException | MalformedURLException | RemoteException ex) {
            Logger.getLogger(UsuarioWS.class.getName()).log(Level.SEVERE, null, ex);
            this.usuarioBO = null; // Inicializa como null en caso de error
        }
    }

    @WebMethod(operationName = "modificarUsuario")
    public Integer modificarUsuario(Integer idUsuario, String nombres, String apellidos) {
        Integer retorno = -1;
        try {
            retorno = usuarioBO.modificar(idUsuario, nombres, apellidos);
        } catch (RemoteException ex) {
            Logger.getLogger(UsuarioWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    @WebMethod(operationName = "verificarIngresoUsuario")
    public Integer verificarIngresoUsuario(String correo, String contrasenha) {
        Integer retorno = -1;
        try {
            retorno = usuarioBO.verificarIngresoUsuario(correo, contrasenha);
        } catch (RemoteException ex) {
            Logger.getLogger(UsuarioWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    @WebMethod(operationName = "obtenerRolUsuario")
    public String obtenerRolUsuario(String correo, String contrasenha) {
        String retorno = null;
        try {
            retorno = usuarioBO.obtenerRolUsuario(correo, contrasenha);
        } catch (RemoteException ex) {
            Logger.getLogger(UsuarioWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    @WebMethod(operationName = "verificarContrasenha")
    public Integer verificarContrasenha(Integer idUsuario, String contrasenha) {
        Integer retorno = -1;
        try {
            retorno = usuarioBO.verificarContrasenha(idUsuario, contrasenha);
        } catch (RemoteException ex) {
            Logger.getLogger(UsuarioWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    @WebMethod(operationName = "cambiarContrasenha")
    public Integer cambiarContrasenha(Integer idUsuario, String contrasenhaNueva) {
        Integer retorno = -1;
        try {
            retorno = usuarioBO.cambiarContrasenha(idUsuario, contrasenhaNueva);
        } catch (RemoteException ex) {
            Logger.getLogger(UsuarioWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    @WebMethod(operationName = "resetearContrasenha")
    public Integer resetearContrasenha(Integer idUsuario) {
        Integer retorno = -1;
        try {
            retorno = usuarioBO.resetearContrasenha(idUsuario);
        } catch (RemoteException ex) {
            Logger.getLogger(UsuarioWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    @WebMethod(operationName = "obtenerIdPorCorreo")
    public Integer obtenerIdPorCorreo(String correo) {
        Integer retorno = -1;
        try {
            retorno = usuarioBO.obtenerIdPorCorreo(correo);
        } catch (RemoteException ex) {
            Logger.getLogger(UsuarioWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
}

