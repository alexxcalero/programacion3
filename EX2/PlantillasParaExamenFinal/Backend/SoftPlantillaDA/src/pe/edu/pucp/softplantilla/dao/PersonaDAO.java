package pe.edu.pucp.softplantilla.dao;

import java.util.ArrayList;
import pe.edu.pucp.softplantilla.model.Persona;

public interface PersonaDAO {
	Integer insertar(Persona persona);

	Integer modificar(Persona persona);

	Integer eliminar(Integer idPersona);

	ArrayList<Persona> listarTodas();

	Persona obtenerPorId(Integer idPersona);
}
