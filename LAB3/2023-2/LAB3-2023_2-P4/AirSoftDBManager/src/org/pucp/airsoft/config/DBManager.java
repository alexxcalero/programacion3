/*
 * Proyecto : AirSoftDBManager
 * Archivo  : DBManager.java
 * Autor    : Alex Calero Revilla
 * Codigo   : 20206455
 * Fecha    : 17 set. 2024, 10:42:51
 */

package org.pucp.airsoft.config;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBManager {
	private Connection con;
	private static DBManager dbManager;
	private static String url = "jdbc:mysql://lp2-labs-inf282-2023-2.c8ttwc6gkvxy.us-east-1.rds.amazonaws.com/inf282";
	private static String username = "master";
	private static String password = "lp2labsinf282";

	public Connection getConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, username, password);
		} catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		return con;
	}

	public static DBManager getInstance() {
		if(dbManager == null) {
			createInstance();
		}
		return dbManager;
	}

	private synchronized static void createInstance() {
		if(dbManager == null) {
			dbManager = new DBManager();
		}
	}
}
