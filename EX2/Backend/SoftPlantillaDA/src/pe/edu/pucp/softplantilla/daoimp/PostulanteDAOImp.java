package pe.edu.pucp.softplantilla.daoimp;

import java.sql.ResultSet;
import pe.edu.pucp.softplantilla.dao.PostulanteDAO;
import pe.edu.pucp.softplantilla.db.DBManager;
import pe.edu.pucp.softplantilla.model.Postulante;

public class PostulanteDAOImp implements PostulanteDAO {
    private ResultSet rs;
    private DBManager dbManager = DBManager.obtenerInstancia();
    
    @Override
    public Integer insertar(Postulante postulante) {
        Integer resultado = 0;

        Object[] parametros = new Object[10];
        parametros[0] = postulante.getIdPostulante();
        parametros[1] = postulante.getUniversidad().getIdUniversidad();
        parametros[2] = postulante.getDni();
        parametros[3] = postulante.getNombre();
        parametros[4] = postulante.getApellidoPaterno();
        parametros[5] = postulante.getPromedioAcumuladoPregrado();
        parametros[6] = postulante.getFueTercioSuperior();
        parametros[7] = postulante.getFueMiembroGrupoInvestigacion();
        parametros[8] = postulante.getFueDeportistaCalificado();
        parametros[9] = postulante.getModalidadPreferida().toString();

        postulante.setIdPostulante(dbManager.EjecutarProcedimiento("INSERTAR_POSTULANTE", parametros, true));

        resultado = postulante.getIdPostulante();

        return resultado;
    }
}
