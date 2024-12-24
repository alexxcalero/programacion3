using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace EventMasterSoftModel
{
    public class Productora
    {
        private int _idProductora;
        private string _nombre;
        private bool _activa;

        public Productora() { }

        public Productora(int idProductora, string nombre)
        {
            this._idProductora = idProductora;
            this._nombre = nombre;
        }
        public Productora(string nombre)
        {
            this._nombre = nombre;
        }
        public Productora(int idProductora, string nombre, bool activa)
        {
            this._idProductora = idProductora;
            this._nombre = nombre;
            this._activa = activa;
        }
        public int IdProductora { get => _idProductora; set => _idProductora = value; }
        public string Nombre { get => _nombre; set => _nombre = value; }
        public bool Activa { get => _activa; set => _activa = value; }
        public override string ToString()
        {
            return _idProductora + ". " + _nombre;
        }
    }
}
