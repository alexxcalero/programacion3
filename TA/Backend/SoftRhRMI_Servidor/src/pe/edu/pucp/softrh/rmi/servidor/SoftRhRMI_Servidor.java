/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pe.edu.pucp.softrh.rmi.servidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.Naming;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import pe.edu.pucp.softrh.rmi.interfaces.AdministradorBO;
import pe.edu.pucp.softrh.rmi.interfaces.CarritoBO;
import pe.edu.pucp.softrh.rmi.interfaces.ClienteBO;
import pe.edu.pucp.softrh.rmi.interfaces.CuponBO;
import pe.edu.pucp.softrh.rmi.interfaces.DireccionBO;
import pe.edu.pucp.softrh.rmi.interfaces.OrdenCompraBO;
import pe.edu.pucp.softrh.rmi.interfaces.PrendaBO;
import pe.edu.pucp.softrh.rmi.interfaces.PrendaSeleccionadaBO;
import pe.edu.pucp.softrh.rmi.interfaces.PromocionBO;
import pe.edu.pucp.softrh.rmi.interfaces.RecuperarContrasenhaBO;
import pe.edu.pucp.softrh.rmi.interfaces.TotalesBO;
import pe.edu.pucp.softrh.rmi.interfaces.TrabajadorBO;
import pe.edu.pucp.softrh.rmi.interfaces.UsuarioBO;
import pe.edu.pucp.softrh.rmi.interfacesImpl.AdministradorBOImpl;
import pe.edu.pucp.softrh.rmi.interfacesImpl.CarritoBOImpl;
import pe.edu.pucp.softrh.rmi.interfacesImpl.ClienteBOImpl;
import pe.edu.pucp.softrh.rmi.interfacesImpl.CuponBOImpl;
import pe.edu.pucp.softrh.rmi.interfacesImpl.DireccionBOImpl;
import pe.edu.pucp.softrh.rmi.interfacesImpl.OrdenCompraBOImpl;
import pe.edu.pucp.softrh.rmi.interfacesImpl.PrendaBOImpl;
import pe.edu.pucp.softrh.rmi.interfacesImpl.PrendaSeleccionadaBOImpl;
import pe.edu.pucp.softrh.rmi.interfacesImpl.PromocionBOImpl;
import pe.edu.pucp.softrh.rmi.interfacesImpl.RecuperarContrasenhaBOImpl;
import pe.edu.pucp.softrh.rmi.interfacesImpl.TotalesBOImpl;
import pe.edu.pucp.softrh.rmi.interfacesImpl.TrabajadorBOImpl;
import pe.edu.pucp.softrh.rmi.interfacesImpl.UsuarioBOImpl;

public class SoftRhRMI_Servidor {

	private static final String ARCHIVO_CONFIGURACION = "datosConexion.txt";
    private static String IP;
    private static Integer puerto;

    public static void main(String[] args) {
		leerArchivoYCrearCadena();
		System.out.println("Datos del servidor RMI: IP = " + IP + " - Puerto = " + puerto);

        try {
			System.setProperty("java.rmi.server.hostname", IP);

            //Registrar el servicio RMI
            LocateRegistry.createRegistry(puerto);

            //Instanciar los objetos remotos
            AdministradorBO administradorBO = new AdministradorBOImpl(puerto);
            CarritoBO carritoBO = new CarritoBOImpl(puerto);
            ClienteBO clienteBO = new ClienteBOImpl(puerto);
            CuponBO cuponBO = new CuponBOImpl(puerto);
            DireccionBO direccionBO = new DireccionBOImpl(puerto);
            OrdenCompraBO ordenCompraBO = new OrdenCompraBOImpl(puerto);
            PrendaBO prendaBO = new PrendaBOImpl(puerto);
            PrendaSeleccionadaBO prendaSeleccionadaBO = new PrendaSeleccionadaBOImpl(puerto);
            PromocionBO promocionBO = new PromocionBOImpl(puerto);
            RecuperarContrasenhaBO recuperarContrasenhaBO = new RecuperarContrasenhaBOImpl(puerto);
            TotalesBO totalesBO = new TotalesBOImpl(puerto);
            TrabajadorBO trabajadorBO = new TrabajadorBOImpl(puerto);
            UsuarioBO usuarioBO = new UsuarioBOImpl(puerto);

            //Colocar los objetos remotos en el RMI

            String nombreServicio = retornarNombreDelServicio("administradorBO");
            Naming.rebind(nombreServicio, administradorBO);

            nombreServicio = retornarNombreDelServicio("carritoBO");
            Naming.rebind(nombreServicio, carritoBO);

            nombreServicio = retornarNombreDelServicio("clienteBO");
            Naming.rebind(nombreServicio, clienteBO);

            nombreServicio = retornarNombreDelServicio("cuponBO");
            Naming.rebind(nombreServicio, cuponBO);

            nombreServicio = retornarNombreDelServicio("direccionBO");
            Naming.rebind(nombreServicio, direccionBO);

            nombreServicio = retornarNombreDelServicio("ordenCompraBO");
            Naming.rebind(nombreServicio, ordenCompraBO);

            nombreServicio = retornarNombreDelServicio("prendaBO");
            Naming.rebind(nombreServicio, prendaBO);

            nombreServicio = retornarNombreDelServicio("prendaSeleccionadaBO");
            Naming.rebind(nombreServicio, prendaSeleccionadaBO);

            nombreServicio = retornarNombreDelServicio("promocionBO");
            Naming.rebind(nombreServicio, promocionBO);

            nombreServicio = retornarNombreDelServicio("recuperarContrasenhaBO");
            Naming.rebind(nombreServicio, recuperarContrasenhaBO);

            nombreServicio = retornarNombreDelServicio("totalesBO");
            Naming.rebind(nombreServicio, totalesBO);

            nombreServicio = retornarNombreDelServicio("trabajadorBO");
            Naming.rebind(nombreServicio, trabajadorBO);

            nombreServicio = retornarNombreDelServicio("usuarioBO");
            Naming.rebind(nombreServicio, usuarioBO);

            System.out.println("Servidor RMI registrado correctamente...");
        } catch (RemoteException | MalformedURLException ex) {
            Logger.getLogger(SoftRhRMI_Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static String retornarNombreDelServicio(String servicio) {
        return "//" + IP + ":" + puerto + "/" + servicio;
    }

	public static void leerArchivoYCrearCadena() {
        Map<String, String> config = new HashMap<>();
		String archivoConfiguracion = "/pe/edu/pucp/softrh/config/" + ARCHIVO_CONFIGURACION;

		try {
			// Obtener el archivo como InputStream desde el JAR
			InputStream inputStream = SoftRhRMI_Servidor.class.getResourceAsStream(archivoConfiguracion);

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
