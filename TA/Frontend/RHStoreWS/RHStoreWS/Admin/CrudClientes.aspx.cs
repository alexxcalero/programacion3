using RHStoreBaseBO.ServiciosWeb;
using RHStoreUsuariosBO;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text.RegularExpressions;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace RHStoreWS.Admin
{
	public partial class CrudClientes : System.Web.UI.Page
	{
		private UsuarioBO usuarioBO;
		private ClienteBO clienteBO;
		private cliente _clientePorModificar;

		public CrudClientes()
		{
			usuarioBO = new UsuarioBO();
			clienteBO = new ClienteBO();
		}

		protected void Page_Init(object sender, EventArgs e)
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
			if (accion != null && accion == "modificar")
			{
				lblTitulo.Text = "Modificación de Cliente";
				_clientePorModificar = (cliente)Session["clientePorModificar"];
				cargarDatosDeLaBD();
			}
		}

		protected void cargarDatosDeLaBD()
		{
			txtID.Text = _clientePorModificar.idUsuario.ToString();
			txtDNI.Text = _clientePorModificar.dni;
			txtNombres.Text = _clientePorModificar.nombres;
			txtApellidos.Text = _clientePorModificar.apellidos;
			txtCorreo.Text = _clientePorModificar.correo;
			txtContrasenha.Text = "**************";
            Session["correoActualDelCliente"] = _clientePorModificar.correo;
			txtTelefono.Text = _clientePorModificar.telefono;
			dtpFechaRegistro.Value = _clientePorModificar.fechaRegistro.ToString("yyyy-MM-dd");
			dtpFechaRegistro.Disabled = true;
			if (_clientePorModificar.recibePromociones == true)
				rbSi.Checked = true;
			else
				rbNo.Checked = true;

			rbSi.Disabled = true;
			rbNo.Disabled = true;
		}

        protected void lbResetear_Click(object sender, EventArgs e)
        {
            int resultado = usuarioBO.resetearContrasenha(_clientePorModificar.idUsuario);
            string script = "showModalFormCambiarContrasenha();";
            ScriptManager.RegisterStartupScript(this, GetType(), "ShowModalFormCambiarContrasenha", script, true);
        }

        protected void lbRegresar_Click(object sender, EventArgs e)
		{
			Response.Redirect("GestionarClientes.aspx");
		}
        protected void lbGuardar_Click(object sender, EventArgs e)
        {
            int resultado;

            // Validación del DNI
            string dni = txtDNI.Text;
            if (dni.Trim().Equals(""))
            {
                ejecutarModalError("Debe ingresar el DNI");
                return;
            }
            if (dni.Trim().Length != 8)
            {
                ejecutarModalError("El DNI debe tener 8 dígitos");
                return;
            }
            try
            {
                Int32.Parse(dni);
            }
            catch (Exception)
            {
                ejecutarModalError("El DNI debe ser un número de 8 dígitos");
                return;
            }

            // Validación de nombres
            string nombres = txtNombres.Text;
            if (nombres.Trim().Equals(""))
            {
                ejecutarModalError("Debe ingresar un nombre");
                return;
            }

            // Validación de apellidos
            string apellidos = txtApellidos.Text;
            if (apellidos.Trim().Equals(""))
            {
                ejecutarModalError("Debe ingresar un apellido");
                return;
            }

            // Validación de correo
            string correo = txtCorreo.Text;
            if (correo.Trim().Equals(""))
            {
                ejecutarModalError("Debe ingresar un correo");
                return;
            }
            if (!Regex.IsMatch(correo, @"^[^@\s]+@[^@\s]+\.[^@\s]+$"))
            {
                ejecutarModalError("El correo no tiene un formato válido");
                return;
            }
            if (Session["correoActualDelCliente"] != null && correo != (string)Session["correoActualDelCliente"])
            {
                int verificarCorreoExistente = clienteBO.verificarCorreoExistente(correo);
                if (verificarCorreoExistente != 0)
                {
                    ejecutarModalError("Este correo ya está registrado");
                    return;
                }
            }

            // Validación de teléfono
            string telefono = txtTelefono.Text;
            if (telefono.Trim().Equals(""))
            {
                ejecutarModalError("Debe ingresar el teléfono");
                return;
            }
            if (telefono.Trim().Length != 9)
            {
                ejecutarModalError("El teléfono debe tener 9 dígitos");
                return;
            }
            try
            {
                Int32.Parse(telefono);
            }
            catch (Exception)
            {
                ejecutarModalError("El teléfono debe ser un número de 9 dígitos");
                return;
            }

            // Validación de fecha de registro
            DateTime fechaRegistro = DateTime.Parse(dtpFechaRegistro.Value);

            // Validación de promociones
            bool recibePromociones;
            if (rbSi.Checked)
                recibePromociones = true;
            else if (rbNo.Checked)
                recibePromociones = false;
            else
            {
                ejecutarModalError("Debe seleccionar una opción");
                return;
            }

            // Modificar cliente
            int idUsuario = Int32.Parse(txtID.Text);
            resultado = clienteBO.modificar(idUsuario, dni, nombres, apellidos, correo, telefono, fechaRegistro, recibePromociones);
            if (resultado != 0)
                Response.Redirect("GestionarClientes.aspx");
        }

		protected void ejecutarModalError(string mensaje)
		{
			lblMensajeError.Text = mensaje;
			string script = "showModalFormError();";
			ScriptManager.RegisterStartupScript(this, GetType(), "ShowModalFormError", script, true);
		}
	}
}
