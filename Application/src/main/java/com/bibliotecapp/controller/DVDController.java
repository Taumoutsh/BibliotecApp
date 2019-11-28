package com.bibliotecapp.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

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
import com.bibliotecapp.entities.Tema;
import com.bibliotecapp.entities.Tipo;
import com.bibliotecapp.entities.VideoJuego;
import com.bibliotecapp.interfaces.IDatabaseRequests;
 
@Controller
@RequestMapping("dvds")
public class DVDController {
	 
	@RequestMapping("todos")
	public ModelAndView paginaPrincipal() throws BDException {
		
		List<DVD> todosDVDs = new ArrayList<DVD>();
		IDatabaseRequests databaseRequests = new DatabaseRequests();
		
		boolean archivos = false;
		
		try {
			todosDVDs = databaseRequests.obtenerTodosDVDs(archivos);
		} catch (BDException e) {
			e.printStackTrace();
		}
		
		ModelAndView mv = new ModelAndView("dvds/dvds");
		mv.addObject("todosDVDs", todosDVDs);
		mv.addObject("archivos", archivos);
		return mv;
	}
	
	@RequestMapping("todosArchivos")
	public ModelAndView paginaArchivos() throws BDException {
		
		List<DVD> todosDVDs = new ArrayList<DVD>();
		IDatabaseRequests databaseRequests = new DatabaseRequests();
		
		boolean archivos = true;
		
		try {
			todosDVDs = databaseRequests.obtenerTodosDVDs(archivos);
		} catch (BDException e) {
			e.printStackTrace();
		}
		
		ModelAndView mv = new ModelAndView("dvds/dvds");
		mv.addObject("todosDVDs", todosDVDs);
		mv.addObject("archivos", archivos);
		return mv;
	}
	
	@RequestMapping(value = "anadir",method = RequestMethod.GET)
	public ModelAndView anadirDvd() throws BDException {

		IDatabaseRequests databaseRequests = new DatabaseRequests();
		List<Tema> temas = databaseRequests.obtenerTodosTemas();
		ModelAndView mv = new ModelAndView("dvds/anadirDvd", "command", new DVD());
		mv.addObject("temas", temas);
		
		return mv;
	}
	@RequestMapping(value = "anadirSave",method = RequestMethod.POST)
	public String saveDvd(@ModelAttribute("dvd") DVD dvd) throws BDException {
		
		IDatabaseRequests databaseRequests = new DatabaseRequests();
		databaseRequests.anadirDvd(dvd);

		return "redirect:todos";
		
	}
	
	@RequestMapping(value = "modificar",method = RequestMethod.GET)
	public ModelAndView modificarDvd(@RequestParam("id") String idDvdString) throws BDException {

		int idDVD = Integer.valueOf(idDvdString);
		IDatabaseRequests databaseRequests = new DatabaseRequests();
		DVD unDVD = databaseRequests.obtenerDVDPorId(idDVD);
		
		List<Tipo> tipos = databaseRequests.obtenerTodosTipos();
		List<Tema> temas = databaseRequests.obtenerTodosTemas();
		
		ModelAndView mv = new ModelAndView("dvds/modificarDvd", "command", new DVD());
		mv.addObject("temas", temas);
		mv.addObject("dvd", unDVD);
		return mv;
	}
	
	@RequestMapping(value = "modificarSave",method = RequestMethod.POST)
	public String saveModificarDvd(@ModelAttribute("dvd") DVD dvd) throws BDException {
		
		IDatabaseRequests databaseRequests = new DatabaseRequests();
		databaseRequests.modificarDvd(dvd);
		
		return "redirect:todos";
	}
	
	@RequestMapping(value = "borrar",method = RequestMethod.GET)
	public String borrarDvd(@RequestParam("id") String idDvdString) throws BDException {
		int idDVD = Integer.valueOf(idDvdString);
		IDatabaseRequests databaseRequests = new DatabaseRequests();
		databaseRequests.archivarDvd(idDVD);
		
		return "redirect:todos";
	}
}
