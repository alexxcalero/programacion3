package pe.edu.pucp.softrh.compras.dao;

import pe.edu.pucp.softrh.compras.model.PrendaSeleccionada;
import java.util.ArrayList;

public interface PrendaSeleccionadaDAO {
    int insertar(int idPrenda, int fidCarrito, int cantidad, double precio);

    int modificar(PrendaSeleccionada prenda);

    int eliminar(int idPrenda, int idCliente);

    ArrayList<PrendaSeleccionada> obtenerPorIdCarrito(int idCarrito, String procedimiento, String columns[]);

    PrendaSeleccionada obtenerPorIdPrenda(int idPrenda, int idCarrito);

    int aplicarCuponLista(int idPrenda, double descuento);
}
