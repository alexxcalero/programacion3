<%@ Page Title="Inicio" Language="C#" MasterPageFile="~/Cliente/Cliente.master" AutoEventWireup="true" CodeBehind="ClienteHome.aspx.cs" Inherits="RHStoreWS.Cliente.ClienteHome" %>

<asp:Content ID="Content2" ContentPlaceHolderID="contenidoPagina" runat="server">
    <style>
        header {
            background-image: url('../Images/fondo.jpg');
            background-size: cover;
            background-position: center;
            height: 400px;
            display: flex;
            flex-direction: column;
            align-items: flex-start;
            justify-content: flex-start;
            color: white;
            position: relative;
        }
    </style>

    <asp:Panel ID="filtersContainer" runat="server">
                        <!-- View Toggle Buttons -->
            <div class="view-toggle">
                <asp:Button ID="btnGridView" runat="server" Text="Vista Cuadrícula" OnClick="btnGridView_Click" CssClass="btn-toggle" />
                <asp:Button ID="btnDetailView" runat="server" Text="Vista Detalle" OnClick="btnDetailView_Click" CssClass="btn-toggle" />
                <asp:Button ID="btnMosaicView" runat="server" Text="Vista Mosaico" OnClick="btnMosaicView_Click" CssClass="btn-toggle" />
            </div>
        <div class="container">
            <div class="filters">
                <!-- Filtro de sexo -->
                <div class="filter-box">
                <h5>SEXO</h5>
                    <asp:Panel ID="PanelSexo" runat="server"></asp:Panel>
                </div>
                <!-- Filtro de precio -->
                <div class="filter-box">
                    <asp:Label ID="lblError" runat="server" CssClass="text-danger" Visible="false"></asp:Label>
                    <h5>PRECIO</h5>
                    <label>Desde:</label>
                    <asp:TextBox ID="minPriceTextBox" runat="server" placeholder="Precio mínimo" style="width: 100%; margin-bottom: 10px;" />
                    <label>Hasta:</label>
                    <asp:TextBox ID="maxPriceTextBox" runat="server" placeholder="Precio máximo" style="width: 100%;" /><br /><br />
                </div>
                <!-- Sección Talla -->
                <div class="filter-box">
                    <h5>TALLA</h5>
                    <asp:Panel ID="PanelTallas" runat="server"></asp:Panel>
                </div>
                    <!-- Sección Color -->
                <div class="filter-box">
                    <h5>COLOR</h5>
                    <asp:Panel ID="PanelColores" runat="server"></asp:Panel>
                </div>
                <!-- Botón Filtrar -->
                <div class="filter-box">
                    <asp:LinkButton ID="btnFiltrar" runat="server" Text="Filtrar" OnClick="btnFiltrar_Click" CssClass="btn-filtrar" />
                </div>
            </div>

             <div class="products" id="productContainer">
                <asp:Repeater ID="RepeaterPrendas" runat="server">
                    <ItemTemplate>
                        <div class="product">
                             <!--<div class="add-to-cart">
                                <i class="fas fa-plus"></i> <!-- Icono de agregar al carrito
                            </div>-->

                            <!-- Enlace que redirige a la página de detalles de la prenda -->
                            <a href='<%# "ProductoDetalle.aspx?idPrenda=" + Eval("idPrenda") %>'>
                                <img src='<%# ObtenerImagen(Convert.ToInt32(Eval("idPrenda"))) %>' alt='<%# Eval("nombre") %>' />
                            </a>
                            <p><%# Eval("nombre") %></p>
                            <p>
                                <%# Convert.ToDecimal(Eval("precioDescontado")) > 0 ?
                                    "S/ " + String.Format("{0:F2}", Eval("precioDescontado")) + 
                                    " <s>S/ " + String.Format("{0:F2}", Eval("precioOriginal")) + "</s>" :
                                    "S/ " + String.Format("{0:F2}", Eval("precioOriginal")) %>
                            </p>
                            <p>Colores disponibles: <%# Eval("color") %></p>
                        </div>
                    </ItemTemplate>
                </asp:Repeater>
            </div>


        </div>
        </asp:Panel>


    <!-- Aquí puedes añadir contenido específico para la página de inicio si es necesario -->
</asp:Content>
