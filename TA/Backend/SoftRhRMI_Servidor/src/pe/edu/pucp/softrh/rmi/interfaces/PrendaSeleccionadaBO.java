package pe.edu.pucp.softrh.rmi.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import pe.edu.pucp.softrh.compras.model.PrendaSeleccionada;

public interface PrendaSeleccionadaBO extends Remote{
    Integer insertar(Integer idPrenda, Integer fidCarrito, Integer cantidad, Double precio) throws RemoteException;

    Integer modificar(PrendaSeleccionada prenda) throws RemoteException;

    Integer eliminar(Integer idPrenda, Integer idCarrito) throws RemoteException;

    PrendaSeleccionada obtenerPorIdPrenda(Integer idPrenda, Integer idCarrito) throws RemoteException;

    ArrayList<PrendaSeleccionada> obtenerPorIdCarrito(Integer idCarrito) throws RemoteException;

    ArrayList<PrendaSeleccionada> obtenerPorIdOrden(Integer idOrden) throws RemoteException;

    Integer aplicarCuponLista(Integer idCarrito, Double descuento) throws RemoteException;
}
