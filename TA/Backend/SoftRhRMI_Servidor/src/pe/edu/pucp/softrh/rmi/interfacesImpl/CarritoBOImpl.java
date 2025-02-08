package pe.edu.pucp.softrh.rmi.interfacesImpl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import pe.edu.pucp.softrh.compras.model.Carrito;
import pe.edu.pucp.softrh.rmi.interfaces.CarritoBO;

public class CarritoBOImpl extends UnicastRemoteObject implements CarritoBO{
    private pe.edu.pucp.softrh.compras.bo.CarritoBO carritoBO;

    public CarritoBOImpl(Integer puerto) throws RemoteException{
        super(puerto);
        this.carritoBO = new pe.edu.pucp.softrh.compras.bo.CarritoBO();
    }

    @Override
    public Integer insertar(Integer idCliente) throws RemoteException {
        return this.carritoBO.insertar(idCliente);
    }

    @Override
    public Integer modificar(Carrito carrito) throws RemoteException {
        return this.carritoBO.modificar(carrito);
    }

    @Override
    public Carrito obtenerPorId(Integer idCliente) throws RemoteException {
        return this.carritoBO.obtenerPorId(idCliente);
    }

    @Override
    public Integer limpiar_carrito(Integer fidCliente) throws RemoteException {
        return this.carritoBO.limpiar_carrito(fidCliente);
    }
}
