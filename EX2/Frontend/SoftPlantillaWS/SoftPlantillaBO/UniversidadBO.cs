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
    public class UniversidadBO : BaseBO
    {
        public BindingList<universidad> listarTodas()
        {
            universidad[] listaUniversidades = UniversidadWS.listarUniversidadesTodas();

            if (listaUniversidades == null)
                return new BindingList<universidad>();
            else
                return new BindingList<universidad>(listaUniversidades);
        }
    }
}
