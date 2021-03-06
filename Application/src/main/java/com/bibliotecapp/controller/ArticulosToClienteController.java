package com.bibliotecapp.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.bibliotecapp.database.BDException;
import com.bibliotecapp.database.DatabaseManager;
import com.bibliotecapp.database.DatabaseRequests;
import com.bibliotecapp.entities.Articulo;
import com.bibliotecapp.entities.ArticuloToCliente;
import com.bibliotecapp.entities.CD;
import com.bibliotecapp.entities.Cliente;
import com.bibliotecapp.entities.DVD;
import com.bibliotecapp.entities.Tema;
import com.bibliotecapp.interfaces.IDatabaseRequests;
 
@Controller
@RequestMapping("articulosToClientes")
public class ArticulosToClienteController {
	
	public final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	 
	@RequestMapping("todos")
	public ModelAndView todosArticulosToClientes() throws BDException {
		
		final ModelAndView mv = new ModelAndView("prestamos/articulosToClientes");
		
		logger.setLevel(Level.INFO);
		logger.info("BIBLIOTEC'APP LOGGER -  Lanzar the hilo recuperando los prestamos en restraso de la base de datos");
		
		Thread t = new Thread() {
			public void run() {
		    	  try {
		    		  
		    		  int contar;
		    		  List<ArticuloToCliente> todosArticulosToClientes = new ArrayList<ArticuloToCliente>();
		    		  IDatabaseRequests databaseRequests = new DatabaseRequests();
		    		  todosArticulosToClientes = databaseRequests.obtenerTodosArticulosToClientes(false);
		    		  contar = databaseRequests.contarRebasarFechaDevolucion(todosArticulosToClientes);
		    		  
		    		  logger.info("BIBLIOTEC'APP LOGGER -  Mostar los prestamos en restraso de la base de datos");
		    		  
		    		  mv.addObject("contar", contar);
		    		  
			      } catch (BDException e) {
			    	  e.printStackTrace();
			      } 
		      }
		    };
		    t.start();
		
		boolean archivos = false;
		
		List<ArticuloToCliente> todosArticulosToClientes = new ArrayList<ArticuloToCliente>();
		IDatabaseRequests databaseRequests = new DatabaseRequests();
		
		try {
			todosArticulosToClientes = databaseRequests.obtenerTodosArticulosToClientes(false);
		} catch (BDException e) {
			e.printStackTrace();
		}
		
		logger.info("BIBLIOTEC'APP LOGGER -  Mostrar los prestamos no archivados de la base de datos");
		
		mv.addObject("archivos", archivos);
		mv.addObject("todosArticulosToClientes", todosArticulosToClientes);
		return mv;
	}
	
	@RequestMapping("todosArchivos")
	public ModelAndView todosArticulosToClientesArchivos() throws BDException {
		
		List<ArticuloToCliente> todosArticulosToClientes = new ArrayList<ArticuloToCliente>();
		IDatabaseRequests databaseRequests = new DatabaseRequests();
		
		boolean archivos = true;
		
		try {
			todosArticulosToClientes = databaseRequests.obtenerTodosArticulosToClientes(archivos);
		} catch (BDException e) {
			e.printStackTrace();
		}
		
		ModelAndView mv = new ModelAndView("prestamos/articulosToClientes");
		mv.addObject("archivos", archivos);
		mv.addObject("todosArticulosToClientes", todosArticulosToClientes);
		
		logger.info("BIBLIOTEC'APP LOGGER -  Mostrar la vista los prestamos archivados de la base de datos");
		
		return mv;
	}
	
	@RequestMapping(value="anadir", method = RequestMethod.GET)
	public ModelAndView anadirArticuloToCliente() throws BDException {
		
		List<Articulo> todosArticulos = new ArrayList<Articulo>();
		List<Cliente> todosClientes = new ArrayList<Cliente>();
		IDatabaseRequests databaseRequests = new DatabaseRequests();
		
		try {
			todosArticulos = databaseRequests.obtenerTodosArticulosPorEstado(true, false);
			todosClientes = databaseRequests.obtenerTodosClientes(false);
			
		} catch (BDException e) {
			e.printStackTrace();
		}
		
		ModelAndView mv = new ModelAndView("prestamos/anadirArticuloToCliente", "command", new ArticuloToCliente());
		mv.addObject("articulos", todosArticulos);
		mv.addObject("clientes", todosClientes);
		
		logger.info("BIBLIOTEC'APP LOGGER - Mostrar la vista para anadir un prestamo en la base de datos");
		
		return mv;
	}
	@RequestMapping(value="saveAnadir", method = RequestMethod.POST)
	public String saveAnadirArticuloToCliente(@ModelAttribute("articuloToCliente") ArticuloToCliente articuloToCliente) throws BDException {
		IDatabaseRequests databaseRequests = new DatabaseRequests();
		
		boolean validezCuento = databaseRequests.checkValidezCuento(articuloToCliente);
		
		if(validezCuento == true) {
			databaseRequests.anadirPrestacion(articuloToCliente);
		}
		
		logger.info("BIBLIOTEC'APP LOGGER - Anade el vista introducido en la base de datos");
		
		return "redirect:todos";
	
	}
	
	@RequestMapping(value="modificar", method = RequestMethod.GET)
	public ModelAndView modificarArticuloToCliente(@RequestParam("id") String idArticuloToClienteString) throws BDException {
		IDatabaseRequests databaseRequests = new DatabaseRequests();
		
		List<Articulo> todosArticulos = new ArrayList<Articulo>();
		List<Cliente> todosClientes = new ArrayList<Cliente>();
		
		int idArticuloToCliente = Integer.valueOf(idArticuloToClienteString);
		ArticuloToCliente atc = databaseRequests.obtenerArticuloToClientePorId(idArticuloToCliente);
		
		todosArticulos = databaseRequests.obtenerTodosArticulosPorEstado(true, false);
		todosClientes = databaseRequests.obtenerTodosClientes(false);
		
		logger.info("BIBLIOTEC'APP LOGGER - Mostrar la vista para modificar un prestamo de la base de datos");
		
		ModelAndView mv = new ModelAndView("prestamos/modificarArticuloToCliente", "command", new ArticuloToCliente());
		mv.addObject("ArticuloToCliente", atc);
		mv.addObject("articulos", todosArticulos);
		mv.addObject("clientes", todosClientes);
		
		logger.info("BIBLIOTEC'APP LOGGER - Modifica el prestamo introducido en la base de datos");
		
		return mv;
	}
	@RequestMapping(value="saveModificar", method = RequestMethod.POST)
	public String saveModificarArticuloToCliente(@ModelAttribute("articuloToCliente") ArticuloToCliente articuloToCliente) throws BDException {
		IDatabaseRequests databaseRequests = new DatabaseRequests();
		databaseRequests.modificarPrestacion(articuloToCliente);
		
		logger.info("BIBLIOTEC'APP LOGGER - Update el prestamo introducido en la base de datos");
		
		return "redirect:todos";
	
	}
	@RequestMapping(value = "archivar",method = RequestMethod.GET)
	public RedirectView archivarArticuloToCliente(@RequestParam("id") String idArticuloToClienteString) throws BDException {
		int idArticuloToCliente = Integer.valueOf(idArticuloToClienteString);
		IDatabaseRequests databaseRequests = new DatabaseRequests();
		databaseRequests.archivarPrestacion(idArticuloToCliente);
		
		logger.info("BIBLIOTEC'APP LOGGER - Archiva el prestamo seleccionado en la base de datos");
		
		return new RedirectView("../todos");
	}
}
