package com.bibliotecapp.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {
	
	Connection conn = null;
	
	public Connection connectDatabase() throws BDException
	{
		
		try {
			//Selection of the driver
			Class.forName("com.mysql.jdbc.Driver");
			//Connection to local database
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bibliotecapp", "root", "password");
        } catch (SQLException e) {
        	throw new BDException("No se pudo connectar a la base de datos", e);
		} catch (ClassNotFoundException e) {
			throw new BDException("No se pudo obtener el driver JDBC", e);
		}
		
		return conn;
	}
	
	public void disconnectDatabase() throws BDException
	{
		try {
			conn.close();
		} catch (SQLException e) {
			throw new BDException("No se pudo disconnectar de la base de datos", e);
		}
	}

}
