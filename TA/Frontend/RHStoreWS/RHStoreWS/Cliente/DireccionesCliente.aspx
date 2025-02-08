<%@ Page Title="Mis Direcciones" Language="C#" MasterPageFile="~/Cliente/Cliente.Master" AutoEventWireup="true" CodeBehind="DireccionesCliente.aspx.cs" Inherits="RHStoreWS.Cliente.DireccionesCliente" %>

<asp:Content ID="Content1" ContentPlaceHolderID="contenidoPagina" runat="server">
	<script src="../Scripts/RHStore/ModalesDeError.js"></script>

	<style>
		/* General container styles */
		.container {
			display: flex; /* Flexbox para centrar */
			flex-direction: column;
			justify-content: center; /* Centra verticalmente */
			align-items: center; /* Centra horizontalmente */
			gap: 20px; /* Espaciado entre los elementos */
			padding: 20px; /* Espaciado interno */
			margin: 0 auto; /* Centrado horizontal */
			max-width: 1200px; /* Ancho máximo en pantallas grandes */
			width: 90%; /* Ocupa el 90% del ancho en pantallas pequeñas */
			min-height: 100vh; /* Asegura que ocupe toda la altura de la pantalla */
			box-sizing: border-box; /* Incluye padding en el cálculo del tamaño */
			background-color: #f8f9fa; /* Opcional: fondo claro para visualización */
			margin-top: 30px;
		}

		/* Header */
		.header-container {
			display: flex;
			justify-content: space-between;
			align-items: center;
			padding: 10px 20px;
			background-color: #f8f9fa;
			border-bottom: 1px solid #ddd;
			margin-left: auto;
		}

		/* Repeater section */
		.direccion-list-container {
			display: flex;
			flex-direction: column;
			gap: 15px;
		}

		.direccion-card {
			display: flex;
			justify-content: space-between;
			align-items: center;
			padding: 15px;
			border: 1px solid #ced4da;
			border-radius: 0.25rem;
			background-color: #ffffff;
		}

		.acciones {
			display: flex;
			gap: 10px;
		}

		/* Form section */
		.form-direccion {
			display: flex;
			flex-direction: column;
			gap: 15px;
			padding: 15px;
			border: 1px solid #ced4da;
			border-radius: 0.25rem;
			background-color: #f8f9fa;
		}

		.row {
			display: flex;
			gap: 15px;
		}

		.col-md-6 {
			flex: 1;
		}

		/* Button */
		.btn-primary, .btn-secondary {
			padding: 10px 15px;
			font-size: 1rem;
			cursor: pointer;
		}

		.btn-primary {
			background-color: #000000;
			border: none;
			color: white;
		}

		.btn-secondary {
			background-color: #000000;
			border: none;
			color: white;
		}

		.btn-primary:hover {
			background-color: #495057;
		}

		.btn-secondary:hover {
			background-color: #495057;
		}

		.btn-primary:active,
		input[type="submit"].btn-primary:active,
		button.btn-primary:active {
			background-color: #495057; /* Color deseado */
			color: white; /* Color del texto */
		}

		.btn-action {
			background-color: #000000;
			color: white;
			border: none;
			padding: 10px 20px;
			font-size: 0.9rem;
			cursor: pointer;
			transition: background-color 0.3s ease;
			text-decoration: none;
			display: inline-block;
		}

			.btn-action:hover {
				background-color: #495057;
			}

			.btn-action:active,
			input[type="submit"].btn-action:active,
			button.btn-action:active {
				background-color: #495057; /* Color deseado */
				color: white; /* Color del texto */
			}
	</style>

	<div class="container">
		<!-- Header Section -->
		<div class="header-container">
			<h2>Mis Direcciones</h2>
		</div>

		<!-- Lista de direcciones -->
		<div class="direccion-list-container">
			<asp:Repeater ID="rptDirecciones" runat="server">
				<ItemTemplate>
					<div class="direccion-card">
						<div>
							<strong><%# Eval("referencia") %></strong><br />
							<%# Eval("direccion1") %><br />
							<%# Eval("provincia") %>, <%# Eval("departamento") %>, <%# Eval("distrito") %>
						</div>
						<div class="acciones">
							<asp:LinkButton ID="btnEditarDireccion" runat="server" CssClass="btn btn-secondary btn-sm" Text="Editar" CommandArgument='<%# Eval("idDireccion") %>' OnClick="btnEditarDireccion_Click"></asp:LinkButton>
							<asp:LinkButton ID="btnEliminarDireccion" runat="server" CssClass="btn btn-danger btn-sm" Text="Eliminar" CommandArgument='<%# Eval("idDireccion") %>' OnClick="btnEliminarDireccion_Click"></asp:LinkButton>
						</div>
					</div>
				</ItemTemplate>
			</asp:Repeater>
		</div>

		<!-- Botones -->
		<div style="display: flex; gap: 10px; justify-content: flex-end; margin-top: 20px;">
			<asp:Button ID="btnAgregarDireccion" runat="server" CssClass="btn btn-primary agregar-direccion-btn" Text="Agregar dirección" OnClick="btnAgregarDireccion_Click" />
			<asp:LinkButton ID="btnVolverPerfil" runat="server" CssClass="btn btn-action" OnClick="btnVolverPerfil_Click">
                Volver a Perfil
			</asp:LinkButton>
		</div>

		<!-- Formulario para agregar/editar dirección -->
		<div class="form-direccion" runat="server" id="formDireccion" visible="false">
			<h4>Agregar/Editar Dirección</h4>
			<div class="row">
				<div class="col-md-6">
					<label>Dirección *</label>
					<asp:TextBox ID="txtDireccion" runat="server" CssClass="form-control"></asp:TextBox>
				</div>
				<div class="col-md-6">
					<label>Referencia</label>
					<asp:TextBox ID="txtReferencia" runat="server" CssClass="form-control"></asp:TextBox>
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
					<label>Provincia *</label>
					<asp:TextBox ID="txtProvincia" runat="server" CssClass="form-control"></asp:TextBox>
				</div>
				<div class="col-md-6">
					<label>Departamento *</label>
					<asp:TextBox ID="txtDepartamento" runat="server" CssClass="form-control"></asp:TextBox>
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
					<label>Distrito *</label>
					<asp:TextBox ID="txtDistrito" runat="server" CssClass="form-control"></asp:TextBox>
				</div>
				<div class="col-md-6">
					<label>Codigo Postal *</label>
					<asp:TextBox ID="txtCodPostal" runat="server" CssClass="form-control"></asp:TextBox>
				</div>
				<div class="col-md-6">
					<asp:Button ID="btnGuardarDireccion" runat="server" CssClass="btn btn-primary" Text="Guardar" OnClick="btnGuardarDireccion_Click" />
					<asp:Button ID="btnCancelarDireccion" runat="server" CssClass="btn btn-secondary" Text="Cancelar" OnClick="btnCancelarDireccion_Click" />
				</div>
			</div>
		</div>
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
