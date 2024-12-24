/* Colocar sus datos personales
 * ------------------------------------------------
 * Nombre Completo: Alex Calero Revilla
 * Codigo PUCP: 20206455
 * ------------------------------------------------
 */

using EventMasterSoftDA.DAO;
using EventMasterSoftDA.MySQL;
using EventMasterSoftModel;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.IO;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace EventMasterSoft
{
    public partial class RegistrarEvento : System.Web.UI.Page
    {
        private ProductoraDAO productoraDAO;
        private EventoDAO eventoDAO;
        private Evento evento;

        protected void Page_Init(object sender, EventArgs e)
        {
            productoraDAO = new ProductoraMySQL();

            ddlProductora.DataSource = productoraDAO.listarTodas();
            ddlProductora.DataTextField = "Nombre";
            ddlProductora.DataValueField = "IdProductora";
            ddlProductora.DataBind();
        }

        protected void Page_Load(object sender, EventArgs e)
        {
            string accion = Request.QueryString["accion"];

            if (accion != null && accion == "ver" && Session["idEvento"] != null)
            {
                lblTitulo.Text = "Visualizar Evento";
            } else
            {
                lblTitulo.Text = "Registrar Evento";
            }


            Cargar_Foto(sender, e);

        }

        public void Deshabilitar_Componentes()
        {
            txtIDEvento.Enabled = false;
            txtNombreEvento.Enabled = false;
            ddlProductora.Enabled = false;
            lbGuardar.Visible = false;
            txtDescripcion.Disabled = true;
            rbConcierto.Disabled = true;
            rbObraTeatral.Disabled = true;
            rbAdultos.Disabled = true;
            rbJovenes.Disabled = true;
            rbNinhos.Disabled = true;
            rbTodos.Disabled = true;
            txtCostoRealizacion.Enabled = false;
            dtpFechaRealizacion.Disabled = true;
            cbReingreso.Disabled = true;
            cbGrabacion.Disabled = true;
            fileUploadBannerPromocional.Enabled = false;
        }

        protected void Cargar_Foto(object sender, EventArgs e)
        {
            if (IsPostBack && fileUploadBannerPromocional.PostedFile != null && fileUploadBannerPromocional.HasFile)
            {
                string extension = System.IO.Path.GetExtension(fileUploadBannerPromocional.FileName);
                if (extension.ToLower() == ".jpg" || extension.ToLower() == ".jpeg" || extension.ToLower() == ".png" || extension.ToLower() == ".gif")
                {
                    string filename = Guid.NewGuid().ToString() + extension;
                    string filePath = Server.MapPath("~/Uploads/") + filename;
                    fileUploadBannerPromocional.SaveAs(Server.MapPath("~/Uploads/") + filename);
                    imgBannerPromocional.ImageUrl = "~/Uploads/" + filename;
                    imgBannerPromocional.Visible = true;
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
            Response.Redirect("ListarEventos.aspx");
        }

        protected void lbGuardar_Click(object sender, EventArgs e)
        {
            eventoDAO = new EventoMySQL();
            evento = new Evento();
            evento.Nombre = txtNombreEvento.Text;
            evento.Productora = new Productora();
            evento.Productora.IdProductora = Int32.Parse(ddlProductora.SelectedValue);
            if (rbObraTeatral.Checked)
                evento.TipoEvento = TipoEvento.OBRA_TEATRAL;
            else
                evento.TipoEvento = TipoEvento.CONCIERTO;
            if (rbAdultos.Checked)
                evento.Clasificacion = 'A';
            else if (rbJovenes.Checked)
                evento.Clasificacion = 'J';
            else if (rbNinhos.Checked)
                evento.Clasificacion = 'N';
            else
                evento.Clasificacion = 'T';
            evento.CostoRealizacion = Double.Parse(txtCostoRealizacion.Text);
            evento.FechaRealizacion = DateTime.Parse(dtpFechaRealizacion.Value);
            if (cbReingreso.Checked)
                evento.PermiteReingreso = true;
            if (cbGrabacion.Checked)
                evento.PermiteGrabacion = true;
            evento.Descripcion = txtDescripcion.Value;
            if (Session["foto"] != null)
                evento.BannerPromocional = (byte[])Session["foto"];
            int resultado = eventoDAO.insertar(evento);
            Response.Redirect("ListarEventos.aspx");
        }
    }
}
