using SoftPlantillaBaseBO.ServicioWS;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace SoftPlantillaBaseBO
{
	public class BaseBO
	{
		// Todas las referencias de servicios
		private PersonaWSClient personaWS;

		// Constructor
		public BaseBO()
		{
			// Agregamos todas las referencias de servicios
			personaWS = new PersonaWSClient();
		}

		// Agregamos las propiedades
		public PersonaWSClient PersonaWS { get => personaWS; set => personaWS = value; }
	}
}
