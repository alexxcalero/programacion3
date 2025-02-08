package pe.edu.pucp.softrh.rmi.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import pe.edu.pucp.softrh.prendas.model.Prenda;
import pe.edu.pucp.softrh.prendas.model.Promocion;
import pe.edu.pucp.softrh.prendas.model.TipoPromocion;
import pe.edu.pucp.softrh.usuarios.model.Trabajador;

public interface PromocionBO extends Remote{
    Integer insertar(String nombre, String descripcion, Double valorDescuento, TipoPromocion tipo, Date fechaInicio, Date fechaFin, ArrayList<Prenda> prendasSeleccionadas, Trabajador trabajador) throws RemoteException;

    Integer modificar(Integer idPromocion, String nombre, String descripcion, Double valorDescuento, TipoPromocion tipo, Date fechaInicio, Date fechaFin, ArrayList<Prenda> prendasSeleccionadas, Trabajador trabajador) throws RemoteException;

    Integer eliminar(Integer idPromocion) throws RemoteException;

    ArrayList<Promocion> listarTodos() throws RemoteException;

    Promocion obtenerPorId(Integer idPromocion) throws RemoteException;

    ArrayList<Promocion> listarPorNombreDescripcion(String cadena) throws RemoteException;

    ArrayList<Prenda> listarPrendasAgregadas(int idPromocion) throws RemoteException;
}
