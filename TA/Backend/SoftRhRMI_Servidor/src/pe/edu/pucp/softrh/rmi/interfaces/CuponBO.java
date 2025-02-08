package pe.edu.pucp.softrh.rmi.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import pe.edu.pucp.softrh.usuarios.model.Cupon;
import pe.edu.pucp.softrh.usuarios.model.Trabajador;

public interface CuponBO extends Remote{

    Integer insertar(String codigo, String descripcion, Double valorDescuento, Date fechaInicio, Date fechaFin, Trabajador trabajador) throws RemoteException;

    Integer modificar(Integer idCupon, String codigo, String descripcion, Double valorDescuento, Date fechaInicio, Date fechaFin, Trabajador trabajador) throws RemoteException;

    Integer eliminar(Integer idCupon) throws RemoteException;

    ArrayList<Cupon> listarTodos() throws RemoteException;

    Cupon obtenerPorId(Integer idCupon) throws RemoteException;

    ArrayList<Cupon> listarPorCodigoDescripcion(String cadena) throws RemoteException;

    Integer insertarCuponUsado(Integer idCliente, Integer idCupon, Date fecha) throws RemoteException;

}
