package test;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.bibliotecapp.database.BDException;
import com.bibliotecapp.database.DatabaseManager;
import com.bibliotecapp.database.DatabaseRequests;
import com.bibliotecapp.entities.Articulo;
import com.bibliotecapp.entities.ArticuloToCliente;
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
	
	@Test
	public void testContarRebasarFechaDevolucion() throws BDException {
		Cliente c1 = new Cliente();
		Cliente c2 = new Cliente();
		
		Articulo a1 = new Articulo();
		Articulo a2 = new Articulo();
		
		List<ArticuloToCliente> atcList = new ArrayList<ArticuloToCliente>();
		
		ArticuloToCliente atc1 = new ArticuloToCliente(1, "10-10-2019", "11-10-2019", null,
				c1, a1, false);
		ArticuloToCliente atc2 = new ArticuloToCliente(2, "10-10-2019", "11-10-2019", null,
				c1, a1, false);
		
		atcList.add(atc1);
		atcList.add(atc2);
		
		int contar = 0;
		
		try {
		
		Date fechaDevolucionPlanificada;
			
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		Date fechaHoy = new Date();
		
		for(ArticuloToCliente unAtc : atcList) {
				fechaDevolucionPlanificada = formatter.parse(unAtc.getFechaPanificadaDevolucion());
				if((fechaDevolucionPlanificada.compareTo(fechaHoy) < 0) && (unAtc.getFechaRealDevolucion() == null)) {
					contar++;
				}	
			}
		
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		IDatabaseRequests db = new DatabaseRequests();
		int contarFunction = db.contarRebasarFechaDevolucion(atcList);
		
		assertEquals(contar, contarFunction);
		
		
	}

}
