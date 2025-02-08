package pe.edu.pucp.softrh.compras.daoimp;

import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.sql.ResultSet;
import java.sql.SQLException;
import pe.edu.pucp.softrh.compras.dao.CarritoDAO;
import pe.edu.pucp.softrh.compras.model.Carrito;
import pe.edu.pucp.softrh.database.config.DBManager;

public class CarritoDAOImp implements CarritoDAO {
    private ResultSet rs;
    private DBManager dbManager = DBManager.obtenerInstancia();

    @Override
    public int insertar(int idCliente){
        int resultado = 0;
        Integer idCarrito = null;
        Object[] parameters = new Object[4];
        parameters[0] = idCarrito;
        parameters[1] = idCliente;
        parameters[2] = 0;
        parameters[3] = 0;

        resultado = dbManager.EjecutarProcedimiento("INSERTAR_CARRITO", parameters, true);

        return resultado;
    }

    @Override
    public int modificar(Carrito carrito) {
        int resultado = 0;
        Object[] parameters = new Object[4];
        parameters[0] = carrito.getIdCarrito();
        parameters[1] = carrito.getfidCliente();
        parameters[2] = carrito.getCantidadTotal();
        parameters[3] = carrito.getPrecioTotal();

        resultado = dbManager.EjecutarProcedimiento("MODIFICAR_CARRITO",parameters, false);

        return resultado;
    }

    @Override
    public Carrito obtenerPorIdCliente(int idCliente) {
        Carrito carrito = new Carrito();
        Object[] parameters = new Object[1];
        parameters[0] = idCliente;
        rs = dbManager.EjecutarProcedimientoLectura("LISTAR_CARRITO_X_ID", parameters);

        try {
            if (rs != null){
                while (rs.next()) {
                    carrito.setIdCarrito(rs.getInt("idCarrito"));
                    carrito.setfidCliente(rs.getInt("fidCliente"));
                    carrito.setCantidadTotal(rs.getInt("cantidadTotal"));
                    carrito.setPrecioTotal(rs.getDouble("precioTotal"));
                }
            }
            else{
                carrito = null;
            }
        } catch (SQLException ex) {
        } finally {
            try {
                dbManager.cerrarConexion();
            } catch (SQLException ex) {
            }
        }

        return carrito;
    }

    @Override
    public int limpia_carrito(int fidCliente) {
        int resultado = 0;
        Object[] parameters = new Object[1];
        parameters[0] = fidCliente;

        resultado = dbManager.EjecutarProcedimiento("LIMPIAR_CARRITO", parameters, false);

        return resultado;
    }
}
