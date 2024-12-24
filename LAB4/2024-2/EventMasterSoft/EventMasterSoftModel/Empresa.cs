using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace EventMasterSoftModel
{
    public class Empresa
    {
        private int _idEmpresa;
        private string _nombre;
        private DateTime _fechaFundacion;
        private bool _activa;

        public Empresa() { }
        public Empresa(string nombre, DateTime fechaFundacion)
        {
            this._nombre = nombre;
            this._fechaFundacion = fechaFundacion;
        }

        public int IdEmpresa { get => _idEmpresa; set => _idEmpresa = value; }
        public string Nombre { get => _nombre; set => _nombre = value; }
        public DateTime FechaFundacion { get => _fechaFundacion; set => _fechaFundacion = value; }
        public bool Activa { get => _activa; set => _activa = value; }
    
    }
}