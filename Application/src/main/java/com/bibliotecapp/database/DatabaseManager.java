package com.bibliotecapp.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {
	
	Connection conn = null;
	
	public Connection connectDatabase() throws BDException
	{
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bibliotecapp", "root", "password");
        } catch (SQLException e) {
			// TODO Auto-generated catch block
        	throw new BDException("No se pudo connectar a la base de datos", e);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			throw new BDException("No se pudo obtener el driver JDBC", e);
		}
		
		return conn;
	}
	
	public void disconnectDatabase() throws BDException
	{
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new BDException("No se pudo disconnectar de la base de datos", e);
		}
	}

}
