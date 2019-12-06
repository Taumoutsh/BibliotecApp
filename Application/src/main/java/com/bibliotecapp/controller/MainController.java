package com.bibliotecapp.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bibliotecapp.database.BDException;
import com.bibliotecapp.database.DatabaseManager;
import com.bibliotecapp.database.DatabaseRequests;
import com.bibliotecapp.entities.Articulo;
import com.bibliotecapp.entities.Cliente;
import com.bibliotecapp.entities.Tema;
import com.bibliotecapp.interfaces.IDatabaseRequests;
 
@Controller
public class MainController {
	
	public final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	 
	@RequestMapping("index")
	public ModelAndView paginaPrincipal() throws BDException {
		
		List<Articulo> todosArticulos = new ArrayList<Articulo>();
		IDatabaseRequests databaseRequests = new DatabaseRequests();
		
		logger.setLevel(Level.INFO);
		logger.info("BIBLIOTEC'APP LOGGER -  Recupera los articulos de la base de datos");
		
		try {
			todosArticulos = databaseRequests.obtenerTodosArticulos(false);
		} catch (BDException e) {
			e.printStackTrace();
		}
		
		ModelAndView mv = new ModelAndView("articulos");
		mv.addObject("todosArticulos", todosArticulos);
		
		logger.info("BIBLIOTEC'APP LOGGER - Mostrar la vista con todos los articulos en la base de datos");
		
		return mv;
	}
}
