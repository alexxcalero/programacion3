<%@ Page Title="" Language="C#" MasterPageFile="~/SazonWare.Master" AutoEventWireup="true" CodeBehind="RegistrarPlato.aspx.cs" Inherits="SazonWareWA.RegistrarPlato" %>
<asp:Content ID="Content1" ContentPlaceHolderID="cphTitulo" runat="server">
    Registrar Plato
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="cphScripts" runat="server">
    <style>
    .no-resize {
        resize: none; /* Desactiva el redimensionamiento */
    }
    </style>
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="cphContenido" runat="server">
    <asp:ScriptManager ID="smPlatos" runat="server"/>
    <asp:UpdatePanel ID="upPlatos" runat="server">
    <ContentTemplate>
    <div class="container">
        <div class="card">
            <div class="card-header">
                <h2>
                    <asp:Label ID="lblTitulo" runat="server" Text="Registrar Plato"></asp:Label>
                </h2>
            </div>
            <div class="card-body">
                <div class="row">
                    <div class="col-md-6">
                        <asp:Label ID="lblFotoPlato" runat="server" Text="Foto del Plato:" CssClass="col-form-label fw-bold" ></asp:Label>
                        <asp:Image ID="imgFotoPlato" runat="server" CssClass="img-fluid img-thumbnail" ImageUrl="/Images/placeholder.jpg" Height="235" Width="720" />
                        <asp:FileUpload ID="fileUploadFotoPlato" CssClass="form-control mb-2" runat="server" onchange="this.form.submit()" ClientIDMode="Static" />
                    </div>
                    <div class="col-md-6">
                        <div class="col-md-12 pb-md-3">
                            <asp:Label ID="lblNombre" runat="server" Text="Nombre del Plato:" CssClass="col-form-label fw-bold"></asp:Label>
                            <asp:TextBox ID="txtNombre" runat="server" CssClass="form-control" ></asp:TextBox>
                        </div>
                        <div class="col-md-12 pb-md-3">
                            <asp:Label ID="lblPrecio" runat="server" Text="Precio del Plato (S/.):" CssClass="col-form-label fw-bold"></asp:Label>
                            <asp:TextBox ID="txtPrecio" runat="server" CssClass="form-control"></asp:TextBox>
                        </div>
                        <div class="col-md-12 pb-md-3">
                            <asp:Label ID="lblTipoEvento" runat="server" Text="Categoría del Plato:" CssClass="col-form-label fw-bold"></asp:Label>
                            <div class="form-control">
                                <div class="form-check form-check-inline col-md-12">
                                    <input id="rbEntrada" class="form-check-input" type="radio" runat="server" name="tipoEvento"/>
                                    <label id="lblEntrada" class="form-check-label" for="cphContenido_rbEntrada">ENTRADA</label>
                                </div>
                                <div class="form-check form-check-inline col-md-12">
                                    <input id="rbFondo" class="form-check-input" type="radio" runat="server" name="tipoEvento" />
                                    <label id="lblFondo" class="form-check-label" for="cphContenido_rbFondo">FONDO</label>
                                </div>
                                <div class="form-check form-check-inline col-md-12">
                                    <input id="rbPostre" class="form-check-input" type="radio" runat="server" name="tipoEvento" />
                                    <label id="lblPostre" class="form-check-label" for="cphContenido_rbPostre">POSTRE</label>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="card border">
                    <div class="card-header bg-light">
                        <h5 class="card-title mb-0">Ingredientes del Plato</h5>
                    </div>
                    <div class="card-body">
                        <div class="row align-items-center pb-3">
                            <div class="col-md-auto">
                                <asp:Label ID="lblIngrediente" runat="server" Text="Seleccione el ingrediente:" CssClass="col-form-label fw-bold" />
                            </div>
                            <div class="col-md">
                                <asp:DropDownList id="ddlIngredientes" runat="server" CssClass="form-select" AutoPostBack="true" OnSelectedIndexChanged="ddlIngredientes_SelectedIndexChanged"></asp:DropDownList>
                            </div>
                        </div>
                        <div class="row align-items-center pb-3">
                            <div class="col-md">
                                <asp:TextBox ID="txtDescripcion" runat="server" TextMode="MultiLine" CssClass="form-control no-resize" Rows="2" Columns="50" ReadOnly="true" />
                            </div>
                            <div class="col-md-auto">
                                <asp:LinkButton ID="lbAgregar" runat="server" Text="<i class='fa-solid fa-plus'></i> Agregar" CssClass="btn btn-success" OnClick="lbAgregar_Click"/>
                            </div>
                        </div>
                        <div class="row">
                            <asp:GridView ID="dgvIngredientes" runat="server" AutoGenerateColumns="false"
                                AllowPaging="true" PageSize="5"
                                CssClass="table table-hover table-responsive table-striped" OnRowDataBound="dgvIngredientes_RowDataBound">
                                <Columns>
                                    <asp:BoundField HeaderText="Ingredientes del Plato" ItemStyle-CssClass="align-middle"/>
                                    <asp:TemplateField>
                                        <ItemTemplate>
                                            <asp:LinkButton runat="server" Text="<i class='fa-solid fa-trash'></i>" CssClass="btn btn-danger" OnClick="btnEliminar_Click" CommandArgument='<%# Eval("idIngrediente") %>'/>
                                        </ItemTemplate>
                                    </asp:TemplateField>
                                </Columns>
                            </asp:GridView>
                        </div>
                    </div>
                </div>
            </div>
            <div class="card-footer clearfix">
                <asp:LinkButton ID="btnRegresar" runat="server" Text="<i class='fa-solid fa-rotate-left'></i> Regresar" CssClass="float-start btn btn-secondary" OnClick="btnRegresar_Click"/>
                <asp:LinkButton ID="btnGuardar" runat="server" Text="<i class='fa-regular fa-floppy-disk'></i> Guardar" CssClass="float-end btn btn-primary" OnClick="btnGuardar_Click"/>
            </div>
        </div>
    </div>
    </ContentTemplate>
    </asp:UpdatePanel>
</asp:Content>