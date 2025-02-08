package pe.edu.pucp.softrh.rmi.interfacesImpl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import pe.edu.pucp.softrh.compras.model.PrendaSeleccionada;
import pe.edu.pucp.softrh.rmi.interfaces.PrendaSeleccionadaBO;

public class PrendaSeleccionadaBOImpl extends UnicastRemoteObject implements PrendaSeleccionadaBO{
    private pe.edu.pucp.softrh.compras.bo.PrendaSeleccionadaBO prendaSeleccionadaBO;

    public PrendaSeleccionadaBOImpl(Integer puerto) throws RemoteException{
        super(puerto);
        this.prendaSeleccionadaBO = new pe.edu.pucp.softrh.compras.bo.PrendaSeleccionadaBO();
    }

    @Override
    public Integer insertar(Integer idPrenda, Integer fidCarrito, Integer cantidad, Double precio) throws RemoteException {
        return this.prendaSeleccionadaBO.insertar(idPrenda, fidCarrito, cantidad, precio);
    }

    @Override
    public Integer modificar(PrendaSeleccionada prenda) throws RemoteException {
        return this.prendaSeleccionadaBO.modificar(prenda);
    }

    @Override
    public Integer eliminar(Integer idPrenda, Integer idCarrito) throws RemoteException {
        return this.prendaSeleccionadaBO.eliminar(idPrenda, idCarrito);
    }

    @Override
    public PrendaSeleccionada obtenerPorIdPrenda(Integer idPrenda, Integer idCarrito) throws RemoteException {
        return this.prendaSeleccionadaBO.obtenerPorIdPrenda(idPrenda, idCarrito);
    }

    @Override
    public ArrayList<PrendaSeleccionada> obtenerPorIdCarrito(Integer idCarrito) throws RemoteException {
        return this.prendaSeleccionadaBO.obtenerPorIdCarrito(idCarrito);
    }

    @Override
    public ArrayList<PrendaSeleccionada> obtenerPorIdOrden(Integer idOrden) throws RemoteException {
        return this.prendaSeleccionadaBO.obtenerPorIdOrden(idOrden);
    }

    @Override
    public Integer aplicarCuponLista(Integer idCarrito, Double descuento) throws RemoteException {
        return this.prendaSeleccionadaBO.aplicarCuponLista(idCarrito, descuento);
    }

}
