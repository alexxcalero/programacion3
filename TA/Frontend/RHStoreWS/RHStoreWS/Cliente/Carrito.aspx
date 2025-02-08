<%@ Page Title="" Language="C#" MasterPageFile="~/Cliente/Cliente.Master" AutoEventWireup="true" CodeBehind="Carrito.aspx.cs" Inherits="RHStoreWS.Cliente.Carrito" %>

<asp:Content ID="headContent" ContentPlaceHolderID="head" runat="server">
    <link href="/Content/bootstrap.min.css" rel="stylesheet" />
    <link href="/Content/fontawesome.min.css" rel="stylesheet" />
    <link href="/Content/all.min.css" rel="stylesheet" />
    <link href="https://fonts.googleapis.com/css2?family=Bebas+Neue&family=Roboto:wght@400;700&display=swap" rel="stylesheet">

    <style>
        body {
            font-family: 'Roboto', sans-serif;
            /*background: linear-gradient(to bottom, #1e1e1e, #3a3a3a);*/
            background: linear-gradient(to bottom, #000000, #1e1e1e, #3a3a3a);
            color: white;
            margin: 0;
            padding: 0;
        }

        h2 {
            font-family: 'Bebas Neue', sans-serif;
            font-size: 36px;
            letter-spacing: 2px;
            text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.5);
            color: #ffffff;
        }

        h3 {
            font-family: 'Bebas Neue', sans-serif;
            letter-spacing: 1.5px;
            text-transform: uppercase;
            color: #f5f5f5;
        }

        /* Ajustar color del texto en el panel blanco */
        .carrito-resumen {
            font-family: 'Bebas Neue', sans-serif;
            font-size: 28px;
            letter-spacing: 1.5px;
            color: #f5f5f5;
        }

        .carrito-resumen h3 {
            color: #000000; /* Asegura que los títulos sean negros */
        }

        .carrito-resumen p {
            color: #444444; /* Color gris para los textos secundarios */
        }


        .prenda-item {
            background: rgba(0, 0, 0, 0.8);
            border: 1px solid rgba(255, 255, 255, 0.1);
            box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.2);
            border-radius: 8px;
            margin-bottom: 20px;
        }

        .btn {
            border-radius: 25px;
            text-transform: uppercase;
        }

        .btn-primary {
            background-color: #1db954;
            color: #ffffff;
            border: none;
        }
        .btn-primary:hover {
            background-color: #17a443;
        }

        .btn-secondary {
            background-color: #6c757d;
            border-color: #6c757d;
        }

        .btn-info {
            background-color: #007bff;
            border-color: #007bff;
        }

        .buscarCupon .form-control {
            border-radius: 25px;
            border: 1px solid #444444;
        }

        .detalleCupon {
            background: rgba(255, 255, 255, 0.1);
            padding: 10px;
            border-radius: 8px;
        }

        .aplicarCupon .btn {
            width: 50%;
        }

        .text-danger {
            color: #ff6b6b !important;
        }

        .alert {
            border-radius: 8px;
        }

        .carrito-container {
            margin: 0 auto; /* Centrar horizontalmente */
            margin-top: 20px; /* Espacio entre el navbar y el contenido */
            max-width: 1200px; /* Ajusta el ancho máximo si es necesario */
            padding: 0 15px; /* Opcional: Espaciado interno */
        }

    </style>
</asp:Content>

<asp:Content ID="Content2" ContentPlaceHolderID="contenidoPagina" runat="server">
    <!--Como se eliminó el form, se podría colocar un section in between --> 
    <div class="carrito-container mt-5">
    <!-- Título del Carrito -->
        <div class="text-center mb-4">
            <h2><i class="fas fa-shopping-cart"></i> Carrito de Compras</h2>
        </div>

        <!-- Resumen del Carrito -->
        <div class="carrito-resumen text-center p-3 rounded bg-light mb-4">
            <h3><i class="fas fa-receipt"></i> Resumen del Carrito</h3>
            <p><strong>Cantidad Total:</strong> <asp:Label ID="lblCantidadTotal" runat="server" /></p>
            <p><strong>Precio Total:</strong> S/. <asp:Label ID="lblPrecioTotal" runat="server" /></p>
        </div>


        <!-- Mensaje de Carrito Vacío -->
        <div class="text-center mb-4">
            <asp:Label ID="lblMensaje" runat="server" ForeColor="Red" Visible="false" CssClass="alert alert-danger text-center fs-4" />
        </div>

        <!-- Lista de Prendas -->
        <div class="prendas-seleccionadas">
            <asp:Repeater ID="rptPrendasSeleccionadas" runat="server">
                <ItemTemplate>
                    <div class="prenda-item d-flex align-items-start p-3 rounded">
                        <asp:Image ID="imgPrenda" runat="server" Width="120" Height="120" ImageUrl='<%# Eval("ImagenUrl") %>' CssClass="rounded me-3" />
                        <div class="prenda-info flex-grow-1">
                            <h4 class="text-uppercase text-white">
                                <asp:Label ID="lblNombrePrenda" runat="server" Text='<%# Eval("Nombre") %>' />
                            </h4>
                            <p class="text-white">Precio: S/ <asp:Label ID="lblPrecioPrenda" runat="server" Text='<%# Eval("Precio") %>' /></p>
                            <div class="d-flex align-items-center">
                                <asp:Button ID="btnRestar" runat="server" Text="-" CssClass="btn btn-outline-primary me-2" CommandArgument='<%# Eval("idPrendaSeleccionada") %>' OnClick="btnRestar_Click" />
                                <asp:Label ID="lblCantidad" runat="server" Text='<%# Eval("Cantidad") %>' CssClass="mx-2 text-white" />
                                <asp:Button ID="btnSumar" runat="server" Text="+" CssClass="btn btn-outline-primary me-2" CommandArgument='<%# Eval("idPrendaSeleccionada") %>' OnClick="btnSumar_Click" />
                                <asp:Button ID="btnEliminar" runat="server" Text="🗑️" CssClass="btn btn-outline-danger" CommandArgument='<%# Eval("idPrendaSeleccionada") %>' OnClick="btnEliminar_Click" />
                            </div>
                        </div>
                    </div>
                </ItemTemplate>
            </asp:Repeater>
        </div>

        <!-- Botones de Pago -->
        <div class="pagar text-center mt-3">
            <asp:Button ID="btnPagar" runat="server" Text="Pagar" CssClass="btn btn-primary me-3" OnClick="btnPagar_Click" />
            <asp:Button ID="btnVolver" runat="server" Text="Menú Principal" CssClass="btn btn-secondary" OnClick="btnVolver_Click" />
        </div>

        <!-- Buscar Cupón -->
        <div class="buscarCupon text-center mt-4">
            <asp:TextBox ID="txtBuscar" runat="server" CssClass="form-control d-inline-block w-50" placeholder="Insertar Cupón" />
            <asp:Button ID="lbBuscar" runat="server" CssClass="btn btn-info" Text="Buscar" OnClick="lbBuscarCupon_Click" />
        </div>

        <!-- Detalle del Cupón -->
        <div class="detalleCupon text-center mt-2">
            <asp:Label ID="lblCuponInfo" runat="server" Visible="false" CssClass="alert alert-success d-block" />
            <asp:Label ID="Label1" runat="server" Visible="false" CssClass="alert alert-danger d-block" />
        </div>

        <!-- Aplicar/Revertir Cupón -->
        <div class="aplicarCupon text-center mt-3">
            <asp:Button ID="btnAplicarCupon" runat="server" Text="Aplicar Cupón" CssClass="btn btn-success" Visible="false" OnClick="btnAplicarCupon_Click" />
            <asp:Button ID="btnRevertirCupon" runat="server" Text="Revertir Cupón" CssClass="btn btn-warning" Visible="false" OnClick="btnRevertirCupon_Click" />
        </div>

         <!-- ingresa tu correo -->
    <div class="datosComprobante text-center mt-4">
        <div class="form-group">
            <asp:TextBox ID="correoComprobante" runat="server" Visible="false" CssClass="form-control d-inline-block w-50" placeholder="Ingresa el correo del comprobante" />
        </div>
        <div class="form-group">
            <asp:TextBox ID="dniComprobante" runat="server" Visible="false" CssClass="form-control d-inline-block w-50" placeholder="Ingresa el dni del comprobante" />
        </div>
        <div class="form-group">
            <asp:Button ID="datos_boton" runat="server" Visible="false" CssClass="btn btn-info" Text="Pagar con Paypal" OnClick="btnDatos" />
        </div>
    </div>

                <!-- Mensaje de Carrito Vacío -->
        <div class="text-center mb-4">
            <asp:Label ID="invalidFields" runat="server" ForeColor="Red" Visible="false" CssClass="alert alert-danger text-center fs-4" />
        </div>

    </div>
</asp:Content>
