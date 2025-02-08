using RHStoreBaseBO.ServiciosWeb;
using RHStoreUsuariosBO;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace RHStoreWS.Cliente
{
    public partial class DireccionesCliente : System.Web.UI.Page
    {
        private cliente _cliente;
        private DireccionBO direccionBO;

        public DireccionesCliente()
        {
            direccionBO = new DireccionBO(); // Inicializa el BO para gestionar las direcciones
        }

        protected void Page_Load(object sender, EventArgs e)
        {
            //((Cliente)Master).ShowFullNav = false; // Ocultar el menú principal
            if (!IsPostBack)
            {
                if (Session["clienteLogueado"] != null)
                {
                    _cliente = (cliente)Session["clienteLogueado"];
                    CargarDirecciones();
                }
                else
                {
                    // Redirigir al inicio de sesión si no hay cliente logueado
                    Response.Redirect("../Admin/IniciarSesion.aspx");
                }
            }
        }

        private void CargarDirecciones()
        {
            try
            {
                cliente clienteLogueado = (cliente)Session["clienteLogueado"];
                if (clienteLogueado == null)
                {
                    Response.Write("<script>alert('El cliente no está logueado.');</script>");
                    return;
                }

                BindingList<direccion> direcciones = direccionBO.listarPorIdCliente(clienteLogueado.idUsuario);

                if (direcciones != null && direcciones.Count > 0)
                {
                    rptDirecciones.DataSource = direcciones;
                    rptDirecciones.DataBind();
                }
                else
                {
                    rptDirecciones.DataSource = null;
                    rptDirecciones.DataBind();
                    Response.Write("<script>alert('No hay direcciones registradas para este cliente.');</script>");
                }
            }
            catch (Exception ex)
            {
                Response.Write("<script>alert('Error al cargar direcciones: " + ex.Message + "');</script>");
            }
        }

        protected void btnAgregarDireccion_Click(object sender, EventArgs e)
        {
            // Mostrar formulario para agregar nueva dirección
            formDireccion.Visible = true;

            // Limpiar los campos del formulario
            txtDireccion.Text = string.Empty;
            txtReferencia.Text = string.Empty;
            txtProvincia.Text = string.Empty;
            txtDepartamento.Text = string.Empty;
            txtDistrito.Text = string.Empty;
            txtCodPostal.Text = string.Empty;
            btnGuardarDireccion.CommandArgument = "0"; // Indicar que es una nueva dirección
        }

        protected void btnGuardarDireccion_Click(object sender, EventArgs e)
        {
            try
            {
                // Obtener el cliente logueado desde la sesión
                cliente clienteLogueado = (cliente)Session["clienteLogueado"];
                if (clienteLogueado == null)
                {
                    Response.Write("<script>alert('El cliente no está logueado.');</script>");
                    return;
                }

                int idDireccion = int.Parse(btnGuardarDireccion.CommandArgument);
                
                string direccion = txtDireccion.Text;
				if (direccion.Trim().Equals(""))
				{
					ejecutarModalError("Debe ingresar la direccion");
					return;
				}

				string distrito = txtDistrito.Text;
				if (distrito.Trim().Equals(""))
				{
					ejecutarModalError("Debe ingresar el distrito");
					return;
				}

				string provincia = txtProvincia.Text;
				if (provincia.Trim().Equals(""))
				{
					ejecutarModalError("Debe ingresar la provincia");
					return;
				}

				string departamento = txtDepartamento.Text;
				if (departamento.Trim().Equals(""))
				{
					ejecutarModalError("Debe ingresar el departamento");
					return;
				}

				string referencia = txtReferencia.Text;
				if (referencia.Trim().Equals(""))
				{
					ejecutarModalError("Debe ingresar la referencia");
					return;
				}

				string codigoPostal = txtCodPostal.Text;
				if (codigoPostal.Trim().Equals(""))
				{
					ejecutarModalError("Debe ingresar el código postal");
					return;
				}

				if (idDireccion == 0)
                {
                    // Insertar nueva dirección con el ID del cliente logueado
                    direccionBO.insertar(direccion, distrito, provincia, departamento, codigoPostal, referencia, clienteLogueado);
                }
                else
                {
                    // Modificar dirección existente
                    direccionBO.modificar(idDireccion, direccion, distrito, provincia, departamento, codigoPostal, referencia, clienteLogueado);
                }

                // Recargar las direcciones
                CargarDirecciones();
                formDireccion.Visible = false;
            }
            catch (Exception ex)
            {
                Response.Write("<script>alert('Error al guardar la dirección: " + ex.Message + "');</script>");
            }
        }

		protected void ejecutarModalError(string mensaje)
		{
			lblMensajeError.Text = mensaje;
			string script = "showModalFormError();";
			ScriptManager.RegisterStartupScript(this, GetType(), "ShowModalFormError", script, true);
		}

		protected void btnCancelarDireccion_Click(object sender, EventArgs e)
        {
            // Ocultar el formulario de dirección sin realizar cambios
            formDireccion.Visible = false;
        }

        protected void btnEditarDireccion_Click(object sender, EventArgs e)
        {
            try
            {
                // Obtener el ID de la dirección desde el CommandArgument
                int idDireccion = int.Parse((sender as LinkButton).CommandArgument);

                // Llamar al método obtenerPorId para obtener la dirección
                direccion direccion = direccionBO.obtenerPorId(idDireccion);

                if (direccion != null)
                {
                    // Cargar datos en el formulario
                    txtDireccion.Text = direccion.direccion1;
                    txtReferencia.Text = direccion.referencia;
                    txtProvincia.Text = direccion.provincia;
                    txtDepartamento.Text = direccion.departamento;
                    txtDistrito.Text = direccion.distrito;
                    txtCodPostal.Text = direccion.codigoPostal;

                    btnGuardarDireccion.CommandArgument = idDireccion.ToString();
                    formDireccion.Visible = true; // Mostrar el formulario
                }
                else
                {
                    // Manejo en caso de que no se encuentre la dirección
                    Response.Write("<script>alert('Dirección no encontrada.');</script>");
                }
            }
            catch (Exception ex)
            {
                // Manejo de errores al cargar datos para edición
                Response.Write("<script>alert('Error al cargar la dirección: " + ex.Message + "');</script>");
            }
        }

        protected void btnEliminarDireccion_Click(object sender, EventArgs e)
        {
            try
            {
                // Obtener el ID de la dirección a eliminar
                int idDireccion = int.Parse((sender as LinkButton).CommandArgument);

                // Eliminar dirección
                direccionBO.eliminar(idDireccion);

                // Recargar direcciones
                CargarDirecciones();
            }
            catch (Exception ex)
            {
                // Manejo de errores al eliminar dirección
                Response.Write("<script>alert('Error al eliminar la dirección: " + ex.Message + "');</script>");
            }
        }

        protected void btnVolverPerfil_Click(object sender, EventArgs e)
        {
            Response.Redirect("~/Cliente/PerfilCliente.aspx");
        }

    }
}