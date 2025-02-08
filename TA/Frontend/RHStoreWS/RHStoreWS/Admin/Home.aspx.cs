using RHStoreBaseBO.ServiciosWeb;
using RHStoreComprasBO;
using RHStorePrendasBO;
using RHStoreUsuariosBO;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace RHStoreWS.Admin
{
    public partial class Home : System.Web.UI.Page
    {
        private TotalesBO totalesBO;
        private PrendaBO prendaBO;
        private PromocionBO promocionBO;
        private CuponBO cuponBO;

        public Home()
        {
            totalesBO = new TotalesBO();
            prendaBO = new PrendaBO();
            promocionBO = new PromocionBO();
            cuponBO = new CuponBO();
        }

        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                bool permiso = (bool)Session["permisoHome"];
                if (!permiso)
                {
                    Response.Redirect("../Cliente/ClienteHome.aspx");
                }

                CargarDatosUsuario();
                CargarValoresActuales();
                CargarStockProductos(); 
                CargarPrendasVendidas();        
                CargarPromocionesActivas();      
                CargarCuponesActivos();          
                CargarGraficos(DateTime.Now.Year);
            }
        }

        private void CargarDatosUsuario()
        {
            if (Session["administradorLogueado"] != null)
            {
                administrador _administrador = (administrador)Session["administradorLogueado"];
                lblNombreUsuario.Text = _administrador.nombres + " " + _administrador.apellidos;
            }
            else if (Session["trabajadorLogueado"] != null)
            {
                trabajador _trabajador = (trabajador)Session["trabajadorLogueado"];
                lblNombreUsuario.Text = _trabajador.nombres + " " + _trabajador.apellidos;
            }
        }

        private void CargarValoresActuales()
        {
            var totales = totalesBO.obtenerValoresTotales();
            lblPrendas.Text = "Total Prendas: " + totales.totalPrendas;
            lblPromociones.Text = "Promociones Activas: " + totales.promocionesActivas;
            lblCupones.Text = "Cupones Activos: " + totales.cuponesActivos;
            lblClientes.Text = "Clientes Activos: " + totales.clientesActivos;
        }

        private void CargarStockProductos()
        {
            var listaPrendas = prendaBO.listarTodos().Take(5).ToList(); // Limitar a los primeros 5
            rptStockProductos.DataSource = listaPrendas;
            rptStockProductos.DataBind();
        }

        protected void ddlYear_SelectedIndexChanged(object sender, EventArgs e)
        {
            int anhoSeleccionado = int.Parse(ddlYear.SelectedValue);
            CargarGraficos(anhoSeleccionado);
        }

        private void CargarGraficos(int anho)
        {
            List<int> datosPrendas = new List<int>();
            List<int> datosPromociones = new List<int>();
            List<int> datosCupones = new List<int>();
            List<int> datosClientes = new List<int>();

            // Obtener los valores por cada mes para el año seleccionado
            for (int mes = 1; mes <= 12; mes++)
            {
                var totales = totalesBO.obtenerValoresTotalesPorMes(anho, mes);
                datosPrendas.Add(totales.totalPrendas);
                datosPromociones.Add(totales.promocionesActivas);
                datosCupones.Add(totales.cuponesActivos);
                datosClientes.Add(totales.clientesActivos);
            }

            // Llamada a JavaScript para cargar los gráficos
            ClientScript.RegisterStartupScript(this.GetType(), "cargarGraficos",
                $"cargarDatosGrafico([{string.Join(",", datosPrendas)}], 'chartPrendas', 'Total Prendas', 'rgba(74, 144, 226, 0.6)');" +
                $"cargarDatosGrafico([{string.Join(",", datosPromociones)}], 'chartPromociones', 'Promociones Activas', 'rgba(67, 160, 71, 0.6)');" +
                $"cargarDatosGrafico([{string.Join(",", datosCupones)}], 'chartCupones', 'Cupones Activos', 'rgba(255, 193, 7, 0.6)');" +
                $"cargarDatosGrafico([{string.Join(",", datosClientes)}], 'chartClientes', 'Clientes Activos', 'rgba(244, 67, 54, 0.6)');", true);
        }
        private void CargarPrendasVendidas()
        {
            var prendasVendidas = prendaBO.listarTodos().Take(5).ToList(); // Limitar a los primeros 5
            rptPrendasVendidas.DataSource = prendasVendidas;
            rptPrendasVendidas.DataBind();
        }

        private void CargarPromocionesActivas()
        {
            var promocionesActivas = promocionBO.listarTodos().Take(3).ToList(); // Limitar a los primeros 3
            rptPromocionesActivas.DataSource = promocionesActivas;
            rptPromocionesActivas.DataBind();
        }

        private void CargarCuponesActivos()
        {
            var cuponesActivos = cuponBO.listarTodos().Take(3).ToList(); // Limitar a los primeros 3
            rptCuponesActivos.DataSource = cuponesActivos;
            rptCuponesActivos.DataBind();
        }


    }
}
