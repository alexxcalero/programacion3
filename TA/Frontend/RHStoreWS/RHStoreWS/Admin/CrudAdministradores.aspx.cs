using RHStoreBaseBO.ServiciosWeb;
using RHStoreUsuariosBO;
using RHStoreWS.Cliente;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text.RegularExpressions;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace RHStoreWS.Admin
{
	public partial class CrudAdministradores : System.Web.UI.Page
	{
		private UsuarioBO usuarioBO;
		private AdministradorBO administradorBO;
		private administrador _administradorPorModificar;
		private bool estaModificando;
		private bool correoNuevo = true;

		public CrudAdministradores()
		{
			usuarioBO = new UsuarioBO();
			administradorBO = new AdministradorBO();
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
				lblTitulo.Text = "Modificación de Administrador";
				_administradorPorModificar = (administrador)Session["administradorPorModificar"];
				lbResetear.Visible = true;
				estaModificando = true;
				cargarDatosDeLaBD();
			}
			else
			{
				lblTitulo.Text = "Registro de Administrador";
				dtpFechaCreacion.Value = DateTime.Today.ToString("yyyy-MM-dd");
				dtpFechaCreacion.Disabled = true;
				lbResetear.Visible = false;
				estaModificando = false;
			}
		}

		protected void cargarDatosDeLaBD()
		{
			txtID.Text = _administradorPorModificar.idUsuario.ToString();
			txtDNI.Text = _administradorPorModificar.dni;
			txtNombres.Text = _administradorPorModificar.nombres;
			txtApellidos.Text = _administradorPorModificar.apellidos;
			txtCorreo.Text = _administradorPorModificar.correo;
			Session["correoActualDelAdministrador"] = _administradorPorModificar.correo;
            txtContrasenha.Text = "**************";
            txtContrasenha.Enabled = false;
			txtConfirmarContrasenha.Text = "**************";
            txtConfirmarContrasenha.Enabled = false;
            dtpFechaCreacion.Value = _administradorPorModificar.fechaCreacion.ToString("yyyy-MM-dd");
			dtpFechaCreacion.Disabled = true;
		}

		protected void lbResetear_Click(object sender, EventArgs e)
		{
			int resultado = usuarioBO.resetearContrasenha(_administradorPorModificar.idUsuario);
			string script = "showModalFormCambiarContrasenha();";
			ScriptManager.RegisterStartupScript(this, GetType(), "ShowModalFormCambiarContrasenha", script, true);
			return;
		}

		protected void lbRegresar_Click(object sender, EventArgs e)
		{
			Response.Redirect("GestionarAdministradores.aspx");
		}

		protected void lbGuardar_Click(object sender, EventArgs e)
		{
			int resultado;
			string dni = txtDNI.Text;
			if (dni.Trim().Equals(""))
			{
				ejecutarModalError("Debe ingresar el DNI");
				return;
			}
			if (dni.Trim().Count() != 8)
			{
				ejecutarModalError("El DNI debe tener 8 dígitos");
				return;
			}
			try
			{
				Int32.Parse(dni);
			}
			catch (Exception ex)
			{
				ejecutarModalError("El DNI debe ser un número de 8 dígitos");
				return;
			}

			string nombres = txtNombres.Text;
			if (nombres.Trim().Equals(""))
			{
				ejecutarModalError("Debe ingresar un nombre");
				return;
			}

			string apellidos = txtApellidos.Text;
			if (apellidos.Trim().Equals(""))
			{
				ejecutarModalError("Debe ingresar un apellido");
				return;
			}

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
			if (estaModificando == true)
			{
				if (Session["correoActualDelAdministrador"] != null && correo == (string)Session["correoActualDelAdministrador"])
				{
					correoNuevo = false;
				}
			}
			if (correoNuevo == true)
			{
				int verificarCorreoExistente = usuarioBO.obtenerIdPorCorreo(correo);
				if (verificarCorreoExistente != 0)
				{
					ejecutarModalError("Este correo ya está registrado");
					return;
				}
			}

			string contrasenha = txtContrasenha.Text;
			if (estaModificando == false)
			{
				if (contrasenha.Trim().Equals(""))
				{
					ejecutarModalError("Debe ingresar una contraseña");
					return;
				}
			}

			string confirmarContrasenha = txtConfirmarContrasenha.Text;
			if (estaModificando == false)
			{
				if (confirmarContrasenha.Trim().Equals(""))
				{
					ejecutarModalError("Debe confirmar la contraseña");
					return;
				}
				if (contrasenha != confirmarContrasenha)
				{
					ejecutarModalError("Las contraseñas no coinciden");
					return;
				}
			}

			DateTime fechaCreacion = DateTime.Parse(dtpFechaCreacion.Value);

			if (estaModificando == true)
			{
				int idUsuario = Int32.Parse(txtID.Text);
				resultado = administradorBO.modificar(idUsuario, dni, nombres, apellidos, correo, null, fechaCreacion);
				if (resultado != 0)
					Response.Redirect("GestionarAdministradores.aspx");
			}
			else
			{
				resultado = administradorBO.insertar(dni, nombres, apellidos, correo, contrasenha, fechaCreacion);
				if (resultado != 0)
					Response.Redirect("GestionarAdministradores.aspx");
			}
		}

		private void ejecutarModalError(string mensaje)
		{
			lblMensajeError.Text = mensaje;
			string script = "showModalFormError();";
			ScriptManager.RegisterStartupScript(this, GetType(), "ShowModalFormError", script, true);
		}
	}
}
