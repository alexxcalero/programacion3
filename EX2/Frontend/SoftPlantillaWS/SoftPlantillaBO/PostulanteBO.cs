using SoftPlantillaBaseBO;
using SoftPlantillaBaseBO.ServicioWS;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace SoftPlantillaBO
{
    public class PostulanteBO : BaseBO
    {
        public int insertar(universidad universidad, string dni, string nombre, string apellidoPaterno, double promedioAcumuladoPregrado, bool fueTercioSuperior, bool fueMiembroGrupoInvestigacion, bool fueDeportistaCalificado, modalidad modalidadPreferida)
        {
            return PostulanteWS.insertarPostulante(universidad, dni, nombre, apellidoPaterno, promedioAcumuladoPregrado, fueTercioSuperior, fueMiembroGrupoInvestigacion, fueDeportistaCalificado, modalidadPreferida);
        }
    }
}
