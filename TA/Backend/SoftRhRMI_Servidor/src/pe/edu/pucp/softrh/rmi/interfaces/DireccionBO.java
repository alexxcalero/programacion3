package pe.edu.pucp.softrh.rmi.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import pe.edu.pucp.softrh.usuarios.model.Cliente;
import pe.edu.pucp.softrh.usuarios.model.Direccion;

public interface DireccionBO extends Remote{
    Integer insertar(String direc, String distrito, String provincia, String departamente, String codigoPostal, String referencia, Cliente cliente) throws RemoteException;

    Integer modificar(Integer idDireccion, String direc, String distrito, String provincia, String departamente, String codigoPostal, String referencia, Cliente cliente) throws RemoteException;

    Integer eliminar(Integer idDireccion) throws RemoteException;

    ArrayList<Direccion> listarTodos() throws RemoteException;

    Direccion obtenerPorId(Integer idDireccion) throws RemoteException;

    ArrayList<Direccion> listarPorIdCliente(int idCliente) throws RemoteException;
}
