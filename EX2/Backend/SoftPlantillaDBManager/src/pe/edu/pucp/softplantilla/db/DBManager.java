package pe.edu.pucp.softplantilla.db;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;
import static pe.edu.pucp.softplantilla.util.Cifrado.decipher_MD5;

public class DBManager {
	private static final String ARCHIVO_CONFIGURACION = "datosConexionBD.txt";
    private Connection conexion;
    private String driver;
    private String driver_type;
    private String database;
    private String host_name;
    private String port;
    private String user;
    private String password;
	private String url;
    private static DBManager dbManager = null;

	private DBManager() {
		try{
			leerArchivoYCrearCadena();
            Class.forName(driver);
        }catch(ClassNotFoundException ex){
            System.out.println("Error registrando el driver: " + ex.getMessage());
        }
	}

    public static DBManager obtenerInstancia() {
        if(DBManager.dbManager == null)
            crearInstancia();
        return DBManager.dbManager;
    }

    private static void crearInstancia() {
        if(DBManager.dbManager == null)
            DBManager.dbManager = new DBManager();
    }

    public Connection obtenerConexion(){
        try{
            conexion = DriverManager.getConnection(url, user, password);
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        return conexion;
    }

    private String getURL(){
        String cad = "";
		cad = cad.concat(this.driver_type.concat("://"));
        cad = cad.concat(this.host_name);
        cad = cad.concat(":");
        cad = cad.concat(this.port);
        cad = cad.concat("/");
        cad = cad.concat(this.database);
		cad = cad.concat("?useSSL=false");
        return cad;
    }

	public void leerArchivoYCrearCadena() {
        Map<String, String> config = new HashMap<>();
		String archivoConfiguracion = "/pe/edu/pucp/softplantilla/config/" + ARCHIVO_CONFIGURACION;

		try {
			// Obtener el archivo como InputStream desde el JAR
			InputStream inputStream = DBManager.class.getResourceAsStream(archivoConfiguracion);

			// Leer el contenido del archivo
			try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
				String linea;
				while ((linea = br.readLine()) != null) {
					String[] partes = linea.split("=");
					if (partes.length == 2) {
						config.put(partes[0].trim(), partes[1].trim());
					}
				}
			}

			// Asignar los valores al final
			driver = config.get("driver");
			driver_type = config.get("driver_type");
			database = config.get("database");
			host_name = config.get("host_name");
			port = config.get("port");
			user = config.get("user");
			password = decipher_MD5(config.get("password"));
			url = getURL();
		} catch (IOException e) {
			System.out.println("Error leyendo archivo de conexion: " + e.getMessage());
		}
    }

    protected void iniciarTransaccion() throws SQLException {
        this.conexion = DBManager.obtenerInstancia().obtenerConexion();
        this.conexion.setAutoCommit(false);
    }

    public void cerrarConexion() throws SQLException {
        if(this.conexion != null) {
            this.conexion.close();
        }
    }

    protected void comitarTransaccion() throws SQLException {
        this.conexion.commit();
    }

    protected void rollbackTransaccion() throws SQLException {
        if(this.conexion != null) {
            this.conexion.rollback();
        }
    }

    public int EjecutarProcedimiento(String nombreProcedimiento, Object[] parameters, Boolean nombreParametroSalida){
        int resultado = -1;
        CallableStatement cs = null;

        try{
            iniciarTransaccion();
            String procedimiento = obtenerCadenaProcedimiento(nombreProcedimiento, parameters, nombreParametroSalida);
            cs = conexion.prepareCall(procedimiento);
            if (parameters != null){
                for (int i=0; i<parameters.length; i++){
                    cs.setObject(i+1, parameters[i]);
                }
            }

            if (nombreParametroSalida == true){
                cs.registerOutParameter((parameters != null ? 1: 1), Types.INTEGER);
            }

            resultado = cs.executeUpdate();

            if (nombreParametroSalida == true){
                resultado = cs.getInt((parameters != null ? 1: 1));
            }

            this.conexion.commit();
        }
        catch (SQLException ex){
            ex.printStackTrace();
        }
        finally{
            try {
                cerrarConexion();
            } catch (SQLException ex) {
                Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return resultado;
    }

    public ResultSet EjecutarProcedimientoLectura(String nombreProcedimiento, Object[] parameters){
        ResultSet rs = null;
        CallableStatement cs = null;

        try{
            iniciarTransaccion();
            String procedimiento = obtenerCadenaProcedimiento(nombreProcedimiento, parameters, null);
            cs = conexion.prepareCall(procedimiento);
            if (parameters != null){
                for (int i=0; i<parameters.length; i++){
                    cs.setObject(i+1, parameters[i]);
                }
            }
            rs = cs.executeQuery();
        }
        catch (SQLException ex){
            ex.printStackTrace();
        }

        return rs;
    }

    protected String obtenerCadenaProcedimiento(String nombreProcedimiento, Object[] parameters, Boolean nombreParametroSalida){
        String cadena = "";
        cadena += "{call " + nombreProcedimiento + "(";
        if (parameters != null){
            for (int i=0; i< parameters.length; i++){
                cadena += "?";
                if (i < parameters.length - 1){
                    cadena += ",";
                }
            }
        }
        cadena += ") }";
        return cadena;
    }
}
