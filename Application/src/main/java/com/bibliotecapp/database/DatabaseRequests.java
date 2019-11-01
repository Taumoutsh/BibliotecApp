package com.bibliotecapp.database;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bibliotecapp.entities.Articulo;
import com.bibliotecapp.entities.ArticuloToCliente;
import com.bibliotecapp.entities.Cliente;
import com.bibliotecapp.entities.Tema;
import com.bibliotecapp.entities.Tipo;

public class DatabaseRequests {
	
	private Connection conn;
	
	public DatabaseRequests() throws BDException{

		DatabaseManager databaseManager = new DatabaseManager();
		conn = databaseManager.connectDatabase();
	}
	
	// Solicitud para obtener todos los objectos de un tipo definido
	
	public ArrayList<Tema> obtenerTodosTemas() throws BDException{

		
		ArrayList<Tema> listTema = new ArrayList<>();

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
	
	public ArrayList<Tipo> obtenerTodosTipos() throws BDException{

		ArrayList<Tipo> listTipo = new ArrayList<>();

		try {
			Statement stmt = conn.createStatement();
			String sql = "SELECT * FROM Tipo";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				listTipo.add(new Tipo(
						rs.getInt("Ti_id"),
						rs.getString("Ti_mensaje")));
			}
		}
		catch (SQLException e) {
			throw new BDException("No se pudo obtener los tipos", e);
		}
		return listTipo;
	}
	
	public ArrayList<Articulo> obtenerTodosArticulos() throws BDException{
		
		ArrayList<Articulo> listArticulo = new ArrayList<>();

		try {
			String sql = "SELECT * FROM Articulo";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
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

	public ArrayList<Cliente> obtenerTodosClientes() throws BDException{
		
		ArrayList<Cliente> listCliente = new ArrayList<>();

		try {
			String sql = "SELECT * FROM Cliente";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				listCliente.add(new Cliente(
						rs.getInt("Cl_id"),
						rs.getString("Cl_nombre"),
						rs.getString("Cl_apellido"),
						rs.getInt("Cl_telefono"),
						rs.getString("Cl_direccion"),
						rs.getString("Cl_email"),
						rs.getString("Cl_inicioSuscripcion"),
						rs.getString("Cl_finSuscripcion")
						));
			}
		}
		catch (SQLException e) {
			throw new BDException("No se pudo obtener los articulos", e);
		}
		return listCliente;
	}
	
	public ArrayList<ArticuloToCliente> obtenerTodosArticulosToClientes() throws BDException{
		
		ArrayList<ArticuloToCliente> listArticuloToCliente = new ArrayList<>();

		try {
			String sql = "SELECT * FROM ArticuloToCliente";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				listArticuloToCliente.add(new ArticuloToCliente(
						rs.getInt("At_id"),
						rs.getString("At_fechaPrestamo"),
						rs.getString("At_fechaPlanificadaDevolucion"),
						rs.getString("At_fechaRealDevolucion"),
						obtenerClientePorId(rs.getInt("fk_cliente")),
						obtenerArticuloPorId(rs.getInt("fk_articulo"))
						));
			}
		}
		catch (SQLException e) {
			throw new BDException("No se pudo obtener los articulos", e);
		}
		return listArticuloToCliente;
	}
	
	// Solicitud para obtener un objecto de tipo Articulo segun un Tema o un Tipo
	
	public ArrayList<Articulo> obtenerTodosArticulosPorTema(int idTema) throws BDException{
		
		ArrayList<Articulo> listArticulo = new ArrayList<>();

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
			throw new BDException("No se pudo obtener los articulos según el tema", e);
		}
		return listArticulo;
	}

	public ArrayList<Articulo> obtenerTodosArticulosPorTipo(int idTipo) throws BDException{
		
		ArrayList<Articulo> listArticulo = new ArrayList<>();

		try {
			String sql = "SELECT * FROM Articulo WHERE fk_tipo=?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, idTipo);
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
			throw new BDException("No se pudo obtener los articulos según el tipo", e);
		}
		return listArticulo;
	}

	public ArrayList<Articulo> obtenerTodosArticulosPorTemaYTipo(int idTema, int idTipo) throws BDException{
		
		ArrayList<Articulo> listArticulo = new ArrayList<>();

		try {
			String sql = "SELECT * FROM Articulo WHERE fk_tema=? AND fk_tipo=?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, idTema);
			stmt.setInt(2, idTipo);
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
			throw new BDException("No se pudo obtener los articulos según el tipo y el tema", e);
		}
		return listArticulo;
	}

	// Solicitud para obtener un objecto de tipo ArticuloToClientes segun un Cliente o un Articulo
	
	public ArrayList<ArticuloToCliente> obtenerTodosArticulosToClientePorCliente(int idCliente) throws BDException{
		
		ArrayList<ArticuloToCliente> listArticuloToCliente = new ArrayList<>();

		try {
			String sql = "SELECT * FROM ArticuloToCliente WHERE fk_cliente=?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, idCliente);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				listArticuloToCliente.add(new ArticuloToCliente(
						rs.getInt("At_id"),
						rs.getString("At_fechaPrestamo"),
						rs.getString("At_fechaPlanificadaDevolucion"),
						rs.getString("At_fechaRealDevolucion"),
						obtenerClientePorId(rs.getInt("fk_cliente")),
						obtenerArticuloPorId(rs.getInt("fk_articulo"))
						));
			}
		}
		catch (SQLException e) {
			throw new BDException("No se pudo obtener los articulosToClientes según el cliente", e);
		}
		return listArticuloToCliente;
	}
	
	public ArrayList<ArticuloToCliente> obtenerTodosArticulosToClientePorArticulo(int idArticulo) throws BDException{
		
		ArrayList<ArticuloToCliente> listArticuloToCliente = new ArrayList<>();

		try {
			String sql = "SELECT * FROM ArticuloToCliente WHERE fk_articulo=?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, idArticulo);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				listArticuloToCliente.add(new ArticuloToCliente(
						rs.getInt("At_id"),
						rs.getString("At_fechaPrestamo"),
						rs.getString("At_fechaPlanificadaDevolucion"),
						rs.getString("At_fechaRealDevolucion"),
						obtenerClientePorId(rs.getInt("fk_cliente")),
						obtenerArticuloPorId(rs.getInt("fk_articulo"))
						));
			}
		}
		catch (SQLException e) {
			throw new BDException("No se pudo obtener los articulosToClientes según el articulo", e);
		}
		return listArticuloToCliente;
	}
	
	public ArrayList<ArticuloToCliente> obtenerTodosArticulosToClientePorArticuloYCliente(int idArticulo, int idCliente) throws BDException{
		
		ArrayList<ArticuloToCliente> listArticuloToCliente = new ArrayList<>();

		try {
			String sql = "SELECT * FROM ArticuloToCliente WHERE fk_articulo=? AND fk_cliente=?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, idArticulo);
			stmt.setInt(2, idCliente);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				listArticuloToCliente.add(new ArticuloToCliente(
						rs.getInt("At_id"),
						rs.getString("At_fechaPrestamo"),
						rs.getString("At_fechaPlanificadaDevolucion"),
						rs.getString("At_fechaRealDevolucion"),
						obtenerClientePorId(rs.getInt("fk_cliente")),
						obtenerArticuloPorId(rs.getInt("fk_articulo"))
						));
			}
		}
		catch (SQLException e) {
			throw new BDException("No se pudo obtener los articulosToClientes según el articulo y el cliente", e);
		}
		return listArticuloToCliente;
	}
	
	// Solicitud para obtener un objecto segun un ID
	
	public Tema obtenerTemaPorId(int idTema) throws BDException{
	
		Tema tema = new Tema();
	
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

	public Articulo obtenerArticuloPorId(int idArticulo) throws BDException{
		
		Articulo articulo = new Articulo();
	
		try {
			String sql = "SELECT * FROM Articulo WHERE Ar_id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, idArticulo);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				articulo = (new Articulo(
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
			throw new BDException("No se pudo obtener el articulo", e);
		}
		return articulo;
	}
	
	public Cliente obtenerClientePorId(int idCliente) throws BDException{
		
		Cliente cliente = new Cliente();
	
		try {
			String sql = "SELECT * FROM Cliente WHERE Cl_id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, idCliente);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				cliente = (new Cliente(
						rs.getInt("Cl_id"),
						rs.getString("Cl_nombre"),
						rs.getString("Cl_apellido"),
						rs.getInt("Cl_telefono"),
						rs.getString("Cl_direccion"),
						rs.getString("Cl_email"),
						rs.getString("Cl_inicioSuscripcion"),
						rs.getString("Cl_finSuscripcion")
						));
			}
		}
		catch (SQLException e) {
			throw new BDException("No se pudo obtener el cliente", e);
		}
		return cliente;
	}

	public ArticuloToCliente obtenerArticuloToClientePorId(int idArticuloToCliente) throws BDException{
		
		ArticuloToCliente articuloToCliente = new ArticuloToCliente();
	
		try {
			String sql = "SELECT * FROM Articulo WHERE At_id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, idArticuloToCliente);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				articuloToCliente = (new ArticuloToCliente(
						rs.getInt("At_id"),
						rs.getString("At_fechaPrestamo"),
						rs.getString("At_fechaPlanificadaDevolucion"),
						rs.getString("At_fechaRealDevolucion"),
						obtenerClientePorId(rs.getInt("fk_cliente")),
						obtenerArticuloPorId(rs.getInt("fk_articulo"))
						));
			}
		}
		catch (SQLException e) {
			throw new BDException("No se pudo obtener el cliente", e);
		}
		return articuloToCliente;
	}

	
}
