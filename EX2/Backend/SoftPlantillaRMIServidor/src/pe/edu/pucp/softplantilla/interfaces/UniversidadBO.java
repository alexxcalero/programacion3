package pe.edu.pucp.softplantilla.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import pe.edu.pucp.softplantilla.model.Universidad;

public interface UniversidadBO extends Remote {
    ArrayList<Universidad> listarTodas() throws RemoteException;
}
