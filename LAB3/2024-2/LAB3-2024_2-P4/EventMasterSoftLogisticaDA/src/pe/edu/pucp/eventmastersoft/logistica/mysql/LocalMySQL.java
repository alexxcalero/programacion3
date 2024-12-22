package pe.edu.pucp.eventmastersoft.logistica.mysql;

import pe.edu.pucp.eventmastersoft.logistica.dao.LocalDAO;
import pe.edu.pucp.eventmastersoft.logistica.model.Local;
import pe.edu.pucp.eventmastersoft.config.DBManager;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class LocalMySQL implements LocalDAO{
    Connection con;
    PreparedStatement pst;
    String instruccion;
    
    @Override
    public int insertar(Local local) {
        int resultado = 0;
        try {
            con = DBManager.getInstance().getConnection();
            instruccion = "INSERT INTO local(nombre, direccion, capacidad, espacio_m2, tipo_local, activo) "
                          + "VALUES(?,?,?,?,?,1)";
            pst = con.prepareStatement(instruccion);
            pst.setString(1, local.getNombre());
            pst.setString(2, local.getDireccion());
            pst.setInt(3, local.getCapacidad());
            pst.setDouble(4, local.getEspacioMetrosCuadrados());
            pst.setString(5, ""+local.getTipoLocal());  // Casteamos el Enum a String, tambien se puede usar toString()
            resultado = pst.executeUpdate();
            con.close();
        } catch(Exception ex) {
            System.out.println(ex.getMessage());
        }
        return resultado;
    }
}
