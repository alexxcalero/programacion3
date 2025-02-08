using SoftPlantillaBaseBO.ServicioWS;
using SoftPlantillaBO;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace SoftPlantillaWS
{
	public partial class Plantilla1 : System.Web.UI.Page
	{
		private PersonaBO personaBO;
		private BindingList<persona> listaPersonas;

		public Plantilla1()
		{
			personaBO = new PersonaBO();
		}

		protected void Page_Load(object sender, EventArgs e)
		{
			listaPersonas = personaBO.listarTodas();
			gvPersonas.DataSource = listaPersonas;
			gvPersonas.DataBind();
		}

        protected void gvPersonas_RowDataBound(object sender, GridViewRowEventArgs e)
        {
			if (e.Row.RowType == DataControlRowType.DataRow)
			{
				e.Row.Cells[0].Text = DataBinder.Eval(e.Row.DataItem, "idPersona").ToString();
				e.Row.Cells[1].Text = DataBinder.Eval(e.Row.DataItem, "nombre").ToString() + "   " + DataBinder.Eval(e.Row.DataItem, "apellido").ToString();
			}
		}
	}
}