using RHStoreBaseBO.ServiciosWeb;
using RHStorePrendasBO;
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace RHStoreWS.Admin
{
	public partial class CrudPrendas : System.Web.UI.Page
	{
		private prenda _prenda;
		private PrendaBO prendaBO;
		private bool estaModificando;

		public CrudPrendas()
		{
			prendaBO = new PrendaBO();
		}

		protected void Page_Load(object sender, EventArgs e)
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

			if (!IsPostBack)
			{
				ddlTalla.DataSource = Enum.GetValues(typeof(talla));
				ddlTalla.DataBind();
			}

			string accion = Request.QueryString["accion"];
			if (accion != null && accion == "modificar")
			{
				lblTitulo.Text = "Modificación de Prenda";
				_prenda = (prenda)Session["prenda"];
				cargarDatosDeLaBD();
				cargarFoto(sender, e);
				estaModificando = true;
			}
			else
			{
				lblTitulo.Text = "Registro de Prenda";
				cargarFoto(sender, e);
				estaModificando = false;
			}
		}

		protected void cargarDatosDeLaBD()
		{
			if (!IsPostBack)
			{
				txtID.Text = _prenda.idPrenda.ToString();
				txtID.Enabled = false;
				txtNombre.Text = _prenda.nombre;
				ddlTalla.SelectedValue = _prenda.talla.ToString();
				ddlTalla.Enabled = false;

				if (_prenda.imagen != null)
				{
					Session["foto"] = _prenda.imagen;

					string base64String = Convert.ToBase64String(_prenda.imagen);
					string imageUrl = "data:image/jpeg;base64," + base64String;
					imgImagen.ImageUrl = imageUrl;
				}

				if (_prenda.tipo.ToString().Equals("Polo"))
					rbPolo.Checked = true;
				else if (_prenda.tipo.ToString().Equals("Pantalon"))
					rbPantalon.Checked = true;
				else if (_prenda.tipo.ToString().Equals("Polera"))
					rbPolera.Checked = true;
				else if (_prenda.tipo.ToString().Equals("Camisa"))
					rbCamisa.Checked = true;
				else
					rbCasaca.Checked = true;

				rbPolo.Disabled = true;
				rbPantalon.Disabled = true;
				rbPolera.Disabled = true;
				rbCamisa.Disabled = true;
				rbCasaca.Disabled = true;

				if (_prenda.genero.ToString().Equals("Hombre"))
					rbHombre.Checked = true;
				else if (_prenda.genero.ToString().Equals("Mujer"))
					rbMujer.Checked = true;
				else
					rbUnisex.Checked = true;

				rbHombre.Disabled = true;
				rbMujer.Disabled = true;
				rbUnisex.Disabled = true;

				txtColor.Text = _prenda.color;
				txtPrecioOriginal.Text = _prenda.precioOriginal.ToString();
				txtPrecioDescontado.Text = _prenda.precioDescontado.ToString();
				txtStock.Text = _prenda.stock.ToString();
				txtCantVendida.Text = _prenda.cantVendida.ToString();
				txtDescripcion.Value = _prenda.descripcion;
			}
		}

		protected void cargarFoto(object sender, EventArgs e)
		{
			if (IsPostBack && fileUploadImagen.PostedFile != null && fileUploadImagen.HasFile)
			{
				string extension = System.IO.Path.GetExtension(fileUploadImagen.FileName);
				if (extension.ToLower() == ".jpg" || extension.ToLower() == ".jpeg" || extension.ToLower() == ".png" || extension.ToLower() == ".gif")
				{
					string filename = Guid.NewGuid().ToString() + extension;
					string filePath = Server.MapPath("~/Uploads/") + filename;
					fileUploadImagen.SaveAs(Server.MapPath("~/Uploads/") + filename);
					imgImagen.ImageUrl = "~/Uploads/" + filename;
					imgImagen.Visible = true;
					FileStream fs = new FileStream(filePath, FileMode.Open, FileAccess.Read);
					BinaryReader br = new BinaryReader(fs);
					Session["foto"] = br.ReadBytes((int)fs.Length);
					fs.Close();
				}
				else
				{
					Response.Write("Por favor, selecciona un archivo de imagen válido.");
				}
			}
		}

		protected void lbRegresar_Click(object sender, EventArgs e)
		{
			Response.Redirect("GestionarPrendas.aspx");
		}
        protected void lbGuardar_Click(object sender, EventArgs e)
        {
            int resultado, stock;
            double precioOriginal;

            string nombre = txtNombre.Text;
            if (nombre.Trim().Equals(""))
            {
                ejecutarModalError("Debe ingresar un nombre");
                return;
            }

            talla _talla = (talla)Enum.Parse(typeof(talla), ddlTalla.SelectedValue.ToString());

            tipoPrenda tipo;
            if (rbPolo.Checked)
                tipo = tipoPrenda.Polo;
            else if (rbPantalon.Checked)
                tipo = tipoPrenda.Pantalon;
            else if (rbPolera.Checked)
                tipo = tipoPrenda.Polera;
            else if (rbCamisa.Checked)
                tipo = tipoPrenda.Camisa;
            else if (rbCasaca.Checked)
                tipo = tipoPrenda.Casaca;
            else
            {
                ejecutarModalError("Debe seleccionar un tipo de prenda");
                return;
            }

            genero _genero;
            if (rbHombre.Checked)
                _genero = genero.Hombre;
            else if (rbMujer.Checked)
                _genero = genero.Mujer;
            else if (rbUnisex.Checked)
                _genero = genero.Unisex;
            else
            {
                ejecutarModalError("Debe seleccionar un género");
                return;
            }

            string color = txtColor.Text;
            if (color.Trim().Equals(""))
            {
                ejecutarModalError("Debe ingresar un color");
                return;
            }

            if (txtPrecioOriginal.Text.Trim().Equals(""))
            {
                ejecutarModalError("Debe ingresar el precio original");
                return;
            }
            try
            {
                precioOriginal = Double.Parse(txtPrecioOriginal.Text);
            }
            catch
            {
                ejecutarModalError("El precio debe ser un número");
                return;
            }

            if (txtStock.Text.Trim().Equals(""))
            {
                ejecutarModalError("Debe ingresar el stock");
                return;
            }
            try
            {
                stock = Int32.Parse(txtStock.Text);
            }
            catch
            {
                ejecutarModalError("El stock debe ser un número");
                return;
            }

            string descripcion = txtDescripcion.Value;
            if (descripcion.Trim().Equals(""))
            {
                ejecutarModalError("Debe ingresar una descripción");
                return;
            }

            byte[] imagen = (byte[])Session["foto"];
            if (imagen == null)
            {
                ejecutarModalError("Debe ingresar una imagen");
                return;
            }

            if (estaModificando)
            {
                int idPrenda = Int32.Parse(txtID.Text);
                resultado = prendaBO.modificar(idPrenda, nombre, descripcion, tipo, imagen, _talla, _genero, color, precioOriginal, stock);
                if (resultado != 0)
                    Response.Redirect("GestionarPrendas.aspx");
            }
            else
            {
                resultado = prendaBO.insertar(nombre, descripcion, tipo, imagen, _talla, _genero, color, precioOriginal, stock);
                if (resultado != 0)
                    Response.Redirect("GestionarPrendas.aspx");
            }
        }
        protected void ejecutarModalError(string mensaje)
        {
            lblMensajeError.Text = mensaje;
            string script = "showModalFormError();";
            ScriptManager.RegisterStartupScript(this, GetType(), "ShowModalFormError", script, true);
        }
    }
}
