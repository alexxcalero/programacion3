<%@ Page Title="" Language="C#" MasterPageFile="~/SoftApply.Master" AutoEventWireup="true" CodeBehind="RegistrarPostulante.aspx.cs" Inherits="SoftApplyWA.RegistrarPostulante" %>
<asp:Content ID="Content1" ContentPlaceHolderID="cphTitulo" runat="server">
    Registrar Postulante
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="cphScripts" runat="server">
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="cphContenido" runat="server">
    <asp:ScriptManager ID="smContenido" runat="server" />
    <asp:UpdatePanel id="upContenido" runat="server">
    <ContentTemplate>
        <div class="container">
        <div class="card">
            <div class="card-header">
                <h2>
                    <asp:Label ID="lblTitulo" runat="server" Text="Registrar Postulante"></asp:Label>
                </h2>
            </div>
            <div class="card-body pb-2">
                <div class="mb-3 row align-items-center">
                    <asp:Label ID="lblDNI" runat="server" Text="DNI:" CssClass="col-sm-auto pe-sm-2 col-form-label fw-bold" ></asp:Label>
                    <div class="col-sm-3">                        
                        <asp:TextBox ID="txtDNI" runat="server" CssClass="form-control" ></asp:TextBox>
                    </div>
                </div>
                <div class="mb-3 row align-items-center">
                    <asp:Label ID="lblNombre" runat="server" Text="Nombre:" CssClass="col-sm-auto pe-sm-2 col-form-label fw-bold" ></asp:Label>
                    <div class="col-sm-5">                        
                        <asp:TextBox ID="txtNombre" runat="server" CssClass="form-control" ></asp:TextBox>
                    </div>
                </div>
                <div class="mb-3 row align-items-center">
                    <asp:Label ID="lblApellidoPaterno" runat="server" Text="Apellido Paterno:" CssClass="col-sm-auto pe-sm-2 col-form-label fw-bold" ></asp:Label>
                    <div class="col-sm-5">
                        <asp:TextBox ID="txtApellidoPaterno" runat="server" CssClass="form-control" ></asp:TextBox>
                    </div>
                </div>
                <div class="mb-3 row align-items-center">
                    <asp:Label ID="lblUniversidadOrigen" runat="server" Text="Universidad Origen:" CssClass="col-sm-auto pe-sm-2 col-form-label fw-bold" ></asp:Label>
                    <div class="col-sm-7">
                        <asp:DropDownList ID="ddlUniversidadOrigen" runat="server" CssClass="form-select" AutoPostBack="true"></asp:DropDownList>
                    </div>
                </div>
                <div class="mb-3 row align-items-center">
                    <asp:Label ID="lblPromedio" runat="server" Text="Promedio Acumulado Obtenido en Pregrado:" CssClass="col-sm-auto pe-sm-2 col-form-label fw-bold" ></asp:Label>
                    <div class="col-sm-3">
                        <asp:TextBox ID="txtPromedio" runat="server" CssClass="form-control" ></asp:TextBox>
                    </div>
                </div>
                <div class="mb-3 row">
                    <asp:Label ID="lblLogros" runat="server" Text="Logros en Pregrado:" CssClass="col col-form-label fw-bold" ></asp:Label>
                    <div class="col-sm-12">
                        <div class="form-control">
                            <div class="form-check form-check-inline col-sm-12">
                                <input id="cbTercioSuperior" class="form-check-input" type="checkbox" runat="server" />
                                <label id="lblTercioSuperior" class="form-check-label" for="cphContenido_cbTercioSuperior">1/3 SUPERIOR</label>
                            </div>
                            <div class="form-check form-check-inline col-sm-12">
                                <input id="cbGrupoInvestigacion" class="form-check-input" type="checkbox" runat="server" />
                                <label id="lblGrupoInvestigacion" class="form-check-label" for="cphContenido_cbGrupoInvestigacion">PERTENENCIA A GRUPO DE INVESTIGACION</label>
                            </div>
                            <div class="form-check form-check-inline col-sm-12">
                                <input id="cbDeportistaCalificado" class="form-check-input" type="checkbox" runat="server" />
                                <label id="lblDeportistaCalificado" class="form-check-label" for="cphContenido_cbDeportistaCalificado">DEPORTISTA CALIFICADO</label>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="mb-3 row align-items-center">
                    <asp:Label ID="lblModalidadEstudios" runat="server" Text="Modalidad de Estudios Preferida:" CssClass="col-sm-auto pe-sm-2 col-form-label fw-bold" ></asp:Label>
                    <div class="col">
                        <div class="form-control">
                            <div class="form-check form-check-inline">
                                <input id="rbPresencial" class="form-check-input" type="radio" runat="server" name="modalidad"/>
                                <label id="lblPresencial" class="form-check-label" for="cphContenido_rbPresencial">PRESENCIAL</label>
                            </div>
                            <div class="form-check form-check-inline">
                                <input id="rbVirtual" class="form-check-input" type="radio" runat="server" name="modalidad"/>
                                <label id="lblVirtual" class="form-check-label" for="cphContenido_rbVirtual">VIRTUAL</label>
                            </div>
                            <div class="form-check form-check-inline">
                                <input id="rbHibrida" class="form-check-input" type="radio" runat="server" name="modalidad"/>
                                <label id="lblHibrida" class="form-check-label" for="cphContenido_rbHibrida">HIBRIDA</label>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="card-footer">
                <asp:LinkButton ID="lbRegresar" runat="server" Text="<i class='fa-solid fa-rotate-left'></i> Regresar" CssClass="float-start btn btn-secondary" OnClick="lbRegresar_Click"/>
                <asp:LinkButton ID="lbGuardar" runat="server" Text="<i class='fa-regular fa-floppy-disk'></i> Guardar" CssClass="float-end btn btn-primary" />
            </div>
        </div>
    </div>
    </ContentTemplate>
    </asp:UpdatePanel>
</asp:Content>