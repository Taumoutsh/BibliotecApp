package com.bibliotecapp.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bibliotecapp.database.BDException;
import com.bibliotecapp.database.DatabaseManager;
import com.bibliotecapp.database.DatabaseRequests;
import com.bibliotecapp.entities.ArticuloToCliente;
import com.bibliotecapp.entities.Cliente;
import com.bibliotecapp.entities.Tema;
 
@Controller
@RequestMapping("articulosToClientes")
public class ArticulosToClienteController {
	 
	@RequestMapping("todos")
	public ModelAndView paginaPrincipal() throws BDException {
		
		ArrayList<ArticuloToCliente> todosArticulosToClientes = new ArrayList<ArticuloToCliente>();
		DatabaseRequests databaseRequests = new DatabaseRequests();
		
		try {
			todosArticulosToClientes = databaseRequests.obtenerTodosArticulosToClientes();
		} catch (BDException e) {
			e.printStackTrace();
		}
		
		ModelAndView mv = new ModelAndView("articulosToClientes");
		mv.addObject("todosArticulosToClientes", todosArticulosToClientes);
		return mv;
	}
}
