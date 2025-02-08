package pe.edu.pucp.softplantilla.bo;

import java.util.ArrayList;
import pe.edu.pucp.softplantilla.dao.UniversidadDAO;
import pe.edu.pucp.softplantilla.daoimp.UniversidadDAOImp;
import pe.edu.pucp.softplantilla.model.Universidad;

public class UniversidadBO {
    private UniversidadDAO universidadDAO;
    
    public UniversidadBO() {
        universidadDAO = new UniversidadDAOImp();
    }
    
    public ArrayList<Universidad> listarTodas() {
        return universidadDAO.listarTodas();
    }
}
