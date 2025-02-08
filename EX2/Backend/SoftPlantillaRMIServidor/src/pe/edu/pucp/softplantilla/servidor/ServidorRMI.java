package pe.edu.pucp.softplantilla.servidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.HashMap;
import java.util.Map;
import pe.edu.pucp.softplantilla.interfaces.PostulanteBO;
import pe.edu.pucp.softplantilla.interfaces.UniversidadBO;
import pe.edu.pucp.softplantilla.interfacesimp.PostulanteBOImp;
import pe.edu.pucp.softplantilla.interfacesimp.UniversidadBOImp;

public class ServidorRMI {
    private static final String ARCHIVO_CONFIGURACION = "datosConexionRMI.txt";
    private static String IP;
    private static Integer puerto;

    public static void main(String[] args) {
        leerArchivoYCrearCadena();
        System.out.println("Datos del servidor RMI: IP = " + IP + " - Puerto = " + puerto);

        try {
            System.setProperty("java.rmi.server.hostname", IP);

            LocateRegistry.createRegistry(puerto);

            // Agregar los BOs necesarios
            UniversidadBO universidadBO = new UniversidadBOImp(puerto);
            PostulanteBO postulanteBO = new PostulanteBOImp(puerto);

            // Agregar los nombres de servicios necesarios
            String nombreServicio = retornarNombreDelServicio("universidadBO");
            Naming.rebind(nombreServicio, universidadBO);
            
            nombreServicio = retornarNombreDelServicio("postulanteBO");
            Naming.rebind(nombreServicio, postulanteBO);

            System.out.println("Servidor RMI registrado correctamente...");
        } catch (RemoteException | MalformedURLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static String retornarNombreDelServicio(String servicio) {
        return "//" + IP + ":" + puerto + "/" + servicio;
    }

    public static void leerArchivoYCrearCadena() {
        Map<String, String> config = new HashMap<>();
        String archivoConfiguracion = "/pe/edu/pucp/softplantilla/config/" + ARCHIVO_CONFIGURACION;

        try {
            // Obtener el archivo como InputStream desde el JAR
            InputStream inputStream = ServidorRMI.class.getResourceAsStream(archivoConfiguracion);

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
            IP = config.get("IP");
            puerto = Integer.valueOf(config.get("puerto"));
        } catch (IOException | NumberFormatException e) {
                    System.out.println("Error leyendo archivo de conexion: " + e.getMessage());
        }
    }
}
