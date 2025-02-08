<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="CambiarContrasenha.aspx.cs" Inherits="RHStoreWS.Admin.CambiarContrasenha" %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" lang="es">
<head>
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

        .container {
            width: 350px;
            background-color: #ffffff;
            padding: 40px;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
            text-align: center;
            position: relative;
        }

        .container h3 {
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

        .show-password {
            display: flex;
            align-items: center;
            justify-content: flex-start;
            margin-top: 10px;
            color: #555;
        }

        .show-password input {
            margin-right: 5px;
        }
    </style>

    <title>
        Cambiar contraseña
    </title>
</head>
<body>
    <div class="container">
        <h3>Cambiar Contraseña</h3>
        <form id="formCambiarContrasenha" runat="server" method="post">
            <div class="form-group">
                <label for="txtNuevaContrasenha">Nueva Contraseña</label>
                <asp:TextBox ID="txtNuevaContrasenha" runat="server" type="password" CssClass="form-control" placeholder="Ingrese su nueva contraseña" />
            </div>
            <div class="form-group">
                <label for="txtConfirmarContrasenha">Confirmar Contraseña</label>
                <asp:TextBox ID="txtConfirmarContrasenha" runat="server" type="password" CssClass="form-control" placeholder="Confirme su nueva contraseña" />
            </div>

            <!-- Mostrar contraseña -->
            <div class="show-password">
                <input type="checkbox" id="showPassword" onclick="togglePasswordVisibility()" />
                <label for="showPassword">Mostrar contraseña</label>
            </div>

            <!-- Mensajes de error -->
            <asp:Label ID="lblErrorContrasenhasNuevas" runat="server" Text="Las contraseñas no coinciden" CssClass="text-danger" Visible="false" Style="color: red; font-weight: bold;" />
            <asp:Label ID="lblError" runat="server" Text="Token inválido o expirado." CssClass="text-danger" Visible="false" Style="color: red; font-weight: bold;" />

            <div class="text-center mt-4">
                <!-- Botón HTML con animación -->
                <button type="button" class="btn-12" onclick="document.getElementById('<%= btnCambiarContrasenha.ClientID %>').click();">
                    <span>Cambiar Contraseña</span>
                </button>
                <!-- Botón de servidor ASP.NET oculto para manejar el evento -->
                <asp:Button ID="btnCambiarContrasenha" runat="server" CssClass="d-none" OnClick="btnCambiarContrasenha_Click" />
            </div>
        </form>
    </div>

    <script>
        function togglePasswordVisibility() {
            var passwordFields = [
                document.getElementById('<%= txtNuevaContrasenha.ClientID %>'),
                document.getElementById('<%= txtConfirmarContrasenha.ClientID %>')
            ];
            passwordFields.forEach(function (field) {
                field.type = field.type === 'password' ? 'text' : 'password';
            });
        }
    </script>
</body>
</html>

