package pe.edu.pucp.sazonware.config;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.Types;

public class DBManager {

    private static DBManager dbManager;
    private final String url = "jdbc:mysql://bd-alex.cp4mukkc4f93.us-east-1.rds.amazonaws.com" + ":3306/" + "lab14" + "?useSSL=false";
    private final String usuario = "admin";
    private final String password = "programacion3";
    private Connection con;
    private ResultSet rs;

    private DBManager(){}

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

    //////////////////////////////////////////////////////////////////////////////////////////////
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

    public int ejecutarProcedimientoTransaccion(String nombreProcedimiento, Map<String, Object> parametrosEntrada, Map<String, Object> parametrosSalida) throws SQLException{
        int resultado;

        CallableStatement cst = formarLlamadaProcedimientoTransaccion(nombreProcedimiento, parametrosEntrada, parametrosSalida);
        if (parametrosEntrada != null) {
            registrarParametrosEntrada(cst, parametrosEntrada);
        }
        if (parametrosSalida != null) {
            registrarParametrosSalida(cst, parametrosSalida);
        }

        resultado = cst.executeUpdate();

        if (parametrosSalida != null)
            obtenerValoresSalida(cst, parametrosSalida);

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
                case byte[] archivo -> cs.setBytes(key, archivo);
                default -> {
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

    public CallableStatement formarLlamadaProcedimientoTransaccion(String nombreProcedimiento, Map<String, Object> parametrosEntrada, Map<String, Object> parametrosSalida) throws SQLException{
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
                case Types.BLOB -> value = cst.getBytes(nombre);
                // Agregar más tipos según sea necesario
            }
            parametrosSalida.put(nombre, value);
        }
    }

    public void iniciarTransaccion() throws SQLException{
        con = getConnection();
        con.setAutoCommit(false);
    }

    public void confirmarTransaccion() throws SQLException{
        con.commit();
    }

    public void cancelarTransaccion(){
        try{
            con.rollback();
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            cerrarConexion();
        }
    }
}
