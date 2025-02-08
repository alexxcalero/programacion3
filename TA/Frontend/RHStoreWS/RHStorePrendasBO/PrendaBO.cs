using RHStoreBaseBO;
using RHStoreBaseBO.ServiciosWeb;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace RHStorePrendasBO
{
	public class PrendaBO : BaseBO
	{
        public int insertar(string nombre, string descripcion, tipoPrenda tipo, byte[] imagen, talla _talla, genero _genero, string color, double precioOriginal, int stock)
        {
            return PrendaWS.insertarPrenda(nombre, descripcion, tipo, imagen, _talla, _genero, color, precioOriginal, stock);
        }
        public int modificar(int idPrenda, string nombre, string descripcion, tipoPrenda tipo, byte[] imagen, talla _talla, genero _genero, string color, double precioOriginal, int stock)
        {
            return PrendaWS.modificarPrenda(idPrenda, nombre, descripcion, tipo, imagen, _talla, _genero, color, precioOriginal, stock);
        }
        public int eliminar(int idPrenda)
        {
            return PrendaWS.eliminarPrenda(idPrenda);
        }

        public BindingList<prenda> listarTodos()
        {

            try
            {
                List<prenda> prendas = PrendaWS.listarPrendasTodas()?.ToList();
                return new BindingList<prenda>(prendas ?? new List<prenda>());
            }
            catch (Exception ex)
            {
                // Manejar la excepción (registrarla o notificar al usuario)
                return new BindingList<prenda>(new List<prenda>());
            }

        }

        public prenda obtenerPorId(int idPrenda)
        {
            return PrendaWS.obtenerPrendaPorId(idPrenda);

        }

        public BindingList<prenda> listarPorNombreDescripcion(string cadena)
        {
            try
            {
                List<prenda> prendas = PrendaWS.listarPrendasPorNombreDescripcion(cadena)?.ToList();
                return new BindingList<prenda>(prendas ?? new List<prenda>());
            }
            catch (Exception ex)
            {
                // Manejar la excepción (registrarla o notificar al usuario)
                return new BindingList<prenda>(new List<prenda>());
            }

        }

        public BindingList<prenda> listarPrendasFiltradas(double minPrice, double maxPrice, bool filterHombre, bool filterMujer, bool filterUnisex, string tallas, string colores)
        {
            // Llama al método del servicio web que corresponde al procedimiento almacenado
            //return new BindingList<prenda>(PrendaWS.listarPrendasFiltradas(minPrice, maxPrice, filterHombre, filterMujer, filterUnisex, tallas, colores));
            try
            {
                // Llama al servicio web para obtener todas las direcciones
                prenda[] prendasArray = PrendaWS.listarPrendasFiltradas(minPrice, maxPrice, filterHombre, filterMujer, filterUnisex, tallas, colores);

                // Si el array es nulo o vacío, inicializa una lista vacía
                if (prendasArray == null || prendasArray.Length == 0)
                {
                    return new BindingList<prenda>(); // Lista vacía
                }

                // Convierte el array a una lista y luego a un BindingList
                return new BindingList<prenda>(prendasArray.ToList());
            }
            catch (Exception ex)
            {
                throw new Exception("Error al listar las prendas filtradas: " + ex.Message, ex);
            }
        }

        public byte[] ObtenerImagen(int idPrenda)
		{
			return PrendaWS.obtenerImagenPorId(idPrenda);
		}

		public BindingList<prenda> listarPrendasPorGenero(string cadena)
		{
			if (PrendaWS.listarPrendasPorGenero(cadena) == null)
				return null;
			return new BindingList<prenda>(PrendaWS.listarPrendasPorGenero(cadena));
		}
        public BindingList<string> obtenerTallasUnicas()
        {
            try
            {
                string[] tallasArray = PrendaWS.obtenerTallasUnicas();
                return new BindingList<string>(tallasArray.ToList());
            }
            catch (Exception ex)
            {
                throw new Exception("Error al obtener las tallas únicas: " + ex.Message, ex);
            }
        }

        public BindingList<string> obtenerColoresUnicos()
        {
            try
            {
                string[] coloresArray = PrendaWS.obtenerColoresUnicos();
                return new BindingList<string>(coloresArray.ToList());
            }
            catch (Exception ex)
            {
                throw new Exception("Error al obtener los colores únicos: " + ex.Message, ex);
            }
        }
        public BindingList<string> obtenerTallasPorPrenda(int idPrenda)
        {
            try
            {
                string[] tallasArray = PrendaWS.obtenerTallasPorPrenda(idPrenda);
                return new BindingList<string>(tallasArray.ToList());
            }
            catch (Exception ex)
            {
                throw new Exception("Error al obtener las tallas específicas de la prenda: " + ex.Message, ex);
            }
        }

        public BindingList<string> obtenerColoresPorPrenda(int idPrenda)
        {
            try
            {
                string[] coloresArray = PrendaWS.obtenerColoresPorPrenda(idPrenda);
                return new BindingList<string>(coloresArray.ToList());
            }
            catch (Exception ex)
            {
                throw new Exception("Error al obtener los colores específicos de la prenda: " + ex.Message, ex);
            }
        }

    }
}
