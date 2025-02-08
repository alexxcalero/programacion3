package pe.edu.pucp.softplantilla.daoimp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import pe.edu.pucp.softplantilla.dao.UniversidadDAO;
import pe.edu.pucp.softplantilla.db.DBManager;
import pe.edu.pucp.softplantilla.model.Universidad;

public class UniversidadDAOImp implements UniversidadDAO {
    private ResultSet rs;
    private DBManager dbManager = DBManager.obtenerInstancia();
    
    @Override
    public ArrayList<Universidad> listarTodas() {
        ArrayList<Universidad> universidades = new ArrayList<>();

	rs = dbManager.EjecutarProcedimientoLectura("LISTAR_UNIVERSIDADES_TODAS", null);

	try {
            while(rs.next()) {
		Universidad universidad = new Universidad();

		universidad.setIdUniversidad(rs.getInt("id_universidad"));
                universidad.setNombre(rs.getString("nombre"));
                universidad.setSiglas(rs.getString("siglas"));
                universidad.setActiva(true);

                universidades.add(universidad);
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

	return universidades;
    }
}
