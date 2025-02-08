<%@ Page Title="" Language="C#" MasterPageFile="~/Admin/Admin.Master" AutoEventWireup="true" CodeBehind="CrudTrabajadores.aspx.cs" Inherits="RHStoreWS.Admin.CrudTrabajadores" %>

<asp:Content ID="Content1" ContentPlaceHolderID="cphTitulo" runat="server">
    Registro o modificación de trabajador
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
                    <asp:Label ID="lblTitulo" runat="server" Text="lblTitulo"></asp:Label>
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
                        <asp:TextBox ID="txtNombres" runat="server" CssClass="form-control" placeholder="Ingrese un nombre" />
                    </div>
                </div>
                <div class="row mb-3">
                    <div class="col-md-2">
                        <asp:Label ID="lblApellidos" runat="server" Text="Apellidos:" CssClass="col-form-label fw-bold" />
                    </div>
                    <div class="col-md-4">
                        <asp:TextBox ID="txtApellidos" runat="server" CssClass="form-control" placeholder="Ingrese un apellido" />
                    </div>
                </div>
                <div class="row mb-3">
                    <div class="col-md-2">
                        <asp:Label ID="lblCorreo" runat="server" Text="Correo:" CssClass="col-form-label fw-bold" />
                    </div>
                    <div class="col-md-4">
                        <asp:TextBox ID="txtCorreo" runat="server" CssClass="form-control" placeholder="Ingrese un correo" />
                    </div>
                </div>
                <div class="row mb-3">
                    <div class="col-md-2">
                        <asp:Label ID="lblContrasenha" runat="server" Text="Contraseña:" CssClass="col-form-label fw-bold" />
                    </div>
                    <div class="col-md-4">
                        <asp:TextBox ID="txtContrasenha" runat="server" CssClass="form-control" placeholder="Ingrese una contraseña" type="Password" />
                    </div>
                    <div class="col-md-4">
                        <asp:LinkButton ID="lbResetear" runat="server" Text="<i class='fa-solid fa-rotate'></i> Restablecer" CssClass="btn btn-danger" OnClick="lbResetear_Click" />
                    </div>
                </div>
                <div class="row mb-3">
                    <div class="col-md-2">
                        <asp:Label ID="lblConfirmarContrasenha" runat="server" Text="Confirmar la contraseña:" CssClass="col-form-label fw-bold" />
                    </div>
                    <div class="col-md-4">
                        <asp:TextBox ID="txtConfirmarContrasenha" runat="server" CssClass="form-control" placeholder="Confirme la contraseña" type="Password" />
                    </div>
                </div>
                <div class="row mb-3">
                    <div class="col-md-2">
                        <asp:Label ID="lblPuesto" runat="server" Text="Puesto:" CssClass="col-form-label fw-bold" />
                    </div>
                    <div class="col-md-4">
                        <asp:TextBox ID="txtPuesto" runat="server" CssClass="form-control" placeholder="Ingrese un puesto" />
                    </div>
                </div>
                <div class="row mb-3">
                    <div class="col-md-2">
                        <asp:Label ID="lblSueldo" runat="server" Text="Sueldo:" CssClass="col-form-label fw-bold" />
                    </div>
                    <div class="col-md-4">
                        <asp:TextBox ID="txtSueldo" runat="server" CssClass="form-control" placeholder="Ingrese un sueldo" />
                    </div>
                </div>
                <div class="row mb-3">
                    <div class="col-md-2">
                        <asp:Label ID="lblFechaIngreso" runat="server" Text="Fecha de ingreso:" CssClass="col-form-label fw-bold" />
                    </div>
                    <div class="col-md-4">
                        <input id="dtpFechaIngreso" class="form-control" type="date" runat="server" text="Fecha de ingreso:" />
                    </div>
                </div>
                <div class="row mb-3">
                    <div class="col-md-2">
                        <asp:Label ID="lblHorarioInicio" runat="server" Text="Inicio de horario:" CssClass="col-form-label fw-bold" />
                    </div>
                    <div class="col-md-4">
                        <input id="tpHorarioInicio" class="form-control" type="time" runat="server" text="Inicio de horario:" />
                    </div>
                </div>
                <div class="row mb-3">
                    <div class="col-md-2">
                        <asp:Label ID="lblHorarioFin" runat="server" Text="Fin de horario:" CssClass="col-form-label fw-bold" />
                    </div>
                    <div class="col-md-4">
                        <input id="tpHorarioFin" class="form-control" type="time" runat="server" text="Fin de horario:" />
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
