package com.bibliotecapp.database;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bibliotecapp.entities.Articulo;
import com.bibliotecapp.entities.Tema;
import com.bibliotecapp.entities.Tipo;

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
	
public ArrayList<Articulo> obtenerTodosArticulosPorTemas(int idTema) throws BDException{
		
		ArrayList<Articulo> listArticulo = new ArrayList<>();
		DatabaseManager databaseManager = new DatabaseManager();
		
		Connection conn = databaseManager.connectDatabase();

		try {
			String sql = "SELECT * FROM Articulo WHERE fk_tema=?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, idTema);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				listArticulo.add(new Articulo(
						rs.getInt("Ar_id"),
						rs.getString("Ar_titulo"),
						rs.getString("Ar_autor"),
						rs.getString("Ar_identificador"),
						rs.getBoolean("Ar_estado"),
						obtenerTipoPorId(rs.getInt("fk_tipo")),
						obtenerTemaPorId(rs.getInt("fk_tema"))
						));
			}
		}
		catch (SQLException e) {
			throw new BDException("No se pudo obtener los articulos", e);
		}
		return listArticulo;
	}

public Tema obtenerTemaPorId(int idTema) throws BDException{
	
	Tema tema = new Tema();
	DatabaseManager databaseManager = new DatabaseManager();
	
	Connection conn = databaseManager.connectDatabase();

	try {
		String sql = "SELECT * FROM Tema WHERE Te_id = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, idTema);
		ResultSet rs = stmt.executeQuery();
		if(rs.next()) {
			tema = new Tema(
					rs.getInt("Te_id"),
					rs.getString("Te_mensaje")
					);
		}
	}
	catch (SQLException e) {
		throw new BDException("No se pudo obtener el tema", e);
	}
	return tema;
}

public Tipo obtenerTipoPorId(int idTipo) throws BDException{
	
	Tipo tipo = new Tipo();
	DatabaseManager databaseManager = new DatabaseManager();
	
	Connection conn = databaseManager.connectDatabase();

	try {
		String sql = "SELECT * FROM Tipo WHERE Ti_id = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, idTipo);
		ResultSet rs = stmt.executeQuery();
		if(rs.next()) {
			tipo = new Tipo(
					rs.getInt("Ti_id"),
					rs.getString("Ti_mensaje")
					);
		}
	}
	catch (SQLException e) {
		throw new BDException("No se pudo obtener el tipo", e);
	}
	return tipo;
}

}
