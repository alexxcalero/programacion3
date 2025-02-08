package pe.edu.pucp.softplantilla.services;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import pe.edu.pucp.softplantilla.interfaces.PostulanteBO;
import pe.edu.pucp.softplantilla.model.Modalidad;
import pe.edu.pucp.softplantilla.model.Universidad;
import pe.edu.pucp.softplantilla.servidor.ServidorRMI;

@WebService(serviceName = "PostulanteWS", targetNamespace = "softplantilla.services")
public class PostulanteWS {
    private static final String ARCHIVO_CONFIGURACION = "datosConexionRMI.txt";
    private static String IP;
    private static Integer puerto;

    private PostulanteBO postulanteBO;
    
    public PostulanteWS() {
	ServidorRMI.leerArchivoYCrearCadena();
        String nombreServicio = ServidorRMI.retornarNombreDelServicio("postulanteBO");
        try {
            this.postulanteBO = (PostulanteBO) Naming.lookup(nombreServicio);
        } catch (NotBoundException | MalformedURLException | RemoteException ex) {
            System.out.println(ex.getMessage());
            this.postulanteBO = null;
        }
    }
    
    @WebMethod(operationName = "insertarPostulante")
    public Integer insertarPostulante(Universidad universidad, String dni, String nombre, String apellidoPaterno, Double promedioAcumuladoPregrado, Boolean fueTercioSuperior, Boolean fueMiembroGrupoInvestigacion, Boolean fueDeportistaCalificado, Modalidad modalidadPreferida) {
        Integer resultado = -1;

        try {
            resultado = postulanteBO.insertar(universidad, dni, nombre, apellidoPaterno, promedioAcumuladoPregrado, fueTercioSuperior, fueMiembroGrupoInvestigacion, fueDeportistaCalificado, modalidadPreferida);
        } catch (RemoteException ex) {
            System.out.println(ex.getMessage());
        }

        return resultado;
    }
}
