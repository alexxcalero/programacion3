package pe.edu.pucp.softrh.prendas.services;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.util.ArrayList;
import pe.edu.pucp.softrh.prendas.model.Genero;
import pe.edu.pucp.softrh.prendas.model.Prenda;
import pe.edu.pucp.softrh.prendas.model.Talla;
import pe.edu.pucp.softrh.prendas.model.TipoPrenda;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import pe.edu.pucp.softrh.rmi.interfaces.PrendaBO;
import pe.edu.pucp.softrh.rmi.servidor.SoftRhRMI_Servidor;

@WebService(serviceName = "PrendaWS", targetNamespace = "softrh.services")
public class PrendaWS {

    private static final String ARCHIVO_CONFIGURACION = "datosConexion.txt";
    private static String IP;
    private static Integer puerto;

    private PrendaBO prendaBO;

    public PrendaWS() {
		SoftRhRMI_Servidor.leerArchivoYCrearCadena();
        String nombreServicio = SoftRhRMI_Servidor.retornarNombreDelServicio("prendaBO");
        try {
            this.prendaBO = (PrendaBO) Naming.lookup(nombreServicio);
        } catch (NotBoundException | MalformedURLException | RemoteException ex) {
            Logger.getLogger(PrendaWS.class.getName()).log(Level.SEVERE, null, ex);
            this.prendaBO = null;
        }
    }

    @WebMethod(operationName = "insertarPrenda")
    public Integer insertarPrenda(String nombre, String descripcion, TipoPrenda tipo, byte[] imagen, Talla talla, Genero genero, String color, Double precioOriginal, Integer stock) {
        Integer retorno = -1;
        try {
            retorno = prendaBO.insertar(nombre, descripcion, tipo, imagen, talla, genero, color, precioOriginal, stock);
        } catch (RemoteException ex) {
            Logger.getLogger(PrendaWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    @WebMethod(operationName = "modificarPrenda")
    public Integer modificarPrenda(Integer idPrenda, String nombre, String descripcion, TipoPrenda tipo, byte[] imagen, Talla talla, Genero genero, String color, Double precioOriginal, Integer stock) {
        Integer retorno = -1;
        try {
            retorno = prendaBO.modificar(idPrenda, nombre, descripcion, tipo, imagen, talla, genero, color, precioOriginal, stock);
        } catch (RemoteException ex) {
            Logger.getLogger(PrendaWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    @WebMethod(operationName = "eliminarPrenda")
    public Integer eliminarPrenda(Integer idPrenda) {
        Integer retorno = -1;
        try {
            retorno = prendaBO.eliminar(idPrenda);
        } catch (RemoteException ex) {
            Logger.getLogger(PrendaWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    @WebMethod(operationName = "listarPrendasTodas")
    public ArrayList<Prenda> listarPrendasTodas() {
        ArrayList<Prenda> retorno = new ArrayList<>();
        try {
            retorno = prendaBO.listarTodos();
        } catch (RemoteException ex) {
            Logger.getLogger(PrendaWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    @WebMethod(operationName = "obtenerPrendaPorId")
    public Prenda obtenerPrendaPorId(Integer idPrenda) {
        Prenda retorno = null;
        try {
            retorno = prendaBO.obtenerPorId(idPrenda);
        } catch (RemoteException ex) {
            Logger.getLogger(PrendaWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    @WebMethod(operationName = "listarPrendasPorNombreDescripcion")
    public ArrayList<Prenda> listarPrendasPorNombreDescripcion(String cadena) {
        ArrayList<Prenda> retorno = new ArrayList<>();
        try {
            retorno = prendaBO.listarPorNombreDescripcion(cadena);
        } catch (RemoteException ex) {
            Logger.getLogger(PrendaWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    @WebMethod(operationName = "listarPrendasFiltradas")
    public ArrayList<Prenda> listarPrendasFiltradas(Double minPrice, Double maxPrice, boolean filterHombre, boolean filterMujer, boolean filterUnisex, String tallas, String colores) {
        ArrayList<Prenda> retorno = new ArrayList<>();
        try {
            retorno = prendaBO.listarPrendasFiltradas(minPrice, maxPrice, filterHombre, filterMujer, filterUnisex, tallas, colores);
        } catch (RemoteException ex) {
            Logger.getLogger(PrendaWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    @WebMethod(operationName = "obtenerImagenPorId")
    public byte[] obtenerImagenPorId(Integer idPrenda) {
        byte[] retorno = null;
        try {
            Prenda prenda = prendaBO.obtenerPorId(idPrenda);
            retorno = prenda != null ? prenda.getImagen() : null;
        } catch (RemoteException ex) {
            Logger.getLogger(PrendaWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    @WebMethod(operationName = "listarPrendasPorGenero")
    public ArrayList<Prenda> listarPrendasPorGenero(String cadena) {
        ArrayList<Prenda> retorno = new ArrayList<>();
        try {
            retorno = prendaBO.listarPrendasPorGenero(cadena);
        } catch (RemoteException ex) {
            Logger.getLogger(PrendaWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    @WebMethod(operationName = "obtenerTallasUnicas")
    public ArrayList<String> obtenerTallasUnicas() {
        ArrayList<String> retorno = new ArrayList<>();
        try {
            retorno = prendaBO.obtenerTallasUnicas();
        } catch (RemoteException ex) {
            Logger.getLogger(PrendaWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    @WebMethod(operationName = "obtenerColoresUnicos")
    public ArrayList<String> obtenerColoresUnicos() {
        ArrayList<String> retorno = new ArrayList<>();
        try {
            retorno = prendaBO.obtenerColoresUnicos();
        } catch (RemoteException ex) {
            Logger.getLogger(PrendaWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    @WebMethod(operationName = "obtenerTallasPorPrenda")
    public ArrayList<String> obtenerTallasPorPrenda(@WebParam(name = "idPrenda") int idPrenda) {
        ArrayList<String> retorno = new ArrayList<>();
        try {
            retorno = prendaBO.obtenerTallasPorPrenda(idPrenda);
        } catch (RemoteException ex) {
            Logger.getLogger(PrendaWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    @WebMethod(operationName = "obtenerColoresPorPrenda")
    public ArrayList<String> obtenerColoresPorPrenda(@WebParam(name = "idPrenda") int idPrenda) {
        ArrayList<String> retorno = new ArrayList<>();
        try {
            retorno = prendaBO.obtenerColoresPorPrenda(idPrenda);
        } catch (RemoteException ex) {
            Logger.getLogger(PrendaWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
}

