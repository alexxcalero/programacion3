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
    public partial class RegistrarPostulante : System.Web.UI.Page
    {
        private PostulanteBO postulanteBO;
        private UniversidadBO universidadBO;
        private BindingList<universidad> listaUniversidades;

        public RegistrarPostulante()
        {
            postulanteBO = new PostulanteBO();
            universidadBO = new UniversidadBO();
            listaUniversidades = universidadBO.listarTodas();
        }

        protected void Page_Load(object sender, EventArgs e)
        {
            if(!IsPostBack)
            {
                ddlUniversidadOrigen.DataSource = listaUniversidades;
                ddlUniversidadOrigen.DataValueField = "idUniversidad";
                ddlUniversidadOrigen.DataTextField = "nombre";
                ddlUniversidadOrigen.DataBind();
            }
        }

        protected void lbRegresar_Click(object sender, EventArgs e)
        {
            Response.Redirect("Home.aspx");
        }

        protected void lbGuardar_Click(object sender, EventArgs e)
        {
            string dni = txtDNI.Text;
            string nombre = txtNombre.Text;
            string apellidoPaterno = txtApellidoPaterno.Text;

            int idUniversidad = Int32.Parse(ddlUniversidadOrigen.SelectedValue);
            universidad universidad = listaUniversidades.SingleOrDefault(x => x.idUniversidad == idUniversidad);

            double promedio = Double.Parse(txtPromedio.Text);

            bool tercioSuperio = false;
            if (cbTercioSuperior.Checked == true)
                tercioSuperio = true;

            bool grupoInvestigacion = false;
            if (cbGrupoInvestigacion.Checked == true)
                grupoInvestigacion = true;

            bool deportistaCalificado = false;
            if(cbDeportistaCalificado.Checked == true)
                deportistaCalificado = true;

            modalidad modalidadPreferida;
            if (rbPresencial.Checked == true)
                modalidadPreferida = modalidad.PRESENCIAL;
            else if (rbVirtual.Checked == true)
                modalidadPreferida = modalidad.VIRTUAL;
            else
                modalidadPreferida = modalidad.HIBRIDA;

            int resultado = postulanteBO.insertar(universidad, dni, nombre, apellidoPaterno, promedio, tercioSuperio, grupoInvestigacion, deportistaCalificado, modalidadPreferida);

            // Si se inserta de manera correcta, redireccionamos a la pantalla de home
            if (resultado != 0)
                Response.Redirect("Home.aspx");
        }
    }
}