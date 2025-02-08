package pe.edu.pucp.softrh.rmi.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import pe.edu.pucp.softrh.usuarios.model.Trabajador;

public interface TrabajadorBO extends Remote{

    Integer insertar(String dni, String nombres, String apellidos, String correo, String contrasenha, String puesto, Double sueldo, Date fechaIngreso, String horarioInicio, String horarioFin) throws RemoteException;

    Integer modificar(Integer idUsuario, String dni, String nombres, String apellidos, String correo, String contrasenha, String puesto, Double sueldo, Date fechaIngreso, String horarioInicio, String horarioFin) throws RemoteException;

    Integer eliminar(Integer idtrabajador) throws RemoteException;

    ArrayList<Trabajador> listarTodos() throws RemoteException;

    Trabajador obtenerPorId(Integer idtrabajador) throws RemoteException;

    ArrayList<Trabajador> listarPorDniNombre(String cadena) throws RemoteException;
}
