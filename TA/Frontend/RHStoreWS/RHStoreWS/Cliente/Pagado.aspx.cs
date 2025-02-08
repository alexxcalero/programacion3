using RHStoreBaseBO.ServiciosWeb;
using RHStoreComprasBO;
using System;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace RHStoreWS.Cliente
{
    public partial class Pagado : System.Web.UI.Page
    {
        private OrdenCompraBO ordenCompraBO = new OrdenCompraBO();
        private CarritoBO carritoBO = new CarritoBO();
        protected void Page_Load(object sender, EventArgs e)
        {
            string idOrden_str = Request.QueryString["idOrden"];
            string canceled = Request.QueryString["canceled"];

            if (!IsPostBack)
            {
                int idOrden = int.Parse(idOrden_str);

                if (canceled == "0")
                {
                    if (ordenCompraBO.capturarPagoConPayPal(idOrden))
                    {
                        string cliente = Request.QueryString["idCliente"];
                        int idCliente = int.Parse(cliente);
                        ordenCompraBO.cambiarEstado(idOrden, "Procesado");
                        lblMensaje.Text = "¡Gracias por tu compra!";
                        carritoBO.limpiarCarrito(idCliente);
                    }
                    else
                    {
                        ordenCompraBO.cambiarEstado(idOrden, "Anulado");
                        lblMensaje.Text = "Error en la transaccion";
                    }
                    
                }
                else
                {
                    ordenCompraBO.cambiarEstado(idOrden, "Anulado");
                    lblMensaje.Text = "Compra cancelada";
                }
                
                lblMensaje.Visible = true;
            }
        }

        protected void btnVolver_Click(object sender, EventArgs e)
        {

            Response.Redirect("~/Cliente/ClienteHome.aspx");
        }
    }
}
