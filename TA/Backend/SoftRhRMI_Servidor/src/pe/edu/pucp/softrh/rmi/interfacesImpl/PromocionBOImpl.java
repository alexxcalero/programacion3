package pe.edu.pucp.softrh.rmi.interfacesImpl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Date;
import pe.edu.pucp.softrh.prendas.model.Prenda;
import pe.edu.pucp.softrh.prendas.model.Promocion;
import pe.edu.pucp.softrh.prendas.model.TipoPromocion;
import pe.edu.pucp.softrh.rmi.interfaces.PromocionBO;
import pe.edu.pucp.softrh.usuarios.model.Trabajador;

public class PromocionBOImpl extends UnicastRemoteObject implements PromocionBO{
    private pe.edu.pucp.softrh.prendas.bo.PromocionBO promocionBO;

    public PromocionBOImpl(Integer puerto) throws RemoteException{
        super(puerto);
        this.promocionBO = new pe.edu.pucp.softrh.prendas.bo.PromocionBO();
    }

    @Override
    public Integer insertar(String nombre, String descripcion, Double valorDescuento, TipoPromocion tipo, Date fechaInicio, Date fechaFin, ArrayList<Prenda> prendasSeleccionadas, Trabajador trabajador) throws RemoteException {
        return this.promocionBO.insertar(nombre, descripcion, valorDescuento, tipo, fechaInicio, fechaFin, prendasSeleccionadas, trabajador);
    }

    @Override
    public Integer modificar(Integer idPromocion, String nombre, String descripcion, Double valorDescuento, TipoPromocion tipo, Date fechaInicio, Date fechaFin, ArrayList<Prenda> prendasSeleccionadas, Trabajador trabajador) throws RemoteException {
        return this.promocionBO.modificar(idPromocion, nombre, descripcion, valorDescuento, tipo, fechaInicio, fechaFin, prendasSeleccionadas, trabajador);
    }

    @Override
    public Integer eliminar(Integer idPromocion) throws RemoteException {
        return this.promocionBO.eliminar(idPromocion);
    }

    @Override
    public ArrayList<Promocion> listarTodos() throws RemoteException {
        return this.promocionBO.listarTodos();
    }

    @Override
    public Promocion obtenerPorId(Integer idPromocion) throws RemoteException {
        return this.promocionBO.obtenerPorId(idPromocion);
    }

    @Override
    public ArrayList<Promocion> listarPorNombreDescripcion(String cadena) throws RemoteException {
        return this.promocionBO.listarPorNombreDescripcion(cadena);
    }

    @Override
    public ArrayList<Prenda> listarPrendasAgregadas(int idPromocion) throws RemoteException {
        return this.promocionBO.listarPrendasAgregadas(idPromocion);
    }

}
