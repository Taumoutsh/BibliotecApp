package com.bibliotecapp.controller;

import java.util.ArrayList;
import java.util.List;

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
@RequestMapping("videojuegos")
public class VideoJuegoController {
	 
	@RequestMapping("todos")
	public ModelAndView paginaPrincipal() throws BDException {
		
		List<VideoJuego> todosVideoJuegos = new ArrayList<VideoJuego>();
		IDatabaseRequests databaseRequests = new DatabaseRequests();
		
		boolean archivos = false;
		
		try {
			todosVideoJuegos = databaseRequests.obtenerTodosVideoJuegos(archivos);
		} catch (BDException e) {
			e.printStackTrace();
		}
		
		ModelAndView mv = new ModelAndView("videojuegos/videojuegos");
		mv.addObject("todosVideoJuegos", todosVideoJuegos);
		mv.addObject("archivos", archivos);
		return mv;
	}
	
	@RequestMapping("todosArchivos")
	public ModelAndView paginaArchivos() throws BDException {
		
		List<VideoJuego> todosVideoJuegos = new ArrayList<VideoJuego>();
		IDatabaseRequests databaseRequests = new DatabaseRequests();
		
		boolean archivos = true;
		
		try {
			todosVideoJuegos = databaseRequests.obtenerTodosVideoJuegos(archivos);
		} catch (BDException e) {
			e.printStackTrace();
		}
		
		ModelAndView mv = new ModelAndView("videojuegos/videojuegos");
		mv.addObject("todosVideoJuegos", todosVideoJuegos);
		mv.addObject("archivos", archivos);
		return mv;
	}
	
	@RequestMapping(value = "anadir",method = RequestMethod.GET)
	public ModelAndView anadirVideoJuego() throws BDException {

		IDatabaseRequests databaseRequests = new DatabaseRequests();
		List<Tema> temas = databaseRequests.obtenerTodosTemas();
		ModelAndView mv = new ModelAndView("videojuegos/anadirVideoJuego", "command", new VideoJuego());
		mv.addObject("temas", temas);
		
		return mv;
	}
	@RequestMapping(value = "anadirSave",method = RequestMethod.POST)
	public String saveAnadirVideoJuego(@ModelAttribute("videoJuego") VideoJuego videoJuego) throws BDException {
		
		IDatabaseRequests databaseRequests = new DatabaseRequests();
		databaseRequests.anadirVideoJuego(videoJuego);
		return "redirect:todos";
		
	}
	
	@RequestMapping(value = "modificar",method = RequestMethod.GET)
	public ModelAndView modificarVideoJuego(@RequestParam("id") String idVideoJuegoString) throws BDException {

		int idVideoJuego = Integer.valueOf(idVideoJuegoString);
		IDatabaseRequests databaseRequests = new DatabaseRequests();
		VideoJuego unVideoJuego = databaseRequests.obtenerVideoJuegoPorId(idVideoJuego);
		
		List<Tipo> tipos = databaseRequests.obtenerTodosTipos();
		List<Tema> temas = databaseRequests.obtenerTodosTemas();
		
		ModelAndView mv = new ModelAndView("videojuegos/modificarVideoJuego", "command", new VideoJuego());
		mv.addObject("temas", temas);
		mv.addObject("videoJuego", unVideoJuego);
		return mv;
	}
	
	@RequestMapping(value = "modificarSave",method = RequestMethod.POST)
	public String saveModificarVideoJuego(@ModelAttribute("videoJuego") VideoJuego videoJuego) throws BDException {
		
		IDatabaseRequests databaseRequests = new DatabaseRequests();
		databaseRequests.modificarVideoJuego(videoJuego);
		
		return "redirect:todos";
	}
	
	@RequestMapping(value = "borrar",method = RequestMethod.GET)
	public String borrarDvd(@RequestParam("id") String idVideoJuegoString) throws BDException {
		int idVideoJuego = Integer.valueOf(idVideoJuegoString);
		IDatabaseRequests databaseRequests = new DatabaseRequests();
		databaseRequests.archivarVideoJuego(idVideoJuego);
		
		return "redirect:todos";
	}
}
