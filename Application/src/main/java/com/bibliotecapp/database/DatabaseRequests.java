package com.bibliotecapp.database;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bibliotecapp.entities.Tema;

public class DatabaseRequests {
	
	public ArrayList<Tema> obtenerTodosTemas() throws BDException{
		
		ArrayList<Tema> listTema = new ArrayList<>();
		DatabaseManager databaseManager = new DatabaseManager();
		
		Connection conn = databaseManager.connectDatabase();

		try {
			Statement stmt = conn.createStatement();
			String sql = "SELECT * FROM Tema";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				listTema.add(new Tema(
						rs.getInt("Te_id"),
						rs.getString("Te_mensaje")));
			}
		}
		catch (SQLException e) {
			throw new BDException("No se pudo obtener los temas", e);
		}
		return listTema;
	}

}
