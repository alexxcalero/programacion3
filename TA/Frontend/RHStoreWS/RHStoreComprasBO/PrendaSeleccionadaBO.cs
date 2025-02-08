using RHStoreBaseBO;
using RHStoreBaseBO.ServiciosWeb;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace RHStoreComprasBO
{
    public class PrendaSeleccionadaBO : BaseBO
    {
        /*
        public int insertar(prendaSeleccionada prenda)
        {
            return this.PrendaSeleccionadaWS.insertarPrendaSeleccionada(prenda);
        }
        */

        public int insertar(int idPrenda, int fidCarrito, int cantidad, double precio)
        {
            return this.PrendaSeleccionadaWS.insertarPrendaSeleccionada(idPrenda, fidCarrito, cantidad, precio);
        }

        public int modificar(prendaSeleccionada prenda)
        {
            return this.PrendaSeleccionadaWS.modificarPrendaSeleccionada(prenda);  //la prenda ya modificada
        }
        public int eliminar(int idPrenda, int idCarrito)
        {
            return this.PrendaSeleccionadaWS.eliminarPrendaSeleccionada(idPrenda, idCarrito);
        }

        public prendaSeleccionada obtenerPorId(int idPrenda, int idCarrito)
        {
            return this.PrendaSeleccionadaWS.obtenerPrendaSeleccionadaPorId(idPrenda, idCarrito);
        }
        public BindingList<prendaSeleccionada> listarPrendasPorCarrito(int idCarrito)
        {
            prendaSeleccionada[] prendas = this.PrendaSeleccionadaWS.listarPrendasIdCarrito(idCarrito);
            return new BindingList<prendaSeleccionada>(prendas != null ? prendas.ToList() : new List<prendaSeleccionada>());
        }

        public BindingList<prendaSeleccionada> listarPrendasIdOrden(int idOrden)
        {
            prendaSeleccionada[] prendas = this.PrendaSeleccionadaWS.listarPrendasIdOrden(idOrden);
            return new BindingList<prendaSeleccionada>(prendas != null ? prendas.ToList() : new List<prendaSeleccionada>());
        }
        public int aplicarCuponLista(int idCarrito, double descuento)
        {
            return this.PrendaSeleccionadaWS.aplicarCuponLista(idCarrito, descuento);
        }

    }
}
