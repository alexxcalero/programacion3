using System;
using System.Web.UI;
using System.Collections.Generic;
using System.IO;
using System.Web;
using iTextSharp.text;
using iTextSharp.text.pdf;
using RHStoreUsuariosBO;
using RHStoreComprasBO;
using RHStorePrendasBO;
using RHStoreBaseBO.ServiciosWeb;
using System.ComponentModel;

namespace RHStoreWS.Admin
{
    public partial class Reportes : System.Web.UI.Page
    {
        private OrdenCompraBO ordenCompraBO;
        private PrendaBO prendaBO;
        private static int codigoContador = 1;
        private string nombreUsuario;
        private string dniUsuario;
        private string rolUsuario;

        public Reportes()
        {
            ordenCompraBO = new OrdenCompraBO();
            prendaBO = new PrendaBO();
        }

        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                CargarDatosUsuario();
            }
        }

        private void CargarDatosUsuario()
        {
            if (Session["administradorLogueado"] != null)
            {
                administrador _administrador = (administrador)Session["administradorLogueado"];
                lblNombreUsuario.Text = _administrador.nombres + " " + _administrador.apellidos;
                nombreUsuario = _administrador.nombres + " " + _administrador.apellidos;
                dniUsuario = _administrador.dni;
                rolUsuario = "Administrador";
            }
            else if (Session["trabajadorLogueado"] != null)
            {
                trabajador _trabajador = (trabajador)Session["trabajadorLogueado"];
                lblNombreUsuario.Text = _trabajador.nombres + " " + _trabajador.apellidos;
                nombreUsuario = _trabajador.nombres + " " + _trabajador.apellidos;
                dniUsuario = _trabajador.dni;
                rolUsuario = "Trabajador";
            }
        }

        protected void btnGenerarReporteOrdenes_Click(object sender, EventArgs e)
        {
            CargarDatosUsuario(); // Asegurarse de cargar los datos del usuario antes de generar el PDF
            BindingList<ordenCompra> listaOrdenes = ordenCompraBO.listarOrdenCompraTodos();
            if(listaOrdenes == null)
            {
				lblMensajeError.Text = "No hay ordenes para mostrar";
				string script = "showModalFormError();";
				ScriptManager.RegisterStartupScript(this, GetType(), "ShowModalFormError", script, true);
				return;
			}
            GenerarPDFReporteOrdenes("Reporte de Órdenes de Venta", listaOrdenes);
        }

        protected void btnGenerarReportePrendas_Click(object sender, EventArgs e)
        {
            CargarDatosUsuario(); // Asegurarse de cargar los datos del usuario antes de generar el PDF
            BindingList<prenda> listaPrendas = prendaBO.listarTodos();
			if (listaPrendas == null)
			{
				lblMensajeError.Text = "No hay prendas para mostrar";
				string script = "showModalFormError();";
				ScriptManager.RegisterStartupScript(this, GetType(), "ShowModalFormError", script, true);
				return;
			}
			GenerarPDFReportePrendas("Reporte de Prendas Vendidas", listaPrendas);
        }
        protected void btnGenerarReporteOrdenesEstado_Click(object sender, EventArgs e)
        {
            CargarDatosUsuario();
            string estadoSeleccionado = ddlEstadoOrdenes.SelectedValue;
			BindingList<ordenCompra> listaOrdenes = ordenCompraBO.listarOrdenCompraPorEstado(estadoSeleccionado);
			if (listaOrdenes == null)
			{
				lblMensajeError.Text = "No hay ordenes con estado " + estadoSeleccionado + " para mostrar";
				string script = "showModalFormError();";
				ScriptManager.RegisterStartupScript(this, GetType(), "ShowModalFormError", script, true);
				return;
			}
			GenerarPDFReporteOrdenes($"Reporte de Órdenes - Estado: {estadoSeleccionado}", listaOrdenes, estadoSeleccionado);
        }

        protected void btnGenerarReportePrendasGenero_Click(object sender, EventArgs e)
        {
            CargarDatosUsuario();
            string generoSeleccionado = ddlGeneroPrendas.SelectedValue;
			BindingList<prenda> listaPrendas = prendaBO.listarPrendasPorGenero(generoSeleccionado);
			if (listaPrendas == null)
			{
				lblMensajeError.Text = "No hay prendas de la sección " + generoSeleccionado + " para mostrar";
				string script = "showModalFormError();";
				ScriptManager.RegisterStartupScript(this, GetType(), "ShowModalFormError", script, true);
				return;
			}
			GenerarPDFReportePrendas($"Reporte de Prendas - Género: {generoSeleccionado}", listaPrendas, generoSeleccionado);
        }
        private void GenerarPDFReporteOrdenes(string titulo, IList<ordenCompra> listaOrdenes, string estadoFiltro = null)
        {
            Document pdfDoc = new Document(PageSize.A4.Rotate(), 25, 25, 30, 30);
            MemoryStream ms = new MemoryStream();
            PdfWriter writer = PdfWriter.GetInstance(pdfDoc, ms);

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

            // Información adicional de cliente, fecha, estado y moneda
            PdfPTable infoTable = new PdfPTable(2);
            infoTable.WidthPercentage = 100;
            infoTable.SetWidths(new float[] { 50f, 50f });

            string estadoInfo = estadoFiltro != null ? $"Estado de órdenes: {estadoFiltro}" : "Estado de órdenes:";

            PdfPCell leftInfoCell = new PdfPCell(new Phrase($"Cliente: {nombreUsuario}\nDNI: {dniUsuario}\nRol: {rolUsuario}",
                FontFactory.GetFont(FontFactory.HELVETICA_BOLD, 10, BaseColor.BLACK)));
            leftInfoCell.Border = Rectangle.NO_BORDER;
            leftInfoCell.PaddingBottom = 10;

            PdfPCell rightInfoCell = new PdfPCell(new Phrase($"Fecha de emisión: {DateTime.Now:dd/MM/yyyy}\n{estadoInfo}\nMoneda: SOLES",
            FontFactory.GetFont(FontFactory.HELVETICA_BOLD, 10, BaseColor.BLACK)));
            rightInfoCell.Border = Rectangle.NO_BORDER;
            rightInfoCell.PaddingBottom = 10;

            infoTable.AddCell(leftInfoCell);
            infoTable.AddCell(rightInfoCell);
            pdfDoc.Add(infoTable);

            pdfDoc.Add(new Paragraph("\n"));

            // Tabla de Órdenes
            PdfPTable table = new PdfPTable(9);
            table.WidthPercentage = 100;
            table.SetWidths(new float[] { 2f, 2f, 2f, 2f, 2f, 1.5f, 1.5f, 3f, 1.5f });

            // Encabezados de tabla
            string[] encabezados = { "Cliente ID", "Fecha Registro", "Fecha Procesado", "Fecha Entregado", "Fecha Anulado", "Estado", "DNI", "Correo", "Subtotal" };
            foreach (string encabezado in encabezados)
            {
                PdfPCell cell = new PdfPCell(new Phrase(encabezado, FontFactory.GetFont(FontFactory.HELVETICA_BOLD, 12, BaseColor.WHITE)))
                {
                    BackgroundColor = new BaseColor(0, 102, 204),
                    HorizontalAlignment = Element.ALIGN_CENTER,
                    Padding = 5
                };
                table.AddCell(cell);
            }

            // Cálculo de totales
            double montoRecaudado = 0;
            double montoPorRecaudar = 0;
            double montoPerdido = 0;

            // Datos de las Órdenes
            foreach (ordenCompra orden in listaOrdenes)
            {
                table.AddCell(new PdfPCell(new Phrase(orden.cliente.idUsuario.ToString(), FontFactory.GetFont(FontFactory.HELVETICA, 10))));
                table.AddCell(new PdfPCell(new Phrase(FormatearFecha(orden.fechaRegistro), FontFactory.GetFont(FontFactory.HELVETICA, 10))));
                table.AddCell(new PdfPCell(new Phrase(FormatearFecha(orden.fechaProcesado), FontFactory.GetFont(FontFactory.HELVETICA, 10))));
                table.AddCell(new PdfPCell(new Phrase(FormatearFecha(orden.fechaEntregado), FontFactory.GetFont(FontFactory.HELVETICA, 10))));
                table.AddCell(new PdfPCell(new Phrase(FormatearFecha(orden.fechaAnulado), FontFactory.GetFont(FontFactory.HELVETICA, 10))));
                table.AddCell(new PdfPCell(new Phrase(orden.estado.ToString(), FontFactory.GetFont(FontFactory.HELVETICA, 10))));
                table.AddCell(new PdfPCell(new Phrase(orden.dni, FontFactory.GetFont(FontFactory.HELVETICA, 10))));
                table.AddCell(new PdfPCell(new Phrase(orden.correo, FontFactory.GetFont(FontFactory.HELVETICA, 10))));
                table.AddCell(new PdfPCell(new Phrase("S/ " + orden.subtotal.ToString("F2"), FontFactory.GetFont(FontFactory.HELVETICA, 10))) { HorizontalAlignment = Element.ALIGN_RIGHT });
                
                if (orden.estado.ToString() == "Procesado" || orden.estado.ToString() == "Entregado")
                {
                    montoRecaudado += orden.subtotal;
                }
                else if (orden.estado.ToString() == "Registrado")
                {
                    montoPorRecaudar += orden.subtotal;
                }
                else if (orden.estado.ToString() == "Anulado")
                {
                    montoPerdido += orden.subtotal;
                }

            }

            pdfDoc.Add(table);
                    

            // Agregar los totales al documento
            pdfDoc.Add(new Paragraph("\n"));

            PdfPTable totalsTable = new PdfPTable(2);
            totalsTable.WidthPercentage = 40;
            totalsTable.HorizontalAlignment = Element.ALIGN_RIGHT;
            totalsTable.SetWidths(new float[] { 70f, 30f });

            // Monto Recaudado
            PdfPCell labelCell1 = new PdfPCell(new Phrase("Monto Recaudado:", FontFactory.GetFont(FontFactory.HELVETICA_BOLD, 12)));
            labelCell1.Border = Rectangle.NO_BORDER;
            labelCell1.HorizontalAlignment = Element.ALIGN_LEFT;
            totalsTable.AddCell(labelCell1);

            PdfPCell valueCell1 = new PdfPCell(new Phrase("S/ " + montoRecaudado.ToString("F2"), FontFactory.GetFont(FontFactory.HELVETICA, 12)));
            valueCell1.Border = Rectangle.NO_BORDER;
            valueCell1.HorizontalAlignment = Element.ALIGN_RIGHT;
            totalsTable.AddCell(valueCell1);

            // Monto Por Recaudar
            PdfPCell labelCell2 = new PdfPCell(new Phrase("Monto Por Recaudar:", FontFactory.GetFont(FontFactory.HELVETICA_BOLD, 12)));
            labelCell2.Border = Rectangle.NO_BORDER;
            labelCell2.HorizontalAlignment = Element.ALIGN_LEFT;
            totalsTable.AddCell(labelCell2);

            PdfPCell valueCell2 = new PdfPCell(new Phrase("S/ " + montoPorRecaudar.ToString("F2"), FontFactory.GetFont(FontFactory.HELVETICA, 12)));
            valueCell2.Border = Rectangle.NO_BORDER;
            valueCell2.HorizontalAlignment = Element.ALIGN_RIGHT;
            totalsTable.AddCell(valueCell2);

            // Monto Perdido
            PdfPCell labelCell3 = new PdfPCell(new Phrase("Monto Perdido:", FontFactory.GetFont(FontFactory.HELVETICA_BOLD, 12)));
            labelCell3.Border = Rectangle.NO_BORDER;
            labelCell3.HorizontalAlignment = Element.ALIGN_LEFT;
            totalsTable.AddCell(labelCell3);

            PdfPCell valueCell3 = new PdfPCell(new Phrase("S/ " + montoPerdido.ToString("F2"), FontFactory.GetFont(FontFactory.HELVETICA, 12)));
            valueCell3.Border = Rectangle.NO_BORDER;
            valueCell3.HorizontalAlignment = Element.ALIGN_RIGHT;
            totalsTable.AddCell(valueCell3);

            pdfDoc.Add(totalsTable);

            pdfDoc.Close();

            byte[] bytes = ms.ToArray();
            ms.Close();
            Response.Clear();
            Response.ContentType = "application/pdf";
            Response.AddHeader("Content-Disposition", "attachment; filename=" + titulo + ".pdf");
            Response.BinaryWrite(bytes);
            Response.End();
        }

        private string FormatearFecha(DateTime fecha)
        {
            return fecha == DateTime.MinValue ? "---" : fecha.ToShortDateString();
        }

        private void GenerarPDFReportePrendas(string titulo, IList<prenda> listaPrendas, string generoFiltro = null)
        {
            Document pdfDoc = new Document(PageSize.A4.Rotate(), 25, 25, 30, 30);
            MemoryStream ms = new MemoryStream();
            PdfWriter writer = PdfWriter.GetInstance(pdfDoc, ms);

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

            // Información adicional de cliente, fecha, estado y moneda
            PdfPTable infoTable = new PdfPTable(2);
            infoTable.WidthPercentage = 100;
            infoTable.SetWidths(new float[] { 50f, 50f });

            string generoInfo = generoFiltro != null ? $"Género de prendas: {generoFiltro}" : "Género de prendas:";

            PdfPCell leftInfoCell = new PdfPCell(new Phrase($"Cliente: {nombreUsuario}\nDNI: {dniUsuario}\nRol: {rolUsuario}",
                FontFactory.GetFont(FontFactory.HELVETICA_BOLD, 10, BaseColor.BLACK)));
            leftInfoCell.Border = Rectangle.NO_BORDER;
            leftInfoCell.PaddingBottom = 10;

            PdfPCell rightInfoCell = new PdfPCell(new Phrase($"Fecha de emisión: {DateTime.Now:dd/MM/yyyy}\n{generoInfo}\nMoneda: SOLES",
            FontFactory.GetFont(FontFactory.HELVETICA_BOLD, 10, BaseColor.BLACK)));
            rightInfoCell.Border = Rectangle.NO_BORDER;
            rightInfoCell.PaddingBottom = 10;

            infoTable.AddCell(leftInfoCell);
            infoTable.AddCell(rightInfoCell);
            pdfDoc.Add(infoTable);

            pdfDoc.Add(new Paragraph("\n"));

            // Tabla de Prendas
            PdfPTable table = new PdfPTable(7);
            table.WidthPercentage = 100;
            table.SetWidths(new float[] { 2f, 3f, 1f, 1f, 1.5f, 1f, 1f });

            // Encabezados de tabla
            string[] encabezados = { "Nombre", "Descripción", "Talla", "Género", "Color", "Stock", "Cant. Vendida" };
            foreach (string encabezado in encabezados)
            {
                PdfPCell cell = new PdfPCell(new Phrase(encabezado, FontFactory.GetFont(FontFactory.HELVETICA_BOLD, 12, BaseColor.WHITE)))
                {
                    BackgroundColor = new BaseColor(0, 102, 204),
                    HorizontalAlignment = Element.ALIGN_CENTER,
                    Padding = 5
                };
                table.AddCell(cell);
            }

            // Datos de las Prendas
            int totalPrendasVendidas = 0;
            int totalPrendasEnStock = 0;
            foreach (var prenda in listaPrendas)
            {
                table.AddCell(new PdfPCell(new Phrase(prenda.nombre, FontFactory.GetFont(FontFactory.HELVETICA, 10))));
                table.AddCell(new PdfPCell(new Phrase(prenda.descripcion, FontFactory.GetFont(FontFactory.HELVETICA, 10))));
                table.AddCell(new PdfPCell(new Phrase(prenda.talla.ToString(), FontFactory.GetFont(FontFactory.HELVETICA, 10))) { HorizontalAlignment = Element.ALIGN_CENTER });
                table.AddCell(new PdfPCell(new Phrase(prenda.genero.ToString(), FontFactory.GetFont(FontFactory.HELVETICA, 10))) { HorizontalAlignment = Element.ALIGN_CENTER });
                table.AddCell(new PdfPCell(new Phrase(prenda.color, FontFactory.GetFont(FontFactory.HELVETICA, 10))) { HorizontalAlignment = Element.ALIGN_CENTER });
                table.AddCell(new PdfPCell(new Phrase(prenda.stock.ToString(), FontFactory.GetFont(FontFactory.HELVETICA, 10))) { HorizontalAlignment = Element.ALIGN_CENTER });
                table.AddCell(new PdfPCell(new Phrase(prenda.cantVendida.ToString(), FontFactory.GetFont(FontFactory.HELVETICA, 10))) { HorizontalAlignment = Element.ALIGN_CENTER });
                totalPrendasVendidas += prenda.cantVendida;
                totalPrendasEnStock += prenda.stock;
            }

            pdfDoc.Add(table);

            // Agregar los totales al documento
            pdfDoc.Add(new Paragraph("\n"));

            PdfPTable totalsTable = new PdfPTable(2);
            totalsTable.WidthPercentage = 40;
            totalsTable.HorizontalAlignment = Element.ALIGN_RIGHT;
            totalsTable.SetWidths(new float[] { 70f, 30f });

            // Total prendas vendidas
            PdfPCell labelCell1 = new PdfPCell(new Phrase("Total prendas vendidas:", FontFactory.GetFont(FontFactory.HELVETICA_BOLD, 12)));
            labelCell1.Border = Rectangle.NO_BORDER;
            labelCell1.HorizontalAlignment = Element.ALIGN_LEFT;
            totalsTable.AddCell(labelCell1);

            PdfPCell valueCell1 = new PdfPCell(new Phrase(totalPrendasVendidas.ToString(), FontFactory.GetFont(FontFactory.HELVETICA, 12)));
            valueCell1.Border = Rectangle.NO_BORDER;
            valueCell1.HorizontalAlignment = Element.ALIGN_RIGHT;
            totalsTable.AddCell(valueCell1);

            // Total prendas en stock
            PdfPCell labelCell2 = new PdfPCell(new Phrase("Total prendas en stock:", FontFactory.GetFont(FontFactory.HELVETICA_BOLD, 12)));
            labelCell2.Border = Rectangle.NO_BORDER;
            labelCell2.HorizontalAlignment = Element.ALIGN_LEFT;
            totalsTable.AddCell(labelCell2);

            PdfPCell valueCell2 = new PdfPCell(new Phrase(totalPrendasEnStock.ToString(), FontFactory.GetFont(FontFactory.HELVETICA, 12)));
            valueCell2.Border = Rectangle.NO_BORDER;
            valueCell2.HorizontalAlignment = Element.ALIGN_RIGHT;
            totalsTable.AddCell(valueCell2);

            pdfDoc.Add(totalsTable);

            pdfDoc.Close();

            byte[] bytes = ms.ToArray();
            ms.Close();
            Response.Clear();
            Response.ContentType = "application/pdf";
            Response.AddHeader("Content-Disposition", "attachment; filename=" + titulo + ".pdf");
            Response.BinaryWrite(bytes);
            Response.End();
        }
    }
}
