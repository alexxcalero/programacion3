package pe.edu.pucp.softrh.usuarios.model;

import java.io.Serializable;

public abstract class Usuario implements Serializable{
	protected Integer idUsuario;
	protected String dni;
	protected String nombres;
	protected String apellidos;
	protected String correo;
	protected String contrasenha;
	protected Boolean activo;

	public Usuario() {
		this.idUsuario = null;
		this.dni = null;
		this.nombres = null;
		this.apellidos = null;
		this.correo = null;
		this.contrasenha = null;
		this.activo = null;
	}

	public Usuario(String dni, String nombres, String apellidos, String correo, String contrasenha) {
		this.dni = dni;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.correo = correo;
		this.contrasenha = contrasenha;
		this.activo = true;
	}
        public Usuario(String dni, String nombres, String apellidos, String correo) {
		this.dni = dni;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.correo = correo;
		this.activo = true;
	}
	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getNombreCompleto() {
		return nombres + " " + apellidos;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getContrasenha() {
		return contrasenha;
	}

	public void setContrasenha(String contrasenha) {
		this.contrasenha = contrasenha;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	@Override
	public String toString(){
		return "idUsuario=" + idUsuario + ", dni=" + dni + ", nombres=" +
				nombres + ", apellidos="  + apellidos + ", correo=" + correo + ", contraseña=" + contrasenha;
	}
}
