package pe.edu.pucp.softrh.rmi.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import pe.edu.pucp.softrh.usuarios.model.Administrador;

public interface AdministradorBO extends Remote{

    Integer insertar(String dni, String nombres, String apellidos, String correo, String contrasenha, Date fechaCreacion) throws RemoteException;

    Integer modificar(Integer idUsuario, String dni, String nombres, String apellidos, String correo, String contrasenha, Date fechaCreacion) throws RemoteException;

    Integer eliminar(Integer idAdministrador) throws RemoteException;

    ArrayList<Administrador> listarTodos() throws RemoteException;

    Administrador obtenerPorId(Integer idAdministrador) throws RemoteException;

    ArrayList<Administrador> listarPorDniNombre(String cadena) throws RemoteException;

}
