package pe.edu.pucp.softplantilla.bo;

import java.util.ArrayList;
import java.util.Date;
import pe.edu.pucp.softplantilla.dao.PersonaDAO;
import pe.edu.pucp.softplantilla.daoimp.PersonaDAOImp;
import pe.edu.pucp.softplantilla.model.Persona;
import pe.edu.pucp.softplantilla.model.Tipo;

public class PersonaBO {
	private PersonaDAO personaDAO;

	public PersonaBO() {
		personaDAO = new PersonaDAOImp();
	}

	public Integer insertar(String nombre, String apellido, Double altura, Tipo tipo, Date fechaNacimiento) {
		Persona persona = new Persona(nombre, apellido, altura, tipo, fechaNacimiento);
		return personaDAO.insertar(persona);
	}

	public Integer modificar(Integer idPersona, String nombre, String apellido, Double altura, Tipo tipo, Date fechaNacimiento) {
		Persona persona = new Persona(idPersona, nombre, apellido, altura, tipo, fechaNacimiento);
		return personaDAO.modificar(persona);
	}

	public Integer eliminar(Integer idPersona) {
		return personaDAO.eliminar(idPersona);
	}

	public ArrayList<Persona> listarTodas() {
		return personaDAO.listarTodas();
	}

	public Persona obtenerPorId(Integer idPersona) {
		return personaDAO.obtenerPorId(idPersona);
	}
}
