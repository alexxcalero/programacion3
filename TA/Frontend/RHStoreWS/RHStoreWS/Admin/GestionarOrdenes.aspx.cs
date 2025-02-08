using RHStoreBaseBO.ServiciosWeb;
using RHStoreComprasBO;
using System;
using System.ComponentModel;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace RHStoreWS.Admin
{
    public partial class GestionarOrdenes : System.Web.UI.Page
    {
        private OrdenCompraBO ordenBO;
        private BindingList<ordenCompra> listaDeOrdenes;

        public GestionarOrdenes()
        {
            ordenBO = new OrdenCompraBO();
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

            listaDeOrdenes = ordenBO.listarOrdenCompraTodos();
            dgvOrdenCompra.DataSource = listaDeOrdenes;
            dgvOrdenCompra.DataBind();
        }

        protected void lbBuscar_Click(object sender, EventArgs e)
        {
            string cadena = txtNombreDescripcion.Text;
            listaDeOrdenes = ordenBO.listarOrdenCompraPorEstado(cadena);
            dgvOrdenCompra.DataSource = listaDeOrdenes;
            dgvOrdenCompra.DataBind();
        }

        protected void lbVisualizar_Click(object sender, EventArgs e)
        {
            int idOrdenCompra = Int32.Parse(((LinkButton)sender).CommandArgument);
            ordenCompra _orden = ordenBO.obtenerPorId(idOrdenCompra);
            Session["orden"] = _orden;
            Response.Redirect("CrudOrdenes.aspx?accion=visualizar");
        }

        protected void lbProcesar_Click(object sender, EventArgs e)
        {
            int idOrdenCompra = Int32.Parse(((LinkButton)sender).CommandArgument);
            ordenBO.cambiarEstado(idOrdenCompra, "Procesado");
            Response.Redirect("GestionarOrdenes.aspx");
        }

        protected void lbEntregar_Click(object sender, EventArgs e)
        {
            int idOrdenCompra = Int32.Parse(((LinkButton)sender).CommandArgument);
            ordenBO.cambiarEstado(idOrdenCompra, "Entregado");
            Response.Redirect("GestionarOrdenes.aspx");
        }

        protected void lbEliminar_Click(object sender, EventArgs e)
        {
            int idOrdenCompra = Int32.Parse(((LinkButton)sender).CommandArgument);
            ordenBO.eliminar(idOrdenCompra);
            Response.Redirect("GestionarOrdenes.aspx");
        }

        protected void dgvOrdenCompra_PageIndexChanging(object sender, GridViewPageEventArgs e)
        {
            dgvOrdenCompra.PageIndex = e.NewPageIndex;
            dgvOrdenCompra.DataBind();
        }
    }
}
