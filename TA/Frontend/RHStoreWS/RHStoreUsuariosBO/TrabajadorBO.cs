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
	public class TrabajadorBO : BaseBO
	{
        public int insertar(string dni, string nombres, string apellidos, string correo, string contrasenha, string puesto, double sueldo, DateTime fechaIngreso, string horarioInicio, string horarioFin)
        {
            return TrabajadorWS.insertarTrabajador(dni, nombres, apellidos, correo, contrasenha, puesto, sueldo, fechaIngreso, horarioInicio, horarioFin);
        }

        public int modificar(int idUsuario, string dni, string nombres, string apellidos, string correo, string contrasenha, string puesto, double sueldo, DateTime fechaIngreso, string horarioInicio, string horarioFin)
        {
            return TrabajadorWS.modificarTrabajador(idUsuario, dni, nombres, apellidos, correo, contrasenha, puesto, sueldo, fechaIngreso, horarioInicio, horarioFin);
        }

        public int eliminar(int idTrabajador)
        {
            return TrabajadorWS.eliminarTrabajador(idTrabajador);
        }

        public BindingList<trabajador> listarTodos()
        {
            try
            {
                List<trabajador> trabajadores = TrabajadorWS.listarTrabajadoresTodos()?.ToList();
                return new BindingList<trabajador>(trabajadores ?? new List<trabajador>());
            }
            catch (Exception ex)
            {
                // Manejar la excepción (registrarla o notificar al usuario)
                return new BindingList<trabajador>(new List<trabajador>());
            }

        }

        public trabajador obtenerPorId(int idTrabajador)
        {
            return TrabajadorWS.obtenerTrabajadorPorId(idTrabajador);
        }

		public BindingList<trabajador> listarPorDniNombre(string cadena)
		{
			
            try
            {
                List<trabajador> trabajadores = TrabajadorWS.listarTrabajadoresPorDniNombre(cadena)?.ToList();
                return new BindingList<trabajador>(trabajadores ?? new List<trabajador>());
            }
            catch (Exception ex)
            {
                // Manejar la excepción (registrarla o notificar al usuario)
                return new BindingList<trabajador>(new List<trabajador>());
            }
        }
	}
}
