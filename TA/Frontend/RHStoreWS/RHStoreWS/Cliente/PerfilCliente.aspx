<%@ Page Title="Mi Perfil" Language="C#" MasterPageFile="~/Cliente/Cliente.Master" AutoEventWireup="true" CodeBehind="PerfilCliente.aspx.cs" Inherits="RHStoreWS.Cliente.PerfilCliente" %>

<asp:Content ID="Content1" ContentPlaceHolderID="contenidoPagina" runat="server">
    <style>
        /* Estructura de cuadrícula para la página */
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
}




        /* Header */
        .header-container {
            display: flex;
            justify-content: flex-end;
            align-items: center;
            gap: 10px;
            margin-left: auto;
        }

        /* Botones de acción */
        .btn-action {
            background-color: #343a40;
            color: white;
            border: none;
            padding: 10px 20px;
            font-size: 0.9rem;
            display: flex;
            align-items: center;
            cursor: pointer;
            transition: background-color 0.3s ease;
            text-decoration: none;
        }

        .btn-action:hover {
            background-color: #495057;
        }

        .btn-action i {
            font-size: 1rem;
            margin-right: 5px;
        }

        /* Sección de contenido */
        .card {
            display: flex;
            justify-content: space-between;
            align-items: center;
            /* padding: 15px; */
            border: 1px solid #ced4da;
            /* border-radius: 0.25rem; */
            background-color: #ffffff;
            width: 100%;
            max-width: 900px;
            margin: 0 auto;
            padding: 20px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            border-radius: 5px;
        }


        /* Footer del card */
        .card-footer {
            text-align: right;
        }

        /* Espaciado adicional */
        .mt-4 {
            margin-top: 1.5rem;
        }
    </style>

    <div class="container mt-4">
        <!-- Encabezado -->
        <div class="header-container">
            <asp:LinkButton ID="btnIrDirecciones" runat="server" CssClass="btn btn-action" OnClick="btnIrDirecciones_Click">
                <i class="fas fa-map-marker-alt"></i> Mis Direcciones
            </asp:LinkButton>
            <asp:LinkButton ID="btnCompras" runat="server" CssClass="btn btn-action" OnClick="btnCompras_Click">
                <i class="fas fa-map-marker-alt"></i> Mis Compras
            </asp:LinkButton>
            <asp:LinkButton ID="btnCerrarSesion" runat="server" CssClass="btn btn-action" OnClick="btnCerrarSesion_Click">
                <i class="fas fa-sign-out-alt"></i> Cerrar sesión
            </asp:LinkButton>
        </div>

        <!-- Datos personales -->
        <div class="card">
            <div class="card-header">
                <h4>Mis Datos Personales 
                    <asp:LinkButton ID="btnEditar" runat="server" CssClass="edit-text float-end" Text="✏️" OnClick="btnEditar_Click"></asp:LinkButton>
                </h4>
            </div>
            <div class="card-body">
                <div class="row">
                        <div class="col-md-6 form-group">
                            <label for="txtNombre">Nombre *</label>
                            <asp:TextBox ID="txtNombre" runat="server" CssClass="form-control" Enabled="false" />
                            <asp:Label ID="lblErrorNombre" runat="server" Text="Debe ingresar su nombre" CssClass="text-danger" Visible="false" />
                        </div>
                        <div class="col-md-6 form-group">
                            <label for="txtApellido">Apellido *</label>
                            <asp:TextBox ID="txtApellido" runat="server" CssClass="form-control" Enabled="false" />
                            <asp:Label ID="lblErrorApellido" runat="server" Text="Debe ingresar su apellido" CssClass="text-danger" Visible="false" />
                        </div>
                    </div>
                    <div class="row mt-3">
                        <div class="col-md-6 form-group">
                            <label for="txtDNI">DNI *</label>
                            <asp:TextBox ID="txtDNI" runat="server" CssClass="form-control" Enabled="false" />
                            <asp:Label ID="lblErrorDNI" runat="server" Text="Debe ingresar un DNI válido" CssClass="text-danger" Visible="false" />
                        </div>
                        <div class="col-md-6 form-group">
                            <label for="ddlPromociones">¿Recibir promociones?</label>
                            <asp:DropDownList ID="ddlPromociones" runat="server" CssClass="form-control" Enabled="false">
                                <asp:ListItem Text="Sí" Value="1"></asp:ListItem>
                                <asp:ListItem Text="No" Value="0"></asp:ListItem>
                            </asp:DropDownList>
                            <asp:Label ID="lblErrorPromociones" runat="server" Text="Debe seleccionar una opción" CssClass="text-danger" Visible="false" />
                        </div>
                    </div>
                    <div class="row mt-3">
                        <div class="col-md-6 form-group">
                            <label for="txtTelefono">Teléfono *</label>
                            <asp:TextBox ID="txtTelefono" runat="server" CssClass="form-control" Enabled="false" />
                            <asp:Label ID="lblErrorTelefono" runat="server" Text="Debe ingresar un teléfono válido" CssClass="text-danger" Visible="false" />
                        </div>
                        <div class="col-md-6 form-group">
                            <label for="txtEmail">Email *</label>
                            <asp:TextBox ID="txtEmail" runat="server" CssClass="form-control" Enabled="false" />
                            <asp:Label ID="lblErrorEmail" runat="server" Text="Debe ingresar un email válido" CssClass="text-danger" Visible="false" />
                        </div>
                    </div>

            </div>
            <div class="card-footer">
                <asp:LinkButton ID="btnGuardar" runat="server" CssClass="btn btn-primary" Text="Guardar" OnClick="btnGuardar_Click" Visible="false"></asp:LinkButton>
            </div>
        </div>
    </div>
</asp:Content>
