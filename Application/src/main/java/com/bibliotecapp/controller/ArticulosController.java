package com.bibliotecapp.controller;

import java.util.ArrayList;

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
 
@Controller
@RequestMapping("articulos")
public class ArticulosController {
	 
	@RequestMapping("todos")
	public ModelAndView paginaPrincipal() throws BDException {
		
		ArrayList<Articulo> todosArticulos = new ArrayList<Articulo>();
		DatabaseRequests databaseRequests = new DatabaseRequests();
		
		try {
			todosArticulos = databaseRequests.obtenerTodosArticulos();
		} catch (BDException e) {
			e.printStackTrace();
		}
		
		ModelAndView mv = new ModelAndView("articulos");
		mv.addObject("todosArticulos", todosArticulos);
		return mv;
	}
}