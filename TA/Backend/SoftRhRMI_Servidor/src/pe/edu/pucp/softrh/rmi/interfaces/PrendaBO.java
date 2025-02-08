package pe.edu.pucp.softrh.rmi.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import pe.edu.pucp.softrh.prendas.model.Genero;
import pe.edu.pucp.softrh.prendas.model.Prenda;
import pe.edu.pucp.softrh.prendas.model.Talla;
import pe.edu.pucp.softrh.prendas.model.TipoPrenda;

public interface PrendaBO extends Remote{
    Integer insertar(String nombre, String descripcion, TipoPrenda tipo, byte[] imagen, Talla talla, Genero genero, String color, Double precioOriginal, Integer stock) throws RemoteException;

    Integer modificar(Integer idPrenda, String nombre, String descripcion, TipoPrenda tipo, byte[] imagen, Talla talla, Genero genero, String color, Double precioOriginal, Integer stock) throws RemoteException;

    Integer eliminar(Integer idPrenda) throws RemoteException;

    ArrayList<Prenda> listarTodos() throws RemoteException;

    Prenda obtenerPorId(Integer idPrenda) throws RemoteException;

    ArrayList<Prenda> listarPorNombreDescripcion(String cadena) throws RemoteException;

    ArrayList<Prenda> listarPrendasFiltradas(Double minPrice, Double maxPrice, Boolean filterHombre, Boolean filterMujer, Boolean filterUnisex, String tallas, String colores) throws RemoteException;

    ArrayList<Prenda> listarPrendasPorGenero(String cadena) throws RemoteException;

    ArrayList<String> obtenerTallasUnicas() throws RemoteException;

    ArrayList<String> obtenerColoresUnicos() throws RemoteException;

    ArrayList<String> obtenerTallasPorPrenda(int idPrenda) throws RemoteException;

    ArrayList<String> obtenerColoresPorPrenda(int idPrenda) throws RemoteException;
}
