package pe.edu.pucp.softrh.usuarios.bo;

import java.util.ArrayList;
import pe.edu.pucp.softrh.usuarios.dao.DireccionDAO;
import pe.edu.pucp.softrh.usuarios.daoimp.DireccionDAOImp;
import pe.edu.pucp.softrh.usuarios.model.Cliente;
import pe.edu.pucp.softrh.usuarios.model.Direccion;

public class DireccionBO {
	private DireccionDAO direccionDAO;

	public DireccionBO() {
		this.direccionDAO = new DireccionDAOImp();
	}

	public Integer insertar(String direc, String distrito, String provincia, String departamente, String codigoPostal, String referencia, Cliente cliente) {
		Direccion direccion = new Direccion(direc, distrito, provincia, departamente, codigoPostal, referencia, cliente);
		return direccionDAO.insertar(direccion);
	}

	public Integer modificar(Integer idDireccion, String direc, String distrito, String provincia, String departamente, String codigoPostal, String referencia, Cliente cliente) {
		Direccion direccion = new Direccion(direc, distrito, provincia, departamente, codigoPostal, referencia, cliente);
		direccion.setIdDireccion(idDireccion);
                return direccionDAO.modificar(direccion);
	}

	public Integer eliminar(Integer idDireccion) {
		return direccionDAO.eliminar(idDireccion);
	}

	public ArrayList<Direccion> listarTodos() {
		return direccionDAO.listarTodos();
	}

	public Direccion obtenerPorId(Integer idDireccion) {
		return direccionDAO.obtenerPorId(idDireccion);
	}
        public ArrayList<Direccion> listarPorIdCliente(int idCliente) {
            return direccionDAO.listarPorIdCliente(idCliente);
        }
}
