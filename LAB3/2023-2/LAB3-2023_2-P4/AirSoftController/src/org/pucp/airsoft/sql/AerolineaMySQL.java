/*
 * Proyecto : AirSoftController
 * Archivo  : AerolineaMySQL.java
 * Autor    : Alex Calero Revilla
 * Codigo   : 20206455
 * Fecha    : 17 set. 2024, 11:16:35
 */

package org.pucp.airsoft.sql;

import org.pucp.airsoft.companies.model.Aerolinea;
import org.pucp.airsoft.dao.AerolineaDAO;
import java.sql.Connection;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import org.pucp.airsoft.config.DBManager;

public class AerolineaMySQL implements AerolineaDAO {

	Connection con;
	Statement st;
	String instruccion;

	@Override
	public int insertar(Aerolinea aerolinea) {
		int resultado = 0;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			con = DBManager.getInstance().getConnection();
			st = con.createStatement();
			instruccion = "INSERT INTO aerolinea(nombre,callsign,tiene_programa_fidelidad,fecha_fundacion,descripcion,activa) " +
					"VALUES('" + aerolinea.getNombre() + "','" + aerolinea.getCallsign() + "'," + aerolinea.isTieneProgramaFidelidad()
					+ ",'" + sdf.format(aerolinea.getFechaFundacion()) + "','" + aerolinea.getDescripcion() + "',1)";
			resultado = st.executeUpdate(instruccion);
			con.close();
		} catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		return resultado;
	}
}
