package pe.edu.pucp.softrh.compras.daoimp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import pe.edu.pucp.softrh.compras.model.Totales;
import pe.edu.pucp.softrh.compras.dao.TotalesDAO;
import pe.edu.pucp.softrh.database.config.DBManager;

public class TotalesDAOImp implements TotalesDAO{
    private ResultSet rs;
    private DBManager dbManager = DBManager.obtenerInstancia();

	@Override
    public Totales obtenerValoresTotales(){
        Totales total = new Totales();
        rs = dbManager.EjecutarProcedimientoLectura("OBTENER_VALORES_ACTUALES",null);

        try {
            while (rs.next()){
                total.setIdTotal(rs.getInt("idTotal"));
                total.setTotalPrendas(rs.getInt("totalPrendas"));
                total.setPromocionesActivas(rs.getInt("promocionesActivas"));
                total.setCuponesActivos(rs.getInt("cuponesActivos"));
                total.setClientesActivos(rs.getInt("clientesActivos"));
                total.setFechaRegistro(rs.getDate("fechaRegistro"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(TotalesDAOImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                dbManager.cerrarConexion();
            } catch (SQLException ex) {
                Logger.getLogger(TotalesDAOImp.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return total;
    }
    
	@Override
    public Totales obtenerValoresTotalesPorMes(int anho, int mes){
        Totales total = new Totales();
        Object[] parameters = new Object[2];
        parameters[0] = anho;
        parameters[1] = mes;

        rs = dbManager.EjecutarProcedimientoLectura("OBTENER_VALORES_POR_MES", parameters);

        try {
            while (rs.next()){
                total.setIdTotal(rs.getInt("idTotal"));
                total.setTotalPrendas(rs.getInt("totalPrendas"));
                total.setPromocionesActivas(rs.getInt("promocionesActivas"));
                total.setCuponesActivos(rs.getInt("cuponesActivos"));
                total.setClientesActivos(rs.getInt("clientesActivos"));
                total.setFechaRegistro(rs.getDate("fechaRegistro"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(TotalesDAOImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                dbManager.cerrarConexion();
            } catch (SQLException ex) {
                Logger.getLogger(TotalesDAOImp.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return total;
    }
}
