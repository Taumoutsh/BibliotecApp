package com.bibliotecapp.controller;

import java.util.ArrayList;

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
import com.bibliotecapp.entities.ArticuloToCliente;
import com.bibliotecapp.entities.CD;
import com.bibliotecapp.entities.Cliente;
import com.bibliotecapp.entities.DVD;
import com.bibliotecapp.entities.Tema;
import com.bibliotecapp.interfaces.IDatabaseRequests;
 
@Controller
@RequestMapping("articulosToClientes")
public class ArticulosToClienteController {
	 
	@RequestMapping("todos")
	public ModelAndView todosArticulosToClientes() throws BDException {
		
		ArrayList<ArticuloToCliente> todosArticulosToClientes = new ArrayList<ArticuloToCliente>();
		IDatabaseRequests databaseRequests = new DatabaseRequests();
		
		try {
			todosArticulosToClientes = databaseRequests.obtenerTodosArticulosToClientes();
		} catch (BDException e) {
			e.printStackTrace();
		}
		
		ModelAndView mv = new ModelAndView("prestamos/articulosToClientes");
		mv.addObject("todosArticulosToClientes", todosArticulosToClientes);
		return mv;
	}
	
	@RequestMapping(value="anadir", method = RequestMethod.GET)
	public ModelAndView anadirArticuloToCliente() throws BDException {
		
		ArrayList<Articulo> todosArticulos = new ArrayList<Articulo>();
		ArrayList<Cliente> todosClientes = new ArrayList<Cliente>();
		IDatabaseRequests databaseRequests = new DatabaseRequests();
		
		try {
			todosArticulos = databaseRequests.obtenerTodosArticulosPorEstado(true);
			todosClientes = databaseRequests.obtenerTodosClientes();
			
		} catch (BDException e) {
			e.printStackTrace();
		}
		
		ModelAndView mv = new ModelAndView("prestamos/anadirArticuloToCliente", "command", new ArticuloToCliente());
		mv.addObject("articulos", todosArticulos);
		mv.addObject("clientes", todosClientes);
		return mv;
	}
	@RequestMapping(value="saveAnadir", method = RequestMethod.POST)
	public String saveAnadirArticuloToCliente(@ModelAttribute("articuloToCliente") ArticuloToCliente articuloToCliente) throws BDException {
		IDatabaseRequests databaseRequests = new DatabaseRequests();
		databaseRequests.anadirPrestacion(articuloToCliente);
		
		return "redirect:todos";
	
	}
	
	@RequestMapping(value="modificar", method = RequestMethod.GET)
	public ModelAndView modificarArticuloToCliente(@RequestParam("id") String idArticuloToClienteString) throws BDException {
		IDatabaseRequests databaseRequests = new DatabaseRequests();
		
		ArrayList<Articulo> todosArticulos = new ArrayList<Articulo>();
		ArrayList<Cliente> todosClientes = new ArrayList<Cliente>();
		
		int idArticuloToCliente = Integer.valueOf(idArticuloToClienteString);
		ArticuloToCliente atc = databaseRequests.obtenerArticuloToClientePorId(idArticuloToCliente);
		
		todosArticulos = databaseRequests.obtenerTodosArticulosPorEstado(true);
		todosClientes = databaseRequests.obtenerTodosClientes();
		
		ModelAndView mv = new ModelAndView("prestamos/modificarArticuloToCliente", "command", new ArticuloToCliente());
		mv.addObject("ArticuloToCliente", atc);
		mv.addObject("articulos", todosArticulos);
		mv.addObject("clientes", todosClientes);
		return mv;
	}
	@RequestMapping(value="saveModificar", method = RequestMethod.POST)
	public String saveModificarArticuloToCliente(@ModelAttribute("articuloToCliente") ArticuloToCliente articuloToCliente) throws BDException {
		IDatabaseRequests databaseRequests = new DatabaseRequests();
		databaseRequests.modificarPrestacion(articuloToCliente);
		
		return "redirect:todos";
	
	}
	
	
}
