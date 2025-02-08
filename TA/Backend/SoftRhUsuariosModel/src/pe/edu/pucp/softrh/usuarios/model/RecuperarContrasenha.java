package pe.edu.pucp.softrh.usuarios.model;

import java.util.Date;
import java.io.Serializable;

public class RecuperarContrasenha implements Serializable{
	private Integer idToken;
    private Integer idUsuario;
    private String token;
    private Date fechaExpiracion;

	public RecuperarContrasenha() {}

    public RecuperarContrasenha(Integer idUsuario, String token, Date fechaExpiracion) {
        this.idUsuario = idUsuario;
        this.token = token;
        this.fechaExpiracion = fechaExpiracion;
    }

    public Integer getIdToken() {
        return idToken;
    }

    public void setIdToken(Integer idToken) {
        this.idToken = idToken;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getFechaExpiracion() {
        return fechaExpiracion;
    }

    public void setFechaExpiracion(Date fechaExpiracion) {
        this.fechaExpiracion = fechaExpiracion;
    }
}
