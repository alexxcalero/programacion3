package pe.edu.pucp.sazonware.mysql;

import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import pe.edu.pucp.sazonware.config.DBManager;
import pe.edu.pucp.sazonware.dao.IngredienteDAO;
import pe.edu.pucp.sazonware.model.Ingrediente;

public class IngredienteMySQL extends UnicastRemoteObject implements IngredienteDAO {
    
    private Connection con;
    private CallableStatement cs;
    private ResultSet rs;
    
    public IngredienteMySQL(int puerto) throws RemoteException{
        super(puerto);
    }

    @Override
    public ArrayList<Ingrediente> listarTodos() throws RemoteException {
        ArrayList<Ingrediente> ingredientes = new ArrayList<>();
        try{
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call LISTAR_INGREDIENTES_TODOS()}");
            rs = cs.executeQuery();
            while(rs.next()){
                Ingrediente ingrediente = new Ingrediente();
                ingrediente.setIdIngrediente(rs.getInt("id_ingrediente"));
                ingrediente.setNombre(rs.getString("nombre"));
                ingrediente.setDescripcion(rs.getString("descripcion"));
                ingrediente.setActivo(true);
                ingredientes.add(ingrediente);
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return ingredientes;
    }
}
