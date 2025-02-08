package pe.edu.pucp.softrh.rmi.interfacesImpl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Date;
import pe.edu.pucp.softrh.rmi.interfaces.ClienteBO;
import pe.edu.pucp.softrh.usuarios.model.Cliente;

public class ClienteBOImpl extends UnicastRemoteObject implements ClienteBO{
    private pe.edu.pucp.softrh.usuarios.bo.ClienteBO clienteBO;

    public ClienteBOImpl(Integer puerto) throws RemoteException{
        super(puerto);
        this.clienteBO = new pe.edu.pucp.softrh.usuarios.bo.ClienteBO();
    }

    @Override
    public Integer insertar(String dni, String nombres, String apellidos, String correo, String contrasenha, String telefono, Date fechaRegistro, Boolean recibePromociones) throws RemoteException {
        return this.clienteBO.insertar(dni, nombres, apellidos, correo, contrasenha, telefono, fechaRegistro, recibePromociones);
    }

    @Override
    public Integer modificar(Integer idUsuario, String dni, String nombres, String apellidos, String correo, String telefono, Date fechaRegistro, Boolean recibePromociones) throws RemoteException {
        return this.clienteBO.modificar(idUsuario, dni, nombres, apellidos, correo, telefono, fechaRegistro, recibePromociones);
    }

    @Override
    public Integer eliminar(Integer idCliente) throws RemoteException {
        return this.clienteBO.eliminar(idCliente);
    }

    @Override
    public ArrayList<Cliente> listarTodos() throws RemoteException {
        return this.clienteBO.listarTodos();
    }

    @Override
    public Cliente obtenerPorId(Integer idCliente) throws RemoteException {
        return this.clienteBO.obtenerPorId(idCliente);
    }

    @Override
    public ArrayList<Cliente> listarPorDniNombre(String cadena) throws RemoteException {
        return this.clienteBO.listarPorDniNombre(cadena);
    }

    @Override
    public Integer verificarCorreoExistente(String correo) throws RemoteException {
        return this.clienteBO.verificarCorreoExistente(correo);
    }

}
