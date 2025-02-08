package pe.edu.pucp.softrh.compras.daoimp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import pe.edu.pucp.softrh.compras.dao.PrendaSeleccionadaDAO;
import pe.edu.pucp.softrh.compras.model.PrendaSeleccionada;
import pe.edu.pucp.softrh.database.config.DBManager;

public class PrendaSeleccionadaDAOImp implements PrendaSeleccionadaDAO {
    private ResultSet rs;
    private DBManager dbManager = DBManager.obtenerInstancia();

    @Override
    public int insertar(int idPrenda, int fidCarrito, int cantidad, double precio) {
        int resultado = 0;
        Object[] parameters = new Object[4];
        parameters[0] = idPrenda;
        parameters[1] = fidCarrito;
        parameters[2] = cantidad;
        parameters[3] = precio;

        resultado = dbManager.EjecutarProcedimiento("INSERTAR_PRENDASELECCIONADA", parameters, false);

        return resultado;
    }

    @Override
    public int modificar(PrendaSeleccionada prenda) {
        int resultado = 0;
        Object[] parameters = new Object[4];
        parameters[0] = prenda.getIdPrendaSeleccionada();
        parameters[1] = prenda.getIdCarrito();
        parameters[2] = prenda.getCantidad();
        parameters[3] = prenda.getPrecio();

        resultado = dbManager.EjecutarProcedimiento("MODIFICAR_PRENDASELECCIONADA", parameters, false);

        return resultado;
    }

    @Override
    public int eliminar(int idPrenda, int idCarrito) {
        int resultado = 0;
        Object[] parameters = new Object[2];
        parameters[0] = idPrenda;
        parameters[1] = idCarrito;
        resultado = dbManager.EjecutarProcedimiento("ELIMINAR_PRENDASELECCIONADA", parameters, false);
        return resultado;
    }

    @Override
    public ArrayList<PrendaSeleccionada> obtenerPorIdCarrito(int idCarrito, String procedimiento, String columns[]) {
        ArrayList<PrendaSeleccionada> prendas = new ArrayList<>();

        Object[] parameters = new Object[1];

        parameters[0] = idCarrito;
        rs = dbManager.EjecutarProcedimientoLectura(procedimiento, parameters);
        try {
            if (rs != null){
                while (rs.next()) {
                    PrendaSeleccionada prendaseleccionada = new PrendaSeleccionada();

                    prendaseleccionada.setIdPrendaSeleccionada(rs.getInt(columns[0]));
                    if(procedimiento.compareTo("LISTAR_PRENDASELECCIONADA_X_ID") == 0){
                        prendaseleccionada.setIdCarrito(rs.getInt(columns[1]));
                    }
                    prendaseleccionada.setCantidad(rs.getInt(columns[2]));
                    prendaseleccionada.setPrecio(rs.getDouble(columns[3]));

                    prendas.add(prendaseleccionada);
                }
            }
        } catch (SQLException ex) {
            System.out.print(ex);
        } finally {
            try {
                dbManager.cerrarConexion();
            } catch (SQLException ex) {
                System.out.print(ex);
            }
        }

        return prendas;
    }

    @Override
    public PrendaSeleccionada obtenerPorIdPrenda(int idPrenda, int idCarrito) {
        PrendaSeleccionada prendaSeleccionada = new PrendaSeleccionada();

        Object[] parameters = new Object[2];
        parameters[0] = idPrenda;
        parameters[1] = idCarrito;

        rs = dbManager.EjecutarProcedimientoLectura("LISTAR_PRENDASELECCIONADA_X_ID_PRENDA", parameters);
        try {
            while (rs.next()) {
                prendaSeleccionada.setIdPrendaSeleccionada(rs.getInt("idPrendaSeleccionada"));
                prendaSeleccionada.setIdCarrito(rs.getInt("fidCarrito"));
                prendaSeleccionada.setCantidad(rs.getInt("cantidad"));
                prendaSeleccionada.setPrecio(rs.getDouble("precio"));
            }
        } catch (SQLException ex) {
            System.out.print(ex);
        } finally {
            try {
                dbManager.cerrarConexion();
            } catch (SQLException ex) {
                System.out.print(ex);
            }
        }

        return prendaSeleccionada;
    }

    @Override
    public int aplicarCuponLista(int idPrenda, double descuento){
        int resultado = 0;
        Object[] parameters = new Object[2];
        parameters[0] = idPrenda;
        parameters[1] = descuento;

        resultado = dbManager.EjecutarProcedimiento("APLICAR_DESCUENTO_LISTA", parameters, false);

        return resultado;
    }
}