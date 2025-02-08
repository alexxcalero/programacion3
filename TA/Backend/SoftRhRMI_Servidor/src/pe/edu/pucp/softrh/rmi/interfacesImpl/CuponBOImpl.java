package pe.edu.pucp.softrh.rmi.interfacesImpl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Date;
import pe.edu.pucp.softrh.rmi.interfaces.CuponBO;
import pe.edu.pucp.softrh.usuarios.model.Cupon;
import pe.edu.pucp.softrh.usuarios.model.Trabajador;

public class CuponBOImpl extends UnicastRemoteObject implements CuponBO{
    private pe.edu.pucp.softrh.usuarios.bo.CuponBO cuponBO;

    public CuponBOImpl(Integer puerto) throws RemoteException{
        super(puerto);
        this.cuponBO = new pe.edu.pucp.softrh.usuarios.bo.CuponBO();
    }

    @Override
    public Integer insertar(String codigo, String descripcion, Double valorDescuento, Date fechaInicio, Date fechaFin, Trabajador trabajador) throws RemoteException {
        return this.cuponBO.insertar(codigo, descripcion, valorDescuento, fechaInicio, fechaFin, trabajador);
    }

    @Override
    public Integer modificar(Integer idCupon, String codigo, String descripcion, Double valorDescuento, Date fechaInicio, Date fechaFin, Trabajador trabajador) throws RemoteException {
        return this.cuponBO.modificar(idCupon, codigo, descripcion, valorDescuento, fechaInicio, fechaFin, trabajador);
    }

    @Override
    public Integer eliminar(Integer idCupon) throws RemoteException {
        return this.cuponBO.eliminar(idCupon);
    }

    @Override
    public ArrayList<Cupon> listarTodos() throws RemoteException {
        return this.cuponBO.listarTodos();
    }

    @Override
    public Cupon obtenerPorId(Integer idCupon) throws RemoteException {
        return this.cuponBO.obtenerPorId(idCupon);
    }

    @Override
    public ArrayList<Cupon> listarPorCodigoDescripcion(String cadena) throws RemoteException {
        return this.cuponBO.listarPorCodigoDescripcion(cadena);
    }

    @Override
    public Integer insertarCuponUsado(Integer idCliente, Integer idCupon, Date fecha) throws RemoteException {
        return this.cuponBO.insertarCuponUsado(idCliente, idCupon, fecha);
    }

}
