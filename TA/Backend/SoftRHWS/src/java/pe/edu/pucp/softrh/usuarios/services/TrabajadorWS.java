package pe.edu.pucp.softrh.usuarios.services;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import pe.edu.pucp.softrh.rmi.interfaces.TrabajadorBO;
import pe.edu.pucp.softrh.rmi.servidor.SoftRhRMI_Servidor;
import pe.edu.pucp.softrh.usuarios.model.Trabajador;

@WebService(serviceName = "TrabajadorWS", targetNamespace = "softrh.services")
public class TrabajadorWS {

    private static final String ARCHIVO_CONFIGURACION = "datosConexion.txt";
    private static String IP;
    private static Integer puerto;
	private String nombreServicio;

    private TrabajadorBO trabajadorBO;

    public TrabajadorWS() {
		SoftRhRMI_Servidor.leerArchivoYCrearCadena();
        nombreServicio = SoftRhRMI_Servidor.retornarNombreDelServicio("trabajadorBO");
		try {
            this.trabajadorBO = (TrabajadorBO) Naming.lookup(nombreServicio);
        } catch (NotBoundException | MalformedURLException | RemoteException ex) {
            Logger.getLogger(TrabajadorWS.class.getName()).log(Level.SEVERE, null, ex);
            this.trabajadorBO = null; // Inicializa como null en caso de error
        }
    }

//	public void inicializarTrabajador() {
//		try {
//            this.trabajadorBO = (TrabajadorBO) Naming.lookup(nombreServicio);
//        } catch (NotBoundException | MalformedURLException | RemoteException ex) {
//            Logger.getLogger(TrabajadorWS.class.getName()).log(Level.SEVERE, null, ex);
//            this.trabajadorBO = null; // Inicializa como null en caso de error
//        }
//	}

    @WebMethod(operationName = "insertarTrabajador")
    public Integer insertarTrabajador(String dni, String nombres, String apellidos, String correo, String contrasenha, String puesto, Double sueldo, Date fechaIngreso, String horarioInicio, String horarioFin) {
        Integer retorno = -1;
        try {
			//inicializarTrabajador();
            retorno = trabajadorBO.insertar(dni, nombres, apellidos, correo, contrasenha, puesto, sueldo, fechaIngreso, horarioInicio, horarioFin);
        } catch (RemoteException ex) {
            Logger.getLogger(TrabajadorWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    @WebMethod(operationName = "modificarTrabajador")
    public Integer modificarTrabajador(Integer idUsuario, String dni, String nombres, String apellidos, String correo, String contrasenha, String puesto, Double sueldo, Date fechaIngreso, String horarioInicio, String horarioFin) {
        Integer retorno = -1;
        try {
			//inicializarTrabajador();
            retorno = trabajadorBO.modificar(idUsuario, dni, nombres, apellidos, correo, contrasenha, puesto, sueldo, fechaIngreso, horarioInicio, horarioFin);
        } catch (RemoteException ex) {
            Logger.getLogger(TrabajadorWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    @WebMethod(operationName = "eliminarTrabajador")
    public Integer eliminarTrabajador(Integer idTrabajador) {
        Integer retorno = -1;
        try {
			//inicializarTrabajador();
            retorno = trabajadorBO.eliminar(idTrabajador);
        } catch (RemoteException ex) {
            Logger.getLogger(TrabajadorWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    @WebMethod(operationName = "listarTrabajadoresTodos")
    public ArrayList<Trabajador> listarTrabajadoresTodos() {
        ArrayList<Trabajador> retorno = new ArrayList<>();
        try {
			//inicializarTrabajador();
            retorno = trabajadorBO.listarTodos();
        } catch (RemoteException ex) {
            Logger.getLogger(TrabajadorWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    @WebMethod(operationName = "obtenerTrabajadorPorId")
    public Trabajador obtenerTrabajadorPorId(Integer idTrabajador) {
        Trabajador retorno = null;
        try {
			//inicializarTrabajador();
            retorno = trabajadorBO.obtenerPorId(idTrabajador);
        } catch (RemoteException ex) {
            Logger.getLogger(TrabajadorWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    @WebMethod(operationName = "listarTrabajadoresPorDniNombre")
    public ArrayList<Trabajador> listarTrabajadoresPorDniNombre(String cadena) {
        ArrayList<Trabajador> retorno = new ArrayList<>();
        try {
			//inicializarTrabajador();
            retorno = trabajadorBO.listarPorDniNombre(cadena);
        } catch (RemoteException ex) {
            Logger.getLogger(TrabajadorWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
}
