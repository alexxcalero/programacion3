<%@ Page Title="" Language="C#" MasterPageFile="~/MasterPage.Master" AutoEventWireup="true" CodeBehind="Plantilla1.aspx.cs" Inherits="SoftPlantillaWS.Plantilla1" %>

<asp:Content ID="Content1" ContentPlaceHolderID="cphTitulo" runat="server">
	Plantilla 1
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="cphScripts" runat="server">
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="cphContenido" runat="server">
	<h1>Pagina de la plantilla 1</h1>
	<div class="row">
		<asp:GridView ID="gvPersonas" runat="server" AllowPaging="true" PageSize="5" OnRowDataBound="gvPersonas_RowDataBound" AutoGenerateColumns="false" CssClass="table table-hover table-responsive table-striped" ShowHeaderWhenEmpty="true">
			<Columns>
				<asp:BoundField HeaderText="ID" />
				<asp:BoundField HeaderText="Nombre completo" />
			</Columns>
		</asp:GridView>
	</div>
</asp:Content>
