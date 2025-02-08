using RHStoreBaseBO.ServiciosWeb;
using RHStorePrendasBO;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data.SqlClient;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace RHStoreWS.Cliente
{

    public partial class Cliente : System.Web.UI.MasterPage
    {
        private PrendaBO prendaBO;
        private BindingList<prenda> listaDePrendas;
        // En Cliente.master.cs
        public bool ShowFullNav { get; set; } = true;
        public Cliente()
        {
            prendaBO = new PrendaBO();
        }

        protected void Page_Load(object sender, EventArgs e)
        {
            ConfigurarCabecera();
            ConfigurarBotonCarrito();
        }

        private void ConfigurarCabecera()
        {
            if (Session["clienteLogueado"] != null)
            {
                cliente clienteLogueado = (cliente)Session["clienteLogueado"];
                lblNombreCliente.Text = clienteLogueado.nombres;
                pnlUserLoggedIn.Visible = true;
                pnlLogin.Visible = false;
            }
            else
            {
                pnlUserLoggedIn.Visible = false;
                pnlLogin.Visible = true;
            }
        }
        // Método para configurar la visibilidad del botón del carrito
        private void ConfigurarBotonCarrito()
        {
            // Si la página actual es PerfilCliente.aspx, ocultar el botón del carrito
            if (Request.Url.AbsolutePath.ToLower().Contains("perfilcliente.aspx") || Request.Url.AbsolutePath.ToLower().Contains("direccionescliente.aspx"))
            {
                pnlCarrito.Visible = false; // Asegúrate de que el botón esté dentro de un Panel llamado pnlCarrito
                pnlUserLoggedIn.Visible = false;
            }
            else
            {
                pnlCarrito.Visible = true;
            }
        }
        private void CargarDatosUsuario()
        {
            // Lógica para cargar datos del usuario
            if (Session["Usuario"] != null)
            {
                string nombreUsuario = Session["Usuario"].ToString();
                // Aquí puedes actualizar el encabezado o cualquier otro control con el nombre del usuario
            }
        }
        protected string ObtenerImagen(int idPrenda)
        {
            // Obtener la imagen como bytes desde la base de datos
            byte[] imagenBytes = prendaBO.ObtenerImagen(idPrenda); // Implementa este método en PrendaBO
            if (imagenBytes != null)
            {
                // Convertir a Base64 para poder usar en src
                return "data:image/jpeg;base64," + Convert.ToBase64String(imagenBytes);
            }
            return string.Empty; // Devuelve una cadena vacía si no hay imagen
        }
        
        protected void lbBuscar_Click(object sender, EventArgs e)
        {
            string nombreBuscado = txtBuscar.Text.Trim();
            if (!string.IsNullOrEmpty(nombreBuscado))
            {
                Response.Redirect("ClienteHome.aspx?cadena=" + nombreBuscado);
            }
        }

        protected void RedirectToPage(string pageName)
        {
            Response.Redirect(pageName);
        }

        protected void btnInicio_Click(object sender, EventArgs e)
        {
            ShowFullNav = true;
            RedirectToPage("ClienteHome.aspx");
        }

        protected void btnIniciarSesion_Click(object sender, EventArgs e)
        {
            // Redirige a la página de inicio de sesión en Admin
            Response.Redirect("~/Admin/IniciarSesion.aspx");
        }

        protected void btnCarrito_Click(object sender, EventArgs e)
        {
            if (Session["clienteLogueado"] == null)
            {
                Response.Redirect("~/Admin/IniciarSesion.aspx");
            }
            else
            {
                Response.Redirect("Carrito.aspx");
            }
        }

        // Ejemplo en la página de inicio de sesión
        protected void btnLogin_Click(object sender, EventArgs e)
        {
            // Supón que el nombre del cliente se obtiene después de validar las credenciales
            string nombreCliente = "NombreDelCliente"; // Reemplaza con el nombre obtenido del login
            Session["NombreCliente"] = nombreCliente;

            // Redirecciona a ClienteHome.aspx
            Response.Redirect("ClienteHome.aspx");
        }

        /*protected void btnAgregarPrenda_Click(object sender, EventArgs e)
        {
            int idPrenda = int.Parse(((Button)sender).CommandArgument);
            prenda prenda = prendaBO.obtenerPorId(idPrenda);
            Session["idPrenda"] = idPrenda;
            Session["prenda"] = prenda;

            Response.Redirect("ProductoDetalle.aspx?executeEvent=true");
        }*/

    }
}