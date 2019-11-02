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
import com.bibliotecapp.entities.VideoJuego;
 
@Controller
@RequestMapping("videojuegos")
public class VideoJuegoController {
	 
	@RequestMapping("todos")
	public ModelAndView paginaPrincipal() throws BDException {
		
		ArrayList<VideoJuego> todosVideoJuegos = new ArrayList<VideoJuego>();
		DatabaseRequests databaseRequests = new DatabaseRequests();
		
		try {
			todosVideoJuegos = databaseRequests.obtenerTodosVideoJuegos();
		} catch (BDException e) {
			e.printStackTrace();
		}
		
		ModelAndView mv = new ModelAndView("videojuegos");
		mv.addObject("todosVideoJuegos", todosVideoJuegos);
		return mv;
	}
}
