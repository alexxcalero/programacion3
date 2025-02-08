package pe.edu.pucp.softrh.usuarios.dao;

import pe.edu.pucp.softrh.usuarios.model.RecuperarContrasenha;

public interface RecuperarContrasenhaDAO {
	int guardarToken(RecuperarContrasenha recuperacion);

	int obtenerIdUsuarioPorToken(String token);
}
