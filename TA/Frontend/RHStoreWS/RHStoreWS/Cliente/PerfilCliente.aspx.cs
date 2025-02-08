using Org.BouncyCastle.Pqc.Crypto.Lms;
using RHStoreBaseBO.ServiciosWeb;
using RHStoreUsuariosBO;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Security.Cryptography;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Web.UI.WebControls.WebParts;

namespace RHStoreWS.Cliente
{
    public partial class PerfilCliente : System.Web.UI.Page
    {
        private cliente _cliente;
        private ClienteBO clienteBO;

        public PerfilCliente()
        {
            _cliente = new cliente();
            clienteBO = new ClienteBO(); // Inicializar el BO para cliente
        }

        protected void Page_Load(object sender, EventArgs e)
        {
            ((Cliente)Master).ShowFullNav = false; // Ocultar el menú principal
            if (!IsPostBack)
            {
                if (Session["clienteLogueado"] != null)
                {
                    _cliente = (cliente)Session["clienteLogueado"];
                    CargarDatos();
                }
                else
                {
                    // Redirigir al inicio de sesión si no hay cliente logueado
                    Response.Redirect("../Admin/IniciarSesion.aspx");
                }
            }
        }

        private void CargarDatos()
        {
            // Cargar los datos del cliente desde la sesión
            txtNombre.Text = _cliente.nombres;
            txtApellido.Text = _cliente.apellidos;
            txtDNI.Text = _cliente.dni;
            txtTelefono.Text = _cliente.telefono;
            txtEmail.Text = _cliente.correo;
            // Cargar si recibe promociones
            ddlPromociones.SelectedValue = _cliente.recibePromociones ? "1" : "0";
        }

        protected void btnEditar_Click(object sender, EventArgs e)
        {
            // Habilitar campos para edición
            txtNombre.Enabled = true;
            txtApellido.Enabled = true;
            txtTelefono.Enabled = true;
            txtEmail.Enabled = true;
            ddlPromociones.Enabled = true;
            // Cambiar el texto del botón a "Guardar"
            btnEditar.Visible = false;
            btnGuardar.Visible = true;
        }

        protected void btnGuardar_Click(object sender, EventArgs e)
        {
            if (Session["clienteLogueado"] != null)
            {
                _cliente = (cliente)Session["clienteLogueado"];

                // Reiniciar mensajes de error
                lblErrorNombre.Visible = false;
                lblErrorApellido.Visible = false;
                lblErrorTelefono.Visible = false;
                lblErrorEmail.Visible = false;
                lblErrorPromociones.Visible = false;

                // Capturar los valores modificados desde los campos editables
                string nuevoNombre = txtNombre.Text;
                string nuevoApellido = txtApellido.Text;
                string nuevoTelefono = txtTelefono.Text;
                string nuevoEmail = txtEmail.Text;
                bool recibePromociones = ddlPromociones.SelectedValue == "1";

                // Validaciones de campos
                if (string.IsNullOrWhiteSpace(nuevoNombre))
                {
                    lblErrorNombre.Text = "Debe ingresar su nombre.";
                    lblErrorNombre.Visible = true;
                    return;
                }

                if (string.IsNullOrWhiteSpace(nuevoApellido))
                {
                    lblErrorApellido.Text = "Debe ingresar su apellido.";
                    lblErrorApellido.Visible = true;
                    return;
                }

                if (string.IsNullOrWhiteSpace(nuevoTelefono) || nuevoTelefono.Length != 9 || !int.TryParse(nuevoTelefono, out _))
                {
                    lblErrorTelefono.Text = "Debe ingresar un teléfono válido (9 dígitos).";
                    lblErrorTelefono.Visible = true;
                    return;
                }

                if (string.IsNullOrWhiteSpace(nuevoEmail) || !System.Text.RegularExpressions.Regex.IsMatch(nuevoEmail, @"^[^@\s]+@[^@\s]+\.[^@\s]+$"))
                {
                    lblErrorEmail.Text = "Debe ingresar un correo válido.";
                    lblErrorEmail.Visible = true;
                    return;
                }

                if (ddlPromociones.SelectedValue != "1" && ddlPromociones.SelectedValue != "0")
                {
                    lblErrorPromociones.Text = "Debe seleccionar si desea recibir promociones.";
                    lblErrorPromociones.Visible = true;
                    return;
                }

                // Valores actuales para campos no editables
                string dni = _cliente.dni;
                DateTime fechaRegistro = _cliente.fechaRegistro;

                // Llamar al método modificar del ClienteBO
                int resultado = clienteBO.modificar(_cliente.idUsuario, dni, nuevoNombre, nuevoApellido, nuevoEmail, nuevoTelefono, fechaRegistro, recibePromociones);

                if (resultado > 0)
                {
                    // Actualizar los datos en la sesión
                    _cliente.nombres = nuevoNombre;
                    _cliente.apellidos = nuevoApellido;
                    _cliente.telefono = nuevoTelefono;
                    _cliente.correo = nuevoEmail;
                    _cliente.recibePromociones = recibePromociones;

                    Session["clienteLogueado"] = _cliente;

                    // Deshabilitar campos y restaurar estado de botones
                    txtNombre.Enabled = false;
                    txtApellido.Enabled = false;
                    txtTelefono.Enabled = false;
                    txtEmail.Enabled = false;
                    ddlPromociones.Enabled = false;

                    btnGuardar.Visible = false;
                    btnEditar.Visible = true;

                    Response.Write("<script>alert('Datos actualizados correctamente.');</script>");
                }
                else
                {
                    Response.Write("<script>alert('Error al guardar los datos.');</script>");
                }
            }
            else
            {
                Response.Redirect("../Admin/IniciarSesion.aspx");
            }
        }


        protected void btnCerrarSesion_Click(object sender, EventArgs e)
        {
            // Eliminar la sesión del cliente logueado
            Session["clienteLogueado"] = null;

            // Redirigir al inicio de sesión
            Response.Redirect("../Admin/IniciarSesion.aspx");
        }
        protected void btnIrDirecciones_Click(object sender, EventArgs e)
        {
            // Redirigir a la página de direcciones del cliente
            Response.Redirect("~/Cliente/DireccionesCliente.aspx");
        }

        protected void btnCompras_Click(object sender, EventArgs e)
        {
            Response.Redirect("~/Cliente/ComprasCliente.aspx");
        }

    }

}