package pe.edu.pucp.sazonware.services;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.net.MalformedURLException;
import pe.edu.pucp.sazonware.dao.PlatoDAO;
import pe.edu.pucp.sazonware.model.Plato;

@WebService(serviceName = "PlatoWS", targetNamespace = "services.sazonware.pucp.edu.pe")
public class PlatoWS {
    private PlatoDAO platoDAO;

    @WebMethod(operationName = "insertarPlato")
    public int insertarPlato(@WebParam(name = "plato") Plato plato) {
        int resultado = 0;
        try{
            platoDAO = (PlatoDAO) Naming.lookup("//127.0.0.1:1234/platoDAO");
            resultado = platoDAO.insertar(plato);
        }catch(MalformedURLException | NotBoundException | RemoteException ex){
            System.out.println(ex.getMessage());
        }
        return resultado;
    }
}
