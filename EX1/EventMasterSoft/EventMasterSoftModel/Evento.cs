using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace EventMasterSoftModel
{
    public class Evento
    {
        private int _idEvento;
        private Productora _productora;
        private char _clasificacion;
        private string _nombre;
        private double _costoRealizacion;
        private TipoEvento _tipoEvento;
        private DateTime _fechaRealizacion;
        private string _descripcion;
        private bool _permiteReingreso;
        private bool _permiteGrabacion;
        private byte[] _bannerPromocional;
        private bool _activo;
        public Evento() { }
        public Evento(int idEvento, Productora productora, char clasificacion, string nombre, double costoRealizacion, TipoEvento tipoEvento, DateTime fechaRealizacion, string descripcion, bool permiteReingreso, bool permiteGrabacion, byte[] bannerPromocional)
        {
            this._idEvento = idEvento;
            this._productora = productora;
            this._clasificacion = clasificacion;
            this._nombre = nombre;
            this._costoRealizacion = costoRealizacion;
            this._tipoEvento = tipoEvento;
            this._fechaRealizacion = fechaRealizacion;
            this._descripcion = descripcion;
            this._permiteReingreso = permiteReingreso;
            this._permiteGrabacion = permiteGrabacion;
            this._bannerPromocional = bannerPromocional;
        }
        public Evento(Productora productora, char clasificacion, string nombre, double costoRealizacion, TipoEvento tipoEvento, DateTime fechaRealizacion, string descripcion, bool permiteReingreso, bool permiteGrabacion, byte[] bannerPromocional)
        {
            this._productora = productora;
            this._clasificacion = clasificacion;
            this._nombre = nombre;
            this._costoRealizacion = costoRealizacion;
            this._tipoEvento = tipoEvento;
            this._fechaRealizacion = fechaRealizacion;
            this._descripcion = descripcion;
            this._permiteReingreso = permiteReingreso;
            this._permiteGrabacion = permiteGrabacion;
            this._bannerPromocional = bannerPromocional;
        }
        public Evento(int idEvento, Productora productora, char clasificacion, string nombre, double costoRealizacion, TipoEvento tipoEvento, DateTime fechaRealizacion, string descripcion, bool permiteReingreso, bool permiteGrabacion, byte[] bannerPromocional, bool activo)
        {
            this._idEvento = idEvento;
            this._productora = productora;
            this._clasificacion = clasificacion;
            this._nombre = nombre;
            this._costoRealizacion = costoRealizacion;
            this._tipoEvento = tipoEvento;
            this._fechaRealizacion = fechaRealizacion;
            this._descripcion = descripcion;
            this._permiteReingreso = permiteReingreso;
            this._permiteGrabacion = permiteGrabacion;
            this._bannerPromocional = bannerPromocional;
            this._activo = activo;
        }
        public int IdEvento { get => _idEvento; set => _idEvento = value; }
        public Productora Productora { get => _productora; set => _productora = value; }
        public char Clasificacion { get => _clasificacion; set => _clasificacion = value; }
        public string Nombre { get => _nombre; set => _nombre = value; }
        public double CostoRealizacion { get => _costoRealizacion; set => _costoRealizacion = value; }
        public TipoEvento TipoEvento { get => _tipoEvento; set => _tipoEvento = value; }
        public DateTime FechaRealizacion { get => _fechaRealizacion; set => _fechaRealizacion = value; }
        public string Descripcion { get => _descripcion; set => _descripcion = value; }
        public bool PermiteReingreso { get => _permiteReingreso; set => _permiteReingreso = value; }
        public bool PermiteGrabacion { get => _permiteGrabacion; set => _permiteGrabacion = value; }
        public byte[] BannerPromocional { get => _bannerPromocional; set => _bannerPromocional = value; }
        public bool Activo { get => _activo; set => _activo = value; }
        public void mostrarDatosGenerales()
        {
            System.Console.WriteLine("Nombre:" + _nombre + " - Productora:" + _productora.Nombre + " - Fecha Realizacion:" + _fechaRealizacion.ToString("dd-MM-yyyy"));
        }
        public void mostrarTodosDatos()
        {
            string cadena = "";
            cadena += "Id del Evento:" + _idEvento + "\n";
            cadena += "Nombre:" + _nombre + "\n";
            cadena += "Id Productora: " + _productora.IdProductora + "\n";
            cadena += "Nombre de la Productora: " + _productora.Nombre + "\n";
            cadena += "Clasificacion: " + _clasificacion + "\n";
            cadena += "Costo de Realizacion (S/.): " + _costoRealizacion.ToString("N2") + "\n";
            cadena += "TipoEvento: " + _tipoEvento + "\n";
            cadena += "Fecha Realizacion: " + _fechaRealizacion.ToString("dd-MM-yyyy") + "\n";
            cadena += "Descripcion: " + _descripcion + "\n";
            cadena += "Permite Reingreso: " + (_permiteReingreso ? "SI" : "NO") + "\n";
            cadena += "Permite Grabacion: " + (_permiteGrabacion ? "SI" : "NO") + "\n";
            cadena += "Banner Promocional: " + (_bannerPromocional != null ? "SI" : "NO") + "\n";
            System.Console.Write(cadena);
        }
    }
}
