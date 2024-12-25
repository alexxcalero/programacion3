package pe.edu.pucp.eventmastersoft.services;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.util.ArrayList;
import pe.edu.pucp.eventmastersoft.dao.ProductoraDAO;
import pe.edu.pucp.eventmastersoft.model.Productora;
import pe.edu.pucp.eventmastersoft.mysql.ProductoraMySQL;

@WebService(serviceName = "ProductoraWS", targetNamespace = "http://services.eventmastersoft.pucp.edu.pe")
public class ProductoraWS {

    private ProductoraDAO daoProductora;

    @WebMethod(operationName = "listarTodasProductoras")
    public ArrayList<Productora> listarTodasProductoras() {
        ArrayList<Productora> productoras = null;
        try{
            daoProductora = new ProductoraMySQL();
            productoras = daoProductora.listarTodas();
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return productoras;
    }
}
