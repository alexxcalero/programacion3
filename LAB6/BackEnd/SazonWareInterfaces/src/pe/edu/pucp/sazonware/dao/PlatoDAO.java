package pe.edu.pucp.sazonware.dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import pe.edu.pucp.sazonware.model.Plato;

public interface PlatoDAO extends Remote {
    int insertar(Plato plato) throws RemoteException;
}
