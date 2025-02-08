<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="Pagado.aspx.cs" Inherits="RHStoreWS.Cliente.Pagado" %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title>Pagado</title>
    <link href="/Content/bootstrap.min.css" rel="stylesheet" />
    <link href="/Content/fontawesome.min.css" rel="stylesheet" />
    <link href="/Content/all.min.css" rel="stylesheet" />
    <link href="/Content/styles.css" rel="stylesheet" />
    <link href="https://fonts.googleapis.com/css2?family=Playfair+Display:wght@700&family=Roboto:wght@400&display=swap" rel="stylesheet" />
</head>
<body>
    <form runat="server" class="container mt-5">
        <div class="text-center mb-4">
            <h2><i class="fas fa-check-circle"></i> ¡Pago Completado!</h2>
        </div>

        <div class="text-center mb-4">
            <asp:Label ID="lblMensaje" runat="server" ForeColor="Green" CssClass="alert alert-success text-center fs-4" Text="-" />
        </div>

        <div class="pagar text-center mt-3">
            <asp:Button ID="btnVolver" runat="server" Text="Menú Principal" OnClick="btnVolver_Click" CssClass="btn btn-secondary" />
        </div>

    </form>
</body>
</html>
