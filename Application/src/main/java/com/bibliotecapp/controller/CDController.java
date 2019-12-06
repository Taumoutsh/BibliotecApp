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
import com.bibliotecapp.entities.CD;
import com.bibliotecapp.entities.Cliente;
import com.bibliotecapp.entities.DVD;
import com.bibliotecapp.entities.Libro;
import com.bibliotecapp.entities.Tema;
import com.bibliotecapp.entities.Tipo;
import com.bibliotecapp.interfaces.IDatabaseRequests;
 
@Controller
@RequestMapping("cds")
public class CDController {
	
	public final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	 
	@RequestMapping("todos")
	public ModelAndView paginaPrincipal() throws BDException {
		
		List<CD> todosCDs = new ArrayList<CD>();
		IDatabaseRequests databaseRequests = new DatabaseRequests();
		
		logger.setLevel(Level.INFO);
		logger.info("BIBLIOTEC'APP LOGGER -  Recupera los CDs de la base de datos");
		
		boolean archivos = false;
		
		try {
			todosCDs = databaseRequests.obtenerTodosCDs(archivos);
		} catch (BDException e) {
			e.printStackTrace();
		}
		
		ModelAndView mv = new ModelAndView("cds/cds");
		mv.addObject("todosCDs", todosCDs);
		mv.addObject("archivos", archivos);
		
		logger.setLevel(Level.INFO);
		logger.info("BIBLIOTEC'APP LOGGER -  Recupera los CDs de la base de datos");
		
		return mv;
	}
	
	@RequestMapping("todosArchivos")
	public ModelAndView paginaArchivos() throws BDException {
		
		List<CD> todosCDs = new ArrayList<CD>();
		IDatabaseRequests databaseRequests = new DatabaseRequests();
		
		boolean archivos = true;
		
		try {
			todosCDs = databaseRequests.obtenerTodosCDs(archivos);
			
			logger.info("BIBLIOTEC'APP LOGGER -  Recupera los CDs archivados de la base de datos");
			
		} catch (BDException e) {
			e.printStackTrace();
		}
		
		ModelAndView mv = new ModelAndView("cds/cds");
		mv.addObject("todosCDs", todosCDs);
		mv.addObject("archivos", archivos);
		
		logger.info("BIBLIOTEC'APP LOGGER - Mostrar la vista con todos los CDs archivados en la base de datos");
		
		return mv;
	}
	
	@RequestMapping(value = "anadir",method = RequestMethod.GET)
	public ModelAndView anadirCd() throws BDException {

		IDatabaseRequests databaseRequests = new DatabaseRequests();
		List<Tema> temas = databaseRequests.obtenerTodosTemas();
		ModelAndView mv = new ModelAndView("cds/anadirCds", "command", new CD());
		mv.addObject("temas", temas);
		
		logger.info("BIBLIOTEC'APP LOGGER - Mostrar la vista para anadir un CD en la base de datos");
		
		return mv;
	}
	@RequestMapping(value = "anadirSave",method = RequestMethod.POST)
	public String saveAnadirCd(@ModelAttribute("cd") CD cd) throws BDException {
		
		IDatabaseRequests databaseRequests = new DatabaseRequests();
		databaseRequests.anadirCd(cd);
		
		logger.info("BIBLIOTEC'APP LOGGER - Anade el CD introducido en la base de datos");

		return "redirect:todos";
		
	}
	
	@RequestMapping(value = "modificar",method = RequestMethod.GET)
	public ModelAndView modificarDvd(@RequestParam("id") String idCdString) throws BDException {

		int idCD = Integer.valueOf(idCdString);
		IDatabaseRequests databaseRequests = new DatabaseRequests();
		CD unCD = databaseRequests.obtenerCDPorId(idCD);
		
		List<Tema> temas = databaseRequests.obtenerTodosTemas();
		
		logger.info("BIBLIOTEC'APP LOGGER - Mostrar la vista para modificar un CD de la base de datos");
		
		ModelAndView mv = new ModelAndView("cds/modificarCd", "command", new CD());
		mv.addObject("temas", temas);
		mv.addObject("cd", unCD);
		
		logger.info("BIBLIOTEC'APP LOGGER - Anade el CD introducido en la base de datos");
		
		return mv;
	}
	
	@RequestMapping(value = "modificarSave",method = RequestMethod.POST)
	public String saveModificarCd(@ModelAttribute("cd") CD cd) throws BDException {
		
		IDatabaseRequests databaseRequests = new DatabaseRequests();
		databaseRequests.modificarCd(cd);
		
		logger.info("BIBLIOTEC'APP LOGGER - Update el CD introducido en la base de datos");
		
		return "redirect:todos";
	}
	
	@RequestMapping(value = "borrar",method = RequestMethod.GET)
	public String borrarCd(@RequestParam("id") String idCdString) throws BDException {
		int idCD = Integer.valueOf(idCdString);
		IDatabaseRequests databaseRequests = new DatabaseRequests();
		databaseRequests.archivarCd(idCD);
		
		logger.info("BIBLIOTEC'APP LOGGER - Archiva el CD seleccionado en la base de datos");
		
		return "redirect:todos";
	}
}
