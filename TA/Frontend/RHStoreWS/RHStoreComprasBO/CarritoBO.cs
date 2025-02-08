using RHStoreBaseBO;
using RHStoreBaseBO.ServiciosWeb;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace RHStoreComprasBO
{
    public class CarritoBO : BaseBO
    {
        public int insertar(int idCliente)
        {
            return this.CarritoWS.insertarCarrito(idCliente);
        }
        public int modificar(carrito carrito)
        {
            return this.CarritoWS.modificarCarrito(carrito);
        }

        public carrito obtenerPorId(int idUsuario)
        {
             return this.CarritoWS.obtenerCarritoPorIdCliente(idUsuario);
        }

        public int limpiarCarrito(int idCliente)
        {
            return this.CarritoWS.limpiarCarrito(idCliente);
        }
    }
}
