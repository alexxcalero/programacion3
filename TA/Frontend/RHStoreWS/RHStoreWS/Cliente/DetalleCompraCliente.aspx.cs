using RHStoreBaseBO.ServiciosWeb;
using RHStoreComprasBO;
using RHStorePrendasBO;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace RHStoreWS.Cliente
{
    public partial class DetalleCompraCliente : System.Web.UI.Page
    {
        private cliente _cliente;
        private PrendaSeleccionadaBO psBO;
        private PrendaBO prendaBO;

        public DetalleCompraCliente()
        {
            prendaBO = new PrendaBO();
            psBO = new PrendaSeleccionadaBO();
        }
        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                if (Session["clienteLogueado"] != null)
                {
                    int idOrden = int.Parse(Request.QueryString["idOrden"]);
                    _cliente = (cliente)Session["clienteLogueado"];
                    LoadCompra(idOrden);
                }
                else
                {
                    // Redirigir al inicio de sesión si no hay cliente logueado
                    Response.Redirect("../Admin/IniciarSesion.aspx");
                }
            }
        }

        protected void LoadCompra(int idOrden)
        {
            BindingList<prendaSeleccionada> prendasSeleccionadas = psBO.listarPrendasIdOrden(idOrden);

            List<Object> listaPrendasDetalles = new List<Object>();
            foreach (prendaSeleccionada prendaSel in prendasSeleccionadas)
            {
                prenda prenda = prendaBO.obtenerPorId(prendaSel.idPrendaSeleccionada);
                string imagenUrl = ConvertirImagenABase64(prenda.imagen);

                listaPrendasDetalles.Add(new
                {
                    prendaSel.idPrendaSeleccionada,
                    Nombre = prenda.nombre,
                    ImagenUrl = imagenUrl,
                    Precio = prendaSel.precio.ToString("F2"),
                    prendaSel.cantidad
                });
            }

            rptPrendasSeleccionadas.DataSource = listaPrendasDetalles;
            rptPrendasSeleccionadas.DataBind();
            rptPrendasSeleccionadas.Visible = true;
        }
        private string ConvertirImagenABase64(byte[] imagen)
        {
            if (imagen != null)
            {
                return "data:image/jpeg;base64," + Convert.ToBase64String(imagen);
            }
            return string.Empty;
        }

        protected void btnVolverPerfil_Click(object sender, EventArgs e)
        {
            Response.Redirect("~/Cliente/PerfilCliente.aspx");
        }

    }
}