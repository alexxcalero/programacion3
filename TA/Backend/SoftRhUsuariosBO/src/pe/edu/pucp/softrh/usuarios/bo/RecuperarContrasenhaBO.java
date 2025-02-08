package pe.edu.pucp.softrh.usuarios.bo;

import java.util.Date;
import pe.edu.pucp.softrh.usuarios.dao.RecuperarContrasenhaDAO;
import pe.edu.pucp.softrh.usuarios.daoimp.RecuperarContrasenhaDAOImp;
import pe.edu.pucp.softrh.usuarios.model.RecuperarContrasenha;

public class RecuperarContrasenhaBO {
	private RecuperarContrasenhaDAO recuperarContrasenhaDAO;

    public RecuperarContrasenhaBO() {
        this.recuperarContrasenhaDAO = new RecuperarContrasenhaDAOImp();
    }

    public int guardarToken(Integer idUsuario, String token, Date fechaExpiracion) {
		RecuperarContrasenha recuperacion = new RecuperarContrasenha(idUsuario, token, fechaExpiracion);
        return recuperarContrasenhaDAO.guardarToken(recuperacion);
    }

	public int obtenerIdUsuarioPorToken(String token){
        return recuperarContrasenhaDAO.obtenerIdUsuarioPorToken(token);
	}
}
