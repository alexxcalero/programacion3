package pe.edu.pucp.softplantilla.services;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import pe.edu.pucp.softplantilla.interfaces.PersonaBO;
import pe.edu.pucp.softplantilla.model.Persona;
import pe.edu.pucp.softplantilla.model.Tipo;
import pe.edu.pucp.softplantilla.servidor.ServidorRMI;

@WebService(serviceName = "PersonaWS", targetNamespace = "softplantilla.services")
public class PersonaWS {
	private static final String ARCHIVO_CONFIGURACION = "datosConexionRMI.txt";
    private static String IP;
    private static Integer puerto;

    private PersonaBO personaBO;

	public PersonaWS() {
		ServidorRMI.leerArchivoYCrearCadena();
        String nombreServicio = ServidorRMI.retornarNombreDelServicio("personaBO");
        try {
            this.personaBO = (PersonaBO) Naming.lookup(nombreServicio);
        } catch (NotBoundException | MalformedURLException | RemoteException ex) {
            System.out.println(ex.getMessage());
            this.personaBO = null;
        }
    }

	@WebMethod(operationName = "insertarPersona")
	public Integer insertarPersona(String nombre, String apellido, Double altura, Tipo tipo, Date fechaNacimiento) {
		Integer resultado = -1;

		try {
            resultado = personaBO.insertar(nombre, apellido, altura, tipo, fechaNacimiento);
        } catch (RemoteException ex) {
            System.out.println(ex.getMessage());
        }

		return resultado;
	}

	@WebMethod(operationName = "modificarPersona")
	public Integer modificarPersona(Integer idPersona, String nombre, String apellido, Double altura, Tipo tipo, Date fechaNacimiento) {
		Integer resultado = -1;

		try {
            resultado = personaBO.modificar(idPersona, nombre, apellido, altura, tipo, fechaNacimiento);
        } catch (RemoteException ex) {
            System.out.println(ex.getMessage());
        }

		return resultado;
	}

	@WebMethod(operationName = "eliminarPersona")
	public Integer eliminarPersona(Integer idPersona) {
		Integer resultado = -1;

		try {
            resultado = personaBO.eliminar(idPersona);
        } catch (RemoteException ex) {
            System.out.println(ex.getMessage());
        }

		return resultado;
	}

	@WebMethod(operationName = "listarPersonasTodas")
	public ArrayList<Persona> listarPersonasTodas() {
		ArrayList<Persona> resultado = new ArrayList<>();

		try {
            resultado = personaBO.listarTodas();
        } catch (RemoteException ex) {
            System.out.println(ex.getMessage());
        }

		return resultado;
	}

	@WebMethod(operationName = "obtenerPersonaPorId")
	public Persona obtenerPersonaPorId(Integer idPersona) {
		Persona resultado = new Persona();

		try {
            resultado = personaBO.obtenerPorId(idPersona);
        } catch (RemoteException ex) {
            System.out.println(ex.getMessage());
        }

		return resultado;
	}
}
