package pe.edu.pucp.softrh.compras.bo;

import pe.edu.pucp.softrh.compras.dao.CarritoDAO;
import pe.edu.pucp.softrh.compras.daoimp.CarritoDAOImp;
import pe.edu.pucp.softrh.compras.model.Carrito;

public class CarritoBO {
    private CarritoDAO carritoDAO;

    public CarritoBO() {
        this.carritoDAO = new CarritoDAOImp();
    }

    public Integer insertar(Integer idCliente) {
        return 1;
    }

    public Integer modificar(Carrito carrito) {
        return this.carritoDAO.modificar(carrito);
    }

    public Carrito obtenerPorId(Integer idCliente) {
        return this.carritoDAO.obtenerPorIdCliente(idCliente);
    }

    public int limpiar_carrito(Integer fidCliente) {
        return this.carritoDAO.limpia_carrito(fidCliente);
    }
}
