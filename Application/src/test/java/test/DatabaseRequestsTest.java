package test;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.Test;

import com.bibliotecapp.database.BDException;
import com.bibliotecapp.database.DatabaseManager;
import com.bibliotecapp.database.DatabaseRequests;
import com.bibliotecapp.entities.Cliente;
import com.bibliotecapp.interfaces.IDatabaseRequests;

public class DatabaseRequestsTest {

	@Test
	public void testObtenerTodosClientes() throws BDException {
		IDatabaseRequests db = new DatabaseRequests();
		db.obtenerTodosClientes(true);
	}
	
	@Test
	public void testAnadirCliente() throws BDException {
		IDatabaseRequests db = new DatabaseRequests();
		db.anadirCliente(new Cliente(1, "Thomas", "SINAN", 0606060606, "18 rue du Calvaire, 49000, Angers", "sinan.thomas@opendeusto.es", "01-12-2019", "20-01-2020", false));
	}
	
	@Test
	public void testModificarCliente() throws BDException {
		IDatabaseRequests db = new DatabaseRequests();
		db.modificarCliente(new Cliente(1, "Thomas", "SINAN", 0606060606, "18 rue du Calvaire, 49000, Angers", "sinan.thomas@opendeusto.es", "01-12-2019", "20-01-2020", false));
	}
	
	@Test
	public void testArchivarCliente() throws BDException {
		IDatabaseRequests db = new DatabaseRequests();
		db.archivarCliente(1);
	}
	
	@Test
	public void testConnectarBaseDeDatos() throws BDException {
		
		DatabaseManager db = new DatabaseManager();
		
		Connection conn = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bibliotecapp", "root", "password");
			assertEquals(conn.getSchema(), db.connectDatabase().getSchema());
	    } catch (SQLException e) {
	    	throw new BDException("No se pudo connectar a la base de datos", e);
		} catch (ClassNotFoundException e) {
			throw new BDException("No se pudo obtener el driver JDBC", e);
		}
	
	}

}
