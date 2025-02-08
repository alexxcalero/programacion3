<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="RecuperarContrasenha.aspx.cs" Inherits="RHStoreWS.Admin.RecuperarContrasenha" %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" lang="es">
<head runat="server">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

    <!-- Importación de la fuente Roboto -->
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500&display=swap" rel="stylesheet" />

    <link href="../Content/bootstrap.css" rel="stylesheet" />
    <link href="../Content/styles.css" rel="stylesheet" />
    <link href="../Content/Fonts/css/all.css" rel="stylesheet" />

    <script src="../Scripts/bootstrap.js"></script>
    <script src="../Scripts/bootstrap.bundle.js"></script>
    <script src="../Scripts/jquery-3.7.1.js"></script>

    <style>
        body {
            font-family: 'Roboto', sans-serif;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
            background: url('../Images/LoginFondo.png') no-repeat center center fixed;
            background-size: cover;
        }

        .login-container {
            width: 350px;
            background-color: #ffffff;
            padding: 40px;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
            text-align: center;
            position: relative;
        }

        .login-container h3 {
            color: #333;
            margin-bottom: 20px;
        }

        .form-group {
            position: relative;
            margin-bottom: 20px;
            text-align: left;
        }

        .form-group label {
            font-weight: bold;
            color: #555;
        }

        .form-control {
            width: 100%;
            padding: 10px;
            margin-top: 5px;
            border: 1px solid #ddd;
            border-radius: 5px;
            font-size: 14px;
            box-sizing: border-box;
        }

        /* Estilo de botón similar a ModificarContrasenha */
        .btn-12,
        .btn-12 *,
        .btn-12 :after,
        .btn-12 :before,
        .btn-12:after,
        .btn-12:before {
            border: 0 solid;
            box-sizing: border-box;
        }

        .btn-12 {
            -webkit-tap-highlight-color: transparent;
            -webkit-appearance: button;
            background-color: #000;
            color: #fff;
            cursor: pointer;
            font-family: ui-sans-serif, system-ui, -apple-system, BlinkMacSystemFont,
                Segoe UI, Roboto, Helvetica Neue, Arial, Noto Sans, sans-serif,
                Apple Color Emoji, Segoe UI Emoji, Segoe UI Symbol, Noto Color Emoji;
            font-size: 100%;
            font-weight: 900;
            line-height: 1.5;
            margin: 0;
            -webkit-mask-image: -webkit-radial-gradient(#000, #fff);
            padding: 0;
            text-transform: uppercase;
        }

        .btn-12:disabled {
            cursor: default;
        }

        .btn-12:-moz-focusring {
            outline: auto;
        }

        .btn-12 svg {
            display: block;
            vertical-align: middle;
        }

        .btn-12 [hidden] {
            display: none;
        }

        .btn-12 {
            border-radius: 99rem;
            border-width: 2px;
            overflow: hidden;
            padding: 0.8rem 3rem;
            position: relative;
        }

        .btn-12 span {
            mix-blend-mode: difference;
        }

        .btn-12:after,
        .btn-12:before {
            background: linear-gradient(
                90deg,
                #fff 25%,
                transparent 0,
                transparent 50%,
                #fff 0,
                #fff 75%,
                transparent 0
            );
            content: "";
            inset: 0;
            position: absolute;
            transform: translateY(var(--progress, 100%));
            transition: transform 0.2s ease;
        }

        .btn-12:after {
            --progress: -100%;
            background: linear-gradient(
                90deg,
                transparent 0,
                transparent 25%,
                #fff 0,
                #fff 50%,
                transparent 0,
                transparent 75%,
                #fff 0
            );
            z-index: -1;
        }

        .btn-12:hover:after,
        .btn-12:hover:before {
            --progress: 0;
        }

        .text-link {
            color: #2193b0;
            text-decoration: none;
        }

        .text-link:hover {
            text-decoration: underline;
        }
    </style>

    <title>
        Recuperación de contraseña
    </title>
</head>
<body>
    <div class="login-container">
        <h3>Recuperación de Contraseña</h3>
        <form id="formRecuperarContrasenha" runat="server">
            <div class="form-group">
                <label for="txtCorreo">Correo electrónico</label>
                <asp:TextBox ID="txtCorreo" runat="server" CssClass="form-control" placeholder="Ingrese su correo electrónico" />
                <asp:Label ID="lblErrorCorreo" runat="server" Text="Ingrese un correo electrónico válido." CssClass="text-danger" Visible="false" Style="color: red; font-weight: bold;"></asp:Label>
            </div>
            <div class="form-group">
                <asp:Label ID="lblResultado" runat="server" CssClass="text-danger" Visible="false" Style="color: red; font-weight: bold;"></asp:Label>
                <asp:Label ID="lblResultadoP" runat="server" CssClass="" Visible="false" Style="color: deepskyblue"></asp:Label>
            </div>
            <div class="text-center mt-3">
                <button type="button" class="btn-12" onclick="document.getElementById('<%= btnRecuperarContrasenha.ClientID %>').click();">Recuperar</button>
                <asp:Button ID="btnRecuperarContrasenha" runat="server" CssClass="d-none" OnClick="btnRecuperarContrasenha_Click" />
            </div>
        </form>
        <div class="text-center mt-3">
            <small><a href="IniciarSesion.aspx" class="text-link">Volver al inicio de sesión</a></small>
        </div>
    </div>
</body>
</html>
