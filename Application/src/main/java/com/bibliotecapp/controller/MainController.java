package com.bibliotecapp.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bibliotecapp.database.BDException;
import com.bibliotecapp.database.DatabaseManager;
import com.bibliotecapp.database.DatabaseRequests;
import com.bibliotecapp.entities.Tema;
 
@Controller
public class MainController {
	String message = "Welcome to Spring MVC!";
	 
	@RequestMapping("/hello.htm")
	public ModelAndView showMessage(
			@RequestParam(value = "name", required = false, defaultValue = "World") String name) {
		
		ArrayList<Tema> todosTemas = new ArrayList<Tema>();
		DatabaseRequests databaseRequests = new DatabaseRequests();
		
		try {
			todosTemas = databaseRequests.obtenerTodosTemas();
		} catch (BDException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(Tema unTema : todosTemas) {
			System.out.println(unTema.getMensaje());
		}

		
		ModelAndView mv = new ModelAndView("helloworld");
		mv.addObject("message", message);
		mv.addObject("name", name);
		mv.addObject("todosTemas", todosTemas);
		return mv;
	}
}
