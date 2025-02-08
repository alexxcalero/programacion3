using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using RHStoreBaseBO.ServiciosWeb;
using RHStoreComprasBO;
using RHStorePrendasBO;
using RHStoreUsuariosBO;

namespace RHStoreWS.Cliente
{
    public partial class Carrito : System.Web.UI.Page
    {
        private cliente _cliente;
        private PrendaBO prendaBO = new PrendaBO();
        private PrendaSeleccionadaBO prendaSeleccionadaBO = new PrendaSeleccionadaBO();
        private CarritoBO carritoBO = new CarritoBO();
        private CuponBO cuponBO = new CuponBO();
        private OrdenCompraBO ordenCompraBO = new OrdenCompraBO();
        private ClienteBO clienteBO = new ClienteBO();

        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                if (Session["clienteLogueado"] != null)
                {
                    _cliente = (cliente)Session["clienteLogueado"];
                    LoadCarrito();
                }
                else
                {
                    // Redirigir al inicio de sesión si no hay cliente logueado
                    Response.Redirect("../Admin/IniciarSesion.aspx");
                }
            }
        }

        private void LoadCarrito()
        {
            carrito carrito= (carrito)Session["carritoUsuario"];
            BindingList<prendaSeleccionada> prendasSeleccionadas = prendaSeleccionadaBO.listarPrendasPorCarrito(carrito.idCarrito);
            if (prendasSeleccionadas.Count == 0)
            {
                carrito = carritoBO.obtenerPorId(carrito.fidCliente);
                Session["carritoUsuario"] = carrito;
            }

            lblCantidadTotal.Text = carrito.cantidadTotal.ToString();
            lblPrecioTotal.Text = carrito.precioTotal.ToString("F2");
            //BindingList<prendaSeleccionada> prendasSeleccionadas = prendaSeleccionadaBO.listarPrendasPorCarrito(carrito.idCarrito);


            bool cuponActivo = (bool)Session["cuponActivo"];

            if (prendasSeleccionadas.Count == 0)
            {
                lblMensaje.Text = "El carrito está vacío.";
                lblMensaje.Visible = true;
                btnPagar.Enabled = false;
                rptPrendasSeleccionadas.Visible = false;
                txtBuscar.Visible = false;
                lbBuscar.Visible = false;
            }
            else
            {
                if (cuponActivo == true)
                {
                    cupon cupon = (cupon)Session["cupon"];
                    btnRevertirCupon.Visible = true;
                    lblCuponInfo.Text = $"Cupón: {cupon.descripcion}, Descuento: {cupon.valorDescuento}%";
                    lblCuponInfo.Visible = true;
                    btnAplicarCupon.Visible = false;
                    txtBuscar.Visible = false;
                    lbBuscar.Visible = false;
                }
                else if (Session["cupon"] == null)
                {
                    lblCuponInfo.Visible = false;
                    txtBuscar.Visible = true;
                    lbBuscar.Visible = true;
                }

                List<Object> listaPrendasDetalles = new List<Object>();
                foreach (prendaSeleccionada prendaSel in prendasSeleccionadas)
                {
                    prenda prenda = prendaBO.obtenerPorId(prendaSel.idPrendaSeleccionada);
                    string imagenUrl = ConvertirImagenABase64(prenda.imagen);

                    listaPrendasDetalles.Add(new
                    {
                        prendaSel.idPrendaSeleccionada,
                        Nombre = prenda.nombre,
                        ImagenUrl = imagenUrl,
                        Precio = prendaSel.precio.ToString("F2"),
                        prendaSel.cantidad
                    });
                }

                lblMensaje.Visible = false;
                rptPrendasSeleccionadas.DataSource = listaPrendasDetalles;
                rptPrendasSeleccionadas.DataBind();
                btnPagar.Enabled = true;
                rptPrendasSeleccionadas.Visible = true;
            }

        }

        private string ConvertirImagenABase64(byte[] imagen)
        {
            if (imagen != null)
            {
                return "data:image/jpeg;base64," + Convert.ToBase64String(imagen);
            }
            return string.Empty;
        }

        private void ActualizarPrendaSeleccionada(int idPrendaSeleccionada, int idCarrito, int cambioCantidad)
        {
            carrito carrito = (carrito)Session["carritoUsuario"];
            prendaSeleccionada prendaSeleccionada = prendaSeleccionadaBO.obtenerPorId(idPrendaSeleccionada, idCarrito);
            prenda prenda = prendaBO.obtenerPorId(idPrendaSeleccionada);
            double precioUnitario = prenda.precioOriginal;

            if (prenda.precioDescontado != 0)
            {
                precioUnitario = prenda.precioDescontado;
            }

            prendaSeleccionada.cantidad += cambioCantidad;
            prendaSeleccionada.precio += precioUnitario * cambioCantidad;

            carrito.cantidadTotal += cambioCantidad;
            carrito.precioTotal += precioUnitario * cambioCantidad;

            prendaSeleccionadaBO.modificar(prendaSeleccionada);
            carritoBO.modificar(carrito);

            Session["carritoUsuario"] = carrito;
            LoadCarrito();
        }

        private bool ValidarAccionConCupon()
        {
            if (Session["cupon"] != null)
            {
                lblMensaje.Text = "No es posible realizar esta acción con un cupón en sesión.";
                lblMensaje.Visible = true;
                return false;
            }
            return true;
        }

        protected void btnRestar_Click(object sender, EventArgs e)
        {
            if (!ValidarAccionConCupon()) return;

            carrito carrito = (carrito)Session["carritoUsuario"];
            int idCarrito = carrito.idCarrito;
            int idPrendaSeleccionada = int.Parse(((Button)sender).CommandArgument);
            prendaSeleccionada prendaSeleccionada = prendaSeleccionadaBO.obtenerPorId(idPrendaSeleccionada, idCarrito);
            double precioUnitario = prendaBO.obtenerPorId(idPrendaSeleccionada).precioOriginal;

            if (prendaSeleccionada.cantidad > 1)
            {
                ActualizarPrendaSeleccionada(idPrendaSeleccionada, idCarrito, -1);
            }
        }

        protected void btnSumar_Click(object sender, EventArgs e)
        {
            if (!ValidarAccionConCupon()) return;

            carrito carrito = (carrito)Session["carritoUsuario"];
            int idCarrito = carrito.idCarrito;
            int idPrendaSeleccionada = int.Parse(((Button)sender).CommandArgument);
            int stock = prendaBO.obtenerPorId(idPrendaSeleccionada).stock;
            prendaSeleccionada prendaSeleccionada = prendaSeleccionadaBO.obtenerPorId(idPrendaSeleccionada, idCarrito);

            if (prendaSeleccionada.cantidad < stock)
            {
                ActualizarPrendaSeleccionada(idPrendaSeleccionada, idCarrito, 1);
            }
        }

        protected void btnPagar_Click(object sender, EventArgs e)
        {
            correoComprobante.Visible = true;
            dniComprobante.Visible = true;
            datos_boton.Visible = true;
        }

        protected void copiar_prendas_del_carrito(int idOrden, int idCarrito)
        {
            BindingList<prendaSeleccionada> prendasSeleccionadas = prendaSeleccionadaBO.listarPrendasPorCarrito(idCarrito);


            foreach (var prenda in prendasSeleccionadas)
            {
                int result = this.ordenCompraBO.insertarPrendaSeleccionada(
                    prenda.idPrendaSeleccionada,
                    idOrden,
                    prenda.cantidad,
                    prenda.precio);

                result += 0;
            }
        }


        protected void btnVolver_Click(object sender, EventArgs e)
        {
            Response.Redirect("ClienteHome.aspx");
        }

        protected void btnEliminar_Click(Object sender, EventArgs e)
        {
            if (Session["cupon"] != null)
            {
                lblMensaje.Text = "No es posible realizar esta acción con un cupón en sesión.";
                lblMensaje.Visible = true;
                return;
            }

            carrito carrito = (carrito)Session["carritoUsuario"];
            int idCarrito = carrito.idCarrito;
            int idPrendaSeleccionada = int.Parse(((Button)sender).CommandArgument);
            prendaSeleccionada prendaSeleccionada = prendaSeleccionadaBO.obtenerPorId(idPrendaSeleccionada, idCarrito);

            
            carrito.cantidadTotal -= prendaSeleccionada.cantidad;
            carrito.precioTotal -= prendaSeleccionada.precio;
            if (carrito.precioTotal < 0)
            {
                carrito.precioTotal = 0;
                carrito.cantidadTotal = 0;
            }

            prendaSeleccionadaBO.eliminar(idPrendaSeleccionada, carrito.idCarrito);
            carritoBO.modificar(carrito);

            Session["carritoUsuario"] = carrito;

            LoadCarrito();
        }

        protected void lbBuscarCupon_Click(Object sender, EventArgs e)
        {
            String codigo = txtBuscar.Text;
            BindingList<cupon> cupones = cuponBO.listarPorCodigoDescripcion(codigo);


            if (cupones.Count == 0 || cupones.Count > 1)
            {
                lblMensaje.Text = "El código es incorrecto.";
                lblMensaje.Visible = true;
                Session["cupon"] = null;
            }
            else
            {
                lblMensaje.Visible = false;
                cupon cupon = cupones[0];
                Session["cupon"] = cupon;

                lblCuponInfo.Text = $"Cupón: {cupon.descripcion}, Descuento: {cupon.valorDescuento}%";
                lblCuponInfo.Visible = true;
                btnAplicarCupon.Visible = true;

                LoadCarrito();
            }

        }

        protected void btnAplicarCupon_Click(Object sender, EventArgs e)
        {
            cupon cupon = (cupon)Session["cupon"];
            carrito carrito = (carrito)Session["carritoUsuario"];
            double descuento = (carrito.precioTotal * (cupon.valorDescuento / 100));

            carrito.precioTotal -= descuento;
            Session["carritoUsuario"] = carrito;

            Session["cuponActivo"] = true;
            //btnRevertirCupon.Visible = true;
            //btnAplicarCupon.Visible = false;
            //lblCuponInfo.Visible = false;

            LoadCarrito();
        }

        protected void btnRevertirCupon_Click(Object sender, EventArgs e)
        {
            int idCliente = (int)Session["idUsuario"];
            Session["carritoUsuario"] = carritoBO.obtenerPorId(idCliente);
            Session["cupon"] = null;
            Session["cuponActivo"] = false;

            btnRevertirCupon.Visible = false;

            LoadCarrito();
        }

        protected void btnDatos(Object sender, EventArgs e)
        {
            string _correoComprobate = correoComprobante.Text;
            string _dniComprobante = dniComprobante.Text;
            bool isEmailValid = IsValidEmail(_correoComprobate);            
            bool isDniValid = IsValidDNI(_dniComprobante);

            if (isEmailValid && isDniValid)
            {
                int idOrden;
                carrito carrito = (carrito)Session["carritoUsuario"];
                cliente cliente = clienteBO.obtenerPorId((int)Session["idUsuario"]);
                cupon cupon = (cupon)Session["cupon"];
                DateTime fechaActual = DateTime.Parse(DateTime.Now.ToString("yyyy-MM-dd"));
                if (cupon == null)
                {
                    idOrden = ordenCompraBO.insertarSinCupon(fechaActual, estado.Registrado, _dniComprobante,
                        _correoComprobate, carrito.precioTotal, cliente, carrito);
                    ordenCompraBO.cambiarEstado(idOrden, "Pagado");
                }
                else
                {
                    idOrden = ordenCompraBO.insertarConCupon(fechaActual, estado.Registrado, _dniComprobante,
                        _correoComprobate, carrito.precioTotal, cliente, cupon, carrito);
                    cuponBO.insertarCuponUsado(cliente.idUsuario, cupon.idCupon, fechaActual);
                    ordenCompraBO.cambiarEstado(idOrden, "Pagado");
                }

                //EN EL DEPLOY SE TIENE QUE CAMBIAR EL STRING DE LINK
                string link = "http://3.81.79.30:8080/Cliente/Pagado.aspx?idOrden=" + idOrden.ToString();
                string url_return = ordenCompraBO.pagarConPaypal(idOrden,
                    link + "&canceled=0" + "&idCliente=" + cliente.idUsuario.ToString(), link + "&canceled=1");
                this.copiar_prendas_del_carrito(idOrden, carrito.idCarrito);
                Response.Redirect(url_return);
            }
            else
            {
                invalidFields.Text = "Correo o dni no valido";
                invalidFields.Visible = true;
            }
        }
        
        private bool IsValidEmail(string email)
        {
            // Simple regular expression to check for a valid email format
            string emailPattern = @"^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$";
            return System.Text.RegularExpressions.Regex.IsMatch(email, emailPattern);
        }

        
        private bool IsValidDNI(string dni)
        {
            // Check if the DNI is a valid numeric value with 8 digits
            return dni.Length == 8 && dni.All(char.IsDigit);
        }
    }

}

