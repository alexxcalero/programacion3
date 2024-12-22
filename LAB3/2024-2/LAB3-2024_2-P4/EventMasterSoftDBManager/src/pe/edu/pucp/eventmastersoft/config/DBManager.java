package pe.edu.pucp.eventmastersoft.config;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DBManager {
    //Coloque sus datos de conexión
    private static DBManager dbManager;
    private String url = "jdbc:mysql://labs-1inf30-prog3-20242.c8ttwc6gkvxy.us-east-1.rds.amazonaws.com:3306/lab04";
    private String usuario = "admin";
    private String password = "prog320242labs";
    private Connection con;
    
    public static DBManager getInstance(){
        if(dbManager == null)
            createInstance();
        return dbManager;
    }
    
    private static void createInstance(){
        dbManager = new DBManager();
    }
    
    public Connection getConnection(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, usuario, password);
        }catch(ClassNotFoundException | SQLException ex){
            System.out.println(ex.getMessage());
        }
        return con;
    }
}