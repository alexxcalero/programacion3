package pe.edu.pucp.softplantilla.interfacesimp;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import pe.edu.pucp.softplantilla.interfaces.UniversidadBO;
import pe.edu.pucp.softplantilla.model.Universidad;

public class UniversidadBOImp extends UnicastRemoteObject implements UniversidadBO {
    pe.edu.pucp.softplantilla.bo.UniversidadBO universidadBO;
    
    public UniversidadBOImp(Integer puerto) throws RemoteException {
        super(puerto);
        universidadBO = new pe.edu.pucp.softplantilla.bo.UniversidadBO();
    }
    
    @Override
    public ArrayList<Universidad> listarTodas() throws RemoteException {
        return universidadBO.listarTodas();
    }
}
