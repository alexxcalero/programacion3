using RHStoreBaseBO.ServiciosWeb;
using RHStorePrendasBO;
using RHStoreUsuariosBO;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Text.RegularExpressions;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace RHStoreWS.Admin
{
    public partial class CrudPromociones : System.Web.UI.Page
    {
        private promocion _promocion;
        private trabajador _trabajadorSeleccionado;
        private PromocionBO promocionBO;
        private TrabajadorBO trabajadorBO;
        private PrendaBO prendaBO;

        private BindingList<trabajador> listaTrabajadores;
        private BindingList<prenda> prendasAgregadas;
        private BindingList<prenda> listaPrendas;
        private bool estaModificando;

        public CrudPromociones()
        {
            promocionBO = new PromocionBO();
            trabajadorBO = new TrabajadorBO();
            prendaBO = new PrendaBO();

        }

        protected void Page_Load(object sender, EventArgs e)
        {

            if (Session["administradorLogueado"] != null)
            {
                administrador _administrador = (administrador)Session["administradorLogueado"];
                lblNombreUsuario.Text = _administrador.nombres + " " + _administrador.apellidos;
				lbBuscarTrabajador.Enabled = true;
				lbBuscarTrabajador.CssClass = "btn btn-primary col-sm-auto";
			}
            else if (Session["trabajadorLogueado"] != null)
            {
                trabajador _trabajador = (trabajador)Session["trabajadorLogueado"];
                Session["idTrabajador"] = _trabajador.idUsuario; // ----------------------------------------- AGREGUE AQUI
                txtNombreTrabajador.Text = _trabajador.nombres + " " + _trabajador.apellidos;
                lblNombreUsuario.Text = _trabajador.nombres + " " + _trabajador.apellidos;
                lbBuscarTrabajador.Visible = false;
            }

            if (Session["prendasAgregadas"] == null)
                this.prendasAgregadas = new BindingList<prenda>();
            else
                this.prendasAgregadas = new BindingList<prenda>(((BindingList<prenda>)Session["prendasAgregadas"]).ToList());

            // Asegúrate de enlazar prendasAgregadas al GridView en cada carga de página

            string accion = Request.QueryString["accion"];
            if (accion != null && accion == "modificar")
            {
                lblTitulo.Text = "Modificación de Promoción";
                _promocion = (promocion)Session["promocion"];
                cargarDatosDeLaBD(_promocion);
                estaModificando = true;
                lbBuscarTrabajador.Visible = false;
            }
            else
            {
                lblTitulo.Text = "Registro de Promoción";
                estaModificando = false;
            }

            gvPrendasSeleccionadas.DataSource = prendasAgregadas;
            gvPrendasSeleccionadas.DataBind();

        }
        protected void cargarDatosDeLaBD(promocion _promocion)
        {
            if (!IsPostBack)
            {
                txtID.Text = _promocion.idPromocion.ToString();

                // Trabajador
                txtNombreTrabajador.Text = _promocion.trabajador.nombres + " " + _promocion.trabajador.apellidos;
                // Prenda

                prendasAgregadas = promocionBO.listarPrendaAgregadas(_promocion.idPromocion); // FUNCION QUE DEVUELVE LA LISTA DE PRENDAS 
                Session["prendasAgregadas"] = prendasAgregadas;

                gvPrendasSeleccionadas.DataSource = prendasAgregadas;
                gvPrendasSeleccionadas.DataBind();


                txtNombrePromocion.Text = _promocion.nombre;

                if (_promocion.tipo == tipoPromocion.Porcentaje)
                    rbPorcentaje.Checked = true;
                else if (_promocion.tipo == tipoPromocion.MontoFijo)
                    rbMontoFijo.Checked = true;

                txtValorDescuento.Text = _promocion.valorDescuento.ToString();
                dtpFechaInicio.Value = _promocion.fechaInicio.ToString("yyyy-MM-dd");
                dtpFechaFin.Value = _promocion.fechaFin.ToString("yyyy-MM-dd");
                txtDescripcion.Value = _promocion.descripcion;
            }
        }

        protected void lbBuscarTrabajador_Click(object sender, EventArgs e)
        {
            string script = "window.onload = function() { showModalSeleccionarTrabajador() };";
            ClientScript.RegisterStartupScript(GetType(), "", script, true);
        }
        protected void lbRegresar_Click(object sender, EventArgs e)
        {
            Session["prendasAgregadas"] = null;
            Response.Redirect("GestionarPromociones.aspx");
        }
        protected void lbGuardar_Click(object sender, EventArgs e)
        {
            int resultado;
            double valorDescuento;
			DateTime fechaInicio, fechaFin;
            
            string nombre = txtNombrePromocion.Text;
			if (nombre.Trim().Equals(""))
			{
				ejecutarModalError("Debe ingresar un nombre");
				return;
			}

			if (txtValorDescuento.Text.Trim().Equals(""))
			{
				ejecutarModalError("Debe ingresar el valor de descuento");
				return;
			}
			try
			{
				valorDescuento = Double.Parse(txtValorDescuento.Text);
			}
			catch (Exception ex)
			{
				ejecutarModalError("El valor de descuento debe ser un número");
				return;
			}

			tipoPromocion tipo;
            if (rbPorcentaje.Checked == true)
                tipo = tipoPromocion.Porcentaje;
            else
                tipo = tipoPromocion.MontoFijo;

			if (rbPorcentaje.Checked == false && rbMontoFijo.Checked == false)
			{
				ejecutarModalError("Debe seleccionar un tipo de promoción");
				return;
			}

			try
			{
				fechaInicio = DateTime.Parse(dtpFechaInicio.Value);
			}
			catch (Exception ex)
			{
				ejecutarModalError("Debe seleccionar la fecha de inicio de la promoción");
				return;
			}

			if (estaModificando == false)
			{
				if (fechaInicio < DateTime.Today)
				{
					ejecutarModalError("Debe ingresar la fecha de inicio correctamente");
					return;
				}
			}

			try
			{
				fechaFin = DateTime.Parse(dtpFechaFin.Value);
			}
			catch (Exception ex)
			{
				ejecutarModalError("Debe seleccionar la fecha de fin de la promoción");
				return;
			}

			if (estaModificando == false)
			{
				if (fechaFin < DateTime.Today)
				{
					ejecutarModalError("Debe ingresar la fecha de fin correctamente");
					return;
				}
			}

			if (fechaFin < fechaInicio)
			{
				ejecutarModalError("Debe ingresar las fechas correctamente");
				return;
			}

			string descripcion = txtDescripcion.Value;
			if (nombre.Trim().Equals(""))
			{
				ejecutarModalError("Debe ingresar una promoción");
				return;
			}

			int idTrabajador = (int)Session["idTrabajador"];
            trabajador _trabajador = trabajadorBO.obtenerPorId(idTrabajador);
            if (_trabajador == null)
            {
				ejecutarModalError("Debe seleccionar un trabajador");
				return;
			}

            if (estaModificando == true)
            {
                int idPromocion = Int32.Parse(txtID.Text);
                resultado = promocionBO.modificar(idPromocion, nombre, descripcion, valorDescuento, tipo, fechaInicio, fechaFin, prendasAgregadas.ToArray(), _trabajador);
                if (resultado != 0)
                    Response.Redirect("GestionarPromociones.aspx");
            }
            else
            {
                resultado = promocionBO.insertar(nombre, descripcion, valorDescuento, tipo, fechaInicio, fechaFin, prendasAgregadas.ToArray(), _trabajador);
                if (resultado != 0)
                    Response.Redirect("GestionarPromociones.aspx");
            }

            Session["prendasAgregadas"] = null;
        }

		protected void ejecutarModalError(string mensaje)
		{
			lblMensajeError.Text = mensaje;
			string script = "showModalFormError();";
			ScriptManager.RegisterStartupScript(this, GetType(), "ShowModalFormError", script, true);
		}

		protected void lbBuscarPrenda_Click(object sender, EventArgs e)
        {
            string script = "window.onload = function() { showModalSeleccionarPrendas() };";
            ClientScript.RegisterStartupScript(GetType(), "", script, true);
        }

        protected void gvPrendasSeleccionadas_RowDataBound(object sender, GridViewRowEventArgs e)
        {
            if (e.Row.RowType == DataControlRowType.DataRow)
            {
                e.Row.Cells[0].Text = DataBinder.Eval(e.Row.DataItem, "nombre").ToString();
                e.Row.Cells[1].Text = DataBinder.Eval(e.Row.DataItem, "talla").ToString();
                e.Row.Cells[2].Text = DataBinder.Eval(e.Row.DataItem, "genero").ToString();
                e.Row.Cells[3].Text = ((double)DataBinder.Eval(e.Row.DataItem, "precioOriginal")).ToString("N2");
            }
        }

        protected void gvPrendasSeleccionadas_lbEliminar_Click(object sender, EventArgs e)
        {
            int idPrenda;
            if (!Int32.TryParse(((LinkButton)sender).CommandArgument, out idPrenda))
            {
                // Manejo de error
                throw new Exception("El CommandArgument no es un número válido.");
            }

            // Verificar la consistencia de la lista
            if (prendasAgregadas == null || !prendasAgregadas.Any(p => p.idPrenda == idPrenda))
            {
                throw new Exception("La prenda no fue encontrada en la lista.");
            }

            // Buscar la prenda en la lista usando el idPrenda
            prenda prendaAEliminar = prendasAgregadas.First(p => p.idPrenda == idPrenda);

            if (prendaAEliminar != null) // Si se encontró la prenda en la lista
            {
                // Eliminar la prenda de la lista
                prendasAgregadas.Remove(prendaAEliminar);

                // Actualizar la sesión y el GridView después de la eliminación
                Session["prendasAgregadas"] = prendasAgregadas;
                gvPrendasSeleccionadas.DataSource = prendasAgregadas;
                gvPrendasSeleccionadas.DataBind();
            }
            else
            {
                throw new Exception("La prenda no se encontró.");
            }
        }


        protected void gvPrendasSeleccionadas_PageIndexChanging(object sender, GridViewPageEventArgs e)
        {
            gvPrendasSeleccionadas.PageIndex = e.NewPageIndex;
            gvPrendasSeleccionadas.DataBind();
        }

        protected void modalSeleccionarTrabajador_lbBuscarTrabajador_Click(object sender, EventArgs e)
        {
            string cadena = modalSeleccionarTrabajador_txtDniNombre.Text;
            listaTrabajadores = trabajadorBO.listarPorDniNombre(cadena);
            modalSeleccionarTrabajador_gvTrabajadores.DataSource = listaTrabajadores;
            modalSeleccionarTrabajador_gvTrabajadores.DataBind();
        }

        protected void modalSeleccionarTrabajador_gvTrabajadores_RowDataBound(object sender, GridViewRowEventArgs e)
        {
            if (e.Row.RowType == DataControlRowType.DataRow)
            {
                e.Row.Cells[0].Text = DataBinder.Eval(e.Row.DataItem, "idUsuario").ToString();
                e.Row.Cells[1].Text = DataBinder.Eval(e.Row.DataItem, "dni").ToString();
                e.Row.Cells[2].Text = DataBinder.Eval(e.Row.DataItem, "nombres").ToString() + " " + DataBinder.Eval(e.Row.DataItem, "apellidos").ToString();
                e.Row.Cells[3].Text = DataBinder.Eval(e.Row.DataItem, "puesto").ToString();
            }
        }

        protected void modalSeleccionarTrabajador_lbSeleccionarTrabajador_Click(object sender, EventArgs e)
        {
            int idTrabajador = Int32.Parse(((LinkButton)sender).CommandArgument);
            _trabajadorSeleccionado = trabajadorBO.obtenerPorId(idTrabajador);
            Session["idTrabajador"] = idTrabajador; //  ----------------------------------------- AGREGUE AQUI
            txtNombreTrabajador.Text = _trabajadorSeleccionado.nombres + " " + _trabajadorSeleccionado.apellidos;
            ScriptManager.RegisterStartupScript(this, GetType(), "", "__doPostBack('','');", true);
        }

        protected void modalSeleccionarTrabajador_gvTrabajadores_PageIndexChanging(object sender, GridViewPageEventArgs e)
        {
            modalSeleccionarTrabajador_gvTrabajadores.PageIndex = e.NewPageIndex;
            modalSeleccionarTrabajador_gvTrabajadores.DataBind();
        }

        protected void modalSeleccionarPrendas_lbBuscarPrenda_Click(object sender, EventArgs e)
        {
            string cadena = modalSeleccionarPrendas_txtNombreDescripcionPrenda.Text;
            listaPrendas = prendaBO.listarPorNombreDescripcion(cadena);
            modalSeleccionarPrendas_gvPrendas.DataSource = listaPrendas;
            modalSeleccionarPrendas_gvPrendas.DataBind();
        }

        protected void modalSeleccionarPrendas_gvPrendas_RowDataBound(object sender, GridViewRowEventArgs e)
        {
            if (e.Row.RowType == DataControlRowType.DataRow)
            {
                e.Row.Cells[0].Text = DataBinder.Eval(e.Row.DataItem, "nombre").ToString();
                e.Row.Cells[1].Text = DataBinder.Eval(e.Row.DataItem, "talla").ToString();
                e.Row.Cells[2].Text = DataBinder.Eval(e.Row.DataItem, "genero").ToString();
                e.Row.Cells[3].Text = ((double)DataBinder.Eval(e.Row.DataItem, "precioOriginal")).ToString("N2");
            }
        }


        protected void modalSeleccionarPrendas_lbSeleccionarPrenda_Click(object sender, EventArgs e)
        {
            int idPrenda = Int32.Parse(((LinkButton)sender).CommandArgument);

            prenda _prenda = prendaBO.obtenerPorId(idPrenda);

            bool existePrenda = prendasAgregadas.Any(p => p.idPrenda == _prenda.idPrenda);

            if (!existePrenda) // Agregar solo si la prenda no está ya en la lista
            {
                prendasAgregadas.Add(_prenda);
                Session["prendasAgregadas"] = prendasAgregadas;

                gvPrendasSeleccionadas.DataSource = prendasAgregadas;
                gvPrendasSeleccionadas.DataBind();
            }

            ScriptManager.RegisterStartupScript(this, GetType(), "", "__doPostBack('','');", true);
        }

        protected void modalSeleccionarPrendas_gvPrendas_PageIndexChanging(object sender, GridViewPageEventArgs e)
        {
            modalSeleccionarPrendas_gvPrendas.PageIndex = e.NewPageIndex;
            modalSeleccionarPrendas_gvPrendas.DataBind();
        }
    }
}
