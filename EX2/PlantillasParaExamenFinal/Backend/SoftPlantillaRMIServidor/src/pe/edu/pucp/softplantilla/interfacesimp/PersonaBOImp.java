package pe.edu.pucp.softplantilla.interfacesimp;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Date;
import pe.edu.pucp.softplantilla.interfaces.PersonaBO;
import pe.edu.pucp.softplantilla.model.Persona;
import pe.edu.pucp.softplantilla.model.Tipo;

public class PersonaBOImp extends UnicastRemoteObject implements PersonaBO {
	private pe.edu.pucp.softplantilla.bo.PersonaBO personaBO;

	public PersonaBOImp(Integer puerto) throws RemoteException {
		super(puerto);
		personaBO = new pe.edu.pucp.softplantilla.bo.PersonaBO();
	}

	@Override
	public Integer insertar(String nombre, String apellido, Double altura, Tipo tipo, Date fechaNacimiento) throws RemoteException {
		return personaBO.insertar(nombre, apellido, altura, tipo, fechaNacimiento);
	}

	@Override
	public Integer modificar(Integer idPersona, String nombre, String apellido, Double altura, Tipo tipo, Date fechaNacimiento) throws RemoteException {
		return personaBO.modificar(idPersona, nombre, apellido, altura, tipo, fechaNacimiento);
	}

	@Override
	public Integer eliminar(Integer idPersona) throws RemoteException {
		return personaBO.eliminar(idPersona);
	}

	@Override
	public ArrayList<Persona> listarTodas() throws RemoteException {
		return personaBO.listarTodas();
	}

	@Override
	public Persona obtenerPorId(Integer idPersona) throws RemoteException {
		return personaBO.obtenerPorId(idPersona);
	}
}
