package pe.edu.pucp.softrh.rmi.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import pe.edu.pucp.softrh.usuarios.model.Usuario;

public interface UsuarioBO extends Remote{
    Integer insertar(Usuario usuario) throws RemoteException;

    Integer modificar(Integer idUsuario, String nombres, String apellidos) throws RemoteException;

    Integer eliminar(Integer idUsuario) throws RemoteException;

    ArrayList<Usuario> listarTodos() throws RemoteException;

    Usuario obtenerPorId(Integer idUsuario) throws RemoteException;

    Integer verificarIngresoUsuario(String correo, String contrasenha) throws RemoteException;

    String obtenerRolUsuario(String correo, String contrasenha) throws RemoteException;

    Integer verificarContrasenha(Integer idUsuario, String contrasenha) throws RemoteException;

    Integer cambiarContrasenha(Integer idUsuario, String contrasenhaNueva) throws RemoteException;

    Integer resetearContrasenha(Integer idUsuario) throws RemoteException;

    Integer obtenerIdPorCorreo(String correo) throws RemoteException;
}
