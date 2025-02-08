package pe.edu.pucp.softrh.rmi.interfacesImpl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Date;
import pe.edu.pucp.softrh.rmi.interfaces.TrabajadorBO;
import pe.edu.pucp.softrh.usuarios.model.Trabajador;

public class TrabajadorBOImpl extends UnicastRemoteObject implements TrabajadorBO{
    private pe.edu.pucp.softrh.usuarios.bo.TrabajadorBO trabajadorBO;

    public TrabajadorBOImpl(Integer puerto) throws RemoteException{
        super(puerto);
        this.trabajadorBO = new pe.edu.pucp.softrh.usuarios.bo.TrabajadorBO();
    }

    @Override
    public Integer insertar(String dni, String nombres, String apellidos, String correo, String contrasenha, String puesto, Double sueldo, Date fechaIngreso, String horarioInicio, String horarioFin) throws RemoteException {
        return this.trabajadorBO.insertar(dni, nombres, apellidos, correo, contrasenha, puesto, sueldo, fechaIngreso, horarioInicio, horarioFin);
    }

    @Override
    public Integer modificar(Integer idUsuario, String dni, String nombres, String apellidos, String correo, String contrasenha, String puesto, Double sueldo, Date fechaIngreso, String horarioInicio, String horarioFin) throws RemoteException {
        return this.trabajadorBO.modificar(idUsuario, dni, nombres, apellidos, correo, contrasenha, puesto, sueldo, fechaIngreso, horarioInicio, horarioFin);
    }

    @Override
    public Integer eliminar(Integer idtrabajador) throws RemoteException {
        return this.trabajadorBO.eliminar(idtrabajador);
    }

    @Override
    public ArrayList<Trabajador> listarTodos() throws RemoteException {
        return this.trabajadorBO.listarTodos();
    }

    @Override
    public Trabajador obtenerPorId(Integer idtrabajador) throws RemoteException {
        return this.trabajadorBO.obtenerPorId(idtrabajador);
    }

    @Override
    public ArrayList<Trabajador> listarPorDniNombre(String cadena) throws RemoteException {
        return this.trabajadorBO.listarPorDniNombre(cadena);
    }
}
