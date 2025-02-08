using SoftPlantillaBaseBO;
using SoftPlantillaBaseBO.ServicioWS;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace SoftPlantillaBO
{
	public class PersonaBO : BaseBO
	{
		public int insertar(string nombre, string apellido, double altura, tipo tipo, DateTime fechaNacimiento)
		{
			return PersonaWS.insertarPersona(nombre, apellido, altura, tipo, fechaNacimiento);
		}

		public int modificar(int idPersona, string nombre, string apellido, double altura, tipo tipo, DateTime fechaNacimiento)
		{
			return PersonaWS.modificarPersona(idPersona, nombre, apellido, altura, tipo, fechaNacimiento);
		}

		public int eliminar(int idPersona)
		{
			return PersonaWS.eliminarPersona(idPersona);
		}

		public BindingList<persona> listarTodas()
		{
			persona[] personas = PersonaWS.listarPersonasTodas();
			if (personas == null)
				return new BindingList<persona>();
			else
				return new BindingList<persona>(personas);
		}

		public persona obtenerPorId(int idPersona)
		{
			return PersonaWS.obtenerPersonaPorId(idPersona);
		}
	}
}
