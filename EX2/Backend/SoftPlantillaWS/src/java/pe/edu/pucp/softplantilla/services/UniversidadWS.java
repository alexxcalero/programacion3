package pe.edu.pucp.softplantilla.services;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import pe.edu.pucp.softplantilla.interfaces.UniversidadBO;
import pe.edu.pucp.softplantilla.model.Universidad;
import pe.edu.pucp.softplantilla.servidor.ServidorRMI;

@WebService(serviceName = "UniversidadWS", targetNamespace = "softplantilla.services")
public class UniversidadWS {
    private static final String ARCHIVO_CONFIGURACION = "datosConexionRMI.txt";
    private static String IP;
    private static Integer puerto;

    private UniversidadBO universidadBO;
    
    public UniversidadWS() {
	ServidorRMI.leerArchivoYCrearCadena();
        String nombreServicio = ServidorRMI.retornarNombreDelServicio("universidadBO");
        try {
            this.universidadBO = (UniversidadBO) Naming.lookup(nombreServicio);
        } catch (NotBoundException | MalformedURLException | RemoteException ex) {
            System.out.println(ex.getMessage());
            this.universidadBO = null;
        }
    }
    
    @WebMethod(operationName = "listarUniversidadesTodas")
    public ArrayList<Universidad> listarUniversidadesTodas() {
        ArrayList<Universidad> resultado = new ArrayList<>();

        try {
            resultado = universidadBO.listarTodas();
        } catch (RemoteException ex) {
            System.out.println(ex.getMessage());
        }

        return resultado;
    }
}
