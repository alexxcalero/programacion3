package pe.edu.pucp.softplantilla.test;

import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import pe.edu.pucp.softplantilla.bo.PersonaBO;
import pe.edu.pucp.softplantilla.db.DBManager;
import pe.edu.pucp.softplantilla.model.Persona;
import pe.edu.pucp.softplantilla.model.Tipo;

public class Principal {
	public static void main(String[] args) throws ParseException {
		//DBManager dbManager = new DBManager();
        DBManager dbManager = DBManager.obtenerInstancia();
//        System.out.println(dbManager);
//        dbManager = DBManager.obtenerInstancia();
//        System.out.println(dbManager);
//        dbManager = DBManager.obtenerInstancia();
//        System.out.println(dbManager);

        Connection conexion = dbManager.obtenerConexion();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		PersonaBO personaBO = new PersonaBO();
//		personaBO.insertar("Alex", "Calero", 1.75, Tipo.Estudiante, sdf.parse("2002-10-24"));
//		personaBO.insertar("Rodrigo", "Roller", 1.75, Tipo.Estudiante, sdf.parse("2003-04-02"));
//		personaBO.insertar("Rodrigo", "Ramos", 1.72, Tipo.Estudiante, sdf.parse("2002-04-10"));
//		personaBO.insertar("Freddy", "Paz", 1.68, Tipo.Profesor, sdf.parse("1987-06-12"));

		ArrayList<Persona> personas = personaBO.listarTodas();
		for(Persona p : personas)
			System.out.println(p.toString());
	}
}
