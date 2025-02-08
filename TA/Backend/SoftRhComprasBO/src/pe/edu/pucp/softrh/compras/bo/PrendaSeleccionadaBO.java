package pe.edu.pucp.softrh.compras.bo;

import java.util.ArrayList;
import pe.edu.pucp.softrh.compras.dao.PrendaSeleccionadaDAO;
import pe.edu.pucp.softrh.compras.daoimp.PrendaSeleccionadaDAOImp;
import pe.edu.pucp.softrh.compras.model.PrendaSeleccionada;

public class PrendaSeleccionadaBO {
	private PrendaSeleccionadaDAO prendaSeleccionadaDAO;

    public PrendaSeleccionadaBO() {
        this.prendaSeleccionadaDAO = new PrendaSeleccionadaDAOImp();
    }

    public Integer insertar(Integer idPrenda, Integer fidCarrito, Integer cantidad, Double precio)  {
        return this.prendaSeleccionadaDAO.insertar(idPrenda, fidCarrito, cantidad, precio);
    }

    public Integer modificar(PrendaSeleccionada prenda) {
        return this.prendaSeleccionadaDAO.modificar(prenda);
    }

    public Integer eliminar(Integer idPrenda, Integer idCarrito) {
        return this.prendaSeleccionadaDAO.eliminar(idPrenda, idCarrito);
    }

    public PrendaSeleccionada obtenerPorIdPrenda(Integer idPrenda, Integer idCarrito){
        return this.prendaSeleccionadaDAO.obtenerPorIdPrenda(idPrenda, idCarrito);
    }

    public ArrayList<PrendaSeleccionada> obtenerPorIdCarrito(Integer idCarrito) {
        String columns[]={"idPrendaSeleccionada","fidCarrito", "cantidad", "precio"};
        return this.prendaSeleccionadaDAO.obtenerPorIdCarrito(idCarrito, "LISTAR_PRENDASELECCIONADA_X_ID", columns);
    }

    public ArrayList<PrendaSeleccionada> obtenerPorIdOrden(Integer idOrden) {
        String columns[]={"idPrendaSeleccionada","fidOrden", "cantidad", "subtotal"};
        return this.prendaSeleccionadaDAO.obtenerPorIdCarrito(idOrden, "LISTAR_PRENDAS_X_ORDEN", columns);
    }

    public Integer aplicarCuponLista(Integer idCarrito, Double descuento) {
        return this.prendaSeleccionadaDAO.aplicarCuponLista(idCarrito, descuento);
    }
}
