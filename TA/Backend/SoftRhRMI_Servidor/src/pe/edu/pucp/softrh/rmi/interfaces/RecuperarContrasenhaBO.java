package pe.edu.pucp.softrh.rmi.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;


public interface RecuperarContrasenhaBO extends Remote{
    Integer guardarToken(Integer idUsuario, String token, Date fechaExpiracion) throws RemoteException;

    Integer obtenerIdUsuarioPorToken(String token) throws RemoteException;
}
