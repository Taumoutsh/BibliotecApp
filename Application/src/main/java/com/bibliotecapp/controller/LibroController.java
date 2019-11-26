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
@RequestMapping("libros")
public class LibroController {
	 
	@RequestMapping("todos")
	public ModelAndView paginaPrincipal() throws BDException {
		
		List<Libro> todosLibros = new ArrayList<Libro>();
		IDatabaseRequests databaseRequests = new DatabaseRequests();
		
		boolean archivos = false;
		
		try {
			todosLibros = databaseRequests.obtenerTodosLibros(archivos);
		} catch (BDException e) {
			e.printStackTrace();
		}
		
		ModelAndView mv = new ModelAndView("libros/libros");
		mv.addObject("libros/todosLibros", todosLibros);
		mv.addObject("archivos", archivos);
		return mv;
	}
	
	@RequestMapping(value = "anadir",method = RequestMethod.GET)
	public ModelAndView anadirCliente() throws BDException {

		ModelAndView mv = new ModelAndView("libros/anadirLibro", "command", new Libro());
		
		
		return mv;
	}
	@RequestMapping(value = "anadirSave",method = RequestMethod.POST)
	public String saveCliente(@ModelAttribute("cliente") Libro libro) throws BDException {
		
		IDatabaseRequests databaseRequests = new DatabaseRequests();
		databaseRequests.anadirLibro(libro);

		return "redirect:todos";
		
	}
	
	@RequestMapping(value = "modificar",method = RequestMethod.GET)
	public ModelAndView modificarLibro(@RequestParam("id") String idLibroString) throws BDException {

		int idLibro = Integer.valueOf(idLibroString);
		IDatabaseRequests databaseRequests = new DatabaseRequests();
		Libro unLibro = databaseRequests.obtenerLibroPorId(idLibro);
		
		List<Tipo> tipos = databaseRequests.obtenerTodosTipos();
		List<Tema> temas = databaseRequests.obtenerTodosTemas();
		
		ModelAndView mv = new ModelAndView("libros/modificarLibro", "command", new Libro());
		mv.addObject("temas", temas);
		mv.addObject("libro", unLibro);
		return mv;
	}
	
	@RequestMapping(value = "modificarSave",method = RequestMethod.POST)
	public String saveModificarLibro(@ModelAttribute("libro") Libro libro) throws BDException {
		
		IDatabaseRequests databaseRequests = new DatabaseRequests();
		databaseRequests.modificarLibro(libro);
		
		return "redirect:todos";
	}
	
	@RequestMapping(value = "borrar",method = RequestMethod.GET)
	public String borrarDvd(@RequestParam("id") String idLibroString) throws BDException {
		int idLibro = Integer.valueOf(idLibroString);
		IDatabaseRequests databaseRequests = new DatabaseRequests();
		databaseRequests.archivarDvd(idLibro);
		
		return "redirect:todos";
	}
}
