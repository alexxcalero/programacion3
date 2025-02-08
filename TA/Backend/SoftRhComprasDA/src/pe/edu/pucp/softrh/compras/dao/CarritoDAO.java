package pe.edu.pucp.softrh.compras.dao;

import pe.edu.pucp.softrh.compras.model.Carrito;

public interface CarritoDAO {
    int insertar(int idCliente);

    int modificar(Carrito carrito);

    Carrito obtenerPorIdCliente(int fidCliente);

    int limpia_carrito(int fidCliente);
}
