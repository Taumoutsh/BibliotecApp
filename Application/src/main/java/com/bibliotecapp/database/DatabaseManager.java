package com.bibliotecapp.database;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseManager {
	
	Connection conn = null;
	
	public Connection connectDatabase() throws BDException
	{
		
		try {
			//Selection of the driver
			Class.forName("com.mysql.jdbc.Driver");
			//Connection to local database
			
			InputStream input;
			input = getClass().getClassLoader().getResourceAsStream("properties.properties");;
			Properties prop = new Properties();
			prop.load(input);
			
			conn = DriverManager.getConnection(prop.getProperty("db.url"), prop.getProperty("db.user"), prop.getProperty("db.password"));
        } catch (SQLException e) {
        	throw new BDException("No se pudo connectar a la base de datos", e);
		} catch (ClassNotFoundException e) {
			throw new BDException("No se pudo obtener el driver JDBC", e);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			throw new BDException("No se pudo encontrar el fichero de properties", e);
		} catch (IOException e) {
				// TODO Auto-generated catch block
			throw new BDException("No se pudo cargar los properties", e);
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
