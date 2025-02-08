using RHStoreBaseBO.ServiciosWeb;
using RHStoreUsuariosBO;
using RHStoreComprasBO;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using RHStorePrendasBO;

namespace RHStoreWS.Admin
{
    public partial class CrudOrdenes : System.Web.UI.Page
    {
        private ordenCompra _orden;
        private OrdenCompraBO ordenBO;
        private ClienteBO clienteBO;
        private bool estaModificando;

        public CrudOrdenes()
        {
            ordenBO = new OrdenCompraBO();
            clienteBO = new ClienteBO();
        }

        protected void Page_Load(object sender, EventArgs e)
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

            string accion = Request.QueryString["accion"];
            if (accion != null && accion == "visualizar")
            {
                lblTitulo.Text = "Visualización de Orden de Venta";
                _orden = (ordenCompra)Session["orden"];
                cargarDatosDeLaBD();
                estaModificando = true;
            }
        }

        protected void cargarDatosDeLaBD()
        {
            if (!IsPostBack)
            {
                txtIdOrden.Text = _orden.idOrden.ToString();
                txtIdOrden.Enabled = false;

                //Cliente
                txtIdCliente.Text = _orden.cliente.nombres + " " + _orden.cliente.apellidos;

                dtpFechaRegistro.Value = _orden.fechaRegistro != DateTime.MinValue ? _orden.fechaRegistro.ToString("yyyy-MM-dd") : "";
                dtpFechaProcesado.Value = _orden.fechaProcesado != DateTime.MinValue ? _orden.fechaProcesado.ToString("yyyy-MM-dd") : "";
                dtpFechaEntregado.Value = _orden.fechaEntregado != DateTime.MinValue ? _orden.fechaEntregado.ToString("yyyy-MM-dd") : "";
                dtpFechaAnulado.Value = _orden.fechaAnulado != DateTime.MinValue ? _orden.fechaAnulado.ToString("yyyy-MM-dd") : "";

                dtpFechaRegistro.Disabled = true;
                dtpFechaProcesado.Disabled = true;
                dtpFechaEntregado.Disabled = true;
                dtpFechaAnulado.Disabled = true;

                if (_orden.estado.ToString().Equals("Registrado"))
                    rbRegistrado.Checked = true;
                else if (_orden.estado.ToString().Equals("Procesado"))
                    rbProcesado.Checked = true;
                else if (_orden.estado.ToString().Equals("Entregado"))
                    rbEntregado.Checked = true;
                else
                    rbAnulado.Checked = true;

                rbRegistrado.Disabled = true;
                rbProcesado.Disabled = true;
                rbEntregado.Disabled = true;
                rbAnulado.Disabled = true;

                txtDniNew.Text = _orden.dni.ToString();
                txtCorreoNew.Text = _orden.correo;
                txtSubtotalNew.Text = _orden.subtotal.ToString();

                txtDniNew.Enabled = false;
                txtCorreoNew.Enabled = false;
                txtSubtotalNew.Enabled = false;
            }          
        }

        protected void lbRegresar_Click(object sender, EventArgs e)
        {
            Response.Redirect("GestionarOrdenes.aspx");
        }       
    }
}
