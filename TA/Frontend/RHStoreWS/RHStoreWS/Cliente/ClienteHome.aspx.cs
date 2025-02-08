using RHStoreBaseBO.ServiciosWeb;
using RHStorePrendasBO;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace RHStoreWS.Cliente
{
    public partial class ClienteHome : System.Web.UI.Page
    {
        private PrendaBO prendaBO;
        private BindingList<prenda> listaDePrendas;

        public ClienteHome()
        {
            prendaBO = new PrendaBO();
        }

        protected void Page_Load(object sender, EventArgs e)
        {
            /*if (!IsPostBack)
            {
                CargarPrendas();
            }*/
            CargarSexos(); // Nuevo método para cargar el filtro de sexo
            CargarTallas();
            CargarColores();
            /*if (Session["clienteLogueado"] == null)
            {
                Response.Write("Session clienteLogueado is null.<br>");
            }
            else
            {
                cliente clienteLogueado = (cliente)Session["clienteLogueado"];
                Response.Write("Cliente logueado: " + clienteLogueado.nombres + "<br>");
            }*/

            // Detectar si estamos en la página principal, para cargar todas las prendas.
            
            string nombreBuscado = Request.QueryString["cadena"];
            if (!string.IsNullOrEmpty(nombreBuscado))
            {
                buscarPrenda(nombreBuscado);
            }
            else if (Request.Url.AbsolutePath.ToLower().Contains("clientehome"))
            {
                CargarPrendas(null); // Cargar todas las prendas
            }
            else
            {
                // Cargar prendas según el género detectado en la página actual
                string genero = GetGenderFromPage();
                CargarPrendas(genero);
            }
        }

        private void CargarTallas()
        {
            try
            {
                BindingList<string> tallas = prendaBO.obtenerTallasUnicas();

                // Limpiar el panel antes de agregar nuevos elementos
                PanelTallas.Controls.Clear();
                foreach (string talla in tallas)
                {
                    CheckBox checkBox = new CheckBox
                    {
                        Text = talla,
                        ID = "cbTalla_" + talla.Replace(" ", "_"), // Reemplazar espacios
                        CssClass = "form-check-input"
                    };
                    PanelTallas.Controls.Add(checkBox);
                    PanelTallas.Controls.Add(new Literal { Text = "<br />" }); // Agregar salto de línea
                }
            }
            catch (Exception ex)
            {
                lblError.Text = "Error al cargar tallas: " + ex.Message;
            }
        }

        private void CargarColores()
        {
            try
            {
                BindingList<string> colores = prendaBO.obtenerColoresUnicos();

                // Limpiar el panel antes de agregar nuevos elementos
                PanelColores.Controls.Clear();

                foreach (string color in colores)
                {
                    CheckBox checkBox = new CheckBox
                    {
                        Text = color,
                        ID = "cbColor_" + color.Replace(" ", "_"), // Reemplazar espacios
                        CssClass = "form-check-input"
                    };
                    PanelColores.Controls.Add(checkBox);
                    PanelColores.Controls.Add(new Literal { Text = "<br />" }); // Agregar salto de línea
                }
            }
            catch (Exception ex)
            {
                lblError.Text = "Error al cargar colores: " + ex.Message;
            }
        }
        private void CargarSexos()
        {
            try
            {
                // Opciones de sexo
                string[] sexos = { "Hombre", "Mujer", "Unisex" };

                // Limpiar el panel antes de agregar nuevos elementos
                PanelSexo.Controls.Clear();

                foreach (string sexo in sexos)
                {
                    CheckBox checkBox = new CheckBox
                    {
                        Text = sexo,
                        ID = "cbSexo_" + sexo.Replace(" ", "_"), // Reemplazar espacios
                        CssClass = "form-check-input"
                    };
                    PanelSexo.Controls.Add(checkBox);
                    PanelSexo.Controls.Add(new Literal { Text = "<br />" }); // Agregar salto de línea
                }
            }
            catch (Exception ex)
            {
                lblError.Text = "Error al cargar sexos: " + ex.Message;
            }
        }

        public void CargarPrendas(string genero)
        {
            if (string.IsNullOrEmpty(genero))
            {
                listaDePrendas = new BindingList<prenda>(prendaBO.listarTodos());
            }
            else
            {
                bool filterHombre = genero.Equals("Hombre", StringComparison.OrdinalIgnoreCase);
                bool filterMujer = genero.Equals("Mujer", StringComparison.OrdinalIgnoreCase);
                bool filterUnisex = genero.Equals("Unisex", StringComparison.OrdinalIgnoreCase);

                listaDePrendas = new BindingList<prenda>(prendaBO.listarPrendasFiltradas(0, 1000, filterHombre, filterMujer, filterUnisex, "", ""));
            }

            RepeaterPrendas.DataSource = listaDePrendas;
            RepeaterPrendas.DataBind();
        }

        private string GetGenderFromPage()
        {
            string currentPage = Request.Url.AbsolutePath.ToLower();
            if (currentPage.Contains("hombre")) return "Hombre";
            if (currentPage.Contains("mujer")) return "Mujer";
            if (currentPage.Contains("unisex")) return "Unisex";
            return null;
        }

        private void buscarPrenda(string nombreBuscado)
        {
            if (!string.IsNullOrEmpty(nombreBuscado))
            {
                // Llamar al procedimiento almacenado para buscar prendas
                listaDePrendas = new BindingList<prenda>(prendaBO.listarPorNombreDescripcion(nombreBuscado)); // Cambia por tu método
                RepeaterPrendas.DataSource = listaDePrendas;
                RepeaterPrendas.DataBind();
            }
            else
            {
                // Si no hay texto, cargar todas las prendas
                CargarPrendas(null);
            }
        }
        protected void btnFiltrar_Click(object sender, EventArgs e)
        {
            double minPrice = double.TryParse(minPriceTextBox.Text, out double min) ? min : 0;
            double maxPrice = double.TryParse(maxPriceTextBox.Text, out double max) ? max : 1000000;

            /*bool filterHombre = hombreCheckBox.Checked;
            bool filterMujer = mujerCheckBox.Checked;
            bool filterUnisex = unisexCheckBox.Checked;
            bool filterHombre = true;
            bool filterMujer = true;
            bool filterUnisex = true;*/

            // Obtener los valores seleccionados para el filtro de sexo
            List<string> sexosSeleccionados = GetSelectedSexos();
            bool filterHombre = sexosSeleccionados.Contains("Hombre");
            bool filterMujer = sexosSeleccionados.Contains("Mujer");
            bool filterUnisex = sexosSeleccionados.Contains("Unisex");

            // Verificar tallas seleccionadas
            string tallasSeleccionadas = string.IsNullOrEmpty(string.Join(",", GetSelectedTallas())) ? "" : string.Join(",", GetSelectedTallas());
            // Verificar colores seleccionados
            string coloresSeleccionados = string.IsNullOrEmpty(string.Join(",", GetSelectedColores())) ? "" : string.Join(",", GetSelectedColores());

            // Llamar al método para obtener prendas filtradas
            BindingList<prenda> prendasFiltradas = prendaBO.listarPrendasFiltradas(minPrice, maxPrice, filterHombre, filterMujer, filterUnisex, tallasSeleccionadas, coloresSeleccionados);

            // Asegúrate de que RepeaterPrendas no sea null
            if (RepeaterPrendas != null)
            {
                RepeaterPrendas.DataSource = prendasFiltradas;
                RepeaterPrendas.DataBind();
            }
            else
            {
                throw new Exception("El RepeaterPrendas no está inicializado en el Master.");
            }
        }

        private List<string> GetSelectedTallas()
        {
            List<string> tallasSeleccionadas = new List<string>();
            foreach (Control control in PanelTallas.Controls)
            {
                if (control is CheckBox checkBox && checkBox.Checked)
                {
                    tallasSeleccionadas.Add(checkBox.Text);
                }
            }
            return tallasSeleccionadas;
        }
        private List<string> GetSelectedColores()
        {
            List<string> coloresSeleccionados = new List<string>();
            foreach (Control control in PanelColores.Controls)
            {
                if (control is CheckBox checkBox && checkBox.Checked)
                {
                    coloresSeleccionados.Add(checkBox.Text);
                }
            }
            return coloresSeleccionados;
        }
        private List<string> GetSelectedSexos()
        {
            List<string> sexosSeleccionados = new List<string>();
            foreach (Control control in PanelSexo.Controls)
            {
                if (control is CheckBox checkBox && checkBox.Checked)
                {
                    sexosSeleccionados.Add(checkBox.Text);
                }
            }
            return sexosSeleccionados;
        }

        protected void btnGridView_Click(object sender, EventArgs e)
        {
            ScriptManager.RegisterStartupScript(this, GetType(), "SwitchView", "document.getElementById('productContainer').className = 'products grid-view';", true);
        }

        protected void btnDetailView_Click(object sender, EventArgs e)
        {
            ScriptManager.RegisterStartupScript(this, GetType(), "SwitchView", "document.getElementById('productContainer').className = 'products detail-view';", true);
        }

        protected void btnMosaicView_Click(object sender, EventArgs e)
        {
            ScriptManager.RegisterStartupScript(this, GetType(), "SwitchView", "document.getElementById('productContainer').className = 'products mosaic-view';", true);
        }
        protected string ObtenerImagen(int idPrenda)
        {
            // Obtener la imagen como bytes desde la base de datos
            byte[] imagenBytes = prendaBO.ObtenerImagen(idPrenda); // Implementa este método en PrendaBO
            if (imagenBytes != null)
            {
                // Convertir a Base64 para poder usar en src
                return "data:image/jpeg;base64," + Convert.ToBase64String(imagenBytes);
            }
            return string.Empty; // Devuelve una cadena vacía si no hay imagen
        }


    }
}