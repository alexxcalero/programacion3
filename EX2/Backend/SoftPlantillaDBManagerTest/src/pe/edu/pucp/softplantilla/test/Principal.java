package pe.edu.pucp.softplantilla.test;

import java.sql.Connection;
import java.text.ParseException;
import pe.edu.pucp.softplantilla.db.DBManager;

public class Principal {
    public static void main(String[] args) throws ParseException {
//        DBManager dbManager = new DBManager();
        DBManager dbManager = DBManager.obtenerInstancia();
        System.out.println(dbManager);
        dbManager = DBManager.obtenerInstancia();
        System.out.println(dbManager);
        dbManager = DBManager.obtenerInstancia();
        System.out.println(dbManager);

        Connection conexion = dbManager.obtenerConexion();
    }
}
