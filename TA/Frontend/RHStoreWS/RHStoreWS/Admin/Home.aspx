<%@ Page Title="" Language="C#" MasterPageFile="~/Admin/Admin.Master" AutoEventWireup="true" CodeBehind="Home.aspx.cs" Inherits="RHStoreWS.Admin.Home" %>

<asp:Content ID="Content1" ContentPlaceHolderID="cphTitulo" runat="server">
    Inicio
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="cphScripts" runat="server">
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="cphNombreUsuario" runat="server">
    <asp:Label ID="lblNombreUsuario" runat="server" Text="Nombre de usuario" />
</asp:Content>
<asp:Content ID="Content4" ContentPlaceHolderID="cphContenido" runat="server">
    <div class="container">
        <style>
            /* Estilos para la sección de bienvenida */
            .welcome-section {
                background-color: #fff6f8;
                border-radius: 12px;
                padding: 20px;
                margin-bottom: 20px;
                box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
                display: flex;
                align-items: center;
                justify-content: center;
                font-family: Arial, sans-serif;
            }

            .welcome-text h1 {
                font-size: 28px;
                color: #333;
                font-weight: bold;
                margin: 0;
            }

            .welcome-text span {
                color: #4a90e2;
            }

            .welcome-image {
                width: 80px;
                height: auto;
                margin-left: 20px;
            }

            /* Contenedor principal para organizar las secciones en columnas */
            .main-content {
                display: flex;
                gap: 20px;
                margin-top: 20px;
            }

            /* Contenedor de los recuadros de información (izquierda) */
            .info-sections {
                display: flex;
                flex-direction: column;
                gap: 20px;
                flex: 1;
            }

            /* Contenedor del gráfico (derecha) */
            .chart-section {
                flex: 2;
            }

            /* Estilos para cada recuadro */
            .content-box {
                background-color: #fff;
                border-radius: 8px;
                padding: 20px;
                box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
                font-family: Arial, sans-serif;
                position: relative; /* Make sure the dropdown is positioned relative to this container */
            }

            .content-box h3 {
                font-size: 22px;
                color: #4a90e2;
                margin-bottom: 15px;
            }

            /* Lista de estadísticas dentro de los recuadros */
            .stats-list {
                list-style-type: none;
                padding-left: 0;
                margin: 0;
            }

            .stat-item {
                font-size: 16px;
                color: #333;
            }

            /* Ajusta el espaciado de los elementos en "Stock de Productos" */
            .stat-item {
                margin: 0;
                padding: 0;
                line-height: 1.6; /* Ajusta el interlineado para reducir el espaciado */
                color: #333;
                font-size: 16px;
            }

            /* Position the dropdown in the top-right corner */
            .year-selector-container {
                position: absolute;
                top: 10px;
                right: 10px;
            }

            /* Estilo profesional para el selector de año */
            .year-selector-container select {
                padding: 8px 12px;
                font-size: 16px;
                color: #4a90e2;
                border: 1px solid #4a90e2;
                border-radius: 4px;
                background-color: #fff;
                box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
                appearance: none;
                transition: all 0.3s ease;
                cursor: pointer;
            }

            .year-selector-container select:hover {
                background-color: #f0f8ff;
                border-color: #357abd;
            }

            .year-selector-container select:focus {
                outline: none;
                box-shadow: 0 0 0 2px rgba(74, 144, 226, 0.5);
            }
        </style>
        <style>
    /* Estilo personalizado ajustado para los botones "Ver más" */
    .btn-ver-mas {
        position: relative;
        padding: 0;
        background: transparent;
        font-size: 14px;
        font-weight: 700;
        border: none;
        text-decoration: none;
        display: inline-block;
        margin-top: 10px;
        width: 100px;
        height: 35px;
        text-align: center;
        overflow: hidden;
    }

    .btn-ver-mas::after {
        content: "Ver más";
        position: absolute;
        top: 50%;
        left: 50%;
        transform: translate(-50%, -50%);
        width: 100%;
        height: 100%;
        background-color: #ffffff;
        font-size: 14px;
        font-weight: 700;
        line-height: 35px;
        color: #181818;
        border: 1.5px solid #181818;
        border-radius: 20px;
        transition: all .2s ease-in-out;
        z-index: 2;
        text-decoration: none;
    }

    /* Estado de redirección: muestra "Redirigiendo" sin afectar el tamaño ni la posición del botón */
    .btn-ver-mas:focus::after {
        content: "Redirigiendo";
        background-color: #f0f0f0;
        color: #888;
        border-color: #888;
    }

    /* Ajuste de clic con ligera expansión */
    .btn-ver-mas:active::after {
        width: 105%;
        height: 37px;
    }

    /* Animación del icono en el estado focus */
    .btn-ver-mas .icon {
        position: absolute;
        top: 0;
        left: 50%;
        transform: translate(-50%, 0);
        width: 15px; /* Tamaño reducido del icono */
        height: auto;
        z-index: -3;
        transition: all .3s ease-in-out;
    }

    .btn-ver-mas:focus .icon {
        top: 200%;
        width: 60px; /* Tamaño expandido del icono en focus */
        height: auto;
    }

    .fil0 { fill: #333333; }
    .fil2 { fill: #222222; }
    .fil1 { fill: #181818; }
    .fil3 { fill: #181818; }
</style>



        <!-- Mensaje de bienvenida -->
        <div class="welcome-section">
            <div class="welcome-text">
                <h1>
                    Bienvenido al sistema de administración de RH Store
                </h1>
            </div>
        </div>

        <!-- Contenedor principal con dos columnas -->
        <div class="main-content">
            <!-- Columna izquierda: Recuadros de información -->
            <div class="info-sections">
                <!-- Primer recuadro: Accesos Recientes -->
                <div class="content-box">
                    <h3>Accesos Rápidos</h3>
                    <ul style="list-style: none; padding: 0;">
                        <li><a href="CrudPrendas.aspx">Registrar Prendas</a></li>
                        <li><a href="CrudPromociones.aspx">Registrar Promociones</a></li>
                        <li><a href="CrudCupones.aspx">Registrar Cupones</a></li>
                        <li><a href="CrudTrabajadores.aspx">Registrar Trabajadores</a></li>
                        <li><a href="GestionarClientes.aspx">Visualizar Clientes</a></li>
                        <li><a href="GestionarOrdenes.aspx">Visualizar Ordenes de Venta</a></li>
                    </ul>
                </div>

                <!-- Segundo recuadro: Estadísticas de Accesos Recientes -->
                <div class="content-box">
                    <h3>Valores Actuales</h3>
                    <ul class="stats-list">
                        <li>
                            <asp:Label ID="lblPrendas" runat="server" Text="Total Prendas: " CssClass="stat-item" />
                        </li>
                        <li>
                            <asp:Label ID="lblPromociones" runat="server" Text="Promociones Activas: " CssClass="stat-item" />
                        </li>
                        <li>
                            <asp:Label ID="lblCupones" runat="server" Text="Cupones Activos: " CssClass="stat-item" />
                        </li>
                        <li>
                            <asp:Label ID="lblClientes" runat="server" Text="Clientes Activos: " CssClass="stat-item" />
                        </li>
                    </ul>
                </div>

               <!-- Stock de Productos -->
                <div class="content-box">
                    <h3>Stock de Productos</h3>
                    <asp:Repeater ID="rptStockProductos" runat="server">
                        <ItemTemplate>
                            <p class="stat-item"><%# Eval("nombre") %>: <%# Eval("stock") %></p>
                        </ItemTemplate>
                    </asp:Repeater>
                    <a href="GestionarPrendas.aspx" class="btn-ver-mas">
                        <div class="icon">
                            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 19.18 20.17" xml:space="preserve"><g id="Layer_x0020_1"><g id="_2478177736128"><path class="fil0" d="M9.59 20.17c-2.99,-1.91 -4.97,-2.73 -7.95,-3.34 -0.44,-3.75 -0.99,-6.77 -1.64,-8.96 4.01,0.22 7.06,0.94 9.59,3.14 0,4.34 0,4.81 0,9.16z"></path><path class="fil1" d="M9.59 20.17c2.99,-1.91 4.97,-2.73 7.95,-3.34 0.44,-3.75 0.99,-6.77 1.64,-8.96 -4.01,0.22 -7.06,0.94 -9.59,3.14 0,4.34 0,4.81 0,9.16z"></path><path class="fil2" d="M9.66 10.55c0,0 2.92,-3.03 8.68,-3.05 0,0 0.01,-0.55 0.29,-1 0,0 -6.73,0.43 -8.97,4.05zm-0.13 0c0,0 -2.92,-3.03 -8.68,-3.05 0,0 -0.01,-0.55 -0.29,-1 0,0 6.73,0.43 8.97,4.05z"></path><path class="fil0" d="M9.33 0.92l0.88 0.15 0.42 -0.8 0.12 0.89 0.88 0.15 -0.81 0.4 0.12 0.89 -0.62 -0.65 -0.81 0.4 0.42 -0.8 -0.62 -0.64zm2.06 3.05c0.1,0 0.18,0.08 0.18,0.18 0,0.1 -0.08,0.18 -0.18,0.18 -0.1,0 -0.18,-0.08 -0.18,-0.18 0,-0.1 0.08,-0.18 0.18,-0.18zm-7.43 -0.4c0.12,0 0.22,0.05 0.3,0.12 0.08,0.08 0.12,0.18 0.12,0.3 0,0.12 -0.05,0.22 -0.12,0.3 -0.08,0.08 -0.18,0.12 -0.3,0.12 -0.12,0 -0.22,-0.05 -0.3,-0.12 -0.08,-0.08 -0.12,-0.18 -0.12,-0.3 0,-0.12 0.05,-0.22 0.12,-0.3 0.08,-0.08 0.18,-0.12 0.3,-0.12zm0.25 0.18c-0.06,-0.06 -0.15,-0.1 -0.25,-0.1 -0.1,0 -0.18,0.04 -0.25,0.1 -0.06,0.06 -0.1,0.15 -0.1,0.25 0,0.1 0.04,0.18 0.1,0.25 0.06,0.06 0.15,0.1 0.25,0.1 0.1,0 0.18,-0.04 0.25,-0.1 0.06,-0.06 0.1,-0.15 0.1,-0.25 0,-0.1 -0.04,-0.18 -0.1,-0.25zm3.36 -0.16c0.23,0 0.43,0.09 0.58,0.24 0.15,0.15 0.24,0.35 0.24,0.58 0,0.23 -0.09,0.43 -0.24,0.58 -0.15,0.15 -0.35,0.24 -0.58,0.24 -0.23,0 -0.43,-0.09 -0.58,-0.24 -0.15,-0.15 -0.24,-0.35 -0.24,-0.58 0,-0.23 0.09,-0.43 0.24,-0.58 0.15,-0.15 0.35,-0.24 0.58,-0.24zm0.47 0.35c-0.12,-0.12 -0.29,-0.2 -0.47,-0.2 -0.18,0 -0.35,0.07 -0.47,0.2 -0.12,0.12 -0.2,0.29 -0.2,0.47 0,0.18 0.07,0.35 0.2,0.47 0.12,0.12 0.29,0.2 0.47,0.2 0.18,0 0.35,-0.07 0.47,-0.2 0.12,-0.12 0.2,-0.29 0.2,-0.47 0,-0.18 -0.07,-0.35 -0.2,-0.47z"></path><path class="fil3" d="M6.75 1.49c0.18,0 0.33,0.15 0.33,0.33 0,0.18 -0.15,0.33 -0.33,0.33 -0.18,0 -0.33,-0.15 -0.33,-0.33 0,-0.18 0.15,-0.33 0.33,-0.33zm2.37 4.79l0.45 0.52 0.64 -0.27 -0.37 0.58 0.45 0.52 -0.67 -0.16 -0.36 0.59 -0.05 -0.68 -0.67 -0.16 0.64 -0.26 -0.05 -0.68zm6.92 -5.91l0.9 0.38 0.64 -0.75 -0.09 0.98 0.9 0.37 -0.95 0.23 -0.09 0.98 -0.5 -0.84 -0.95 0.23 0.65 -0.74 -0.5 -0.83z"></path></g></g></svg>
                        </div>
                    </a>
                </div>

                <!-- Prendas Vendidas -->
                <div class="content-box">
                    <h3>Prendas Vendidas</h3>
                    <asp:Repeater ID="rptPrendasVendidas" runat="server">
                        <ItemTemplate>
                            <p class="stat-item"><%# Eval("nombre") %>: <%# Eval("cantVendida") %></p>
                        </ItemTemplate>
                    </asp:Repeater>
                    <a href="GestionarPrendas.aspx" class="btn-ver-mas">
                        <div class="icon">
                            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 19.18 20.17" xml:space="preserve"><g id="Layer_x0020_1"><g id="_2478177736128"><path class="fil0" d="M9.59 20.17c-2.99,-1.91 -4.97,-2.73 -7.95,-3.34 -0.44,-3.75 -0.99,-6.77 -1.64,-8.96 4.01,0.22 7.06,0.94 9.59,3.14 0,4.34 0,4.81 0,9.16z"></path><path class="fil1" d="M9.59 20.17c2.99,-1.91 4.97,-2.73 7.95,-3.34 0.44,-3.75 0.99,-6.77 1.64,-8.96 -4.01,0.22 -7.06,0.94 -9.59,3.14 0,4.34 0,4.81 0,9.16z"></path><path class="fil2" d="M9.66 10.55c0,0 2.92,-3.03 8.68,-3.05 0,0 0.01,-0.55 0.29,-1 0,0 -6.73,0.43 -8.97,4.05zm-0.13 0c0,0 -2.92,-3.03 -8.68,-3.05 0,0 -0.01,-0.55 -0.29,-1 0,0 6.73,0.43 8.97,4.05z"></path><path class="fil0" d="M9.33 0.92l0.88 0.15 0.42 -0.8 0.12 0.89 0.88 0.15 -0.81 0.4 0.12 0.89 -0.62 -0.65 -0.81 0.4 0.42 -0.8 -0.62 -0.64zm2.06 3.05c0.1,0 0.18,0.08 0.18,0.18 0,0.1 -0.08,0.18 -0.18,0.18 -0.1,0 -0.18,-0.08 -0.18,-0.18 0,-0.1 0.08,-0.18 0.18,-0.18zm-7.43 -0.4c0.12,0 0.22,0.05 0.3,0.12 0.08,0.08 0.12,0.18 0.12,0.3 0,0.12 -0.05,0.22 -0.12,0.3 -0.08,0.08 -0.18,0.12 -0.3,0.12 -0.12,0 -0.22,-0.05 -0.3,-0.12 -0.08,-0.08 -0.12,-0.18 -0.12,-0.3 0,-0.12 0.05,-0.22 0.12,-0.3 0.08,-0.08 0.18,-0.12 0.3,-0.12zm0.25 0.18c-0.06,-0.06 -0.15,-0.1 -0.25,-0.1 -0.1,0 -0.18,0.04 -0.25,0.1 -0.06,0.06 -0.1,0.15 -0.1,0.25 0,0.1 0.04,0.18 0.1,0.25 0.06,0.06 0.15,0.1 0.25,0.1 0.1,0 0.18,-0.04 0.25,-0.1 0.06,-0.06 0.1,-0.15 0.1,-0.25 0,-0.1 -0.04,-0.18 -0.1,-0.25zm3.36 -0.16c0.23,0 0.43,0.09 0.58,0.24 0.15,0.15 0.24,0.35 0.24,0.58 0,0.23 -0.09,0.43 -0.24,0.58 -0.15,0.15 -0.35,0.24 -0.58,0.24 -0.23,0 -0.43,-0.09 -0.58,-0.24 -0.15,-0.15 -0.24,-0.35 -0.24,-0.58 0,-0.23 0.09,-0.43 0.24,-0.58 0.15,-0.15 0.35,-0.24 0.58,-0.24zm0.47 0.35c-0.12,-0.12 -0.29,-0.2 -0.47,-0.2 -0.18,0 -0.35,0.07 -0.47,0.2 -0.12,0.12 -0.2,0.29 -0.2,0.47 0,0.18 0.07,0.35 0.2,0.47 0.12,0.12 0.29,0.2 0.47,0.2 0.18,0 0.35,-0.07 0.47,-0.2 0.12,-0.12 0.2,-0.29 0.2,-0.47 0,-0.18 -0.07,-0.35 -0.2,-0.47z"></path><path class="fil3" d="M6.75 1.49c0.18,0 0.33,0.15 0.33,0.33 0,0.18 -0.15,0.33 -0.33,0.33 -0.18,0 -0.33,-0.15 -0.33,-0.33 0,-0.18 0.15,-0.33 0.33,-0.33zm2.37 4.79l0.45 0.52 0.64 -0.27 -0.37 0.58 0.45 0.52 -0.67 -0.16 -0.36 0.59 -0.05 -0.68 -0.67 -0.16 0.64 -0.26 -0.05 -0.68zm6.92 -5.91l0.9 0.38 0.64 -0.75 -0.09 0.98 0.9 0.37 -0.95 0.23 -0.09 0.98 -0.5 -0.84 -0.95 0.23 0.65 -0.74 -0.5 -0.83z"></path></g></g></svg>
                        </div>
                    </a>
                </div>

                <!-- Promociones Activas -->
                <div class="content-box">
                    <h3>Promociones Activas</h3>
                    <asp:Repeater ID="rptPromocionesActivas" runat="server">
                        <ItemTemplate>
                            <p class="stat-item"><%# Eval("nombre") %>: <%# Eval("tipo") == "MontoFijo" ? "S/ " + Eval("valorDescuento") : Eval("valorDescuento") + " %" %></p>
                        </ItemTemplate>
                    </asp:Repeater>                    
                    <a href="GestionarPromociones.aspx" class="btn-ver-mas">
                        <div class="icon">
                            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 19.18 20.17" xml:space="preserve"><g id="Layer_x0020_1"><g id="_2478177736128"><path class="fil0" d="M9.59 20.17c-2.99,-1.91 -4.97,-2.73 -7.95,-3.34 -0.44,-3.75 -0.99,-6.77 -1.64,-8.96 4.01,0.22 7.06,0.94 9.59,3.14 0,4.34 0,4.81 0,9.16z"></path><path class="fil1" d="M9.59 20.17c2.99,-1.91 4.97,-2.73 7.95,-3.34 0.44,-3.75 0.99,-6.77 1.64,-8.96 -4.01,0.22 -7.06,0.94 -9.59,3.14 0,4.34 0,4.81 0,9.16z"></path><path class="fil2" d="M9.66 10.55c0,0 2.92,-3.03 8.68,-3.05 0,0 0.01,-0.55 0.29,-1 0,0 -6.73,0.43 -8.97,4.05zm-0.13 0c0,0 -2.92,-3.03 -8.68,-3.05 0,0 -0.01,-0.55 -0.29,-1 0,0 6.73,0.43 8.97,4.05z"></path><path class="fil0" d="M9.33 0.92l0.88 0.15 0.42 -0.8 0.12 0.89 0.88 0.15 -0.81 0.4 0.12 0.89 -0.62 -0.65 -0.81 0.4 0.42 -0.8 -0.62 -0.64zm2.06 3.05c0.1,0 0.18,0.08 0.18,0.18 0,0.1 -0.08,0.18 -0.18,0.18 -0.1,0 -0.18,-0.08 -0.18,-0.18 0,-0.1 0.08,-0.18 0.18,-0.18zm-7.43 -0.4c0.12,0 0.22,0.05 0.3,0.12 0.08,0.08 0.12,0.18 0.12,0.3 0,0.12 -0.05,0.22 -0.12,0.3 -0.08,0.08 -0.18,0.12 -0.3,0.12 -0.12,0 -0.22,-0.05 -0.3,-0.12 -0.08,-0.08 -0.12,-0.18 -0.12,-0.3 0,-0.12 0.05,-0.22 0.12,-0.3 0.08,-0.08 0.18,-0.12 0.3,-0.12zm0.25 0.18c-0.06,-0.06 -0.15,-0.1 -0.25,-0.1 -0.1,0 -0.18,0.04 -0.25,0.1 -0.06,0.06 -0.1,0.15 -0.1,0.25 0,0.1 0.04,0.18 0.1,0.25 0.06,0.06 0.15,0.1 0.25,0.1 0.1,0 0.18,-0.04 0.25,-0.1 0.06,-0.06 0.1,-0.15 0.1,-0.25 0,-0.1 -0.04,-0.18 -0.1,-0.25zm3.36 -0.16c0.23,0 0.43,0.09 0.58,0.24 0.15,0.15 0.24,0.35 0.24,0.58 0,0.23 -0.09,0.43 -0.24,0.58 -0.15,0.15 -0.35,0.24 -0.58,0.24 -0.23,0 -0.43,-0.09 -0.58,-0.24 -0.15,-0.15 -0.24,-0.35 -0.24,-0.58 0,-0.23 0.09,-0.43 0.24,-0.58 0.15,-0.15 0.35,-0.24 0.58,-0.24zm0.47 0.35c-0.12,-0.12 -0.29,-0.2 -0.47,-0.2 -0.18,0 -0.35,0.07 -0.47,0.2 -0.12,0.12 -0.2,0.29 -0.2,0.47 0,0.18 0.07,0.35 0.2,0.47 0.12,0.12 0.29,0.2 0.47,0.2 0.18,0 0.35,-0.07 0.47,-0.2 0.12,-0.12 0.2,-0.29 0.2,-0.47 0,-0.18 -0.07,-0.35 -0.2,-0.47z"></path><path class="fil3" d="M6.75 1.49c0.18,0 0.33,0.15 0.33,0.33 0,0.18 -0.15,0.33 -0.33,0.33 -0.18,0 -0.33,-0.15 -0.33,-0.33 0,-0.18 0.15,-0.33 0.33,-0.33zm2.37 4.79l0.45 0.52 0.64 -0.27 -0.37 0.58 0.45 0.52 -0.67 -0.16 -0.36 0.59 -0.05 -0.68 -0.67 -0.16 0.64 -0.26 -0.05 -0.68zm6.92 -5.91l0.9 0.38 0.64 -0.75 -0.09 0.98 0.9 0.37 -0.95 0.23 -0.09 0.98 -0.5 -0.84 -0.95 0.23 0.65 -0.74 -0.5 -0.83z"></path></g></g></svg>
                        </div>
                    </a>
                </div>

                <!-- Cupones Activos -->
                <div class="content-box">
                    <h3>Cupones Activos</h3>
                    <asp:Repeater ID="rptCuponesActivos" runat="server">
                        <ItemTemplate>
                            <p class="stat-item"><%# Eval("codigo") %></p>
                        </ItemTemplate>
                    </asp:Repeater>
                    <a href="GestionarCupones.aspx" class="btn-ver-mas">
                        <div class="icon">
                            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 19.18 20.17" xml:space="preserve"><g id="Layer_x0020_1"><g id="_2478177736128"><path class="fil0" d="M9.59 20.17c-2.99,-1.91 -4.97,-2.73 -7.95,-3.34 -0.44,-3.75 -0.99,-6.77 -1.64,-8.96 4.01,0.22 7.06,0.94 9.59,3.14 0,4.34 0,4.81 0,9.16z"></path><path class="fil1" d="M9.59 20.17c2.99,-1.91 4.97,-2.73 7.95,-3.34 0.44,-3.75 0.99,-6.77 1.64,-8.96 -4.01,0.22 -7.06,0.94 -9.59,3.14 0,4.34 0,4.81 0,9.16z"></path><path class="fil2" d="M9.66 10.55c0,0 2.92,-3.03 8.68,-3.05 0,0 0.01,-0.55 0.29,-1 0,0 -6.73,0.43 -8.97,4.05zm-0.13 0c0,0 -2.92,-3.03 -8.68,-3.05 0,0 -0.01,-0.55 -0.29,-1 0,0 6.73,0.43 8.97,4.05z"></path><path class="fil0" d="M9.33 0.92l0.88 0.15 0.42 -0.8 0.12 0.89 0.88 0.15 -0.81 0.4 0.12 0.89 -0.62 -0.65 -0.81 0.4 0.42 -0.8 -0.62 -0.64zm2.06 3.05c0.1,0 0.18,0.08 0.18,0.18 0,0.1 -0.08,0.18 -0.18,0.18 -0.1,0 -0.18,-0.08 -0.18,-0.18 0,-0.1 0.08,-0.18 0.18,-0.18zm-7.43 -0.4c0.12,0 0.22,0.05 0.3,0.12 0.08,0.08 0.12,0.18 0.12,0.3 0,0.12 -0.05,0.22 -0.12,0.3 -0.08,0.08 -0.18,0.12 -0.3,0.12 -0.12,0 -0.22,-0.05 -0.3,-0.12 -0.08,-0.08 -0.12,-0.18 -0.12,-0.3 0,-0.12 0.05,-0.22 0.12,-0.3 0.08,-0.08 0.18,-0.12 0.3,-0.12zm0.25 0.18c-0.06,-0.06 -0.15,-0.1 -0.25,-0.1 -0.1,0 -0.18,0.04 -0.25,0.1 -0.06,0.06 -0.1,0.15 -0.1,0.25 0,0.1 0.04,0.18 0.1,0.25 0.06,0.06 0.15,0.1 0.25,0.1 0.1,0 0.18,-0.04 0.25,-0.1 0.06,-0.06 0.1,-0.15 0.1,-0.25 0,-0.1 -0.04,-0.18 -0.1,-0.25zm3.36 -0.16c0.23,0 0.43,0.09 0.58,0.24 0.15,0.15 0.24,0.35 0.24,0.58 0,0.23 -0.09,0.43 -0.24,0.58 -0.15,0.15 -0.35,0.24 -0.58,0.24 -0.23,0 -0.43,-0.09 -0.58,-0.24 -0.15,-0.15 -0.24,-0.35 -0.24,-0.58 0,-0.23 0.09,-0.43 0.24,-0.58 0.15,-0.15 0.35,-0.24 0.58,-0.24zm0.47 0.35c-0.12,-0.12 -0.29,-0.2 -0.47,-0.2 -0.18,0 -0.35,0.07 -0.47,0.2 -0.12,0.12 -0.2,0.29 -0.2,0.47 0,0.18 0.07,0.35 0.2,0.47 0.12,0.12 0.29,0.2 0.47,0.2 0.18,0 0.35,-0.07 0.47,-0.2 0.12,-0.12 0.2,-0.29 0.2,-0.47 0,-0.18 -0.07,-0.35 -0.2,-0.47z"></path><path class="fil3" d="M6.75 1.49c0.18,0 0.33,0.15 0.33,0.33 0,0.18 -0.15,0.33 -0.33,0.33 -0.18,0 -0.33,-0.15 -0.33,-0.33 0,-0.18 0.15,-0.33 0.33,-0.33zm2.37 4.79l0.45 0.52 0.64 -0.27 -0.37 0.58 0.45 0.52 -0.67 -0.16 -0.36 0.59 -0.05 -0.68 -0.67 -0.16 0.64 -0.26 -0.05 -0.68zm6.92 -5.91l0.9 0.38 0.64 -0.75 -0.09 0.98 0.9 0.37 -0.95 0.23 -0.09 0.98 -0.5 -0.84 -0.95 0.23 0.65 -0.74 -0.5 -0.83z"></path></g></g></svg>
                        </div>
                    </a>
                </div>
            </div>

            <!-- Columna derecha: Gráfico estadístico -->
            <div class="chart-section">
                <div class="content-box">
                    <h3>Gráfico Estadístico</h3>
                    
                    <!-- Container for the dropdown positioned to the top right -->
                    <div class="year-selector-container">
                        <asp:DropDownList ID="ddlYear" runat="server" AutoPostBack="true" OnSelectedIndexChanged="ddlYear_SelectedIndexChanged">
                            <asp:ListItem Text="2024" Value="2024" />
                            <asp:ListItem Text="2025" Value="2025" />
                        </asp:DropDownList>
                    </div>

                    <!-- Contenedores para cada gráfico con títulos -->
                    <div>
                        <h4>Total Prendas</h4>
                        <canvas id="chartPrendas" style="width:100%; height:300px;"></canvas>
                    </div>
                    <div>
                        <h4>Promociones Activas</h4>
                        <canvas id="chartPromociones" style="width:100%; height:300px;"></canvas>
                    </div>
                    <div>
                        <h4>Cupones Activos</h4>
                        <canvas id="chartCupones" style="width:100%; height:300px;"></canvas>
                    </div>
                    <div>
                        <h4>Clientes Activos</h4>
                        <canvas id="chartClientes" style="width:100%; height:300px;"></canvas>
                    </div>
                </div>
            </div>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
        <script>
            // Función para cargar los datos en el gráfico con color específico
            function cargarDatosGrafico(datos, idGrafico, etiqueta, color) {
                new Chart(document.getElementById(idGrafico), {
                    type: 'bar',
                    data: {
                        labels: ["Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"],
                        datasets: [{
                            label: etiqueta,
                            data: datos,
                            backgroundColor: color
                        }]
                    },
                    options: {
                        responsive: true,
                        scales: {
                            y: { beginAtZero: true }
                        }
                    }
                });
            }
        </script>

        <style>
            /* Estilo para la imagen del gráfico */
            .chart-image {
                width: 100%;
                max-width: 500px; /* Ajusta el tamaño máximo según sea necesario */
                height: auto;
                display: block;
                margin: 0 auto; /* Centra la imagen en su contenedor */
            }
        </style>
    </div>
</asp:Content>


