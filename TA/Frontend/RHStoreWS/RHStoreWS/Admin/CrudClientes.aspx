<%@ Page Title="" Language="C#" MasterPageFile="~/Admin/Admin.Master" AutoEventWireup="true" CodeBehind="CrudClientes.aspx.cs" Inherits="RHStoreWS.Admin.CrudClientes" %>

<asp:Content ID="Content1" ContentPlaceHolderID="cphTitulo" runat="server">
    Registro o modificación de cliente
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="cphScripts" runat="server">
    <script src="../Scripts/RHStore/ModalesDeError.js"></script>
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="cphNombreUsuario" runat="server">
    <asp:Label ID="lblNombreUsuario" runat="server" Text="Nombre de usuario" />
</asp:Content>
<asp:Content ID="Content4" ContentPlaceHolderID="cphContenido" runat="server">
    <div class="container">
        <div class="card">
            <div class="card-header">
                <h2>
                    <!-- Cambiar el titulo dependiendo de si se registran o muestran datos -->
                    <asp:Label ID="lblTitulo" runat="server" Text="lblTitulo" />
                </h2>
            </div>
            <div class="card-body pb-2">
                <div class="row mb-3">
                    <div class="col-md-2">
                        <asp:Label ID="lblID" runat="server" Text="ID:" CssClass="col-form-label fw-bold" />
                    </div>
                    <div class="col-md-2">
                        <asp:TextBox ID="txtID" runat="server" CssClass="form-control" Enabled="false" />
                    </div>
                </div>
                <div class="row mb-3">
                    <div class="col-md-2">
                        <asp:Label ID="lblDNI" runat="server" Text="DNI:" CssClass="col-form-label fw-bold" />
                    </div>
                    <div class="col-md-4">
                        <asp:TextBox ID="txtDNI" runat="server" CssClass="form-control" placeholder="Ingrese un DNI" />
                    </div>
                </div>
                <div class="row mb-3">
                    <div class="col-md-2">
                        <asp:Label ID="lblNombres" runat="server" Text="Nombres:" CssClass="col-form-label fw-bold" />
                    </div>
                    <div class="col-md-4">
                        <asp:TextBox ID="txtNombres" runat="server" CssClass="form-control" Enabled="false" />
                    </div>
                </div>
                <div class="row mb-3">
                    <div class="col-md-2">
                        <asp:Label ID="lblApellidos" runat="server" Text="Apellidos:" CssClass="col-form-label fw-bold" />
                    </div>
                    <div class="col-md-4">
                        <asp:TextBox ID="txtApellidos" runat="server" CssClass="form-control" Enabled="false" />
                    </div>
                </div>
                <div class="row mb-3">
                    <div class="col-md-2">
                        <asp:Label ID="lblCorreo" runat="server" Text="Correo:" CssClass="col-form-label fw-bold" />
                    </div>
                    <div class="col-md-4">
                        <asp:TextBox ID="txtCorreo" runat="server" CssClass="form-control" Enabled="false" />
                    </div>
                </div>
                <div class="row mb-3">
                    <div class="col-md-2">
                        <asp:Label ID="lblContrasenha" runat="server" Text="Contraseña:" CssClass="col-form-label fw-bold" />
                    </div>
                    <div class="col-md-4">
                        <asp:TextBox ID="txtContrasenha" runat="server" CssClass="form-control" type="Password" Enabled="false" />
                    </div>
                </div>
                <div class="row mb-3">
                    <div class="col-md-2">
                        <asp:Label ID="lblTelefono" runat="server" Text="Teléfono:" CssClass="col-form-label fw-bold" />
                    </div>
                    <div class="col-md-4">
                        <asp:TextBox ID="txtTelefono" runat="server" CssClass="form-control" Enabled="false" />
                    </div>
                </div>
                <div class="row mb-3">
                    <div class="col-md-2">
                        <asp:Label ID="lblFechaRegistro" runat="server" Text="Fecha de registro:" CssClass="col-form-label fw-bold" />
                    </div>
                    <div class="col-md-4">
                        <input id="dtpFechaRegistro" class="form-control" type="date" runat="server" text="Fecha de registro:" />
                    </div>
                </div>
                <div class="row mb-3">
                    <div class="col-md-2">
                        <asp:Label ID="lblRecibePromocion" runat="server" Text="Desea suscribirse:" CssClass="col-form-label fw-bold" />
                    </div>
                    <div class="col-md-4">
                        <div class="form-control" e="false">
                            <div class="form-check form-check-inline">
                                <input id="rbSi" class="form-check-input" type="radio" runat="server" name="recibePromocion" />
                                <label id="lblSi" class="form-check-label" for="rbSi">Sí</label>
                            </div>
                            <div class="form-check form-check-inline">
                                <input id="rbNo" class="form-check-input" type="radio" runat="server" name="recibePromocion" />
                                <label id="lblNo" class="form-check-label" for="rbNo">No</label>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="card-footer clearfix">
                    <asp:LinkButton ID="lbRegresar" runat="server" Text="<i class='fa-solid fa-rotate-left'></i> Regresar" CssClass="float-start btn btn-secondary" OnClick="lbRegresar_Click" />
                    <asp:LinkButton ID="lbGuardar" runat="server" Text="<i class='fa-regular fa-floppy-disk'></i> Guardar" CssClass="float-end btn btn-primary" OnClick="lbGuardar_Click" />
                </div>
            </div>
        </div>
    </div>

    <!-- Modal de errores -->
    <div class="modal fade" id="errorModal" tabindex="-1" aria-labelledby="errorModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-header bg-danger text-white">
                    <h5 class="modal-title" id="errorModalLabel">
                        <i class="fa-solid fa-triangle-exclamation bi bi-exclamation-triangle-fill me-2"></i>Mensaje de error
                    </h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close" />
                </div>
                <div class="modal-body">
                    <asp:Label ID="lblMensajeError" runat="server" Text="Mensaje de ejemplo..." CssClass="fs-5 form-text text-danger fw-bold" />
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
                </div>
            </div>
        </div>
    </div>

    <!-- Modal de contrasenha cambiada -->
    <div class="modal fade" id="restablecerContrasenhaModal" tabindex="-1" aria-labelledby="restablecerContrasenhaModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-header bg-danger text-white">
                    <h5 class="modal-title" id="restablecerContrasenhaModalLabel">
                        <i class="fa-solid fa-thumbs-up bi bi-exclamation-triangle-fill me-2"></i>Contraseña restablecida
                    </h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close" />
                </div>
                <div class="modal-body">
                    <asp:Label ID="restablecerContrasenhaModalMensaje" runat="server" Text="La contraseña ha sido restablecida con éxito" CssClass="fs-5 form-text text-danger fw-bold" />
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
                </div>
            </div>
        </div>
    </div>
</asp:Content>
