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
@RequestMapping("clientes")
public class ClienteController {
	 	
	@RequestMapping("todos")
	public ModelAndView paginaClientes() throws BDException {
		
		ArrayList<Cliente> todosClientes = new ArrayList<Cliente>();
		DatabaseRequests databaseRequests = new DatabaseRequests();
		
		try {
			todosClientes = databaseRequests.obtenerTodosClientes();
		} catch (BDException e) {
			e.printStackTrace();
		}
		
		ModelAndView mv = new ModelAndView("clientes");
		mv.addObject("todosClientes", todosClientes);
		return mv;
	}
	
	@RequestMapping(value = "cliente")
	public ModelAndView paginaClientePorId(@RequestParam("id") String idClienteString) throws BDException {
		int idCliente = Integer.valueOf(idClienteString);
		Cliente unCliente = new Cliente();
		DatabaseRequests databaseRequests = new DatabaseRequests();
		
		try {
			unCliente = databaseRequests.obtenerClientePorId(idCliente);
		} catch (BDException e) {
			e.printStackTrace();
		}
		
		ModelAndView mv = new ModelAndView("cliente");
		mv.addObject("cliente", unCliente);
		return mv;
	}
}
