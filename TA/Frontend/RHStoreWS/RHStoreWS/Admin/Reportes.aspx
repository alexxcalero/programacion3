<%@ Page Title="" Language="C#" MasterPageFile="~/Admin/Admin.Master" AutoEventWireup="true" CodeBehind="Reportes.aspx.cs" Inherits="RHStoreWS.Admin.Reportes" %>

<asp:Content ID="Content1" ContentPlaceHolderID="cphTitulo" runat="server">
	Generación de reportes
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="cphScripts" runat="server">
	<script src="../Scripts/RHStore/ModalesDeError.js"></script>
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="cphNombreUsuario" runat="server">
	<asp:Label ID="lblNombreUsuario" runat="server" Text="Nombre de usuario" />
</asp:Content>
<asp:Content ID="Content4" ContentPlaceHolderID="cphContenido" runat="server">
	<div id="reportes-container" class="container">
		<div class="content-box">
			<h3>Generación de Reporte de Órdenes de Venta</h3>
			<asp:Button ID="btnGenerarReporteOrdenes" runat="server" Text="Generar Reporte de Órdenes" OnClick="btnGenerarReporteOrdenes_Click" CssClass="btn btn-primary" />
		</div>

		<div class="content-box">
			<h3>Reporte de Órdenes por Estado</h3>
			<asp:Button ID="btnGenerarReporteOrdenesEstado" runat="server" Text="Generar Reporte" OnClick="btnGenerarReporteOrdenesEstado_Click" CssClass="btn btn-primary" />
			<asp:DropDownList ID="ddlEstadoOrdenes" runat="server" CssClass="dropdown">
				<asp:ListItem Text="Registrado" Value="Registrado"></asp:ListItem>
				<asp:ListItem Text="Procesado" Value="Procesado"></asp:ListItem>
				<asp:ListItem Text="Entregado" Value="Entregado"></asp:ListItem>
				<asp:ListItem Text="Anulado" Value="Anulado"></asp:ListItem>
			</asp:DropDownList>
		</div>

		<div class="content-box">
			<h3>Generación de Reporte de Prendas Vendidas</h3>
			<asp:Button ID="btnGenerarReportePrendas" runat="server" Text="Generar Reporte de Prendas Vendidas" OnClick="btnGenerarReportePrendas_Click" CssClass="btn btn-primary" />
		</div>

		<div class="content-box">
			<h3>Reporte de Prendas por Género</h3>
			<asp:Button ID="btnGenerarReportePrendasGenero" runat="server" Text="Generar Reporte" OnClick="btnGenerarReportePrendasGenero_Click" CssClass="btn btn-primary" />
			<asp:DropDownList ID="ddlGeneroPrendas" runat="server" CssClass="dropdown">
				<asp:ListItem Text="Hombre" Value="Hombre"></asp:ListItem>
				<asp:ListItem Text="Mujer" Value="Mujer"></asp:ListItem>
				<asp:ListItem Text="Unisex" Value="Unisex"></asp:ListItem>
			</asp:DropDownList>
		</div>

		<style>
		    .container {
		        padding: 20px;
		    }

		    .content-box {
		        background-color: #fff;
		        border-radius: 12px;
		        padding: 20px;
		        margin-bottom: 20px;
		        box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
		    }

		    /* Estilos específicos para los dropdowns dentro de Reportes.aspx */
		    #reportes-container .dropdown {
		        font-family: 'Roboto', sans-serif;
		        font-weight: 500;
		        font-size: 15px;
		        line-height: 2;
		        height: 45px;
		        border-radius: 6px;
		        width: 220px;
		        letter-spacing: 1px;
		        display: inline-flex;
		        align-items: center;
		        justify-content: center;
		        border: none;
		        background-color: #f0f0f0; /* Fondo gris claro */
		        color: #333; /* Texto en gris oscuro */
		        box-shadow: 0 8px 16px rgba(0, 0, 0, 0.1);
		        transition: all 0.2s ease;
		    }

		        #reportes-container .dropdown:hover {
		            background-color: #e0e0e0; /* Fondo gris intermedio */
		            color: #000; /* Texto en negro para contraste */
		        }

		        #reportes-container .dropdown option {
		            background-color: #ffffff; /* Fondo blanco en las opciones */
		            color: #333; /* Texto en gris oscuro */
		        }

		            #reportes-container .dropdown option:hover {
		                background-color: #d1e7ff; /* Fondo azul claro cuando se selecciona */
		                color: #102770; /* Texto azul para contraste */
		            }

		    /* Estilos específicos para los botones dentro de Reportes.aspx */
		    #reportes-container .btn {
		        background-color: #4a90e2;
		        color: white;
		        padding: 10px 20px;
		        border: none;
		        border-radius: 4px;
		        cursor: pointer;
		        font-family: Arial, sans-serif;
		        transition: background-color 0.3s ease;
		    }

		        #reportes-container .btn:hover {
		            background-color: #357abd;
		        }
		</style>
	</div>

	<!-- Modal de errores -->
	<div class="modal fade" id="errorModal" tabindex="-1" aria-labelledby="errorModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered">
			<div class="modal-content">
				<div class="modal-header bg-danger text-white">
					<h5 class="modal-title" id="errorModalLabel">
						<i class="fa-solid fa-triangle-exclamation bi bi-exclamation-triangle-fill me-2"></i>Mensaje de error
					</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close" />
				</div>
				<div class="modal-body">
					<asp:Label ID="lblMensajeError" runat="server" Text="Mensaje de ejemplo..." CssClass="fs-5 form-text text-danger fw-bold" />
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
				</div>
			</div>
		</div>
	</div>
</asp:Content>


