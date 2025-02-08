package pe.edu.pucp.softplantilla.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import pe.edu.pucp.softplantilla.model.Persona;
import pe.edu.pucp.softplantilla.model.Tipo;

public interface PersonaBO extends Remote {
	Integer insertar(String nombre, String apellido, Double altura, Tipo tipo, Date fechaNacimiento) throws RemoteException;

	Integer modificar(Integer idPersona, String nombre, String apellido, Double altura, Tipo tipo, Date fechaNacimiento) throws RemoteException;

	Integer eliminar(Integer idPersona) throws RemoteException;

	ArrayList<Persona> listarTodas() throws RemoteException;

	Persona obtenerPorId(Integer idPersona) throws RemoteException;
}
