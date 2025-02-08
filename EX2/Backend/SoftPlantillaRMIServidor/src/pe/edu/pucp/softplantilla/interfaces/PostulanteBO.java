package pe.edu.pucp.softplantilla.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import pe.edu.pucp.softplantilla.model.Modalidad;
import pe.edu.pucp.softplantilla.model.Universidad;

public interface PostulanteBO extends Remote {
    Integer insertar(Universidad universidad, String dni, String nombre, String apellidoPaterno, Double promedioAcumuladoPregrado, Boolean fueTercioSuperior, Boolean fueMiembroGrupoInvestigacion, Boolean fueDeportistaCalificado, Modalidad modalidadPreferida) throws RemoteException;
}
