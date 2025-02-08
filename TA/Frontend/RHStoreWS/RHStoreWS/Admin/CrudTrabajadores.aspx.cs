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
	public partial class CrudTrabajadores : System.Web.UI.Page
	{
		private UsuarioBO usuarioBO;
		private TrabajadorBO trabajadorBO;
		private trabajador _trabajadorPorModificar;
		private bool estaModificando;
		private bool correoNuevo = true;

		public CrudTrabajadores()
		{
			usuarioBO = new UsuarioBO();
			trabajadorBO = new TrabajadorBO();
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
				lblTitulo.Text = "Modificación de Trabajador";
				_trabajadorPorModificar = (trabajador)Session["trabajadorPorModificar"];
				lbResetear.Visible = true;
				estaModificando = true;
				cargarDatosDeLaBD();
			}
			else
			{
				lblTitulo.Text = "Registro de Trabajador";
				lbResetear.Visible = false;
				estaModificando = false;
			}
		}

		protected void cargarDatosDeLaBD()
		{
			txtID.Text = _trabajadorPorModificar.idUsuario.ToString();
			txtDNI.Text = _trabajadorPorModificar.dni;
			txtNombres.Text = _trabajadorPorModificar.nombres;
			txtApellidos.Text = _trabajadorPorModificar.apellidos;
			txtCorreo.Text = _trabajadorPorModificar.correo;
			Session["correoActualDelTrabajador"] = _trabajadorPorModificar.correo;
            txtContrasenha.Text = "**************";
            txtContrasenha.Enabled = false;
            txtConfirmarContrasenha.Text = "**************";
            txtConfirmarContrasenha.Enabled = false;
            txtPuesto.Text = _trabajadorPorModificar.puesto;
			txtSueldo.Text = ((double)_trabajadorPorModificar.sueldo).ToString("N2");
			dtpFechaIngreso.Value = _trabajadorPorModificar.fechaIngreso.ToString("yyyy-MM-dd");
			dtpFechaIngreso.Disabled = true;
			tpHorarioInicio.Value = _trabajadorPorModificar.horarioInicio;
			tpHorarioFin.Value = _trabajadorPorModificar.horarioFin;
		}

        protected void lbResetear_Click(object sender, EventArgs e)
        {
            usuarioBO.resetearContrasenha(_trabajadorPorModificar.idUsuario);
            ejecutarModalError("La contraseña ha sido reseteada. Notifique al trabajador.");
        }

        protected void lbRegresar_Click(object sender, EventArgs e)
		{
			Response.Redirect("GestionarTrabajadores.aspx");
		}

        protected void lbGuardar_Click(object sender, EventArgs e)
        {
			int resultado;
			double sueldo;
			DateTime fechaIngreso;

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
				if (Session["correoActualDelTrabajador"] != null && correo == (string)Session["correoActualDelTrabajador"])
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

			string puesto = txtPuesto.Text;
			if (puesto.Trim().Equals(""))
			{
				ejecutarModalError("Debe ingresar un puesto");
				return;
			}

			if (txtSueldo.Text.Trim().Equals(""))
			{
				ejecutarModalError("Debe ingresar el sueldo");
				return;
			}
			try
			{
				sueldo = Double.Parse(txtSueldo.Text);
			}
			catch (Exception ex)
			{
				ejecutarModalError("El sueldo debe ser un número");
				return;
			}

			try
			{
				fechaIngreso = DateTime.Parse(dtpFechaIngreso.Value);
			}
			catch (Exception ex)
			{
				ejecutarModalError("Debe seleccionar la fecha de ingreso");
				return;
			}
			if (estaModificando != true)
			{
				if (fechaIngreso < DateTime.Today)
				{
					ejecutarModalError("Debe ingresar una fecha correcta");
					return;
				}
			}

			string horarioInicio = tpHorarioInicio.Value.ToString();
			if (horarioInicio.Equals(""))
			{
				ejecutarModalError("Debe seleccionar la hora de inicio del turno");
				return;
			}

			string horarioFin = tpHorarioFin.Value.ToString();
			if (horarioFin.Equals(""))
			{
				ejecutarModalError("Debe seleccionar la hora de fin del turno");
				return;
			}

			TimeSpan horaInicio, horaFin;
			if (TimeSpan.TryParse(horarioInicio, out horaInicio) && TimeSpan.TryParse(horarioFin, out horaFin))
			{
				if (horaInicio > horaFin)
				{
					ejecutarModalError("Debe ingresar las horas correctamente");
					return;
				}
			}
			else
			{
				ejecutarModalError("Formato de hora inválido");
			}

			if (estaModificando == true)
			{
				int idUsuario = Int32.Parse(txtID.Text);
				resultado = trabajadorBO.modificar(idUsuario, dni, nombres, apellidos, correo, null, puesto, sueldo, fechaIngreso, horarioInicio, horarioFin);
				if (resultado != 0)
					Response.Redirect("GestionarTrabajadores.aspx");
			}
			else
			{
				resultado = trabajadorBO.insertar(dni, nombres, apellidos, correo, contrasenha, puesto, sueldo, fechaIngreso, horarioInicio, horarioFin);
				if (resultado != 0)
					Response.Redirect("GestionarTrabajadores.aspx");
			}

		}

		protected void ejecutarModalError(string mensaje)
        {
            lblMensajeError.Text = mensaje;
            ScriptManager.RegisterStartupScript(this, GetType(), "ShowModalFormError", "showModalFormError();", true);
        }
    }
}
