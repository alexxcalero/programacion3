package pe.edu.pucp.eventmastersoft.config;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.CallableStatement;
import java.sql.Types;
import java.net.URL;
import java.net.URLDecoder;

public final class DBManager {
    
    private static DBManager dbManager;
    private String url;
    private String usuario;
    private String password;
    private Connection con;
    private ResultSet rs;
    private final String nombreArchivo = "datosConexion.txt";
    
    private DBManager(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            leerArchivoYCrearCadena();
        }catch(ClassNotFoundException ex){
            System.out.println("Error registrando el driver: " + ex.getMessage());
        }
    }
    
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
            con = DriverManager.getConnection(url, usuario, password);
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        return con;
    }
    
    public void leerArchivoYCrearCadena() {
        Map<String, String> config = new HashMap<>();
        String rutaArchivo = "";
        try{
            URL resourceUrl = DBManager.class.getResource("/pe/edu/pucp/eventmastersoft/config/");
            String decodedPath = URLDecoder.decode(resourceUrl.getPath(), "UTF-8");
            rutaArchivo = decodedPath + nombreArchivo;
        }catch(UnsupportedEncodingException ex){
            System.out.println(ex.getMessage());
        }
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split("=");
                if (partes.length == 2) {
                    config.put(partes[0].trim(), partes[1].trim());
                }
            }
        } catch (IOException e) {
            System.out.println("Error leyendo archivo de conexion: " + e.getMessage());
        }

        usuario = config.get("user");
        password = config.get("password");
        url = "jdbc:mysql://" + config.get("hostname") + ":" + config.get("port") + "/" + config.get("database") + "?useSSL=false";

    }
    
    public void cerrarConexion() {
        if(rs != null){
            try{
                rs.close();
            }catch(SQLException ex){
                System.out.println("Error al cerrar el lector:" + ex.getMessage());
            }
        }
        if (con != null) {
            try {
                con.close();  
            } catch (SQLException ex) {
                System.out.println("Error al cerrar la conexión:" + ex.getMessage());
            }
        }
    }
    
    public int ejecutarProcedimiento(String nombreProcedimiento, Map<String, Object> parametrosEntrada, Map<String, Object> parametrosSalida) {
        int resultado = 0;
        try{
            CallableStatement cst = formarLlamadaProcedimiento(nombreProcedimiento, parametrosEntrada, parametrosSalida);
            if(parametrosEntrada != null)
                registrarParametrosEntrada(cst, parametrosEntrada);
            if(parametrosSalida != null)
                registrarParametrosSalida(cst, parametrosSalida);
        
            resultado = cst.executeUpdate();
        
            if(parametrosSalida != null)
                obtenerValoresSalida(cst, parametrosSalida);
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            cerrarConexion();
        }
        return resultado;
    }
    
    private void registrarParametrosEntrada(CallableStatement cs, Map<String, Object> parametros) throws SQLException {
        for (Map.Entry<String, Object> entry : parametros.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            switch (value) {
                case Integer entero -> cs.setInt(key, entero);
                case String cadena -> cs.setString(key, cadena);
                case Double decimal -> cs.setDouble(key, decimal);
                case Boolean booleano -> cs.setBoolean(key, booleano);
                case java.util.Date fecha -> cs.setDate(key, new java.sql.Date(fecha.getTime()));
                case byte[] bytes -> cs.setBytes(key, bytes);
                default -> {
			cs.setString(key, value.toString());
                }
                // Agregar más tipos según sea necesario
            }
        }
    }
    
    private void registrarParametrosSalida(CallableStatement cst, Map<String, Object> params) throws SQLException {
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            String nombre = entry.getKey();
            int sqlType = (int) entry.getValue();
            cst.registerOutParameter(nombre, sqlType);
        }
    }
    
    public CallableStatement formarLlamadaProcedimiento(String nombreProcedimiento, Map<String, Object> parametrosEntrada, Map<String, Object> parametrosSalida) throws SQLException{
        con = getConnection();
        StringBuilder call = new StringBuilder("{call " + nombreProcedimiento + "(");
        int cantParametrosEntrada = 0;
        int cantParametrosSalida = 0;
        if(parametrosEntrada!=null) cantParametrosEntrada = parametrosEntrada.size();
        if(parametrosSalida!=null) cantParametrosSalida = parametrosSalida.size();
        int numParams =  cantParametrosEntrada + cantParametrosSalida;
        for (int i = 0; i < numParams; i++) {
            call.append("?");
            if (i < numParams - 1) {
                call.append(",");
            }
        }
        call.append(")}");
        return con.prepareCall(call.toString());
    }
    
    public ResultSet ejecutarProcedimientoLectura(String nombreProcedimiento, Map<String, Object> parametrosEntrada){
        try{
            CallableStatement cs = formarLlamadaProcedimiento(nombreProcedimiento, parametrosEntrada, null);
            if(parametrosEntrada!=null) 
                registrarParametrosEntrada(cs,parametrosEntrada);
            rs = cs.executeQuery();
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        return rs;
    }
    
    private void obtenerValoresSalida(CallableStatement cst, Map<String, Object> parametrosSalida) throws SQLException {
        for (Map.Entry<String, Object> entry : parametrosSalida.entrySet()) {
            String nombre = entry.getKey();
            int sqlType = (int) entry.getValue();
            Object value = null;
            switch (sqlType) {
                case Types.INTEGER -> value = cst.getInt(nombre);
                case Types.VARCHAR -> value = cst.getString(nombre);
                case Types.DOUBLE -> value = cst.getDouble(nombre);
                case Types.BOOLEAN -> value = cst.getBoolean(nombre);
                case Types.DATE -> value = cst.getDate(nombre);
                // Agregar más tipos según sea necesario
            }
            parametrosSalida.put(nombre, value);
        }
    }
}