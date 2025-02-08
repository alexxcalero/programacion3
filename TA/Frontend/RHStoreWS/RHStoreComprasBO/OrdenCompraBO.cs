using RHStoreBaseBO.ServiciosWeb;
using RHStoreBaseBO;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace RHStoreComprasBO
{
	public class OrdenCompraBO : BaseBO
	{
        public int insertarSinCupon(DateTime fechaRegistro, estado _estado, string dni,
                    string correo, double subtotal, cliente _cliente, carrito _carrito)
        {
            return OrdencompraWS.insertarOrdenCompraSinCupon(fechaRegistro, _estado, dni, correo, subtotal, _cliente, _carrito);
        }

        public int insertarConCupon(DateTime fechaRegistro, estado _estado, string dni,
            string correo, double subtotal, cliente _cliente, cupon _cupon, carrito _carrito)
        {
            return OrdencompraWS.insertarOrdenCompraConCupon(fechaRegistro, _estado, dni, correo, subtotal, _cliente, _cupon, _carrito);
        }

        public int modificar(int IdOrdenCompra, DateTime fechaRegistro, DateTime fechaProcesado, DateTime fechaEntregado, DateTime fechaAnulado,
			estado _estado, string dni, string correo, double subtotal, cliente _cliente, string paypal_id)
		{
			return OrdencompraWS.modificarOrdenCompra(IdOrdenCompra, fechaRegistro, fechaProcesado, fechaEntregado, fechaAnulado, _estado, dni, correo, subtotal, _cliente, paypal_id);
		}

		public int eliminar(int idOrdenCompra)
		{
			return OrdencompraWS.eliminarOrdenCompra(idOrdenCompra);
		}

		public BindingList<ordenCompra> listarOrdenCompraTodos()
		{
			if (OrdencompraWS.listarOrdenCompraTodos() == null)
				return null;
			return new BindingList<ordenCompra>(OrdencompraWS.listarOrdenCompraTodos());
		}

        public BindingList<ordenCompra> listarPorIdCliente(int idCliente)
        {
            if (OrdencompraWS.listarPorIdCliente(idCliente) == null)
                return null;
            return new BindingList<ordenCompra>(OrdencompraWS.listarPorIdCliente(idCliente));
        }

        public ordenCompra obtenerPorId(int idOrdenCompra)
		{
			return OrdencompraWS.obtenerOrdenCompraPorId(idOrdenCompra);
		}

		public BindingList<ordenCompra> listarOrdenCompraPorEstado(string estado)
		{
			if (OrdencompraWS.listarOrdenPorEstado(estado) == null)
				return null;
			return new BindingList<ordenCompra>(OrdencompraWS.listarOrdenPorEstado(estado));
		}

		public int cambiarEstado(int idOrdenCompra, string cadena)
		{
			return OrdencompraWS.cambiarEstado(idOrdenCompra, cadena);
		}

        public int insertarPrendaSeleccionada(int idPrenda, int fidOrden, int cantidad, double precio)
        {
			return OrdencompraWS.insertarPrendaSeleccionada(idPrenda, fidOrden, cantidad, precio);

        }
        public string pagarConPaypal(int idOrden, string url_confirmacion, string url_cancelacion)
        {
            //esta funcion es lo mismo pero implementado en el back pero no funciona
            //en el test del back si funciona pero cuando se deployea en el glassfish deja de funcionar
            //return OrdencompraWS.pagarConPaypal(idOrden, url_confirmacion, url_cancelacion);
            
			PayPalAPI api = new PayPalAPI();
            ordenCompra orden = OrdencompraWS.obtenerOrdenCompraPorId(idOrden);
            string url = api.create_order(idOrden, idOrden, "USD", orden.subtotal, url_confirmacion, url_cancelacion, "RHStore");
			string order_id = api.extract_order_id_from_link(url);
			DateTime fechaActual = DateTime.Parse(DateTime.Now.ToString("yyyy-MM-dd"));
			this.modificar(idOrden, orden.fechaRegistro, orden.fechaProcesado, orden.fechaEntregado, orden.fechaAnulado, orden.estado, orden.dni, orden.correo, orden.subtotal, orden.cliente, order_id);
			
            return url;
        }

        public bool capturarPagoConPayPal(int idOrden)
        {
            PayPalAPI api = new PayPalAPI();
            ordenCompra orden = OrdencompraWS.obtenerOrdenCompraPorId(idOrden);
            DateTime fechaActual = DateTime.Parse(DateTime.Now.ToString("yyyy-MM-dd"));

            if (api.capture_order(orden.paypal_id))
			{
                this.modificar(idOrden, orden.fechaRegistro, fechaActual, orden.fechaEntregado, orden.fechaAnulado, estado.Procesado, orden.dni, orden.correo, orden.subtotal, orden.cliente, "-");
				return true;
            }

			return false;
        }
    }
}
