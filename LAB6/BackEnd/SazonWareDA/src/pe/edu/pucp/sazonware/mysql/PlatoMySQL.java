package pe.edu.pucp.sazonware.mysql;

import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import pe.edu.pucp.sazonware.config.DBManager;
import pe.edu.pucp.sazonware.dao.PlatoDAO;
import pe.edu.pucp.sazonware.model.Ingrediente;
import pe.edu.pucp.sazonware.model.Plato;

public class PlatoMySQL extends UnicastRemoteObject implements PlatoDAO {
    
    private Connection con;
    private CallableStatement cs;
    private ResultSet rs;
    
    public PlatoMySQL(int puerto) throws RemoteException{
        super(puerto);
    }

    @Override
    public int insertar(Plato plato) throws RemoteException {
        int resultado = 0;
        try{
            con = DBManager.getInstance().getConnection();
            con.setAutoCommit(false);
            cs = con.prepareCall("{call INSERTAR_PLATO(?,?,?,?,?)}");
            cs.registerOutParameter("_id_plato", java.sql.Types.INTEGER);
            cs.setString("_nombre", plato.getNombre());
            cs.setDouble("_precio", plato.getPrecio());
            cs.setString("_categoria", String.valueOf(plato.getCategoria()));
            cs.setBytes("_foto", plato.getFoto());
            cs.executeUpdate();
            plato.setIdPlato(cs.getInt("_id_plato"));
            resultado = plato.getIdPlato();
            
            for(Ingrediente ingrediente : plato.getIngredientes()) {
                cs = con.prepareCall("{call INSERTAR_PLATO_INGREDIENTE(?,?,?)}");
                cs.registerOutParameter("_id_plato_ingrediente", java.sql.Types.INTEGER);
                cs.setInt("_fid_plato", plato.getIdPlato());
                cs.setInt("_fid_ingrediente", ingrediente.getIdIngrediente());
                cs.executeUpdate();
                resultado = cs.getInt("_id_plato_ingrediente");
            }
            
            con.commit();
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return resultado;
    }
}
