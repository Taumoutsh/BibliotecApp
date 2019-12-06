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

import com.bibliotecapp.database.BDException;
import com.bibliotecapp.database.DatabaseManager;
import com.bibliotecapp.database.DatabaseRequests;
import com.bibliotecapp.entities.Articulo;
import com.bibliotecapp.entities.Cliente;
import com.bibliotecapp.entities.DVD;
import com.bibliotecapp.entities.Libro;
import com.bibliotecapp.entities.Tema;
import com.bibliotecapp.entities.Tipo;
import com.bibliotecapp.entities.VideoJuego;
import com.bibliotecapp.interfaces.IDatabaseRequests;
 
@Controller
@RequestMapping("libros")
public class LibroController {
	 
	public final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	
	@RequestMapping("todos")
	public ModelAndView paginaPrincipal() throws BDException {
		
		List<Libro> todosLibros = new ArrayList<Libro>();
		IDatabaseRequests databaseRequests = new DatabaseRequests();
		
		boolean archivos = false;
		
		try {
			todosLibros = databaseRequests.obtenerTodosLibros(archivos);
			
			logger.setLevel(Level.INFO);
			logger.info("BIBLIOTEC'APP LOGGER -  Recupera los libros de la base de datos");
			
		} catch (BDException e) {
			e.printStackTrace();
		}
		
		ModelAndView mv = new ModelAndView("libros/libros");
		mv.addObject("todosLibros", todosLibros);
		mv.addObject("archivos", archivos);
		
		logger.info("BIBLIOTEC'APP LOGGER - Mostrar la vista con todos los libros en la base de datos");
		
		return mv;
	}
	
	@RequestMapping("todosArchivos")
	public ModelAndView paginaArchivos() throws BDException {
		
		List<Libro> todosLibros = new ArrayList<Libro>();
		IDatabaseRequests databaseRequests = new DatabaseRequests();
		
		logger.info("BIBLIOTEC'APP LOGGER -  Recupera los libros archivados de la base de datos");
		
		boolean archivos = true;
		
		try {
			todosLibros = databaseRequests.obtenerTodosLibros(archivos);
		} catch (BDException e) {
			e.printStackTrace();
		}
		
		ModelAndView mv = new ModelAndView("libros/libros");
		mv.addObject("todosLibros", todosLibros);
		mv.addObject("archivos", archivos);
		
		logger.info("BIBLIOTEC'APP LOGGER - Mostrar la vista con todos los libros archivados en la base de datos");
		
		return mv;
	}
	
	@RequestMapping(value = "anadir",method = RequestMethod.GET)
	public ModelAndView anadirLibro() throws BDException {
		
		IDatabaseRequests databaseRequests = new DatabaseRequests();
		List<Tema> temas = databaseRequests.obtenerTodosTemas();
		ModelAndView mv = new ModelAndView("libros/anadirLibro", "command", new Libro());
		mv.addObject("temas", temas);
		
		logger.info("BIBLIOTEC'APP LOGGER - Mostrar la vista para anadir un libro en la base de datos");
		
		return mv;
	}
	@RequestMapping(value = "anadirSave",method = RequestMethod.POST)
	public String saveCliente(@ModelAttribute("cliente") Libro libro) throws BDException {
		
		IDatabaseRequests databaseRequests = new DatabaseRequests();
		databaseRequests.anadirLibro(libro);

		logger.info("BIBLIOTEC'APP LOGGER - Anade el libro introducido en la base de datos");
		
		return "redirect:todos";
		
	}
	
	@RequestMapping(value = "modificar",method = RequestMethod.GET)
	public ModelAndView modificarLibro(@RequestParam("id") String idLibroString) throws BDException {

		int idLibro = Integer.valueOf(idLibroString);
		IDatabaseRequests databaseRequests = new DatabaseRequests();
		Libro unLibro = databaseRequests.obtenerLibroPorId(idLibro);
		
		List<Tema> temas = databaseRequests.obtenerTodosTemas();
		
		logger.info("BIBLIOTEC'APP LOGGER - Mostrar la vista para modificar un libro de la base de datos");
		
		ModelAndView mv = new ModelAndView("libros/modificarLibro", "command", new Libro());
		mv.addObject("temas", temas);
		mv.addObject("libro", unLibro);
		
		logger.info("BIBLIOTEC'APP LOGGER - Anade el libro introducido en la base de datos");
		
		return mv;
	}
	
	@RequestMapping(value = "modificarSave",method = RequestMethod.POST)
	public String saveModificarLibro(@ModelAttribute("libro") Libro libro) throws BDException {
		
		IDatabaseRequests databaseRequests = new DatabaseRequests();
		databaseRequests.modificarLibro(libro);
		
		logger.info("BIBLIOTEC'APP LOGGER - Update el libro introducido en la base de datos");
		
		return "redirect:todos";
	}
	
	@RequestMapping(value = "borrar",method = RequestMethod.GET)
	public String borrarDvd(@RequestParam("id") String idLibroString) throws BDException {
		int idLibro = Integer.valueOf(idLibroString);
		IDatabaseRequests databaseRequests = new DatabaseRequests();
		databaseRequests.archivarDvd(idLibro);
		
		logger.info("BIBLIOTEC'APP LOGGER - Archiva el libro seleccionado en la base de datos");
		
		return "redirect:todos";
	}
}
