package pe.edu.pucp.sazonware.services;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.util.ArrayList;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.net.MalformedURLException;
import pe.edu.pucp.sazonware.dao.IngredienteDAO;
import pe.edu.pucp.sazonware.model.Ingrediente;

@WebService(serviceName = "IngredienteWS", targetNamespace = "services.sazonware.pucp.edu.pe")
public class IngredienteWS {
    private IngredienteDAO ingredienteDAO;

    @WebMethod(operationName = "listarIngredientesTodos")
    public ArrayList<Ingrediente> listarIngredientesTodos() {
        ArrayList<Ingrediente> ingredientes = null;
        try{
            ingredienteDAO = (IngredienteDAO) Naming.lookup("//127.0.0.1:1234/ingredienteDAO");
            ingredientes = ingredienteDAO.listarTodos();
        }catch(MalformedURLException | NotBoundException | RemoteException ex){
            System.out.println(ex.getMessage());
        }
        return ingredientes;
    }
}
