package pe.edu.pucp.softrh.rmi.interfacesImpl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Date;
import pe.edu.pucp.softrh.rmi.interfaces.AdministradorBO;
import pe.edu.pucp.softrh.usuarios.model.Administrador;

public class AdministradorBOImpl extends UnicastRemoteObject implements AdministradorBO{
    private pe.edu.pucp.softrh.usuarios.bo.AdministradorBO administradorBO;

    public AdministradorBOImpl(Integer puerto) throws RemoteException{
        super(puerto);
        this.administradorBO = new pe.edu.pucp.softrh.usuarios.bo.AdministradorBO();
    }

    @Override
    public Integer insertar(String dni, String nombres, String apellidos, String correo, String contrasenha, Date fechaCreacion) throws RemoteException {
        return this.administradorBO.insertar(dni, nombres, apellidos, correo, contrasenha, fechaCreacion);
    }

    @Override
    public Integer modificar(Integer idUsuario, String dni, String nombres, String apellidos, String correo, String contrasenha, Date fechaCreacion) throws RemoteException {
        return this.administradorBO.modificar(idUsuario, dni, nombres, apellidos, correo, contrasenha, fechaCreacion);
    }

    @Override
    public Integer eliminar(Integer idAdministrador) throws RemoteException {
        return this.administradorBO.eliminar(idAdministrador);
    }

    @Override
    public ArrayList<Administrador> listarTodos() throws RemoteException {
        return this.administradorBO.listarTodos();
    }

    @Override
    public Administrador obtenerPorId(Integer idAdministrador) throws RemoteException {
        return this.administradorBO.obtenerPorId(idAdministrador);
    }

    @Override
    public ArrayList<Administrador> listarPorDniNombre(String cadena) throws RemoteException {
        return this.administradorBO.listarPorDniNombre(cadena);
    }

}
