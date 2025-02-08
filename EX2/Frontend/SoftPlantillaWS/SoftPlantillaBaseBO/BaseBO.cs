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
		private PostulanteWSClient postulanteWS;
		private UniversidadWSClient universidadWS;

		// Constructor
		public BaseBO()
		{
			// Agregamos todas las referencias de servicios
			postulanteWS = new PostulanteWSClient();
			universidadWS = new UniversidadWSClient();
		}

		// Agregamos las propiedades
        public PostulanteWSClient PostulanteWS { get => postulanteWS; set => postulanteWS = value; }
        public UniversidadWSClient UniversidadWS { get => universidadWS; set => universidadWS = value; }
    }
}
