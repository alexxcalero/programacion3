using RHStoreBaseBO.ServiciosWeb;
using RHStoreUsuariosBO;
using System;
using System.Net.Mail;
using System.Text.RegularExpressions;
using System.Web.UI;

namespace RHStoreWS.Admin
{
    public partial class RecuperarContrasenha : Page
    {
        private UsuarioBO usuarioBO;
        private ClienteBO clienteBO;
        private RecuperarContrasenhaBO recuperarContrasenhaBO;

        public RecuperarContrasenha()
        {
            usuarioBO = new UsuarioBO();
            clienteBO = new ClienteBO();
            recuperarContrasenhaBO = new RecuperarContrasenhaBO();
        }

        protected void Page_Load(object sender, EventArgs e)
        {
        }

        protected void btnRecuperarContrasenha_Click(object sender, EventArgs e)
        {
            lblErrorCorreo.Visible = false;
            lblResultado.Visible = false;

            string correoUsuario = txtCorreo.Text.Trim();

            if (!EsFormatoCorreoValido(correoUsuario))
            {
                lblErrorCorreo.Text = "Formato de correo incorrecto.";
                lblErrorCorreo.Visible = true;
                return;
            }

            int esCorreoValido = clienteBO.verificarCorreoExistente(correoUsuario);
            if (esCorreoValido == 0)
            {
                lblResultado.Text = "Correo no encontrado en el sistema.";
                lblResultado.Visible = true;
                return;
            }

            int idUsuario = usuarioBO.obtenerIdPorCorreo(correoUsuario);
            if (idUsuario > 0)
            {
                string token = Guid.NewGuid().ToString();
                DateTime fechaExpiracion = DateTime.Now.AddHours(1);

                // Guardar token en la base de datos
                recuperarContrasenhaBO.guardarToken(idUsuario, token, fechaExpiracion);

                // Enviar correo de recuperación
                EnviarCorreoRecuperacion(correoUsuario, token);

                lblResultadoP.Text = "Se ha enviado un enlace de recuperación a su correo.";
                lblResultadoP.Visible = true;
            }
            else
            {
                lblResultado.Text = "Error al procesar la solicitud.";
                lblResultado.Visible = true;
            }
        }
        private void EnviarCorreoRecuperacion(string correo, string token)
        {
            string linkRecuperacion = $"{Request.Url.GetLeftPart(UriPartial.Authority)}/Admin/CambiarContrasenha.aspx?token={token}";
            string cuerpoCorreo = $@"
            <div style='font-family: Arial, sans-serif; background-color: #f5f5f5; padding: 20px; text-align: center;'>
                <div style='max-width: 600px; margin: auto; background-color: #ffffff; border-radius: 10px; overflow: hidden; box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);'>
                    <!-- Imagen superior -->
                    <div style='background-color: #000000; padding: 10px;'>
                        <img src='cid:TopImage' alt='RH Store' style='width: 50%; max-width: 300px; height: auto; margin: auto;' />
                    </div>
                
                    <!-- Contenido -->
                    <div style='padding: 20px;'>
                        <p style='font-size: 16px; color: #333333;'>Para restablecer su contraseña, haga clic en el siguiente enlace:</p>
                        <p style='margin: 20px 0;'>
                            <a href='{linkRecuperacion}' 
                               style='text-decoration: none; color: #ffffff; background-color: #000000; padding: 10px 20px; border-radius: 5px; font-size: 16px;'>
                               Restablecer contraseña
                            </a>
                        </p>
                        <p style='font-size: 14px; font-weight: bold; color: #555555;'>
                            Agradecemos que formes parte de la familia de RH Store, seguiremos ofreciéndote el mejor servicio.
                        </p>
                    </div>
                
                    <!-- Imagen inferior -->
                    <div style='background-color: #000000; padding: 10px;'>
                        <img src='cid:BottomImage' alt='RH Store' style='width: 20%; max-width: 100px; height: auto; margin: auto;' />
                    </div>
                </div>
            </div>";

            MailMessage mail = new MailMessage();
            mail.To.Add(correo);
            mail.From = new MailAddress("contactorhstoresw@gmail.com");
            mail.Subject = "Recuperación de contraseña";
            mail.Body = cuerpoCorreo;
            mail.IsBodyHtml = true;

            // Obtener las rutas físicas de las imágenes en el servidor
            string rutaBase = Server.MapPath("~/Images/");
            string rutaTopImage = System.IO.Path.Combine(rutaBase, "RHStoreLG.jpg");
            string rutaBottomImage = System.IO.Path.Combine(rutaBase, "rhreportes.png");

            // Agregar las imágenes como recursos embebidos (inline)
            AlternateView htmlView = AlternateView.CreateAlternateViewFromString(cuerpoCorreo, null, "text/html");

            LinkedResource topImage = new LinkedResource(rutaTopImage, "image/jpeg")
            {
                ContentId = "TopImage",
                TransferEncoding = System.Net.Mime.TransferEncoding.Base64
            };
            htmlView.LinkedResources.Add(topImage);

            LinkedResource bottomImage = new LinkedResource(rutaBottomImage, "image/jpeg")
            {
                ContentId = "BottomImage",
                TransferEncoding = System.Net.Mime.TransferEncoding.Base64
            };
            htmlView.LinkedResources.Add(bottomImage);

            mail.AlternateViews.Add(htmlView);

            SmtpClient smtp = new SmtpClient
            {
                Host = "smtp.gmail.com",
                Port = 587,
                Credentials = new System.Net.NetworkCredential("contactorhstoresw@gmail.com", "jwpiozfgrfkqpclf"),
                EnableSsl = true
            };

            try
            {
                smtp.Send(mail);
            }
            catch (Exception ex)
            {
                ClientScript.RegisterStartupScript(this.GetType(), "alert", $"alert('Error al enviar el correo: {ex.Message}');", true);
            }
        }


        private bool EsFormatoCorreoValido(string correo)
        {
            if (Regex.IsMatch(correo, @"^[^@\s]+@[^@\s]+\.[^@\s]+$"))
                return true;
            else
                return false;
		}
    }
}
