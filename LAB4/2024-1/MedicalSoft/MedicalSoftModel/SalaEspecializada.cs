using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace MedicalSoftModel
{
    public class SalaEspecializada : AmbienteClinico
    {
        private string nombre;
        private bool poseeEquipamientoImagenologia;
        private TipoSala tipoSala;
        public string Nombre { get => nombre; set => nombre = value; }
        public bool PoseeEquipamientoImagenologia { get => poseeEquipamientoImagenologia; set => poseeEquipamientoImagenologia = value; }
        public TipoSala TipoSala { get => tipoSala; set => tipoSala = value; }
        public override void mostrarDatos()
        {
            System.Console.WriteLine(IdAmbienteClinico + ". SALA ESPECIALIZADA:" + nombre + " - TORRE:" + Torre + " - PISO:" + Piso + " - EQ. IMAGENOLOGIA:" + (poseeEquipamientoImagenologia ? "SI" : "NO") + " - ESPACIO:" + EspacioMetrosCuadrados + " - TIPO SALA:" + TipoSala);
        }
    }
}
