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
	public class AdministradorBO : BaseBO
	{
        public int insertar(string dni, string nombres, string apellidos, string correo, string contrasenha, DateTime fechaCreacion)
        {
            return AdministradorWS.insertarAdministrador(dni, nombres, apellidos, correo, contrasenha, fechaCreacion);
        }

        public int modificar(int idUsuario, string dni, string nombres, string apellidos, string correo, string contrasenha, DateTime fechaCreacion)
        {
            return AdministradorWS.modificarAdministrador(idUsuario, dni, nombres, apellidos, correo, contrasenha, fechaCreacion);
        }

        public int eliminar(int idAdministrador)
        {
            return AdministradorWS.eliminarAdministrador(idAdministrador);
        }

        public BindingList<administrador> listarTodos()
        {

            try
            {
                List<administrador> administradores = AdministradorWS.listarAdministradoresTodos()?.ToList();
                return new BindingList<administrador>(administradores ?? new List<administrador>());
            }
            catch (Exception ex)
            {
                // Manejar la excepción (registrarla o notificar al usuario)
                return new BindingList<administrador>(new List<administrador>());
            }

        }

        public administrador obtenerPorId(int idAdministrador)
        {
            return AdministradorWS.obtenerAdministradorPorId(idAdministrador);
        }

		public BindingList<administrador> listarPorDniNombre(string cadena)
		{
            try
            {
                List<administrador> administradores = AdministradorWS.listarAdministradoresPorDniNombre(cadena)?.ToList();
                return new BindingList<administrador>(administradores ?? new List<administrador>());
            }
            catch (Exception ex)
            {
                // Manejar la excepción (registrarla o notificar al usuario)
                return new BindingList<administrador>(new List<administrador>());
            }
        }
	}
}
