using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace MedicalSoftModel
{
    public class Consultorio : AmbienteClinico
    {
        private string numero;
        public string Numero { get => numero; set => numero = value; }
        public override void mostrarDatos()
        {
            System.Console.WriteLine("CONSULTORIO:" + numero + " - TORRE:" + Torre + " - PISO:" + Piso);
        }
    }
}
