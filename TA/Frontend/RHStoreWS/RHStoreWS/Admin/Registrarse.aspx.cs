using RHStoreUsuariosBO;
using System;
using System.Linq;
using System.Text.RegularExpressions;
using System.Web.UI;

namespace RHStoreWS.Admin
{
    public partial class Registrarse : Page
    {
        private ClienteBO clienteBO;

        public Registrarse()
        {
            clienteBO = new ClienteBO();
        }

        protected void Page_Load(object sender, EventArgs e)
        {

        }

        protected void btnRegistrarse_Click(object sender, EventArgs e)
        {
			lblErrorDNI.Visible = false;
			lblErrorNombres.Visible = false;
			lblErrorApellidos.Visible = false;
			lblErrorCorreo.Visible = false;
			lblErrorContrasenha.Visible = false;
			lblErrorConfirmarContrasenha.Visible = false;
			lblErrorTelefono.Visible = false;
			lblErrorRecibePromociones.Visible = false;

			string dni = txtDNI.Text;
			if (dni.Trim().Equals(""))
			{
				lblErrorDNI.Text = "Debe ingresar el DNI";
                lblErrorDNI.Visible = true;
                return;
			}else
			{
				lblErrorDNI.Visible = false;
			}
			if (dni.Trim().Count() != 8)
			{
				lblErrorDNI.Text = "El DNI debe tener 8 dígitos";
				lblErrorDNI.Visible = true;
				return;
			}else
			{
				lblErrorDNI.Visible = false;
			}
			try
			{
				Int32.Parse(dni);
			}
			catch (Exception ex)
			{
				lblErrorDNI.Text = "El DNI debe ser un número de 8 dígitos";
				lblErrorDNI.Visible = true;
				return;
			}

			string nombres = txtNombres.Text;
			if (nombres.Trim().Equals(""))
			{
                lblErrorNombres.Visible = true;
                return;
			}else
			{
				lblErrorNombres.Visible = false;
			}

			string apellidos = txtApellidos.Text;
			if (apellidos.Trim().Equals(""))
			{
				lblErrorApellidos.Visible = true;
				return;
			}
			else
			{
				lblErrorApellidos.Visible = false;
			}

			string correo = txtCorreo.Text;
			if (correo.Trim().Equals(""))
			{
				lblErrorCorreo.Text = "Debe ingresar un correo";
				lblErrorCorreo.Visible = true;
				return;
			}else
			{
				lblErrorCorreo.Visible = false;
			}
			if (!Regex.IsMatch(correo, @"^[^@\s]+@[^@\s]+\.[^@\s]+$"))
			{
				lblErrorCorreo.Text = "El correo no tiene un formato válido";
				lblErrorCorreo.Visible = true;
				return;
			}
			else
			{
				lblErrorCorreo.Visible = false;
			}
			int verificarCorreoExistente = clienteBO.verificarCorreoExistente(correo);
			if(verificarCorreoExistente != 0)
			{
				lblErrorCorreo.Text = "Este correo ya está registrado, por favor inicie sesión con su cuenta";
				lblErrorCorreo.Visible = true;
				return;
			}
			else
			{
				lblErrorCorreo.Visible = false;
			}

			string contrasenha = txtContrasenha.Text;
			if (contrasenha.Trim().Equals(""))
			{
				lblErrorContrasenha.Visible = true;
				return;
			}else
			{
				lblErrorContrasenha.Visible = false;
			}

			string confirmarContrasenha = txtConfirmarContrasenha.Text;
			if(confirmarContrasenha.Trim().Equals(""))
			{
				lblErrorConfirmarContrasenha.Text = "Debe verificar la contraseña";
				lblErrorConfirmarContrasenha.Visible = true;
				return;
			}else
			{
				lblErrorConfirmarContrasenha.Visible = false;
			}
			if (contrasenha != confirmarContrasenha)
			{
				lblErrorConfirmarContrasenha.Text = "Las contraseñas no coinciden";
				lblErrorConfirmarContrasenha.Visible = true;
				return;
			}
			else
			{
				lblErrorConfirmarContrasenha.Visible = false;
			}

			string telefono = txtTelefono.Text;
			if (telefono.Trim().Equals(""))
			{
				lblErrorTelefono.Text = "Debe ingresar el teléfono";
				lblErrorTelefono.Visible = true;
				return;
			}else
			{
				lblErrorTelefono.Visible = false;
			}
			if (telefono.Trim().Count() != 9)
			{
				lblErrorTelefono.Text = "El teléfono debe tener 9 dígitos";
				lblErrorTelefono.Visible = true;
				return;
			}else
			{
				lblErrorTelefono.Visible = false;
			}
			try
			{
				Int32.Parse(telefono);
			}
			catch (Exception ex)
			{
				lblErrorTelefono.Text = "El teléfono debe ser un número de 9 dígitos";
				lblErrorTelefono.Visible = true;
				return;
			}

			DateTime fechaRegistro = DateTime.Today;

			bool recibePromociones;
			if (rbSi.Checked == true)
				recibePromociones = true;
			else
				recibePromociones = false;

			if (rbSi.Checked == false && rbNo.Checked == false)
			{
				lblErrorRecibePromociones.Visible = true;
				return;
			}else
			{
				lblErrorRecibePromociones.Visible = false;
			}

			int resultado = clienteBO.insertar(dni, nombres, apellidos, correo, contrasenha, telefono, fechaRegistro, recibePromociones);
            if (resultado != 0)
                Response.Redirect("IniciarSesion.aspx");
        }
    }
}
