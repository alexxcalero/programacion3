/* Colocar sus datos personales
 * ------------------------------------------------
 * Nombre Completo: Alex Calero Revilla
 * Codigo PUCP: 20206455
 * ------------------------------------------------
 */

using SazonWareWA.ServicioWeb;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.IO;
using System.Linq;
using System.Web;
using System.Web.Services.Description;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace SazonWareWA
{
    public partial class RegistrarPlato : System.Web.UI.Page
    {
        private BindingList<ingrediente> ingredientes;
        private BindingList<ingrediente> ingredientesPlato;
        private IngredienteWSClient ingredienteWS;
        private PlatoWSClient platoWS;

        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                ingredienteWS = new IngredienteWSClient();
                ingredientes = new BindingList<ingrediente>(ingredienteWS.listarIngredientesTodos().ToList());
                ddlIngredientes.DataSource = ingredientes;
                ViewState["ingredientes"] = ingredientes;
                ddlIngredientes.DataTextField = "nombre";
                ddlIngredientes.DataValueField = "idIngrediente";
                ddlIngredientes.DataBind();
            }

            if (ViewState["ingredientesPlato"] == null)
                ingredientesPlato = new BindingList<ingrediente>();
            else
                ingredientesPlato = (BindingList<ingrediente>)ViewState["ingredientesPlato"];
            dgvIngredientes.DataSource = ingredientesPlato;
            dgvIngredientes.DataBind();

            ddlIngredientes_SelectedIndexChanged(sender, e);
            Cargar_Foto(sender, e);
        }

        protected void Cargar_Foto(object sender, EventArgs e)
        {
            if (IsPostBack && fileUploadFotoPlato.PostedFile != null && fileUploadFotoPlato.HasFile)
            {
                string extension = System.IO.Path.GetExtension(fileUploadFotoPlato.FileName);
                if (extension.ToLower() == ".jpg" || extension.ToLower() == ".jpeg" || extension.ToLower() == ".png" || extension.ToLower() == ".gif")
                {
                    string filename = Guid.NewGuid().ToString() + extension;
                    string filePath = Server.MapPath("~/Uploads/") + filename;
                    fileUploadFotoPlato.SaveAs(Server.MapPath("~/Uploads/") + filename);
                    imgFotoPlato.ImageUrl = "~/Uploads/" + filename;
                    imgFotoPlato.Visible = true;
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

        protected void btnRegresar_Click(object sender, EventArgs e)
        {
            Response.Redirect("Home.aspx");
        }

        protected void ddlIngredientes_SelectedIndexChanged(object sender, EventArgs e)
        {
            int idIngrediente = Int32.Parse(ddlIngredientes.SelectedValue);
            ingredientes = (BindingList<ingrediente>)ViewState["ingredientes"];
            ingrediente ingrediente = ingredientes.SingleOrDefault(x => x.idIngrediente == idIngrediente);
            txtDescripcion.Text = ingrediente.descripcion;
        }

        protected void lbAgregar_Click(object sender, EventArgs e)
        {
            int idIngrediente = Int32.Parse(ddlIngredientes.SelectedValue);
            ingredientes = (BindingList<ingrediente>)ViewState["ingredientes"];
            ingrediente ingrediente = ingredientes.SingleOrDefault(x => x.idIngrediente == idIngrediente);
            ingredientesPlato.Add(ingrediente);
            ViewState["ingredientesPlato"] = ingredientesPlato;
            dgvIngredientes.DataSource = ingredientesPlato;
            dgvIngredientes.DataBind();
        }

        protected void btnEliminar_Click(object sender, EventArgs e)
        {
            int idIngrediente = Int32.Parse(((LinkButton)sender).CommandArgument);
            ingredientesPlato = (BindingList<ingrediente>)ViewState["ingredientesPlato"];
            ingrediente ingrediente = ingredientesPlato.SingleOrDefault(x => x.idIngrediente == idIngrediente);
            ingredientesPlato.Remove(ingrediente);
            ViewState["ingredientesPlato"] = ingredientesPlato;
            dgvIngredientes.DataSource = ingredientesPlato;
            dgvIngredientes.DataBind();
        }

        protected void btnGuardar_Click(object sender, EventArgs e)
        {
            platoWS = new PlatoWSClient();

            plato _plato = new plato();
            _plato.nombre = txtNombre.Text;
            _plato.precio = Double.Parse(txtPrecio.Text);

            if (rbEntrada.Checked == true)
                _plato.categoria = categoria.ENTRADA;
            else if (rbFondo.Checked == true)
                _plato.categoria = categoria.FONDO;
            else
                _plato.categoria = categoria.POSTRE;

            _plato.ingredientes = ingredientesPlato.ToArray();
            _plato.foto = (byte[])Session["foto"];

            int resultado = platoWS.insertarPlato(_plato);

            if (resultado != 0)
                Response.Redirect("Home.aspx");
        }

        protected void dgvIngredientes_RowDataBound(object sender, GridViewRowEventArgs e)
        {
            if (e.Row.RowType == DataControlRowType.DataRow)
            {
                e.Row.Cells[0].Text = DataBinder.Eval(e.Row.DataItem, "nombre").ToString();
            }
        }
    }
}
