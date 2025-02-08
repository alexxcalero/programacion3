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
	public class DireccionBO : BaseBO
	{
        public int insertar(string direccion, string distrito, string provincia, string departamento, string codigoPostal, string referencia, cliente _cliente)
        {
            return DireccionWS.insertarDireccion(direccion, distrito, provincia, departamento, codigoPostal, referencia, _cliente);
        }

        public int modificar(int idDireccion, string direccion, string distrito, string provincia, string departamento, string codigoPostal, string referencia, cliente _cliente)
        {
            return DireccionWS.modificarDireccion(idDireccion, direccion, distrito, provincia, departamento, codigoPostal, referencia, _cliente);
        }

        public int eliminar(int idDireccion)
        {
            return DireccionWS.eliminarDireccion(idDireccion);
        }

        public BindingList<direccion> listarTodos()
        {
            try
            {
                // Llama al servicio web para obtener todas las direcciones
                direccion[] direccionesArray = DireccionWS.listarDireccionesTodas();

                // Si el array es nulo o vacío, inicializa una lista vacía
                if (direccionesArray == null || direccionesArray.Length == 0)
                {
                    return new BindingList<direccion>(); // Lista vacía
                }

                // Convierte el array a una lista y luego a un BindingList
                return new BindingList<direccion>(direccionesArray.ToList());
            }
            catch (Exception ex)
            {
                throw new Exception("Error al listar las direcciones: " + ex.Message, ex);
            }
        }

        public direccion obtenerPorId(int idDireccion)
        {
            try
            {
                // Llama al servicio web para obtener la dirección por ID
                return DireccionWS.obtenerDireccionPorId(idDireccion);
            }
            catch (Exception ex)
            {
                // Maneja excepciones en caso de errores
                throw new Exception("Error al obtener la dirección por ID: " + ex.Message, ex);
            }
        }
        public BindingList<direccion> listarPorIdCliente(int idCliente)
        {
            try
            {
                // Llama al servicio web para obtener direcciones por ID de cliente
                direccion[] direccionesArray = DireccionWS.listarDireccionesPorIdCliente(idCliente);

                // Si no hay datos, inicializa una lista vacía
                if (direccionesArray == null || direccionesArray.Length == 0)
                {
                    return new BindingList<direccion>();
                }

                // Convierte el array a BindingList
                return new BindingList<direccion>(direccionesArray.ToList());
            }
            catch (Exception ex)
            {
                throw new Exception("Error al listar direcciones por ID de cliente: " + ex.Message, ex);
            }
        }
    }
}
