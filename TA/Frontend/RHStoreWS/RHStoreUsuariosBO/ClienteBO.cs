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
	public class ClienteBO : BaseBO
	{
        public int insertar(string dni, string nombres, string apellidos, string correo, string contrasenha, string telefono, DateTime fechaRegistro, bool recibePromociones)
        {
            return ClienteWS.insertarCliente(dni, nombres, apellidos, correo, contrasenha, telefono, fechaRegistro, recibePromociones);
        }

        public int modificar(int idUsuario, string dni, string nombres, string apellidos, string correo, string telefono, DateTime fechaRegistro, bool recibePromociones)
        {
            return ClienteWS.modificarCliente(idUsuario, dni, nombres, apellidos, correo, telefono, fechaRegistro, recibePromociones);
        }

        public int eliminar(int idCliente)
        {
            return ClienteWS.eliminarCliente(idCliente);
        }

        public BindingList<cliente> listarTodos()
        {
  
            try
            {
                List<cliente> clientes = ClienteWS.listarClientesTodos()?.ToList();
                return new BindingList<cliente>(clientes ?? new List<cliente>());
            }
            catch (Exception ex)
            {
                // Manejar la excepción (registrarla o notificar al usuario)
                return new BindingList<cliente>(new List<cliente>());
            }

        }

        public cliente obtenerPorId(int idCliente)
        {
            return ClienteWS.obtenerClientePorId(idCliente);
        }

		public BindingList<cliente> listarPorDniNombre(string cadena)
		{
            try
            {
                List<cliente> clientes = ClienteWS.listarClientesPorDniNombre(cadena)?.ToList();
                return new BindingList<cliente>(clientes ?? new List<cliente>());
            }
            catch (Exception ex)
            {
                // Manejar la excepción (registrarla o notificar al usuario)
                return new BindingList<cliente>(new List<cliente>());
            }

        }

		public int verificarCorreoExistente(string correo)
		{
			return ClienteWS.verificarCorreoExistente(correo);
		}
	}
}
