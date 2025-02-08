package pe.edu.pucp.softrh.rmi.interfacesImpl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;
import pe.edu.pucp.softrh.rmi.interfaces.RecuperarContrasenhaBO;

public class RecuperarContrasenhaBOImpl extends UnicastRemoteObject implements RecuperarContrasenhaBO{
    private pe.edu.pucp.softrh.usuarios.bo.RecuperarContrasenhaBO recuperarContrasenhaBO;

    public RecuperarContrasenhaBOImpl(Integer puerto) throws RemoteException{
        super(puerto);
        this.recuperarContrasenhaBO = new pe.edu.pucp.softrh.usuarios.bo.RecuperarContrasenhaBO();
    }

    @Override
    public Integer guardarToken(Integer idUsuario, String token, Date fechaExpiracion) throws RemoteException {
        return this.recuperarContrasenhaBO.guardarToken(idUsuario, token, fechaExpiracion);
    }

    @Override
    public Integer obtenerIdUsuarioPorToken(String token) throws RemoteException {
        return this.recuperarContrasenhaBO.obtenerIdUsuarioPorToken(token);
    }
}
