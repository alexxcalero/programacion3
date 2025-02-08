package pe.edu.pucp.softplantilla.daoimp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import pe.edu.pucp.softplantilla.dao.PersonaDAO;
import pe.edu.pucp.softplantilla.db.DBManager;
import pe.edu.pucp.softplantilla.model.Persona;
import pe.edu.pucp.softplantilla.model.Tipo;

public class PersonaDAOImp implements PersonaDAO {
	private ResultSet rs;
    private DBManager dbManager = DBManager.obtenerInstancia();

	@Override
	public Integer insertar(Persona persona) {
		Integer resultado = 0;

		Object[] parametros = new Object[6];
		parametros[0] = persona.getIdPersona();
		parametros[1] = persona.getNombre();
		parametros[2] = persona.getApellido();
		parametros[3] = persona.getAltura();
		parametros[4] = persona.getTipo().toString();  // A los enums se les pone "toString()"
		parametros[5] = persona.getFechaNacimiento();
		// parametros[6] = persona.getFoto();  // Por si tiene foto

		persona.setIdPersona(dbManager.EjecutarProcedimiento("INSERTAR_PERSONA", parametros, true));

		resultado = persona.getIdPersona();

		return resultado;
	}

	@Override
	public Integer modificar(Persona persona) {
		Integer resultado = 0;

		Object[] parametros = new Object[6];
		parametros[0] = persona.getIdPersona();
		parametros[1] = persona.getNombre();
		parametros[2] = persona.getApellido();
		parametros[3] = persona.getAltura();
		parametros[4] = persona.getTipo().toString();  // A los enums se les pone "toString()"
		parametros[5] = persona.getFechaNacimiento();
		// parametros[6] = persona.getFoto();  // Por si tiene foto

		resultado = dbManager.EjecutarProcedimiento("MODIFICAR_PERSONA", parametros, false);

		return resultado;
	}

	@Override
	public Integer eliminar(Integer idPersona) {
		Integer resultado = 0;

		Object[] parametros = new Object[1];
		parametros[0] = idPersona;

		resultado = dbManager.EjecutarProcedimiento("ELIMINAR_PERSONA", parametros, false);

		return resultado;
	}

	@Override
	public ArrayList<Persona> listarTodas() {
		ArrayList<Persona> personas = new ArrayList<>();

		rs = dbManager.EjecutarProcedimientoLectura("LISTAR_PERSONAS_TODAS", null);

		try {
			while(rs.next()) {
				Persona persona = new Persona();

				persona.setIdPersona(rs.getInt("idPersona"));
				persona.setNombre(rs.getString("nombre"));
				persona.setApellido(rs.getString("apellido"));
				persona.setAltura(rs.getDouble("altura"));
				persona.setTipo(Tipo.valueOf(rs.getString("tipo")));  // Cuando el atributo es enum, se coloca Enum.valueOf(rs.getString())
				persona.setFechaNacimiento(rs.getDate("fechaNacimiento"));
				// persona.setFoto(rs.getByte("foto"));  // Por si hay un atributo de foto
				persona.setActivo(true);

				personas.add(persona);
			}
		} catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        finally{
            try {
                dbManager.cerrarConexion();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }

		return personas;
	}

	@Override
	public Persona obtenerPorId(Integer idPersona) {
		Persona persona = new Persona();

		Object[] parametros = new Object[1];
		parametros[0] = idPersona;

		rs = dbManager.EjecutarProcedimientoLectura("OBTENER_PERSONA_X_ID", parametros);

		try {
			if(rs.next()) {
				persona.setIdPersona(rs.getInt("idPersona"));
				persona.setNombre(rs.getString("nombre"));
				persona.setApellido(rs.getString("apellido"));
				persona.setAltura(rs.getDouble("altura"));
				persona.setTipo(Tipo.valueOf(rs.getString("tipo")));  // Cuando el atributo es enum, se coloca Enum.valueOf(rs.getString())
				persona.setFechaNacimiento(rs.getDate("fechaNacimiento"));
				// persona.setFoto(rs.getByte("foto"));  // Por si hay un atributo de foto
				persona.setActivo(true);
			}
		} catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        finally{
            try {
                dbManager.cerrarConexion();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }

		return persona;
	}
}
