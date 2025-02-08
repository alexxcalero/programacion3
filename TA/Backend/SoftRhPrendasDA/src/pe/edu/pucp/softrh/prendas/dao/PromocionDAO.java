package pe.edu.pucp.softrh.prendas.dao;

import java.util.ArrayList;
import pe.edu.pucp.softrh.prendas.model.Prenda;
import pe.edu.pucp.softrh.prendas.model.Promocion;

public interface PromocionDAO {
    int insertar(Promocion promocion);

    int modificar(Promocion promocionModificada);

    int eliminar(int idPromocion);

    ArrayList<Promocion> listarTodos();

    Promocion obtenerPorId(int idPromocion);

	ArrayList<Promocion> listarPorNombreDescripcion(String cadena);

	ArrayList<Prenda> listarPrendasAgregadas(int idPromocion);
}
