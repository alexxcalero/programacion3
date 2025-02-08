package pe.edu.pucp.softrh.rmi.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import pe.edu.pucp.softrh.compras.model.Carrito;

public interface CarritoBO extends Remote{
    Integer insertar(Integer idCliente) throws RemoteException;

    Integer modificar(Carrito carrito) throws RemoteException;

    Carrito obtenerPorId(Integer idCliente) throws RemoteException;

    Integer limpiar_carrito(Integer fidCliente) throws RemoteException;
}
