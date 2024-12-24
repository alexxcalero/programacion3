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
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace EventMasterSoft
{
    public partial class ListarEventos : System.Web.UI.Page
    {
        private EventoDAO eventoDAO;
        private BindingList<Evento> eventos;

        protected void Page_Load(object sender, EventArgs e)
        {
            eventoDAO = new EventoMySQL();
            eventos = eventoDAO.listarPorNombre("");
            gvEventos.DataSource = eventos;
            gvEventos.DataBind();
        }

        protected void lbRegistrar_Click(object sender, EventArgs e)
        {
            Response.Redirect("RegistrarEvento.aspx");
        }

        protected void lbVisualizar_Click(object sender, EventArgs e)
        {
            int idEvento = Int32.Parse(((LinkButton)sender).CommandArgument);
            Response.Redirect("RegistrarEvento.aspx?accion=ver&idEvento=" + idEvento);
        }

        protected void lbBuscar_Click(object sender, EventArgs e)
        {
            string nombre = txtNombre.Text;
            eventos = eventoDAO.listarPorNombre(nombre);
            gvEventos.DataSource = eventos;
            gvEventos.DataBind();
        }
    }
}
