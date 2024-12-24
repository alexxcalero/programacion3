using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace MedicalSoftModel
{
    public abstract class AmbienteClinico : IConsultable
    {
        private int idAmbienteClinico;
        private double espacioMetrosCuadrados;
        private char torre;
        private int piso;
        private bool activo;
        public double EspacioMetrosCuadrados { get => espacioMetrosCuadrados; set => espacioMetrosCuadrados = value; }
        public char Torre { get => torre; set => torre = value; }
        public int Piso { get => piso; set => piso = value; }
        public bool Activo { get => activo; set => activo = value; }
        public int IdAmbienteClinico { get => idAmbienteClinico; set => idAmbienteClinico = value; }
        public abstract void mostrarDatos();
    }
}
