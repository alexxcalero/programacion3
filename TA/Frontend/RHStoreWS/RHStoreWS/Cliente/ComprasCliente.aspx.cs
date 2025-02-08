using RHStoreBaseBO.ServiciosWeb;
using RHStoreComprasBO;
using RHStoreUsuariosBO;
using RHStorePrendasBO;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using iTextSharp.text;
using iTextSharp.text.pdf;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.IO;

namespace RHStoreWS.Cliente
{
    public partial class ComprasCliente : System.Web.UI.Page
    {
        private cliente _cliente;
        private OrdenCompraBO ordenBO;

        private static int codigoContador = 1;

        public ComprasCliente()
        {
            ordenBO = new OrdenCompraBO(); // Inicializa el BO para gestionar las direcciones
        }
        protected void Page_Load(object sender, EventArgs e)
        {
            //((Cliente)Master).ShowFullNav = false; // Ocultar el menú principal
            if (!IsPostBack)
            {
                if (Session["clienteLogueado"] != null)
                {
                    _cliente = (cliente)Session["clienteLogueado"];
                    CargarCompras();
                }
                else
                {
                    // Redirigir al inicio de sesión si no hay cliente logueado
                    Response.Redirect("../Admin/IniciarSesion.aspx");
                }
            }
        }

        private void CargarCompras()
        {
            try
            {
                cliente clienteLogueado = (cliente)Session["clienteLogueado"];
                if (clienteLogueado == null)
                {
                    Response.Write("<script>alert('El cliente no está logueado.');</script>");
                    return;
                }

                BindingList<ordenCompra> compras = ordenBO.listarPorIdCliente(clienteLogueado.idUsuario);

                if (compras != null && compras.Count > 0)
                {
                    rptCompras.DataSource = compras;
                    rptCompras.DataBind();
                }
                else
                {
                    rptCompras.DataSource = null;
                    rptCompras.DataBind();
                    Response.Write("<script>alert('No hay compras registradas para este cliente.');</script>");
                }
            }
            catch (Exception ex)
            {
                Response.Write("<script>alert('Error al cargar compras: " + ex.Message + "');</script>");
            }
        }
        protected void btnVer_Click(object sender, EventArgs e)
        {
            LinkButton btnVerCompra = (LinkButton)sender;
            string idOrden = btnVerCompra.CommandArgument;
            Response.Redirect($"~/Cliente/DetalleCompraCliente.aspx?idOrden={idOrden}");
        }
        protected void btnVolverPerfil_Click(object sender, EventArgs e)
        {
            Response.Redirect("~/Cliente/PerfilCliente.aspx");
        }
        protected void btnGenerarComprobante_Click(object sender, EventArgs e)
        {
            LinkButton btnGenerarComprobante = (LinkButton)sender;
            int idOrden = int.Parse(btnGenerarComprobante.CommandArgument);

            // Recuperar cliente y orden
            cliente clienteLogueado = (cliente)Session["clienteLogueado"];
            if (clienteLogueado == null)
            {
                Response.Write("<script>alert('Debe iniciar sesión para generar el comprobante.');</script>");
                return;
            }

            OrdenCompraBO ordenBO = new OrdenCompraBO();
            PrendaSeleccionadaBO prendaSeleccionadaBO = new PrendaSeleccionadaBO();

            ordenCompra orden = ordenBO.obtenerPorId(idOrden);
            var prendas = prendaSeleccionadaBO.listarPrendasIdOrden(idOrden);

            if (orden == null || prendas == null)
            {
                Response.Write("<script>alert('No se pudo recuperar la información de la orden.');</script>");
                return;
            }

            GenerarComprobantePago(orden, prendas, clienteLogueado);
        }

        private void GenerarComprobantePago(ordenCompra orden, BindingList<prendaSeleccionada> prendas, cliente cliente)
        {
            PrendaBO prendaBO = new PrendaBO();
            string titulo = "Comprobante de Pago";
            Document pdfDoc = new Document(PageSize.A4.Rotate(), 25, 25, 30, 30);
            MemoryStream ms = new MemoryStream();
            PdfWriter.GetInstance(pdfDoc, ms);

            pdfDoc.Open();

            // Crear la tabla de encabezado con 3 columnas
            PdfPTable headerTable = new PdfPTable(3);
            headerTable.WidthPercentage = 100;
            headerTable.SetWidths(new float[] { 15f, 60f, 25f });

            // Agregar el logotipo
            string logoPath = Server.MapPath("~/images/rhreportes.png"); // Ruta del logotipo en el servidor
            iTextSharp.text.Image logo = iTextSharp.text.Image.GetInstance(logoPath);
            logo.ScaleToFit(100f, 100f);
            PdfPCell logoCell = new PdfPCell(logo) { Border = Rectangle.NO_BORDER, Rowspan = 3, PaddingBottom = 10 };
            headerTable.AddCell(logoCell);

            // Información de la empresa a la derecha del logotipo
            PdfPCell empresaInfoCell = new PdfPCell(new Phrase("RH Store Streetwear S.A.C.\nAv. La Marina 1234, San Miguel, Lima, Perú\nCorreo: contactoRH@rhstore.com\nTeléfono: (01) 564-7890",
                FontFactory.GetFont(FontFactory.HELVETICA_BOLD, 10, BaseColor.BLACK)));
            empresaInfoCell.Border = Rectangle.NO_BORDER;
            empresaInfoCell.VerticalAlignment = Element.ALIGN_MIDDLE;
            empresaInfoCell.PaddingBottom = 10;
            headerTable.AddCell(empresaInfoCell);

            // Generar el código de reporte único
            string codigoReporte = "REP-" + codigoContador.ToString("D6");
            codigoContador++;

            // Recuadro de información a la derecha
            PdfPCell infoCell = new PdfPCell();
            infoCell.Border = Rectangle.BOX;
            infoCell.BackgroundColor = new BaseColor(220, 230, 241); // Color azul claro
            infoCell.Padding = 10;

            Paragraph infoParagraph = new Paragraph();
            infoParagraph.Add(new Chunk("RUC: 20604512389\n", FontFactory.GetFont(FontFactory.HELVETICA_BOLD, 12, BaseColor.BLACK)));
            infoParagraph.Add(new Chunk(titulo + "\n", FontFactory.GetFont(FontFactory.HELVETICA_BOLD, 16, BaseColor.BLACK)));
            infoParagraph.Add(new Chunk(codigoReporte, FontFactory.GetFont(FontFactory.HELVETICA_BOLD, 12, BaseColor.BLACK)));
            infoParagraph.Alignment = Element.ALIGN_CENTER;

            infoCell.AddElement(infoParagraph);
            headerTable.AddCell(infoCell);

            pdfDoc.Add(headerTable);

            // Espacio entre el encabezado y la siguiente información
            pdfDoc.Add(new Paragraph("\n"));

            // Encabezado
            PdfPTable infoTable = new PdfPTable(2);
            infoTable.WidthPercentage = 100;
            infoTable.SetWidths(new float[] { 50f, 50f });

            PdfPCell clienteInfo = new PdfPCell(new Phrase(
                $"Cliente: {cliente.nombres} {cliente.apellidos}\nDNI: {cliente.dni}\nCorreo: {cliente.correo}",
                FontFactory.GetFont(FontFactory.HELVETICA_BOLD, 10, BaseColor.BLACK)));
            clienteInfo.Border = Rectangle.NO_BORDER;
            clienteInfo.PaddingBottom = 10;

            PdfPCell ordenInfo = new PdfPCell(new Phrase(
                $"Fecha de compra: {orden.fechaProcesado:dd/MM/yyyy}\nMoneda: Soles\nComprobante: Boleta #{orden.idOrden}",
                FontFactory.GetFont(FontFactory.HELVETICA_BOLD, 10, BaseColor.BLACK)));
            ordenInfo.Border = Rectangle.NO_BORDER;
            ordenInfo.PaddingBottom = 10;

            infoTable.AddCell(clienteInfo);
            infoTable.AddCell(ordenInfo);
            pdfDoc.Add(infoTable);

            pdfDoc.Add(new Paragraph("\n"));

            // Tabla de detalles
            PdfPTable detallesTable = new PdfPTable(6);
            detallesTable.WidthPercentage = 100;
            detallesTable.SetWidths(new float[] { 10f, 35f, 10f, 15f, 10f , 20f });

            string[] encabezados = { "Item", "Prenda", "Talla", "Color", "Cantidad", "Precio Unitario" };
            foreach (string encabezado in encabezados)
            {
                PdfPCell cell = new PdfPCell(new Phrase(encabezado, FontFactory.GetFont(FontFactory.HELVETICA_BOLD, 12, BaseColor.WHITE)));
                cell.BackgroundColor = new BaseColor(0, 102, 204);
                cell.HorizontalAlignment = Element.ALIGN_CENTER;
                cell.Padding = 5;
                detallesTable.AddCell(cell);
            }
            // Cálculo de totales
            double sumaMonto = 0;
            double montoOrden = orden.subtotal;
            double montoDescuento;

            int item = 1;
            foreach (var prendaSelecc in prendas)
            {
                prenda prenda = prendaBO.obtenerPorId(prendaSelecc.idPrendaSeleccionada);

                detallesTable.AddCell(new PdfPCell(new Phrase(item.ToString(), FontFactory.GetFont(FontFactory.HELVETICA, 10))) { HorizontalAlignment = Element.ALIGN_CENTER });
                detallesTable.AddCell(new PdfPCell(new Phrase(prenda.nombre, FontFactory.GetFont(FontFactory.HELVETICA, 10))));
                detallesTable.AddCell(new PdfPCell(new Phrase(prenda.talla.ToString(), FontFactory.GetFont(FontFactory.HELVETICA, 10))) { HorizontalAlignment = Element.ALIGN_CENTER });
                detallesTable.AddCell(new PdfPCell(new Phrase(prenda.color, FontFactory.GetFont(FontFactory.HELVETICA, 10))) { HorizontalAlignment = Element.ALIGN_CENTER });
                detallesTable.AddCell(new PdfPCell(new Phrase(prendaSelecc.cantidad.ToString(), FontFactory.GetFont(FontFactory.HELVETICA, 10))) { HorizontalAlignment = Element.ALIGN_CENTER });
                detallesTable.AddCell(new PdfPCell(new Phrase($"S/ {prendaSelecc.precio:F2}", FontFactory.GetFont(FontFactory.HELVETICA, 10))) { HorizontalAlignment = Element.ALIGN_RIGHT });

                sumaMonto += prendaSelecc.precio;
                item++;
            }

            montoDescuento = sumaMonto - montoOrden;

            pdfDoc.Add(detallesTable);


            // Agregar los totales al documento
            pdfDoc.Add(new Paragraph("\n"));

            PdfPTable totalsTable = new PdfPTable(2);
            totalsTable.WidthPercentage = 40;
            totalsTable.HorizontalAlignment = Element.ALIGN_RIGHT;
            totalsTable.SetWidths(new float[] { 70f, 30f });

            // sumaMonto
            PdfPCell labelCell1 = new PdfPCell(new Phrase("Total a Pagar:", FontFactory.GetFont(FontFactory.HELVETICA_BOLD, 12)));
            labelCell1.Border = Rectangle.NO_BORDER;
            labelCell1.HorizontalAlignment = Element.ALIGN_LEFT;
            totalsTable.AddCell(labelCell1);

            PdfPCell valueCell1 = new PdfPCell(new Phrase("S/ " + sumaMonto.ToString("F2"), FontFactory.GetFont(FontFactory.HELVETICA, 12)));
            valueCell1.Border = Rectangle.NO_BORDER;
            valueCell1.HorizontalAlignment = Element.ALIGN_RIGHT;
            totalsTable.AddCell(valueCell1);

            // Monto Por Recaudar
            PdfPCell labelCell2 = new PdfPCell(new Phrase("Monto Descontado:", FontFactory.GetFont(FontFactory.HELVETICA_BOLD, 12)));
            labelCell2.Border = Rectangle.NO_BORDER;
            labelCell2.HorizontalAlignment = Element.ALIGN_LEFT;
            totalsTable.AddCell(labelCell2);

            PdfPCell valueCell2 = new PdfPCell(new Phrase("S/ " + montoDescuento.ToString("F2"), FontFactory.GetFont(FontFactory.HELVETICA, 12)));
            valueCell2.Border = Rectangle.NO_BORDER;
            valueCell2.HorizontalAlignment = Element.ALIGN_RIGHT;
            totalsTable.AddCell(valueCell2);

            // Monto Perdido
            PdfPCell labelCell3 = new PdfPCell(new Phrase("Total Neto:", FontFactory.GetFont(FontFactory.HELVETICA_BOLD, 12)));
            labelCell3.Border = Rectangle.NO_BORDER;
            labelCell3.HorizontalAlignment = Element.ALIGN_LEFT;
            totalsTable.AddCell(labelCell3);

            PdfPCell valueCell3 = new PdfPCell(new Phrase("S/ " + montoOrden.ToString("F2"), FontFactory.GetFont(FontFactory.HELVETICA, 12)));
            valueCell3.Border = Rectangle.NO_BORDER;
            valueCell3.HorizontalAlignment = Element.ALIGN_RIGHT;
            totalsTable.AddCell(valueCell3);

            pdfDoc.Add(totalsTable);


            pdfDoc.Close();

            byte[] bytes = ms.ToArray();
            ms.Close();

            Response.Clear();
            Response.ContentType = "application/pdf";
            Response.AddHeader("Content-Disposition", $"attachment; filename=Boleta_{orden.idOrden}.pdf");
            Response.BinaryWrite(bytes);
            Response.End();
        }
    }
}