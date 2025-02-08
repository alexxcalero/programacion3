package pe.edu.pucp.softrh.rmi.interfacesImpl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import pe.edu.pucp.softrh.compras.model.Estado;
import pe.edu.pucp.softrh.prendas.model.Genero;
import pe.edu.pucp.softrh.prendas.model.Prenda;
import pe.edu.pucp.softrh.prendas.model.Talla;
import pe.edu.pucp.softrh.prendas.model.TipoPrenda;
import pe.edu.pucp.softrh.rmi.interfaces.PrendaBO;

public class PrendaBOImpl extends UnicastRemoteObject implements PrendaBO{
    private pe.edu.pucp.softrh.prendas.bo.PrendaBO prendaBO;

    public PrendaBOImpl(Integer puerto) throws RemoteException{
        super(puerto);
        this.prendaBO = new pe.edu.pucp.softrh.prendas.bo.PrendaBO();
    }

    @Override
    public Integer insertar(String nombre, String descripcion, TipoPrenda tipo, byte[] imagen, Talla talla, Genero genero, String color, Double precioOriginal, Integer stock) throws RemoteException {
        return this.prendaBO.insertar(nombre, descripcion, tipo, imagen, talla, genero, color, precioOriginal, stock);
    }

    @Override
    public Integer modificar(Integer idPrenda, String nombre, String descripcion, TipoPrenda tipo, byte[] imagen, Talla talla, Genero genero, String color, Double precioOriginal, Integer stock) throws RemoteException {
        return this.prendaBO.modificar(idPrenda, nombre, descripcion, tipo, imagen, talla, genero, color, precioOriginal, stock);
    }

    @Override
    public Integer eliminar(Integer idPrenda) throws RemoteException {
        return this.prendaBO.eliminar(idPrenda);
    }

    @Override
    public ArrayList<Prenda> listarTodos() throws RemoteException {
        return this.prendaBO.listarTodos();
    }

    @Override
    public Prenda obtenerPorId(Integer idPrenda) throws RemoteException {
        return this.prendaBO.obtenerPorId(idPrenda);
    }

    @Override
    public ArrayList<Prenda> listarPorNombreDescripcion(String cadena) throws RemoteException {
        return this.prendaBO.listarPorNombreDescripcion(cadena);
    }

    @Override
    public ArrayList<Prenda> listarPrendasFiltradas(Double minPrice, Double maxPrice, Boolean filterHombre, Boolean filterMujer, Boolean filterUnisex, String tallas, String colores) throws RemoteException {
        return this.prendaBO.listarPrendasFiltradas(minPrice, maxPrice, filterHombre, filterMujer, filterUnisex, tallas, colores);
    }

    @Override
    public ArrayList<Prenda> listarPrendasPorGenero(String cadena) throws RemoteException {
        return this.prendaBO.listarPrendasPorGenero(cadena);
    }

    @Override
    public ArrayList<String> obtenerTallasUnicas() throws RemoteException {
        return this.prendaBO.obtenerTallasUnicas();
    }

    @Override
    public ArrayList<String> obtenerColoresUnicos() throws RemoteException {
        return this.prendaBO.obtenerColoresUnicos();
    }

    @Override
    public ArrayList<String> obtenerTallasPorPrenda(int idPrenda) throws RemoteException {
        return this.prendaBO.obtenerTallasPorPrenda(idPrenda);
    }

    @Override
    public ArrayList<String> obtenerColoresPorPrenda(int idPrenda) throws RemoteException {
        return this.prendaBO.obtenerColoresPorPrenda(idPrenda);
    }

}
