package pe.edu.pucp.softrh.usuarios.services;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.util.Date;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import pe.edu.pucp.softrh.rmi.interfaces.RecuperarContrasenhaBO;
import pe.edu.pucp.softrh.rmi.servidor.SoftRhRMI_Servidor;

@WebService(serviceName = "RecuperarContrasenhaWS", targetNamespace = "softrh.services")
public class RecuperarContrasenhaWS {

    private static final String ARCHIVO_CONFIGURACION = "datosConexion.txt";
    private static String IP;
    private static Integer puerto;

    private RecuperarContrasenhaBO recuperarContrasenhaBO;

    public RecuperarContrasenhaWS() {
		SoftRhRMI_Servidor.leerArchivoYCrearCadena();
        String nombreServicio = SoftRhRMI_Servidor.retornarNombreDelServicio("recuperarContrasenhaBO");
        try {
            this.recuperarContrasenhaBO = (RecuperarContrasenhaBO) Naming.lookup(nombreServicio);
        } catch (NotBoundException | MalformedURLException | RemoteException ex) {
            Logger.getLogger(RecuperarContrasenhaWS.class.getName()).log(Level.SEVERE, null, ex);
            this.recuperarContrasenhaBO = null; // Inicializa como null en caso de error
        }
    }

    @WebMethod(operationName = "guardarToken")
    public int guardarToken(Integer idUsuario, String token, Date fechaExpiracion) {
        int retorno = -1;
        try {
            retorno = recuperarContrasenhaBO.guardarToken(idUsuario, token, fechaExpiracion);
        } catch (RemoteException ex) {
            Logger.getLogger(RecuperarContrasenhaWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    @WebMethod(operationName = "obtenerIdUsuarioPorToken")
    public int obtenerIdUsuarioPorToken(String token) {
        int retorno = -1;
        try {
            retorno = recuperarContrasenhaBO.obtenerIdUsuarioPorToken(token);
        } catch (RemoteException ex) {
            Logger.getLogger(RecuperarContrasenhaWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
}

