using RHStoreBaseBO;
using RHStoreBaseBO.ServiciosWeb;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace RHStoreUsuariosBO
{
	public class CuponBO : BaseBO
	{
        public int insertar(string codigo, string descripcion, double valorDescuento, DateTime fechaInicio, DateTime fechaFin, trabajador _trabajador)
        {
            return CuponWS.insertarCupon(codigo, descripcion, valorDescuento, fechaInicio, fechaFin, _trabajador);
        }

        public int modificar(int idCupon, string codigo, string descripcion, double valorDescuento, DateTime fechaInicio, DateTime fechaFin, trabajador _trabajador)
        {
            return CuponWS.modificarCupon(idCupon, codigo, descripcion, valorDescuento, fechaInicio, fechaFin, _trabajador);
        }

        public int eliminar(int idCupon)
        {
            return CuponWS.eliminarCupon(idCupon);
        }

        public BindingList<cupon> listarTodos()
        {
            try
            {
                List<cupon> cupones = CuponWS.listarCuponesTodos()?.ToList();
                return new BindingList<cupon>(cupones ?? new List<cupon>());
            }
            catch (Exception ex)
            {
                // Manejar la excepción (registrarla o notificar al usuario)
                return new BindingList<cupon>(new List<cupon>());
            }

        }

        public cupon obtenerPorId(int idCupon)
        {
            return CuponWS.obtenerCuponPorId(idCupon);
        }

        public BindingList<cupon> listarPorCodigoDescripcion(string cadena)
        {
            cupon[] cupones = CuponWS.listarCuponesPorCodigoDescripcion(cadena);
            return new BindingList<cupon>(cupones != null ? cupones.ToList() : new List<cupon>());
        }
        public int insertarCuponUsado(int idCliente, int idCupon, DateTime fecha)
        {
            return CuponWS.insertarCuponUsado(idCliente, idCupon, fecha);
        }
    }
}
