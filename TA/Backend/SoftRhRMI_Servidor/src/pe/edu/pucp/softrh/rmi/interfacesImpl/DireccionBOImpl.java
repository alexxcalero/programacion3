package pe.edu.pucp.softrh.rmi.interfacesImpl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import pe.edu.pucp.softrh.rmi.interfaces.DireccionBO;
import pe.edu.pucp.softrh.usuarios.model.Cliente;
import pe.edu.pucp.softrh.usuarios.model.Direccion;

public class DireccionBOImpl extends UnicastRemoteObject implements DireccionBO{
    private pe.edu.pucp.softrh.usuarios.bo.DireccionBO direccionBO;

    public DireccionBOImpl(Integer puerto) throws RemoteException{
        super(puerto);
        this.direccionBO = new pe.edu.pucp.softrh.usuarios.bo.DireccionBO();
    }

    @Override
    public Integer insertar(String direc, String distrito, String provincia, String departamente, String codigoPostal, String referencia, Cliente cliente) throws RemoteException {
        return this.direccionBO.insertar(direc, distrito, provincia, departamente, codigoPostal, referencia, cliente);
    }

    @Override
    public Integer modificar(Integer idDireccion, String direc, String distrito, String provincia, String departamente, String codigoPostal, String referencia, Cliente cliente) throws RemoteException {
        return this.direccionBO.modificar(idDireccion, direc, distrito, provincia, departamente, codigoPostal, referencia, cliente);
    }

    @Override
    public Integer eliminar(Integer idDireccion) throws RemoteException {
        return this.direccionBO.eliminar(idDireccion);
    }

    @Override
    public ArrayList<Direccion> listarTodos() throws RemoteException {
        return this.direccionBO.listarTodos();
    }

    @Override
    public Direccion obtenerPorId(Integer idDireccion) throws RemoteException {
        return this.direccionBO.obtenerPorId(idDireccion);
    }

    @Override
    public ArrayList<Direccion> listarPorIdCliente(int idCliente) throws RemoteException {
        return this.direccionBO.listarPorIdCliente(idCliente);
    }

}
