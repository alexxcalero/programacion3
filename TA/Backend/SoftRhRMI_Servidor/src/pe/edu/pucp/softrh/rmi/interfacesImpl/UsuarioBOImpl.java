package pe.edu.pucp.softrh.rmi.interfacesImpl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import pe.edu.pucp.softrh.rmi.interfaces.UsuarioBO;
import pe.edu.pucp.softrh.usuarios.model.Usuario;

public class UsuarioBOImpl extends UnicastRemoteObject implements UsuarioBO{
    private pe.edu.pucp.softrh.usuarios.bo.UsuarioBO usuarioBO;

    public UsuarioBOImpl(Integer puerto) throws RemoteException{
        super(puerto);
        this.usuarioBO = new pe.edu.pucp.softrh.usuarios.bo.UsuarioBO();
    }

    @Override
    public Integer insertar(Usuario usuario) throws RemoteException {
        return this.usuarioBO.insertar(usuario);
    }

    @Override
    public Integer modificar(Integer idUsuario, String nombres, String apellidos) throws RemoteException {
        return this.usuarioBO.modificar(idUsuario, nombres, apellidos);
    }

    @Override
    public Integer eliminar(Integer idUsuario) throws RemoteException {
        return this.usuarioBO.eliminar(idUsuario);
    }

    @Override
    public ArrayList<Usuario> listarTodos() throws RemoteException {
        return this.usuarioBO.listarTodos();
    }

    @Override
    public Usuario obtenerPorId(Integer idUsuario) throws RemoteException {
        return this.usuarioBO.obtenerPorId(idUsuario);
    }

    @Override
    public Integer verificarIngresoUsuario(String correo, String contrasenha) throws RemoteException {
       return this.usuarioBO.verificarIngresoUsuario(correo, contrasenha);
    }

    @Override
    public String obtenerRolUsuario(String correo, String contrasenha) throws RemoteException {
        return this.usuarioBO.obtenerRolUsuario(correo, contrasenha);
    }

    @Override
    public Integer verificarContrasenha(Integer idUsuario, String contrasenha) throws RemoteException {
        return this.usuarioBO.verificarContrasenha(idUsuario, contrasenha);
    }

    @Override
    public Integer cambiarContrasenha(Integer idUsuario, String contrasenhaNueva) throws RemoteException {
        return this.usuarioBO.cambiarContrasenha(idUsuario, contrasenhaNueva);
    }

    @Override
    public Integer resetearContrasenha(Integer idUsuario) throws RemoteException {
        return this.usuarioBO.resetearContrasenha(idUsuario);
    }

    @Override
    public Integer obtenerIdPorCorreo(String correo) throws RemoteException {
        return this.usuarioBO.obtenerIdPorCorreo(correo);
    }
}
