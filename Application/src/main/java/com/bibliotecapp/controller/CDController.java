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
import com.bibliotecapp.entities.CD;
import com.bibliotecapp.entities.Cliente;
import com.bibliotecapp.entities.Tema;
 
@Controller
@RequestMapping("cds")
public class CDController {
	 
	@RequestMapping("todos")
	public ModelAndView paginaPrincipal() throws BDException {
		
		ArrayList<CD> todosCDs = new ArrayList<CD>();
		DatabaseRequests databaseRequests = new DatabaseRequests();
		
		try {
			todosCDs = databaseRequests.obtenerTodosCDs();
		} catch (BDException e) {
			e.printStackTrace();
		}
		
		ModelAndView mv = new ModelAndView("cds");
		mv.addObject("todosCDs", todosCDs);
		return mv;
	}
}
