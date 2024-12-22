package com.medicalsoft.config;

import java.sql.DriverManager;
import java.sql.Connection;

public class DBManager {

    private Connection con;
    private static DBManager dbManager;
    private static String url = "jdbc:mysql://prog3-labs-1inf30.c8ttwc6gkvxy.us-east-1.rds.amazonaws.com/laboratorio4";
    private static String username = "admin";
    private static String password = "labs1inf3020241";

	public static String getUrl() {
		return url;
	}

	public static String getUsername() {
		return username;
	}

	public static String getPassword() {
		return password;
	}

    public Connection getConnection(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, username, password);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return con;
    }

    public static DBManager getInstance(){
        if(dbManager == null){
            createInstance();
        }
        return dbManager;
    }

    private synchronized static void createInstance(){
        if(dbManager == null){
            dbManager = new DBManager();
        }
    }
}