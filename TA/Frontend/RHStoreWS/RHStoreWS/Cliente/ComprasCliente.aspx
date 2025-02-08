<%@ Page Title="Mis compras" Language="C#" MasterPageFile="~/Cliente/Cliente.Master" AutoEventWireup="true" CodeBehind="ComprasCliente.aspx.cs" Inherits="RHStoreWS.Cliente.ComprasCliente" %>

<asp:Content ID="Content1" ContentPlaceHolderID="contenidoPagina" runat="server">

    <div class="container">
        <!-- Header Section -->
        <div class="header-container">
            <h2>Mis Compras</h2>
        </div>

        <!-- xd -->
        <table class="table">
            <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Fecha</th>
                    <th scope="col">Estado</th>
                    <th scope="col">Subtotal</th>
                    <th scope="col"></th>
                </tr>
            </thead>
            <tbody>
                <asp:Repeater ID="rptCompras" runat="server">
                    <ItemTemplate>
                        <tr>
                            <th><%# Eval("idOrden") %></th>
                            <td><%# ((DateTime)Eval("fechaRegistro")).ToString("MM/dd/yyyy") %></td>
                            <td><%# Eval("estado") %></td>
                            <td><%# Eval("subtotal") %></td>
                            <td>
                                <asp:LinkButton
                                    ID="btnVerCompra"
                                    runat="server"
                                    CssClass="btn btn-secondary"
                                    CommandArgument=<%# Eval("idOrden") %>
                                    OnClick="btnVer_Click">
                                Ver Detalle
                                </asp:LinkButton>
                                <asp:LinkButton
                                    ID="btnGenerarComprobante"
                                    runat="server"
                                    CssClass="btn btn-primary"
                                    CommandArgument='<%# Eval("idOrden") %>'
                                    OnClick="btnGenerarComprobante_Click"
                                    Visible='<%# Eval("estado").ToString() == "Procesado" || Eval("estado").ToString() == "Entregado" %>'>
                                    <i class="fa fa-file-alt"></i>
                                </asp:LinkButton>
                            </td>
                        </tr>
                    </ItemTemplate>
                </asp:Repeater>
            </tbody>
        </table>

        <!-- Botones -->
        <asp:LinkButton ID="LinkButton1" runat="server" CssClass="btn btn-primary" OnClick="btnVolverPerfil_Click">
            Volver a Perfil
        </asp:LinkButton>


    </div>
</asp:Content>
