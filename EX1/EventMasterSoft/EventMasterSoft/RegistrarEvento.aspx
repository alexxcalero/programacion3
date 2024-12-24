<%@ Page Title="" Language="C#" MasterPageFile="~/EventMasterSoft.Master" AutoEventWireup="true" CodeBehind="RegistrarEvento.aspx.cs" Inherits="EventMasterSoft.RegistrarEvento" %>
<asp:Content ID="Content1" ContentPlaceHolderID="cphTitulo" runat="server">
    Registrar Evento
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="cphScripts" runat="server">
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="cphContenido" runat="server">
    <div class="container">
        <div class="card">
            <div class="card-header">
                <h2>
                    <!-- Cambiar el titulo dependiendo de si se registran o muestran datos -->
                    <asp:Label ID="lblTitulo" runat="server" Text="lblTitulo"></asp:Label>
                </h2>
            </div>
            <div class="card-body pb-2">
                <div class="row">
                    <div class="col-md-6">
                        <asp:Label ID="lblBannerPromocional" runat="server" Text="Banner Promocional:" CssClass="col-form-label fw-bold" ></asp:Label>
                        <asp:Image ID="imgBannerPromocional" runat="server" CssClass="img-fluid img-thumbnail" ImageUrl="/Images/placeholder.jpg" Height="235" Width="720" />
                        <asp:FileUpload ID="fileUploadBannerPromocional" CssClass="form-control mb-2" runat="server" onchange="this.form.submit()" ClientIDMode="Static" />
                    </div>
                    <div class="col-md-6">
                        <div class="col-md-6 pb-md-3">
                            <asp:Label ID="lblIDEvento" runat="server" Text="ID del Evento:" CssClass="col-form-label fw-bold"></asp:Label>
                            <asp:TextBox ID="txtIDEvento" runat="server" CssClass="form-control" ReadOnly="true" ></asp:TextBox>
                        </div>
                        <div class="col-md-12 pb-md-3">
                            <asp:Label ID="lblNombreEvento" runat="server" Text="Nombre del Evento:" CssClass="col-form-label fw-bold"></asp:Label>
                            <asp:TextBox ID="txtNombreEvento" runat="server" CssClass="form-control" ></asp:TextBox>
                        </div>
                        <div class="col-md-12 pb-md-3">
                            <asp:Label ID="lblProductora" runat="server" Text="Productora:" CssClass="col-form-label fw-bold"></asp:Label>
                            <asp:DropDownList ID="ddlProductora" runat="server" AutoPostBack="true" CssClass="form-select"></asp:DropDownList>
                        </div>
                        <div class="col-md-12">
                            <asp:Label ID="lblTipoEvento" runat="server" Text="Tipo Evento:" CssClass="col-form-label fw-bold"></asp:Label>
                            <div class="form-control">
                                <div class="form-check form-check-inline">
                                    <input id="rbObraTeatral" class="form-check-input" type="radio" runat="server" name="tipoEvento"/>
                                    <label id="lblObraTeatral" class="form-check-label" for="cphContenido_rbObraTeatral">OBRA TEATRAL</label>
                                </div>
                                <div class="form-check form-check-inline">
                                    <input id="rbConcierto" class="form-check-input" type="radio" runat="server" name="tipoEvento"/>
                                    <label id="lblConcierto" class="form-check-label" for="cphContenido_rbConcierto">CONCIERTO</label>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12">
                        <asp:Label ID="lblClasificacion" runat="server" Text="Clasificación (Público al cual se encuentra dirigido:):" CssClass="col-form-label fw-bold" ></asp:Label>
                        <div class="form-control">
                            <div class="form-check form-check-inline">
                                <input id="rbAdultos" class="form-check-input" type="radio" runat="server" name="clasificacion"/>
                                <label id="lblAdultos" class="form-check-label" for="cphContenido_rbAdultos">ADULTOS</label>
                            </div>
                            <div class="form-check form-check-inline">
                                <input id="rbJovenes" class="form-check-input" type="radio" runat="server" name="clasificacion"/>
                                <label id="lblJovenes" class="form-check-label" for="cphContenido_rbJovenes">JÓVENES</label>
                            </div>
                            <div class="form-check form-check-inline">
                                <input id="rbNinhos" class="form-check-input" type="radio" runat="server" name="clasificacion"/>
                                <label id="lblNinhos" class="form-check-label" for="cphContenido_rbNinhos">NIÑOS</label>
                            </div>
                            <div class="form-check form-check-inline">
                                <input id="rbTodos" class="form-check-input" type="radio" runat="server" name="clasificacion"/>
                                <label id="lblTodos" class="form-check-label" for="cphContenido_rbTodos">TODO PÚBLICO</label>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row mt-2">
                    <div class="col-md-6">
                        <asp:Label ID="lblCostoRealizacion" runat="server" Text="Costo de Realización (S/.):" CssClass="col-form-label fw-bold" ></asp:Label>
                        <asp:TextBox ID="txtCostoRealizacion" runat="server" CssClass="form-control" ></asp:TextBox>
                    </div>
                    <div class="col-md-6">
                        <asp:Label ID="lblFechaRealizacion" runat="server" Text="Fecha de Realización:" CssClass="col-form-label fw-bold" ></asp:Label>
                        <input id="dtpFechaRealizacion" runat="server" type="date" class="form-control"/>
                    </div>
                </div>
                <div class="row mt-2 align-items-center">
                    <div class="col-md-auto">
                        <asp:Label ID="lblPermisosEvento" runat="server" Text="Permisos en el Evento:" CssClass="col-form-label fw-bold" ></asp:Label>
                    </div>
                    <div class="col">
                        <div class="form-control">
                            <div class="form-check form-check-inline">
                                <input id="cbReingreso" class="form-check-input" type="checkbox" runat="server" />
                                <label id="lblReingreso" class="form-check-label" for="cphContenido_cbReingreso">REINGRESO</label>
                            </div>
                            <div class="form-check form-check-inline">
                                <input id="cbGrabacion" class="form-check-input" type="checkbox" runat="server" />
                                <label id="lblGrabacion" class="form-check-label" for="cphContenido_cbGrabacion">GRABACIÓN</label>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row mt-2">
                    <div class="col-md-12">
                        <asp:Label ID="lblDescripcion" runat="server" Text="Descripcion del Evento:" CssClass="col-form-label fw-bold" ></asp:Label>
                        <textarea id="txtDescripcion" runat="server" class="form-control" cols="20" rows="2"></textarea>
                    </div>
                </div>
            </div>
            <div class="card-footer clearfix">
                <asp:LinkButton ID="lbRegresar" runat="server" Text="<i class='fa-solid fa-rotate-left'></i> Regresar" CssClass="float-start btn btn-secondary" OnClick="lbRegresar_Click"/>
                <asp:LinkButton ID="lbGuardar" runat="server" Text="<i class='fa-regular fa-floppy-disk'></i> Guardar" CssClass="float-end btn btn-primary" OnClick="lbGuardar_Click"/>
            </div>
        </div>
    </div>
</asp:Content>