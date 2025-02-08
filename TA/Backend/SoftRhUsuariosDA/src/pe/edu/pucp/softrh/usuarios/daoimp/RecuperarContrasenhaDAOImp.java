package pe.edu.pucp.softrh.usuarios.daoimp;

import java.sql.ResultSet;
import java.sql.SQLException;
import pe.edu.pucp.softrh.database.config.DBManager;
import pe.edu.pucp.softrh.usuarios.dao.RecuperarContrasenhaDAO;
import pe.edu.pucp.softrh.usuarios.model.RecuperarContrasenha;

public class RecuperarContrasenhaDAOImp implements RecuperarContrasenhaDAO {

	private ResultSet rs;
    private DBManager dbManager = DBManager.obtenerInstancia();

	@Override
	public int guardarToken(RecuperarContrasenha recuperacion) {
		int resultado = 0;

		Object[] parameters = new Object[4];
        parameters[0] = recuperacion.getIdToken();
        parameters[1] = recuperacion.getIdUsuario();
        parameters[2] = recuperacion.getToken();
        parameters[3] = recuperacion.getFechaExpiracion();

        recuperacion.setIdToken(dbManager.EjecutarProcedimiento("INSERTAR_TOKEN_RECUPERACION", parameters, true));
        resultado = recuperacion.getIdToken();
        return resultado;
	}

	@Override
    public int obtenerIdUsuarioPorToken(String token){
        Integer resultado = 0;
        Object[] parameters = new Object[1];
        parameters[0] = token;

        rs = dbManager.EjecutarProcedimientoLectura("OBTENER_ID_USUARIO_POR_TOKEN", parameters);

        try {
            if(rs.next()) {
                resultado = rs.getInt("idUsuario");
            }
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(RecuperarContrasenhaDAOImp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        finally{
            try {
                dbManager.cerrarConexion();
            } catch (SQLException ex) {
                java.util.logging.Logger.getLogger(RecuperarContrasenhaDAOImp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
        }

        return resultado;
	}
}
