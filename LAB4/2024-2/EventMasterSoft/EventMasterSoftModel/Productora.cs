using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace EventMasterSoftModel
{
    public class Productora : Empresa
    {
        private int _numeroFilialesEnPeru;
        private double _presupuestoAnual;
        private bool _ofreceAlquilerEquipos;
        private TipoEspecializacion _tipoEspecializacion;

        public Productora() { }
        public Productora(string nombre, DateTime fechaFundacion, int numeroFilialesEnPeru, double presupuestoAnual, bool ofreceAlquilerEquipos, TipoEspecializacion tipoEspecializacion) : base (nombre,fechaFundacion) {
            this.NumeroFilialesEnPeru = numeroFilialesEnPeru;
            this.PresupuestoAnual = presupuestoAnual;
            this.OfreceAlquilerEquipos = ofreceAlquilerEquipos;
            this.TipoEspecializacion = tipoEspecializacion;
        }
        public int NumeroFilialesEnPeru { get => _numeroFilialesEnPeru; set => _numeroFilialesEnPeru = value; }
        public double PresupuestoAnual { get => _presupuestoAnual; set => _presupuestoAnual = value; }
        public bool OfreceAlquilerEquipos { get => _ofreceAlquilerEquipos; set => _ofreceAlquilerEquipos = value; }
        public TipoEspecializacion TipoEspecializacion { get => _tipoEspecializacion; set => _tipoEspecializacion = value; }

        public override string ToString()
        {
            return this.IdEmpresa + ". PRODUCTORA:" + this.Nombre + " - " + "FF:" + this.FechaFundacion.ToString("dd-MM-yyyy") + " - #Filiales:" + this.NumeroFilialesEnPeru + " - PA:" + this.PresupuestoAnual.ToString() + " - OF.ALQ.EQ.:" + (this.OfreceAlquilerEquipos == true ? "SI" : "NO") + " - TIPO ESP.:" + this.TipoEspecializacion;
        }

        public string mostrarDatos()
        {
            return this.IdEmpresa + ". PRODUCTORA:" + this.Nombre + " - " + "FF:" + this.FechaFundacion.ToString("dd-MM-yyyy") + " - #Filiales:" + this.NumeroFilialesEnPeru + " - PA:" + this.PresupuestoAnual.ToString() + " - OF.ALQ.EQ.:" + (this.OfreceAlquilerEquipos == true ? "SI" : "NO") + " - TIPO ESP.:" + this.TipoEspecializacion;
        }
    }
}
