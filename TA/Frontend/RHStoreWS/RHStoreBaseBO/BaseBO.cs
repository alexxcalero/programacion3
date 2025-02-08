using RHStoreBaseBO.ServiciosWeb;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace RHStoreBaseBO
{
	public class BaseBO
	{
		private AdministradorWSClient administradorWS;
		private ClienteWSClient clienteWS;
		private TrabajadorWSClient trabajadorWS;
		private DireccionWSClient direccionWS;
		private CuponWSClient cuponWS;
		private PrendaWSClient prendaWS;
		private PromocionWSClient promocionWS;
		private UsuarioWSClient usuarioWS;
		private OrdenCompraWSClient ordenCompraWS;
		private TotalesWSClient totalesWS;
		private RecuperarContrasenhaWSClient recuperarContrasenhaWS;
        private PrendaSeleccionadaWSClient prendaSeleccionadaWS;
        private CarritoWSClient carritoWS;
        public BaseBO()
		{
			administradorWS = new AdministradorWSClient();
			clienteWS = new ClienteWSClient();
			trabajadorWS = new TrabajadorWSClient();
			direccionWS = new DireccionWSClient();
			cuponWS = new CuponWSClient();
            carritoWS = new CarritoWSClient();
            prendaWS = new PrendaWSClient();
			promocionWS = new PromocionWSClient();
			usuarioWS = new UsuarioWSClient();
			ordenCompraWS = new OrdenCompraWSClient();
			totalesWS = new TotalesWSClient();
			recuperarContrasenhaWS = new RecuperarContrasenhaWSClient();
            prendaSeleccionadaWS = new PrendaSeleccionadaWSClient();
        }

		public AdministradorWSClient AdministradorWS { get => administradorWS; set => administradorWS = value; }
		public ClienteWSClient ClienteWS { get => clienteWS; set => clienteWS = value; }
		public TrabajadorWSClient TrabajadorWS { get => trabajadorWS; set => trabajadorWS = value; }
		public DireccionWSClient DireccionWS { get => direccionWS; set => direccionWS = value; }
		public CuponWSClient CuponWS { get => cuponWS; set => cuponWS = value; }
		public PrendaWSClient PrendaWS { get => prendaWS; set => prendaWS = value; }
		public PromocionWSClient PromocionWS { get => promocionWS; set => promocionWS = value; }
		public UsuarioWSClient UsuarioWS { get => usuarioWS; set => usuarioWS = value; }
		public OrdenCompraWSClient OrdencompraWS { get => ordenCompraWS; set => ordenCompraWS = value; }
		public TotalesWSClient TotalesWS { get => totalesWS; set => totalesWS = value; }
		public RecuperarContrasenhaWSClient RecuperarContrasenhaWS { get => recuperarContrasenhaWS; set => recuperarContrasenhaWS = value; }
        public CarritoWSClient CarritoWS { get => carritoWS; set => carritoWS = value; }
        public PrendaSeleccionadaWSClient PrendaSeleccionadaWS { get => prendaSeleccionadaWS; set => prendaSeleccionadaWS = value; }
    }
}
