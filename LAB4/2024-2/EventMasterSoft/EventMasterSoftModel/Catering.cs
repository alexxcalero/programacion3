using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace EventMasterSoftModel
{
    public class Catering : Empresa
    {
        private int _capacidadServicio;
        private bool _ofreceDecoracionAmbientacion;
        private bool _ofreceAlimentacionEspecial;

        public Catering() { }
        public Catering(string nombre, DateTime fechaFundacion, int capacidadServicio, bool ofreceDecoracionAmbientacion, bool ofreceAlimentacionEspecial) : base(nombre,fechaFundacion)
        {
            this._capacidadServicio = capacidadServicio;
            this._ofreceDecoracionAmbientacion = ofreceDecoracionAmbientacion;
            this._ofreceAlimentacionEspecial = ofreceAlimentacionEspecial;
        }

        public int CapacidadServicio { get => _capacidadServicio; set => _capacidadServicio = value; }
        public bool OfreceDecoracionAmbientacion { get => _ofreceDecoracionAmbientacion; set => _ofreceDecoracionAmbientacion = value; }
        public bool OfreceAlimentacionEspecial { get => _ofreceAlimentacionEspecial; set => _ofreceAlimentacionEspecial = value; }
    }
}
