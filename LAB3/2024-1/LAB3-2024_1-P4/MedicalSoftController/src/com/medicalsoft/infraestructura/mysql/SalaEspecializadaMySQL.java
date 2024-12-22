package com.medicalsoft.infraestructura.mysql;

import com.medicalsoft.config.DBManager;
import com.medicalsoft.infraestructura.dao.SalaEspecializadaDAO;
import com.medicalsoft.infraestructura.model.SalaEspecializada;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;

public class SalaEspecializadaMySQL implements SalaEspecializadaDAO{

    private Connection con;
	private Statement st;
    private String sql;

    @Override
    public int insertar(SalaEspecializada salaEspecializada) {
		int resultado = 0;
        try {
			// Registrar el driver
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Creamos la conexion
			con = DriverManager.getConnection(DBManager.getUrl(), DBManager.getUsername(), DBManager.getPassword());

			// Creamos un statement
			st = con.createStatement();

			// Creamos la instruccion
			sql = "INSERT INTO sala_especializada(nombre,espacio_en_m2,torre,piso,posee_equipamiento_imagenologia,activa) VALUES("
				+ "'" + salaEspecializada.getNombre() + "'," + salaEspecializada.getEspacioEnMetrosCuadrados()
				+ ",'" + salaEspecializada.getTorre() + "'," + salaEspecializada.getPiso() + "," + salaEspecializada.isPoseeEquipamientoImagenologia()
				+ ",1)";

			// Ejecutamos la instruccion
			resultado = st.executeUpdate(sql);
			
			// Cerramos la conexion
			con.close();
		} catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		return resultado;
    }
}
