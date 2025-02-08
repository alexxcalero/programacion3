package pe.edu.pucp.softrh.compras.dao;

import pe.edu.pucp.softrh.compras.model.OrdenCompra;
import java.util.ArrayList;

public interface OrdenCompraDAO {
    int insertar(OrdenCompra orden);

    public int modificar(OrdenCompra orden);

    int eliminar(int idOrden);

    ArrayList<OrdenCompra> listarTodos();

    OrdenCompra obtenerPorId(int idOrden);

    int insertarPrendaSeleccionada(int idPrenda, int fidOrden, int cantidad, double precio);

    ArrayList<OrdenCompra> listarPorEstado(String cadena);

    int cambiarEstado(int idOrden, String cadena);

    public ArrayList<OrdenCompra> obtenerPorIdCliente(int idOrden);
}
