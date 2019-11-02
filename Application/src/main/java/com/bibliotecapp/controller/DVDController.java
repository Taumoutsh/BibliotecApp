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
import com.bibliotecapp.entities.DVD;
import com.bibliotecapp.entities.Tema;
import com.bibliotecapp.entities.VideoJuego;
 
@Controller
@RequestMapping("dvds")
public class DVDController {
	 
	@RequestMapping("todos")
	public ModelAndView paginaPrincipal() throws BDException {
		
		ArrayList<DVD> todosDVDs = new ArrayList<DVD>();
		DatabaseRequests databaseRequests = new DatabaseRequests();
		
		try {
			todosDVDs = databaseRequests.obtenerTodosDVDs();
		} catch (BDException e) {
			e.printStackTrace();
		}
		
		ModelAndView mv = new ModelAndView("dvds");
		mv.addObject("todosDVDs", todosDVDs);
		return mv;
	}
}
