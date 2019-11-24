package com.bibliotecapp.database;

import java.sql.Connection;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.bibliotecapp.entities.Articulo;
import com.bibliotecapp.entities.ArticuloToCliente;
import com.bibliotecapp.entities.CD;
import com.bibliotecapp.entities.Cliente;
import com.bibliotecapp.entities.DVD;
import com.bibliotecapp.entities.Libro;
import com.bibliotecapp.entities.Tema;
import com.bibliotecapp.entities.Tipo;
import com.bibliotecapp.entities.VideoJuego;
import com.bibliotecapp.interfaces.IDatabaseRequests;

public class DatabaseRequests implements IDatabaseRequests{
	
	private Connection conn;
	
	public DatabaseRequests() throws BDException{

		DatabaseManager databaseManager = new DatabaseManager();
		conn = databaseManager.connectDatabase();
	}
	
	// Solicitud para obtener todos los objectos de un tipo definido
	
	public List<Tema> obtenerTodosTemas() throws BDException{

		
		List<Tema> listTema = new ArrayList<Tema>();

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
	
	public List<Tipo> obtenerTodosTipos() throws BDException{

		List<Tipo> listTipo = new ArrayList<Tipo>();

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
	
	public List<Articulo> obtenerTodosArticulos(boolean archivo) throws BDException{
		
		List<Articulo> listArticulo = new ArrayList<Articulo>();

		try {
			String sql = "SELECT * FROM Articulo WHERE Ar_archivo = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setBoolean(1, archivo);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				listArticulo.add(new Articulo(
						rs.getInt("Ar_id"),
						rs.getString("Ar_titulo"),
						rs.getString("Ar_autor"),
						rs.getString("Ar_identificador"),
						rs.getBoolean("Ar_estado"),
						obtenerTipoPorId(rs.getInt("fk_tipo")),
						obtenerTemaPorId(rs.getInt("fk_tema")),
						rs.getBoolean("Ar_archivo")
						));
			}
		}
		catch (SQLException e) {
			throw new BDException("No se pudo obtener los articulos", e);
		}
		return listArticulo;
	}

	public List<VideoJuego> obtenerTodosVideoJuegos(boolean archivo) throws BDException{
		
		List<VideoJuego> listVideoJuego = new ArrayList<VideoJuego>();

		try {
			String sql = "SELECT * FROM Articulo, Tipo WHERE Articulo.fk_tipo = Tipo.Ti_id AND Ti_mensaje = ? AND Ar_archivo = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, "Video juego");
			stmt.setBoolean(2, archivo);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				listVideoJuego.add(new VideoJuego(
						rs.getInt("Ar_id"),
						rs.getString("Ar_titulo"),
						rs.getString("Ar_autor"),
						rs.getString("Ar_identificador"),
						rs.getBoolean("Ar_estado"),
						rs.getString("Ar_plataforma"),
						obtenerTipoPorId(rs.getInt("fk_tipo")),
						obtenerTemaPorId(rs.getInt("fk_tema")),
						rs.getBoolean("Ar_archivo")
						));
			}
		}
		catch (SQLException e) {
			throw new BDException("No se pudo obtener los video juegos", e);
		}
		return listVideoJuego;
	}

	public List<Libro> obtenerTodosLibros(boolean archivo) throws BDException{
	
	List<Libro> listLibro = new ArrayList<Libro>();

	try {
		String sql = "SELECT * FROM Articulo, Tipo WHERE Articulo.fk_tipo = Tipo.Ti_id AND Ti_mensaje =? AND Ar_archivo = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, "Libro");
		stmt.setBoolean(2, archivo);
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			listLibro.add(new Libro(
					rs.getInt("Ar_id"),
					rs.getString("Ar_titulo"),
					rs.getString("Ar_autor"),
					rs.getString("Ar_identificador"),
					rs.getBoolean("Ar_estado"),
					rs.getInt("Ar_numeroPaginas"),
					obtenerTipoPorId(rs.getInt("fk_tipo")),
					obtenerTemaPorId(rs.getInt("fk_tema")),
					rs.getBoolean("Ar_archivo")
					));
		}
	}
	catch (SQLException e) {
		throw new BDException("No se pudo obtener los libros", e);
	}
	return listLibro;
}

	public List<DVD> obtenerTodosDVDs(boolean archivo) throws BDException{
	
	List<DVD> listDVD = new ArrayList<DVD>();

	try {
		String sql = "SELECT * FROM Articulo, Tipo WHERE Articulo.fk_tipo = Tipo.Ti_id AND Ti_mensaje =? AND Ar_archivo = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, "DVD");
		stmt.setBoolean(2, archivo);
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			listDVD.add(new DVD(
					rs.getInt("Ar_id"),
					rs.getString("Ar_titulo"),
					rs.getString("Ar_autor"),
					rs.getString("Ar_identificador"),
					rs.getBoolean("Ar_estado"),
					rs.getString("Ar_qualidad"),
					obtenerTipoPorId(rs.getInt("fk_tipo")),
					obtenerTemaPorId(rs.getInt("fk_tema")),
					rs.getBoolean("Ar_archivo")
					));
		}
	}
	catch (SQLException e) {
		throw new BDException("No se pudo obtener los articulos", e);
	}
	return listDVD;
}

	public List<CD> obtenerTodosCDs(boolean archivo) throws BDException{
	
	List<CD> listCD = new ArrayList<CD>();

	try {		
		String sql = "SELECT * FROM Articulo, Tipo WHERE Articulo.fk_tipo = Tipo.Ti_id AND Ti_mensaje =? AND Ar_archivo = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, "CD");
		stmt.setBoolean(2, archivo);
		ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				listCD.add(new CD(
					rs.getInt("Ar_id"),
					rs.getString("Ar_titulo"),
					rs.getString("Ar_autor"),
					rs.getString("Ar_identificador"),
					rs.getBoolean("Ar_estado"),
					rs.getInt("Ar_numeroPistas"),
					obtenerTipoPorId(rs.getInt("fk_tipo")),
					obtenerTemaPorId(rs.getInt("fk_tema")),
					rs.getBoolean("Ar_archivo")
					));
		}
	}
	catch (SQLException e) {
		throw new BDException("No se pudo obtener los articulos", e);
	}
	return listCD;
}

	public List<Cliente> obtenerTodosClientes(boolean archivo) throws BDException{
		
		List<Cliente> listCliente = new ArrayList<Cliente>();

		try {
			String sql = "SELECT * FROM Cliente WHERE Cl_archivo = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setBoolean(1, archivo);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				listCliente.add(new Cliente(
						rs.getInt("Cl_id"),
						rs.getString("Cl_nombre"),
						rs.getString("Cl_apellido"),
						rs.getInt("Cl_telefono"),
						rs.getString("Cl_direccion"),
						rs.getString("Cl_email"),
						rs.getString("Cl_inicioSuscripcion"),
						rs.getString("Cl_finSuscripcion"),
						rs.getBoolean("Cl_archivo")
						));
			}
		}
		catch (SQLException e) {
			throw new BDException("No se pudo obtener los articulos", e);
		}
		return listCliente;
	}
	
	public List<ArticuloToCliente> obtenerTodosArticulosToClientes(boolean archivo) throws BDException{
		
		List<ArticuloToCliente> listArticuloToCliente = new ArrayList<ArticuloToCliente>();

		try {
			String sql = "SELECT * FROM ArticuloToCliente WHERE At_archivo = ? ORDER BY At_id DESC";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setBoolean(1, archivo);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				listArticuloToCliente.add(new ArticuloToCliente(
						rs.getInt("At_id"),
						rs.getString("At_fechaPrestamo"),
						rs.getString("At_fechaPlanificadaDevolucion"),
						rs.getString("At_fechaRealDevolucion"),
						obtenerClientePorId(rs.getInt("fk_cliente")),
						obtenerArticuloPorId(rs.getInt("fk_articulo")),
						rs.getBoolean("At_archivo")
						));
			}
		}
		catch (SQLException e) {
			throw new BDException("No se pudo obtener los articulos", e);
		}
		return listArticuloToCliente;
	}
	
	// Solicitud para obtener un objecto de tipo Articulo segun un Tema o un Tipo
	
	public List<Articulo> obtenerTodosArticulosPorTema(int idTema, boolean archivo) throws BDException{
		
		List<Articulo> listArticulo = new ArrayList<Articulo>();

		try {
			String sql = "SELECT * FROM Articulo WHERE fk_tema=? AND Ar_archivo = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, idTema);
			stmt.setBoolean(2, archivo);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				listArticulo.add(new Articulo(
						rs.getInt("Ar_id"),
						rs.getString("Ar_titulo"),
						rs.getString("Ar_autor"),
						rs.getString("Ar_identificador"),
						rs.getBoolean("Ar_estado"),
						obtenerTipoPorId(rs.getInt("fk_tipo")),
						obtenerTemaPorId(rs.getInt("fk_tema")),
						rs.getBoolean("Ar_archivo")
						));
			}
		}
		catch (SQLException e) {
			throw new BDException("No se pudo obtener los articulos según el tema", e);
		}
		return listArticulo;
	}

	public List<Articulo> obtenerTodosArticulosPorTipo(int idTipo, boolean archivo) throws BDException{
		
		List<Articulo> listArticulo = new ArrayList<Articulo>();

		try {
			String sql = "SELECT * FROM Articulo WHERE fk_tipo=? AND Ar_archivo = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, idTipo);
			stmt.setBoolean(2, archivo);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				listArticulo.add(new Articulo(
						rs.getInt("Ar_id"),
						rs.getString("Ar_titulo"),
						rs.getString("Ar_autor"),
						rs.getString("Ar_identificador"),
						rs.getBoolean("Ar_estado"),
						obtenerTipoPorId(rs.getInt("fk_tipo")),
						obtenerTemaPorId(rs.getInt("fk_tema")),
						rs.getBoolean("Ar_archivo")
						));
			}
		}
		catch (SQLException e) {
			throw new BDException("No se pudo obtener los articulos según el tipo", e);
		}
		return listArticulo;
	}

	public List<Articulo> obtenerTodosArticulosPorTemaYTipo(int idTema, int idTipo, boolean archivo) throws BDException{
		
		List<Articulo> listArticulo = new ArrayList<Articulo>();

		try {
			String sql = "SELECT * FROM Articulo WHERE fk_tema=? AND fk_tipo=? AND Ar_archivo = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, idTema);
			stmt.setInt(2, idTipo);
			stmt.setBoolean(3, archivo);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				listArticulo.add(new Articulo(
						rs.getInt("Ar_id"),
						rs.getString("Ar_titulo"),
						rs.getString("Ar_autor"),
						rs.getString("Ar_identificador"),
						rs.getBoolean("Ar_estado"),
						obtenerTipoPorId(rs.getInt("fk_tipo")),
						obtenerTemaPorId(rs.getInt("fk_tema")),
						rs.getBoolean("Ar_archivo")
						));
			}
		}
		catch (SQLException e) {
			throw new BDException("No se pudo obtener los articulos según el tipo y el tema", e);
		}
		return listArticulo;
	}

	public List<Articulo> obtenerTodosArticulosPorEstado(boolean estado, boolean archivo) throws BDException{
		
		List<Articulo> listArticulos = new ArrayList<Articulo>();

		try {
			String sql = "SELECT * FROM Articulo WHERE Ar_estado = ? AND Ar_archivo = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setBoolean(1, estado);
			stmt.setBoolean(2, archivo);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				listArticulos.add(new Articulo(
						rs.getInt("Ar_id"),
						rs.getString("Ar_titulo"),
						rs.getString("Ar_autor"),
						rs.getString("Ar_identificador"),
						rs.getBoolean("Ar_estado"),
						obtenerTipoPorId(rs.getInt("fk_tipo")),
						obtenerTemaPorId(rs.getInt("fk_tema")),
						rs.getBoolean("Ar_archivo")
						));
			}
		}
		catch (SQLException e) {
			throw new BDException("No se pudo obtener los articulosToClientes según el estado", e);
		}
		return listArticulos;
	}
	
	// Solicitud para obtener un objecto de tipo ArticuloToClientes segun un Cliente o un Articulo
	
	public List<ArticuloToCliente> obtenerTodosArticulosToClientePorCliente(int idCliente, boolean archivo) throws BDException{
		
		List<ArticuloToCliente> listArticuloToCliente = new ArrayList<ArticuloToCliente>();

		try {
			String sql = "SELECT * FROM ArticuloToCliente WHERE fk_cliente=? AND At_archivo = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, idCliente);
			stmt.setBoolean(2, archivo);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				listArticuloToCliente.add(new ArticuloToCliente(
						rs.getInt("At_id"),
						rs.getString("At_fechaPrestamo"),
						rs.getString("At_fechaPlanificadaDevolucion"),
						rs.getString("At_fechaRealDevolucion"),
						obtenerClientePorId(rs.getInt("fk_cliente")),
						obtenerArticuloPorId(rs.getInt("fk_articulo")),
						rs.getBoolean("At_archivo")
						));
			}
		}
		catch (SQLException e) {
			throw new BDException("No se pudo obtener los articulosToClientes según el cliente", e);
		}
		return listArticuloToCliente;
	}
	
	public List<ArticuloToCliente> obtenerTodosArticulosToClientePorArticulo(int idArticulo, boolean archivo) throws BDException{
		
		List<ArticuloToCliente> listArticuloToCliente = new ArrayList<ArticuloToCliente>();

		try {
			String sql = "SELECT * FROM ArticuloToCliente WHERE fk_articulo=? AND Ar_archivo = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, idArticulo);
			stmt.setBoolean(2, archivo);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				listArticuloToCliente.add(new ArticuloToCliente(
						rs.getInt("At_id"),
						rs.getString("At_fechaPrestamo"),
						rs.getString("At_fechaPlanificadaDevolucion"),
						rs.getString("At_fechaRealDevolucion"),
						obtenerClientePorId(rs.getInt("fk_cliente")),
						obtenerArticuloPorId(rs.getInt("fk_articulo")),
						rs.getBoolean("At_archivo")
						));
			}
		}
		catch (SQLException e) {
			throw new BDException("No se pudo obtener los articulosToClientes según el articulo", e);
		}
		return listArticuloToCliente;
	}
	
	public List<ArticuloToCliente> obtenerTodosArticulosToClientePorArticuloYCliente(int idArticulo, int idCliente, boolean archivo) throws BDException{
		
		List<ArticuloToCliente> listArticuloToCliente = new ArrayList<ArticuloToCliente>();

		try {
			String sql = "SELECT * FROM ArticuloToCliente WHERE fk_articulo=? AND fk_cliente=? AND Ar_archivo = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, idArticulo);
			stmt.setInt(2, idCliente);
			stmt.setBoolean(3, archivo);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				listArticuloToCliente.add(new ArticuloToCliente(
						rs.getInt("At_id"),
						rs.getString("At_fechaPrestamo"),
						rs.getString("At_fechaPlanificadaDevolucion"),
						rs.getString("At_fechaRealDevolucion"),
						obtenerClientePorId(rs.getInt("fk_cliente")),
						obtenerArticuloPorId(rs.getInt("fk_articulo")),
						rs.getBoolean("At_archivo")
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
						obtenerTemaPorId(rs.getInt("fk_tema")),
						rs.getBoolean("Ar_archivo")
						));
			}
		}
		catch (SQLException e) {
			throw new BDException("No se pudo obtener el articulo", e);
		}
		return articulo;
	}
	
	public VideoJuego obtenerVideoJuegoPorId(int idVideoJuego) throws BDException{
		
		VideoJuego videoJuego = new VideoJuego();
	
		try {
			String sql = "SELECT * FROM Articulo WHERE Ar_id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, idVideoJuego);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				videoJuego = (new VideoJuego(
						rs.getInt("Ar_id"),
						rs.getString("Ar_titulo"),
						rs.getString("Ar_autor"),
						rs.getString("Ar_identificador"),
						rs.getBoolean("Ar_estado"),
						rs.getString("Ar_plataforma"),
						obtenerTipoPorId(rs.getInt("fk_tipo")),
						obtenerTemaPorId(rs.getInt("fk_tema")),
						rs.getBoolean("Ar_archivo")
						));
			}
		}
		catch (SQLException e) {
			throw new BDException("No se pudo obtener el articulo", e);
		}
		return videoJuego;
	}
	
	public Libro obtenerLibroPorId(int idLibro) throws BDException{
	
		Libro libro = new Libro();

	try {
		String sql = "SELECT * FROM Articulo WHERE Ar_id = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, idLibro);
		ResultSet rs = stmt.executeQuery();
		if(rs.next()) {
			libro = (new Libro(
					rs.getInt("Ar_id"),
					rs.getString("Ar_titulo"),
					rs.getString("Ar_autor"),
					rs.getString("Ar_identificador"),
					rs.getBoolean("Ar_estado"),
					rs.getInt("Ar_numeroPaginas"),
					obtenerTipoPorId(rs.getInt("fk_tipo")),
					obtenerTemaPorId(rs.getInt("fk_tema")),
					rs.getBoolean("Ar_archivo")
					));
		}
	}
	catch (SQLException e) {
		throw new BDException("No se pudo obtener el articulo", e);
	}
	return libro;
}

	public DVD obtenerDVDPorId(int idDVD) throws BDException{
	
	DVD dvd = new DVD();

	try {
		String sql = "SELECT * FROM Articulo WHERE Ar_id = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, idDVD);
		ResultSet rs = stmt.executeQuery();
		if(rs.next()) {
			dvd = (new DVD(
					rs.getInt("Ar_id"),
					rs.getString("Ar_titulo"),
					rs.getString("Ar_autor"),
					rs.getString("Ar_identificador"),
					rs.getBoolean("Ar_estado"),
					rs.getString("Ar_qualidad"),
					obtenerTipoPorId(rs.getInt("fk_tipo")),
					obtenerTemaPorId(rs.getInt("fk_tema")),
					rs.getBoolean("Ar_archivo")
					));
		}
	}
	catch (SQLException e) {
		throw new BDException("No se pudo obtener el articulo", e);
	}
	return dvd;
}

	public CD obtenerCDPorId(int idCD) throws BDException{
	
	CD cd = new CD();

	try {
		String sql = "SELECT * FROM Articulo WHERE Ar_id = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, idCD);
		ResultSet rs = stmt.executeQuery();
		if(rs.next()) {
			cd = (new CD(
					rs.getInt("Ar_id"),
					rs.getString("Ar_titulo"),
					rs.getString("Ar_autor"),
					rs.getString("Ar_identificador"),
					rs.getBoolean("Ar_estado"),
					rs.getInt("Ar_numeroPistas"),
					obtenerTipoPorId(rs.getInt("fk_tipo")),
					obtenerTemaPorId(rs.getInt("fk_tema")),
					rs.getBoolean("Ar_archivo")
					));
		}
	}
	catch (SQLException e) {
		throw new BDException("No se pudo obtener el articulo", e);
	}
	return cd;
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
						rs.getString("Cl_finSuscripcion"),
						rs.getBoolean("Cl_archivo")
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
			String sql = "SELECT * FROM ArticuloToCliente WHERE At_id = ?";
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
						obtenerArticuloPorId(rs.getInt("fk_articulo")),
						rs.getBoolean("At_archivo")
						));
			}
		}
		catch (SQLException e) {
			throw new BDException("No se pudo obtener el articulo con el cliente", e);
		}
		return articuloToCliente;
	}
	
	// Methodos por la gestion de clientes
	
	public void anadirCliente(Cliente cliente) throws BDException{
		
		try {
			String sql = "INSERT INTO Cliente(Cl_nombre, Cl_apellido, Cl_telefono, Cl_direccion, Cl_email, Cl_inicioSuscripcion, Cl_finSuscripcion, Cl_archivo) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, cliente.getNombre());
			stmt.setString(2, cliente.getApellido());
			stmt.setInt(3, cliente.getTelefono());
			stmt.setString(4, cliente.getDireccion());
			stmt.setString(5, cliente.getEmail());
			stmt.setString(6, cliente.getInicioSuscripcion());
			stmt.setString(7, cliente.getFinSuscripcion());
			stmt.setBoolean(8, cliente.isArchivo());
			
			stmt.executeUpdate();
		}
		catch (SQLException e) {
			throw new BDException("No se pudo anadir el cliente en la DB", e);
		}
		
	}
	
	public void modificarCliente(Cliente cliente) throws BDException{
		
		try {
			String sql = "UPDATE Cliente SET Cl_nombre = ?, Cl_apellido = ?, Cl_telefono = ?, Cl_direccion = ?, Cl_email = ?, Cl_inicioSuscripcion = ?, Cl_finSuscripcion = ?, Cl_archivo = ? WHERE Cl_id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, cliente.getNombre());
			stmt.setString(2, cliente.getApellido());
			stmt.setInt(3, cliente.getTelefono());
			stmt.setString(4, cliente.getDireccion());
			stmt.setString(5, cliente.getEmail());
			stmt.setString(6, cliente.getInicioSuscripcion());
			stmt.setString(7, cliente.getFinSuscripcion());
			stmt.setBoolean(8, cliente.isArchivo());
			stmt.setInt(9, cliente.getId());
			
			stmt.executeUpdate();
		}
		catch (SQLException e) {
			throw new BDException("No se pudo modificar el cliente en la DB", e);
		}
		
	}
	
	public void archivarCliente(int idCliente) throws BDException{
			
		try {
			
			String sql = "UPDATE Cliente SET Cl_archivo = ? WHERE Cl_id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setBoolean(1, true);
			stmt.setInt(2, idCliente);
			stmt.executeUpdate();
		}
		catch (SQLException e) {				
			throw new BDException("No se pudo modificar el cliente en la DB", e);
		}
			
	}
	
	// Methodos por la gestion de DVD
	
	public void archivarDvd(int idDVD) throws BDException{
		
		try {
			String sql = "UPDATE Articulo SET Ar_archivo = ? WHERE Ar_id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setBoolean(1, true);
			stmt.setInt(2, idDVD);
			stmt.executeUpdate();
			
			stmt.executeUpdate();
			
		} 
		catch (SQLException e) {
			throw new BDException("No se pudo modificar el articulo en la DB", e);
		}
		
	}
	
	public void modificarDvd(DVD dvd) throws BDException{
		
		try {
			String sql = "UPDATE Articulo SET Ar_titulo = ?, Ar_autor = ?, Ar_identificador = ?, fk_tema = ?, Ar_archivo = ?, Ar_qualidad = ? WHERE Ar_id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, dvd.getTitulo());
			stmt.setString(2, dvd.getAutor());
			stmt.setString(3, dvd.getIdentificador());
			stmt.setInt(4, dvd.getUnTema().getId());
			stmt.setBoolean(5, dvd.isArchivo());
			stmt.setString(6, dvd.getQualidad());
			stmt.setInt(7, dvd.getId());
			
			stmt.executeUpdate();
		}
		catch (SQLException e) {
			throw new BDException("No se pudo modificar el cliente en la DB", e);
		}
		
	}
	
	// Methodos por la gestion de CD
	
	
	public void archivarCd(int idCD) throws BDException{
		
		try {
			String sql = "UPDATE Articulo SET Ar_archivo = ? WHERE Ar_id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setBoolean(1, true);
			stmt.setInt(2, idCD);
			stmt.executeUpdate();
		} 
		catch (SQLException e) {
			throw new BDException("No se pudo modificar el CD en la DB", e);
		}
		
	}
	
	
	public void modificarCd(CD cd) throws BDException{
		
		try {
			String sql = "UPDATE Articulo SET Ar_titulo = ?, Ar_autor = ?, Ar_identificador = ?, fk_tema = ?, Ar_archivo = ?, Ar_numeroPistas = ? WHERE Ar_id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, cd.getTitulo());
			stmt.setString(2, cd.getAutor());
			stmt.setString(3, cd.getIdentificador());
			stmt.setInt(4, cd.getUnTema().getId());
			stmt.setBoolean(5, cd.isArchivo());
			stmt.setInt(6, cd.getNumeroPistas());
			stmt.setInt(7, cd.getId());
			
			stmt.executeUpdate();
		}
		catch (SQLException e) {
			throw new BDException("No se pudo modificar el CD en la DB", e);
		}
		
	}

	// Methodos por la prestacion de articulos
	
	
	public void anadirPrestacion(ArticuloToCliente atc) throws BDException{
		
		try {
			String sqlAnadir = "INSERT INTO ArticuloToCliente(At_fechaPrestamo, At_fechaPlanificadaDevolucion, fk_cliente, fk_articulo, At_archivo) VALUES(?, ?, ?, ?, ?)";
			PreparedStatement stmtAnadir = conn.prepareStatement(sqlAnadir);
			stmtAnadir.setString(1, atc.getFechaPrestamo());
			stmtAnadir.setString(2, atc.getFechaPanificadaDevolucion());
			stmtAnadir.setInt(3, atc.getUnCliente().getId());
			stmtAnadir.setInt(4, atc.getUnArticulo().getId());
			stmtAnadir.setBoolean(5, atc.isArchivo());
			
			String sqlModificar = "UPDATE Articulo SET Ar_estado = false WHERE Ar_id = ?";
			PreparedStatement stmtModificar = conn.prepareStatement(sqlModificar);
			stmtModificar.setInt(1, atc.getUnArticulo().getId());
			
			stmtAnadir.executeUpdate();
			stmtModificar.executeUpdate();
		}
		catch (SQLException e) {
			throw new BDException("No se pudo gardar la prestacion en la DB", e);
		}
		
	}
	
	
	public void modificarPrestacion(ArticuloToCliente atc) throws BDException{
		
		try {
			
			System.out.println(atc.getFechaRealDevolucion());
			
			
			if(atc.getFechaRealDevolucion() != "") {
				atc.getUnArticulo().setEstado(true);
			}
			else {
				atc.getUnArticulo().setEstado(false);
			}
			
			String sqlAnadir = "UPDATE ArticuloToCliente SET At_fechaPrestamo = ?, At_fechaPlanificadaDevolucion = ?, At_fechaRealDevolucion = ?, At_archivo = ? WHERE At_id = ?";
			PreparedStatement stmtAnadir = conn.prepareStatement(sqlAnadir);
			stmtAnadir.setString(1, atc.getFechaPrestamo());
			stmtAnadir.setString(2, atc.getFechaPanificadaDevolucion());
			stmtAnadir.setString(3, atc.getFechaRealDevolucion());
			stmtAnadir.setBoolean(4, atc.isArchivo());
			stmtAnadir.setInt(5, atc.getId());
			
			String sqlModificar = "UPDATE Articulo SET Ar_estado = ? WHERE Ar_id = ?";
			PreparedStatement stmtModificar = conn.prepareStatement(sqlModificar);
			stmtModificar.setBoolean(1, atc.getUnArticulo().isEstado());
			stmtModificar.setInt(2, atc.getUnArticulo().getId());
			
			stmtAnadir.executeUpdate();
			stmtModificar.executeUpdate();
		}
		catch (SQLException e) {
			throw new BDException("No se pudo gardar la prestacion en la DB", e);
		}
		
	}
	
	public void archivarPrestacion(int atcId) throws BDException{
		
		try {
			String sql = "UPDATE ArticuloToCliente SET At_archivo = ? WHERE At_id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setBoolean(1, true);
			stmt.setInt(2, atcId);
			stmt.executeUpdate();
		} 
		catch (SQLException e) {
			throw new BDException("No se pudo archivar la prestacion en la DB", e);
		}
		
	}
	
	public boolean checkValidezCuento(ArticuloToCliente atc) throws BDException{
	
		boolean validezCuento = true;
		
		try {
		IDatabaseRequests dbr = new DatabaseRequests();
		Cliente cliente = dbr.obtenerClientePorId(atc.getUnCliente().getId());
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		Date dateFinSuscripcion = formatter.parse(cliente.getFinSuscripcion());
		
		Date dateDevolucionPlanificada = formatter.parse(atc.getFechaPanificadaDevolucion());
		
		
			if (dateFinSuscripcion.compareTo(dateDevolucionPlanificada) >= 0) {
				validezCuento = true;
	        }
			else {
				validezCuento = false;
			}
		}
		catch(ParseException e) {
			throw new BDException("No se pudo convertir la fecha", e);
		}
		
		
		return validezCuento;
		
	}

	
}