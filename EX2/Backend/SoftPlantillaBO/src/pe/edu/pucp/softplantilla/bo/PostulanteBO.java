package pe.edu.pucp.softplantilla.bo;

import pe.edu.pucp.softplantilla.dao.PostulanteDAO;
import pe.edu.pucp.softplantilla.daoimp.PostulanteDAOImp;
import pe.edu.pucp.softplantilla.model.Modalidad;
import pe.edu.pucp.softplantilla.model.Postulante;
import pe.edu.pucp.softplantilla.model.Universidad;

public class PostulanteBO {
    private PostulanteDAO postulanteDAO;
    
    public PostulanteBO() {
        postulanteDAO = new PostulanteDAOImp();
    }
    
    public Integer insertar(Universidad universidad, String dni, String nombre, String apellidoPaterno, Double promedioAcumuladoPregrado, Boolean fueTercioSuperior, Boolean fueMiembroGrupoInvestigacion, Boolean fueDeportistaCalificado, Modalidad modalidadPreferida) {
        Postulante postulante = new Postulante(universidad, dni, nombre, apellidoPaterno, promedioAcumuladoPregrado, fueTercioSuperior, fueMiembroGrupoInvestigacion, fueDeportistaCalificado, modalidadPreferida);
        return postulanteDAO.insertar(postulante);
    }
}
