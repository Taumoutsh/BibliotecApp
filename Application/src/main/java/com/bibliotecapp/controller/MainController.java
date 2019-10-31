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
import com.bibliotecapp.entities.Tema;
 
@Controller
public class MainController {
	String message = "Welcome to Spring MVC!";
	 
	@RequestMapping("/index.htm")
	public ModelAndView showMessage() throws BDException {
		
		ArrayList<Articulo> todosArticulos = new ArrayList<Articulo>();
		DatabaseRequests databaseRequests = new DatabaseRequests();
		
		try {
			todosArticulos = databaseRequests.obtenerTodosArticulos();
		} catch (BDException e) {
			e.printStackTrace();
		}
		
		ModelAndView mv = new ModelAndView("helloworld");
		mv.addObject("message", message);
		mv.addObject("todosArticulos", todosArticulos);
		return mv;
	}
}
