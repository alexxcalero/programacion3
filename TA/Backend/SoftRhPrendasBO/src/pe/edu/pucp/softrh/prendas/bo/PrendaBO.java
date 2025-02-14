package pe.edu.pucp.softrh.prendas.bo;

import java.util.ArrayList;
import pe.edu.pucp.softrh.prendas.dao.PrendaDAO;
import pe.edu.pucp.softrh.prendas.daoimp.PrendaDAOImp;
import pe.edu.pucp.softrh.prendas.model.Genero;
import pe.edu.pucp.softrh.prendas.model.Prenda;
import pe.edu.pucp.softrh.prendas.model.Talla;
import pe.edu.pucp.softrh.prendas.model.TipoPrenda;

public class PrendaBO {
    private PrendaDAO prendaDAO;

    public PrendaBO(){
        this.prendaDAO = new PrendaDAOImp();
    }

    public Integer insertar(String nombre, String descripcion, TipoPrenda tipo, byte[] imagen, Talla talla, Genero genero, String color, Double precioOriginal, Integer stock){
        Prenda prenda = new Prenda(nombre, descripcion, tipo, imagen, talla, genero, color, precioOriginal, stock);
        return this.prendaDAO.insertar(prenda);
    }

    public Integer modificar(Integer idPrenda, String nombre, String descripcion, TipoPrenda tipo, byte[] imagen, Talla talla, Genero genero, String color, Double precioOriginal, Integer stock){
        Prenda prenda = new Prenda(nombre, descripcion, tipo, imagen, talla, genero, color, precioOriginal, stock);
		prenda.setIdPrenda(idPrenda);
		return this.prendaDAO.modificar(prenda);
    }

    public Integer eliminar(Integer idPrenda){
        return this.prendaDAO.eliminar(idPrenda);
    }

    public ArrayList<Prenda> listarTodos() {
        return this.prendaDAO.listarTodos();
    }

    public Prenda obtenerPorId(Integer idPrenda) {
        return this.prendaDAO.obtenerPorId(idPrenda);
    }

    public ArrayList<Prenda> listarPorNombreDescripcion(String cadena) {
        return this.prendaDAO.listarPorNombreDescripcion(cadena);
    }

    public ArrayList<Prenda> listarPrendasFiltradas(Double minPrice, Double maxPrice, Boolean filterHombre, Boolean filterMujer, Boolean filterUnisex, String tallas, String colores) {
        return this.prendaDAO.listarPrendasFiltradas(minPrice, maxPrice, filterHombre, filterMujer, filterUnisex, tallas, colores);
    }

    public ArrayList<Prenda> listarPrendasPorGenero(String cadena) {
        return this.prendaDAO.listarPrendasPorGenero(cadena);
    }
    public ArrayList<String> obtenerTallasUnicas() {
        return prendaDAO.obtenerTallasUnicas();
    }

    public ArrayList<String> obtenerColoresUnicos() {
        return prendaDAO.obtenerColoresUnicos();
    }
    public ArrayList<String> obtenerTallasPorPrenda(int idPrenda) {
        return prendaDAO.obtenerTallasPorPrenda(idPrenda);
    }

    public ArrayList<String> obtenerColoresPorPrenda(int idPrenda) {
        return prendaDAO.obtenerColoresPorPrenda(idPrenda);
    }

}
