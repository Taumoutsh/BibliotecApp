package com.bibliotecapp.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bibliotecapp.database.BDException;
import com.bibliotecapp.database.DatabaseManager;
import com.bibliotecapp.database.DatabaseRequests;
import com.bibliotecapp.entities.Articulo;
import com.bibliotecapp.entities.ArticuloToCliente;
import com.bibliotecapp.entities.Cliente;
import com.bibliotecapp.entities.Tema;
import com.bibliotecapp.interfaces.IDatabaseRequests;
 
@Controller
@RequestMapping("clientes")
public class ClienteController {
	
	public final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	 	
	@RequestMapping("todos")
	public ModelAndView paginaClientes() throws BDException {
		
		List<Cliente> todosClientes = new ArrayList<Cliente>();
		IDatabaseRequests databaseRequests = new DatabaseRequests();
		
		boolean archivos = false;
		
		try {
			
			logger.setLevel(Level.INFO);
			logger.info("BIBLIOTEC'APP LOGGER - Recupera clientes de la base de datos");
			
			todosClientes = databaseRequests.obtenerTodosClientes(archivos);
		} catch (BDException e) {
			e.printStackTrace();
		}
		
		logger.setLevel(Level.INFO);
		logger.info("BIBLIOTEC'APP LOGGER - Mostra la vista con todos los clientes de la base de datos");
		
		ModelAndView mv = new ModelAndView("clientes/clientes");
		mv.addObject("todosClientes", todosClientes);
		mv.addObject("archivos", archivos);
		return mv;
	}
	
	@RequestMapping("todosArchivos")
	public ModelAndView paginaClientesArchivados() throws BDException {
		
		List<Cliente> todosClientes = new ArrayList<Cliente>();
		IDatabaseRequests databaseRequests = new DatabaseRequests();
		
		boolean archivos = true;
		
		try {
			
			logger.setLevel(Level.INFO);
			logger.info("BIBLIOTEC'APP LOGGER - Recupera clientes de la base de datos");
			
			todosClientes = databaseRequests.obtenerTodosClientes(archivos);
		} catch (BDException e) {
			e.printStackTrace();
		}
		
		logger.setLevel(Level.INFO);
		logger.info("BIBLIOTEC'APP LOGGER - Mostra la vista con todos los clientes de la base de datos");
		
		ModelAndView mv = new ModelAndView("clientes/clientes");
		mv.addObject("todosClientes", todosClientes);
		mv.addObject("archivos", archivos);
		return mv;
	}
	
	@RequestMapping(value = "cliente",method = RequestMethod.GET)
	public ModelAndView paginaClientePorId(@RequestParam("id") String idClienteString) throws BDException {
		int idCliente = Integer.valueOf(idClienteString);
		Cliente unCliente = new Cliente();
		IDatabaseRequests databaseRequests = new DatabaseRequests();
		boolean archivo = false;
		
		try {
			unCliente = databaseRequests.obtenerClientePorId(idCliente);
			archivo = unCliente.isArchivo();
		} catch (BDException e) {
			e.printStackTrace();
		}
		
		logger.setLevel(Level.INFO);
		logger.info("BIBLIOTEC'APP LOGGER - Mostra la vista del cliente : "+unCliente.getApellido()+" "+unCliente.getNombre());
		
		ModelAndView mv = new ModelAndView("clientes/cliente");
		mv.addObject("cliente", unCliente);
		mv.addObject("archivo", archivo);
		return mv;
	}
	
	@RequestMapping(value = "anadir",method = RequestMethod.GET)
	public ModelAndView anadirCliente() throws BDException {

		ModelAndView mv = new ModelAndView("clientes/anadirCliente", "command", new Cliente());
		
		logger.setLevel(Level.INFO);
		logger.info("BIBLIOTEC'APP LOGGER - Mostra la vista para anadir un nuevo cliente en la base de datos");
		
		
		return mv;
	}
	@RequestMapping(value = "save",method = RequestMethod.POST)
	public String saveCliente(@ModelAttribute("cliente") Cliente cliente) throws BDException {
		
		IDatabaseRequests databaseRequests = new DatabaseRequests();
		databaseRequests.anadirCliente(cliente);
		
		logger.setLevel(Level.INFO);
		logger.info("BIBLIOTEC'APP LOGGER - Anado el cliente con el nombre "+cliente.getApellido()+" "+cliente.getNombre()+ " en la base de datos");
		
		return "redirect:todos";
		
	}
	
	@RequestMapping(value = "modificar",method = RequestMethod.GET)
	public ModelAndView modificarCliente(@RequestParam("id") String idClienteString) throws BDException {

		int idCliente = Integer.valueOf(idClienteString);
		IDatabaseRequests databaseRequests = new DatabaseRequests();
		Cliente unCliente = databaseRequests.obtenerClientePorId(idCliente);
		
		ModelAndView mv = new ModelAndView("clientes/modificarCliente", "command", new Cliente());
		mv.addObject("cliente", unCliente);
		
		logger.setLevel(Level.INFO);
		logger.info("BIBLIOTEC'APP LOGGER - Mostra la vista para modificar un cliente de la base de datos");
		
		return mv;
	}
	@RequestMapping(value = "modificarSave",method = RequestMethod.POST)
	public String saveModificarCliente(@ModelAttribute("cliente") Cliente cliente) throws BDException {
		
		IDatabaseRequests databaseRequests = new DatabaseRequests();
		databaseRequests.modificarCliente(cliente);
		
		logger.setLevel(Level.INFO);
		logger.info("BIBLIOTEC'APP LOGGER - Modificar el cliente "+cliente.getApellido()+" "+cliente.getNombre()+" de la base de datos");
		
		return "redirect:todos";
		
	}
	
	@RequestMapping(value = "archivar",method = RequestMethod.GET)
	public String borrarCliente(@RequestParam("id") String idClienteString) throws BDException {
		int idCliente = Integer.valueOf(idClienteString);
		IDatabaseRequests databaseRequests = new DatabaseRequests();
		databaseRequests.archivarCliente(idCliente);
		
		logger.setLevel(Level.INFO);
		logger.info("BIBLIOTEC'APP LOGGER - Archiva el cliente con el identificador "+idCliente+" de la base de datos");
		
		return "redirect:todos";
	}
	
	@RequestMapping(value = "prestamos",method = RequestMethod.GET)
	public String prestamosPorClienteFichieros(@RequestParam("id") String idClienteString) throws BDException {
		
		List<ArticuloToCliente> articulosToClientePorCliente = new ArrayList<ArticuloToCliente>();
		List<ArticuloToCliente> articulosToClientePorClienteArchivados = new ArrayList<ArticuloToCliente>();
		
		int idCliente = Integer.valueOf(idClienteString);
		IDatabaseRequests databaseRequests = new DatabaseRequests();
		Cliente cliente = databaseRequests.obtenerClientePorId(idCliente);
		articulosToClientePorCliente = databaseRequests.obtenerTodosArticulosToClientePorCliente(idCliente, false);
		articulosToClientePorClienteArchivados = databaseRequests.obtenerTodosArticulosToClientePorCliente(idCliente, true);
		
		
		DateFormat dateFormatTitulo = new SimpleDateFormat("yyyyMMdd-HHmmss");
		DateFormat dateFormatContent = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		Date date = new Date();
		
		String nombreFichero = "/Users/Tomoutch/Downloads/"+cliente.getApellido() + cliente.getNombre() + dateFormatTitulo.format(date) +".txt";
		System.out.println(nombreFichero);
		 try{
			  File file = new File(nombreFichero);
		      PrintWriter out  = new PrintWriter(new FileWriter(file));
		      out.println("\nPrestamos por el cliente "+cliente.getApellido()+" "+cliente.getNombre());
		      out.println("Fichero creado el : "+dateFormatContent.format(date)+"\n");
		      out.println("****************\n");
		      out.println("###########################");
		      out.println("Prestamos no archivados");
		      out.println("###########################\n");
		      for (ArticuloToCliente a : articulosToClientePorCliente) {
		    	  out.println("Articulo prestado : "+a.getUnArticulo().getTitulo()+" ("+a.getUnArticulo().getUnTipo().getMensaje()+")");
		    	  out.println("Identificador del articulo : "+a.getUnArticulo().getIdentificador());
		    	  out.println("Fecha de prestamo : "+a.getFechaPrestamo());
			      out.println("Fecha de devolucion planificada : "+a.getFechaPanificadaDevolucion());
			      out.println("Fecha de devolucion real : "+a.getFechaRealDevolucion());
			      out.println("-------------------");
		      }
		      out.println("\n###########################");
		      out.println("Prestamos archivados");
		      out.println("###########################\n");
		      for (ArticuloToCliente a : articulosToClientePorClienteArchivados) {
		    	  out.println("Articulo prestado : "+a.getUnArticulo().getTitulo()+" ("+a.getUnArticulo().getUnTipo().getMensaje()+")");
		    	  out.println("Identificador del articulo : "+a.getUnArticulo().getIdentificador());
		    	  out.println("Fecha de prestamo : "+a.getFechaPrestamo());
			      out.println("Fecha de devolucion planificada : "+a.getFechaPanificadaDevolucion());
			      out.println("Fecha de devolucion real : "+a.getFechaRealDevolucion());
			      out.println("-------------------");
		      }
		        
		      out.close();
		      System.out.printf("File is located at %s%n", file.getAbsolutePath());
		}
		catch(Exception e){
			e.printStackTrace();
		}
		 
		logger.setLevel(Level.INFO);
		logger.info("BIBLIOTEC'APP LOGGER - Crea un fichera TXT contenido los pretamos del cliente "+cliente.getApellido()+" "+cliente.getNombre());
		 
		return "redirect:todos";
		
	}

	
	
}
