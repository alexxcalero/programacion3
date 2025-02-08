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
import pe.edu.pucp.softrh.rmi.interfaces.ClienteBO;
import pe.edu.pucp.softrh.rmi.servidor.SoftRhRMI_Servidor;
import pe.edu.pucp.softrh.usuarios.model.Cliente;

@WebService(serviceName = "ClienteWS", targetNamespace = "softrh.services")
public class ClienteWS {

    private static final String ARCHIVO_CONFIGURACION = "datosConexion.txt";
    private static String IP;
    private static Integer puerto;

    private ClienteBO clienteBO;

    public ClienteWS() {
		SoftRhRMI_Servidor.leerArchivoYCrearCadena();
        String nombreServicio = SoftRhRMI_Servidor.retornarNombreDelServicio("clienteBO");
        try {
            this.clienteBO = (ClienteBO) Naming.lookup(nombreServicio);
        } catch (NotBoundException | MalformedURLException | RemoteException ex) {
            Logger.getLogger(ClienteWS.class.getName()).log(Level.SEVERE, null, ex);
            this.clienteBO = null; // Inicializa como null en caso de error
        }
    }

    @WebMethod(operationName = "insertarCliente")
    public Integer insertarCliente(String dni, String nombres, String apellidos, String correo, String contrasenha, String telefono, Date fechaRegistro, Boolean recibePromociones) {
        Integer retorno = -1;
        try {
            retorno = clienteBO.insertar(dni, nombres, apellidos, correo, contrasenha, telefono, fechaRegistro, recibePromociones);
        } catch (RemoteException ex) {
            Logger.getLogger(ClienteWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    @WebMethod(operationName = "modificarCliente")
    public Integer modificarCliente(Integer idUsuario, String dni, String nombres, String apellidos, String correo, String telefono, Date fechaRegistro, Boolean recibePromociones) {
        Integer retorno = -1;
        try {
            retorno = clienteBO.modificar(idUsuario, dni, nombres, apellidos, correo, telefono, fechaRegistro, recibePromociones);
        } catch (RemoteException ex) {
            Logger.getLogger(ClienteWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    @WebMethod(operationName = "eliminarCliente")
    public Integer eliminarCliente(Integer idCliente) {
        Integer retorno = -1;
        try {
            retorno = clienteBO.eliminar(idCliente);
        } catch (RemoteException ex) {
            Logger.getLogger(ClienteWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    @WebMethod(operationName = "listarClientesTodos")
    public ArrayList<Cliente> listarClientesTodos() {
        ArrayList<Cliente> retorno = new ArrayList<>();
        try {
            retorno = clienteBO.listarTodos();
        } catch (RemoteException ex) {
            Logger.getLogger(ClienteWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    @WebMethod(operationName = "obtenerClientePorId")
    public Cliente obtenerClientePorId(Integer idCliente) {
        Cliente retorno = null;
        try {
            retorno = clienteBO.obtenerPorId(idCliente);
        } catch (RemoteException ex) {
            Logger.getLogger(ClienteWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    @WebMethod(operationName = "listarClientesPorDniNombre")
    public ArrayList<Cliente> listarClientesPorDniNombre(String cadena) {
        ArrayList<Cliente> retorno = new ArrayList<>();
        try {
            retorno = clienteBO.listarPorDniNombre(cadena);
        } catch (RemoteException ex) {
            Logger.getLogger(ClienteWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    @WebMethod(operationName = "verificarCorreoExistente")
    public Integer verificarCorreoExistente(String correo) {
        Integer retorno = -1;
        try {
            retorno = clienteBO.verificarCorreoExistente(correo);
        } catch (RemoteException ex) {
            Logger.getLogger(ClienteWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
}
