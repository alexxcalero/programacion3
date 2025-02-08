package pe.edu.pucp.softrh.rmi.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import pe.edu.pucp.softrh.usuarios.model.Cliente;

public interface ClienteBO extends Remote{
    Integer insertar(String dni, String nombres, String apellidos, String correo, String contrasenha, String telefono, Date fechaRegistro, Boolean recibePromociones) throws RemoteException;

    Integer modificar(Integer idUsuario, String dni, String nombres, String apellidos, String correo, String telefono, Date fechaRegistro, Boolean recibePromociones) throws RemoteException;

    Integer eliminar(Integer idCliente) throws RemoteException;

    ArrayList<Cliente> listarTodos() throws RemoteException;

    Cliente obtenerPorId(Integer idCliente) throws RemoteException;

    ArrayList<Cliente> listarPorDniNombre(String cadena) throws RemoteException;

    Integer verificarCorreoExistente(String correo) throws RemoteException;
}
