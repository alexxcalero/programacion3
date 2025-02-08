using RHStoreBaseBO.ServiciosWeb;
using RHStoreUsuariosBO;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace RHStoreWS.Admin
{
    public partial class CambiarContrasenha : System.Web.UI.Page
    {
        private UsuarioBO usuarioBO;
        private RecuperarContrasenhaBO recuperarContrasenhaBO;

        public CambiarContrasenha()
        {
            usuarioBO = new UsuarioBO();
            recuperarContrasenhaBO = new RecuperarContrasenhaBO();
        }

        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
            }
        }

        protected void btnCambiarContrasenha_Click(object sender, EventArgs e)
        {
            string nuevaContrasenha = txtNuevaContrasenha.Text.Trim();
            string confirmarContrasenha = txtConfirmarContrasenha.Text.Trim();

            if (nuevaContrasenha != confirmarContrasenha)
            {                
                lblErrorContrasenhasNuevas.Visible = true;
                return;
            }

            // Obtener el token de la URL
            string token = Request.QueryString["token"];

            try
            {
                // Obtener el ID del usuario asociado al token
                int idUsuario = recuperarContrasenhaBO.obtenerIdUsuarioPorToken(token);

                if (idUsuario > 0)
                {
                    // Cambiar la contraseña del usuario
                    int resultado = usuarioBO.cambiarContrasenha(idUsuario, nuevaContrasenha);

                    if (resultado > 0)
                    {
                        Response.Redirect("IniciarSesion.aspx");
                    }                  
                }
                else
                {
                    lblError.Visible = true;
                }
            }
            catch (Exception ex)
            {
                lblError.Text = $"Ocurrió un error: {ex.Message}";
                lblError.Visible = true;
            }
        }
    }
}
