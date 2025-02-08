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
    public partial class ProductoDetalle : System.Web.UI.Page
    {
        private PrendaBO prendaBO = new PrendaBO();
        private CarritoBO carritoBO = new CarritoBO();
        private PrendaSeleccionadaBO prendaSeleccionadaBO = new PrendaSeleccionadaBO();

        protected void Page_Load(object sender, EventArgs e)
        {
            //((Cliente)Master).ShowFullNav = false;
            if (!IsPostBack)
            {
                lblMensaje.Visible = false;
                int idPrenda = Convert.ToInt32(Request.QueryString["idPrenda"]);
                Session["idPrenda"] = idPrenda;
                if (idPrenda > 0)
                {
                    // Llamar al método para obtener la prenda por su ID y mostrarla
                    MostrarDetallePrenda(idPrenda);
                }
            }
        }

        private void MostrarDetallePrenda(int idPrenda)
        {
            prenda prenda = prendaBO.obtenerPorId(idPrenda);
            Session["prenda"] = prenda;

            if (prenda != null)
            {
                lblNombre.Text = prenda.nombre;
                lblDescripcion.Text = prenda.descripcion;

                if (prenda.precioDescontado > 0)
                {
                    lblPrecio.Text = "S/ " + prenda.precioDescontado.ToString("F2");
                    lblPrecioOriginal.Text = "S/ " + prenda.precioOriginal.ToString("F2");
                }
                else
                {
                    lblPrecio.Text = "S/ " + prenda.precioOriginal.ToString("F2");
                    lblPrecioOriginal.Text = "";
                }

                imgProducto.ImageUrl = "data:image/jpeg;base64," + Convert.ToBase64String(prenda.imagen);

                // Cargar colores y tallas dinámicamente
                CargarColores(idPrenda);
                CargarTallas(idPrenda);
            }
        }
        private void CargarColores(int idPrenda)
        {
            try
            {
                BindingList<string> colores = prendaBO.obtenerColoresPorPrenda(idPrenda);
                colorContainer.InnerHtml = ""; // Limpia el contenedor de colores

                foreach (string color in colores)
                {
                    // Agrega el texto del color dentro de un div
                    colorContainer.InnerHtml += $"<div>{color}</div>";
                }
            }
            catch (Exception ex)
            {
                lblMensaje.Text = "Error al cargar colores: " + ex.Message;
                lblMensaje.Visible = true;
            }
        }

        private void CargarTallas(int idPrenda)
        {
            try
            {
                BindingList<string> tallas = prendaBO.obtenerTallasPorPrenda(idPrenda);
                sizeContainer.InnerHtml = ""; // Limpia el contenedor de tallas

                foreach (string talla in tallas)
                {
                    // Agrega un div con la talla dinámica
                    sizeContainer.InnerHtml += $"<div class='size-box'>{talla}</div>";
                }
            }
            catch (Exception ex)
            {
                lblMensaje.Text = "Error al cargar tallas: " + ex.Message;
                lblMensaje.Visible = true;
            }
        }
        protected void btnDecrementar_Click(object sender, EventArgs e)
        {
            int cantidad = int.Parse(txtCantidad.Text);
            if (cantidad > 1)
            {
                cantidad -= 1;
                txtCantidad.Text = cantidad.ToString();
            }
        }

        protected void btnIncrementar_Click(object sender, EventArgs e)
        {
            int cantidad = int.Parse(txtCantidad.Text);
            prenda prenda = (prenda)Session["prenda"];
            int stock = prenda.stock;
            if (cantidad != stock)
            {
                cantidad += 1;
                txtCantidad.Text = cantidad.ToString();
            }
        }


        protected void btnAgregarCarrito_Click(object sender, EventArgs e)
        {

            if (Session["clienteLogueado"] == null)
            {
                Response.Redirect("~/Admin/IniciarSesion.aspx");
            }
            else
            {
                bool cuponActivo = (bool)Session["cuponActivo"];
                if (cuponActivo == true)
                {
                    lblMensaje.Text = "No es posible realizar esta acción con un cupón en sesión.";
                    lblMensaje.Visible = true;
                    return;
                }

                int idPrenda = (int)Session["idPrenda"];
                int cantidad = int.Parse(txtCantidad.Text);
                carrito carrito = (carrito)Session["carritoUsuario"];
                int fidCarrito = (int)carrito.idCarrito;
                prenda prenda = (prenda)Session["prenda"];
                double precioUnitario = prenda.precioOriginal;
                if (prenda.precioDescontado != 0)
                {
                    precioUnitario = prenda.precioDescontado;
                }
                double precio = cantidad * precioUnitario;
                prendaSeleccionadaBO.insertar(idPrenda, fidCarrito, cantidad, precio);

                carrito.precioTotal += precio;
                carrito.cantidadTotal += cantidad;
                carritoBO.modificar(carrito);

                // Redirigir a Carrito.aspx después de agregar el producto
                Response.Redirect("Carrito.aspx");
            }


        }

    }
}