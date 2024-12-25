package pe.edu.pucp.sazonware.dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import pe.edu.pucp.sazonware.model.Ingrediente;

public interface IngredienteDAO extends Remote {
    ArrayList<Ingrediente> listarTodos() throws RemoteException;
}
