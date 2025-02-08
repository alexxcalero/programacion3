package pe.edu.pucp.softplantilla.interfacesimp;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import pe.edu.pucp.softplantilla.interfaces.PostulanteBO;
import pe.edu.pucp.softplantilla.model.Modalidad;
import pe.edu.pucp.softplantilla.model.Universidad;

public class PostulanteBOImp extends UnicastRemoteObject implements PostulanteBO {
    pe.edu.pucp.softplantilla.bo.PostulanteBO postulanteBO;
    
    public PostulanteBOImp(Integer puerto) throws RemoteException {
        super(puerto);
        postulanteBO = new pe.edu.pucp.softplantilla.bo.PostulanteBO();
    }
    
    @Override
    public Integer insertar(Universidad universidad, String dni, String nombre, String apellidoPaterno, Double promedioAcumuladoPregrado, Boolean fueTercioSuperior, Boolean fueMiembroGrupoInvestigacion, Boolean fueDeportistaCalificado, Modalidad modalidadPreferida) throws RemoteException {
        return postulanteBO.insertar(universidad, dni, nombre, apellidoPaterno, promedioAcumuladoPregrado, fueTercioSuperior, fueMiembroGrupoInvestigacion, fueDeportistaCalificado, modalidadPreferida);
    }
}
