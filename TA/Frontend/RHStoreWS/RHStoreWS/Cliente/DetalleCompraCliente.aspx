<%@ Page Title="Mi Compra" Language="C#" MasterPageFile="~/Cliente/Cliente.Master" AutoEventWireup="true" CodeBehind="DetalleCompraCliente.aspx.cs" Inherits="RHStoreWS.Cliente.DetalleCompraCliente" %>

<asp:Content ID="Content1" ContentPlaceHolderID="contenidoPagina" runat="server">
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

    <div class="container">
        <!-- Header Section -->
        <div class="header-container">
            <h2>Detalle de la compra</h2>
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
                            <p class="text-white">
                                Precio: S/
                                <asp:Label ID="lblPrecioPrenda" runat="server" Text='<%# Eval("Precio") %>' />
                            </p>
                            <div class="d-flex align-items-center">

                                <asp:Label ID="lblCantidad" runat="server" Text='<%# Eval("Cantidad") %>' CssClass="mx-2 text-white" />

                            </div>
                        </div>
                    </div>
                </ItemTemplate>
            </asp:Repeater>
        </div>

        <!-- Botones -->
        <div style="display: flex; gap: 10px; justify-content: flex-end; margin-top: 20px;">

            <asp:LinkButton ID="btnVolverPerfil" runat="server" CssClass="btn btn-action" OnClick="btnVolverPerfil_Click">
                Volver a Perfil
            </asp:LinkButton>
        </div>

    </div>
</asp:Content>
