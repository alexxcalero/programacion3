/* Colocar sus datos personales
 * ------------------------------------------------
 * Nombre Completo: Alex Calero Revilla
 * Codigo PUCP: 20206455
 * ------------------------------------------------
 */

using EventMasterSoftWA.ServicioWS;
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace EventMasterSoft
{
    public partial class RegistrarEvento : System.Web.UI.Page
    {
        private ProductoraWSClient daoProductora = new ProductoraWSClient();
        private EventoWSClient daoEvento = new EventoWSClient();

        protected void Page_Load(object sender, EventArgs e)
        {
            

            if(!IsPostBack)
            {
                ddlProductora.DataSource = daoProductora.listarTodasProductoras();
                ddlProductora.DataTextField = "nombre";
                ddlProductora.DataValueField = "idProductora";
                ddlProductora.DataBind();
            }

            string idEvento = Request.QueryString["idEvento"];
            string accion = Request.QueryString["accion"];
            if(accion != null && accion == "ver" && idEvento != null)
            {
				lblTitulo.Text = "Visualizar Evento";
				evento evento = daoEvento.obtenerEventoPorId(Int32.Parse(idEvento));
				string base64String = Convert.ToBase64String(evento.bannerPromocional);
				string imageUrl = "data:image/jpeg;base64," + base64String;
				imgBannerPromocional.ImageUrl = imageUrl;
				dtpFechaRealizacion.Value = evento.fechaRealizacion.ToString("yyyy-MM-dd");
				txtDescripcion.Value = evento.descripcion;
				txtNombreEvento.Text = evento.nombre;
				txtCostoRealizacion.Text = evento.costoRealizacion.ToString("N2");
				if (evento.clasificacion.Equals('A') ? rbAdultos.Checked = true : (evento.clasificacion.Equals('J') ?  rbJovenes.Checked = true : (evento.clasificacion.Equals('N') ? rbNinhos.Checked = true : rbTodos.Checked =  true)));
				ddlProductora.SelectedValue = evento.productora.idProductora.ToString();
				if (evento.tipoEvento == tipoEvento.OBRA_TEATRAL ? rbObraTeatral.Checked = true : rbConcierto.Checked = true);
				txtIDEvento.Text = evento.idEvento.ToString();
				cbReingreso.Checked = evento.permiteReingreso;
				cbGrabacion.Checked = evento.permiteGrabacion;
				Deshabilitar_Componentes();
			}
			else
            {
				lblTitulo.Text = "Registrar Evento";
				Cargar_Foto(sender, e);
			}

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
			evento evento = new evento();
			evento.nombre = txtNombreEvento.Text;
			evento.productora = new productora();
			evento.productora.idProductora = Int32.Parse(ddlProductora.SelectedValue);
			evento.descripcion = txtDescripcion.Value;
			evento.fechaRealizacion = DateTime.Parse(dtpFechaRealizacion.Value);
			evento.fechaRealizacionSpecified = true;
			evento.clasificacion = (rbAdultos.Checked ? 'A' : (rbJovenes.Checked ? 'J' : (rbNinhos.Checked ? 'N' : 'T')));
			evento.permiteGrabacion = cbGrabacion.Checked;
			evento.permiteReingreso = cbReingreso.Checked;
			evento.costoRealizacion = Double.Parse(txtCostoRealizacion.Text);
			evento.tipoEvento = rbConcierto.Checked ? tipoEvento.CONCIERTO : tipoEvento.OBRA_TEATRAL;
			evento.tipoEventoSpecified = true;
			evento.bannerPromocional = (byte[])Session["foto"];
			int resultado = daoEvento.insertarEvento(evento);
			if (resultado != 0)
				Response.Redirect("ListarEventos.aspx");
		}
	}
}