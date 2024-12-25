package pe.edu.pucp.sazonware.server;

import java.rmi.registry.LocateRegistry;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.net.MalformedURLException;
import pe.edu.pucp.sazonware.dao.IngredienteDAO;
import pe.edu.pucp.sazonware.dao.PlatoDAO;
import pe.edu.pucp.sazonware.mysql.IngredienteMySQL;
import pe.edu.pucp.sazonware.mysql.PlatoMySQL;

public class RMIServer {
    
    private static String IPServidor = "127.0.0.1";
    private static String puertoServicio = "1234";
    
    public static void main(String[] args){
        try{
            //Registramos el servicio de RMI
            LocateRegistry.createRegistry(Integer.parseInt(puertoServicio));
            
            //Inicializamos los objetos remotos
            IngredienteDAO ingredienteDAO = new IngredienteMySQL(Integer.parseInt(puertoServicio));
            PlatoDAO platoDAO = new PlatoMySQL(Integer.parseInt(puertoServicio));
            
            //Colocamos los objetos en el servicio RMI
            Naming.rebind("//"+IPServidor+":"+String.valueOf(puertoServicio)+"/ingredienteDAO", ingredienteDAO);
            Naming.rebind("//"+IPServidor+":"+String.valueOf(puertoServicio)+"/platoDAO", platoDAO);
            
            //Imprimimos mensaje de confirmación
            System.out.println("El servidor RMI se ha inicializado correctamente..");
        }catch(MalformedURLException | RemoteException ex){
            System.out.println("Error inicializando el RMI: " + ex.getMessage());
        }
    }
}
