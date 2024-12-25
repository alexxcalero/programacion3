package pe.edu.pucp.eventmastersoft.mysql;
import java.util.ArrayList;
import pe.edu.pucp.eventmastersoft.dao.ProductoraDAO;
import pe.edu.pucp.eventmastersoft.model.Productora;
import pe.edu.pucp.eventmastersoft.config.DBManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductoraMySQL implements ProductoraDAO{

    private ResultSet rs;

    @Override
    public ArrayList<Productora> listarTodas() {
        ArrayList<Productora> productoras = new ArrayList<>();
		rs = DBManager.getInstance().ejecutarProcedimientoLectura("LISTAR_PRODUCTORAS_TODAS", null);
		try{
			while(rs.next()){
				Productora productora = new Productora();
				productora.setIdProductora(rs.getInt("id_productora"));
				productora.setNombre(rs.getString("nombre"));
				productora.setActiva(true);
				productoras.add(productora);
			}
		}catch(SQLException ex){
			System.out.println("Error leyendo datos: " + ex.getMessage());
		}
		return productoras;
    }
}
