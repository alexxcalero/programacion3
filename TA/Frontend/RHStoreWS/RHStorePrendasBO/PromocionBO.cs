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
	public class PromocionBO : BaseBO
	{
        public int insertar(string nombre, string descripcion, double valorDescuento, tipoPromocion tipo, DateTime fechaInicio, DateTime fechaFin, prenda[] prendasSeleccionadas, trabajador _trabajador)
        {
            return PromocionWS.insertarPromocion(nombre, descripcion, valorDescuento, tipo, fechaInicio, fechaFin, prendasSeleccionadas, _trabajador);
        }
        public int modificar(int idPromocion, string nombre, string descripcion, double valorDescuento, tipoPromocion tipo, DateTime fechaInicio, DateTime fechaFin, prenda[] prendasSeleccionadas, trabajador _trabajador)
        {
            return PromocionWS.modificarPromocion(idPromocion, nombre, descripcion, valorDescuento, tipo, fechaInicio, fechaFin, prendasSeleccionadas, _trabajador);
        }
        public int eliminar(int idPromocion)
        {
            return PromocionWS.eliminarPromocion(idPromocion);
        }

        public BindingList<promocion> listarTodos()
        {
            return new BindingList<promocion>(PromocionWS.listarPromocionesTodas());
        }

        public promocion obtenerPorId(int idPromocion)
        {
            return PromocionWS.obtenerPromocionPorId(idPromocion);
        }

		public BindingList<promocion> listarPorNombreDescripcion(string cadena)
		{
			return new BindingList<promocion>(PromocionWS.listarPromocionesPorNombreDescripcion(cadena));
		}

		public BindingList<prenda> listarPrendaAgregadas(int idPromocion)
		{
			// Obtener la lista de prendas desde el servicio y convertir a List<prenda>
			List<prenda> prendas = PromocionWS.listarPrendasAgregadas(idPromocion)?.ToList();

			// Si prendas es null, inicializar con una lista vacía
			return new BindingList<prenda>(prendas ?? new List<prenda>());
		}
	}
}
