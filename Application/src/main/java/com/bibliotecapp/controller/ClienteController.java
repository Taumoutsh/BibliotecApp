package com.bibliotecapp.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import com.bibliotecapp.entities.ArticuloToCliente;
import com.bibliotecapp.entities.Cliente;
import com.bibliotecapp.entities.Tema;
import com.bibliotecapp.interfaces.IDatabaseRequests;
 
@Controller
@RequestMapping("clientes")
public class ClienteController {
	 	
	@RequestMapping("todos")
	public ModelAndView paginaClientes() throws BDException {
		
		List<Cliente> todosClientes = new ArrayList<Cliente>();
		IDatabaseRequests databaseRequests = new DatabaseRequests();
		
		try {
			todosClientes = databaseRequests.obtenerTodosClientes();
		} catch (BDException e) {
			e.printStackTrace();
		}
		
		ModelAndView mv = new ModelAndView("clientes/clientes");
		mv.addObject("todosClientes", todosClientes);
		return mv;
	}
	
	@RequestMapping(value = "cliente",method = RequestMethod.GET)
	public ModelAndView paginaClientePorId(@RequestParam("id") String idClienteString) throws BDException {
		int idCliente = Integer.valueOf(idClienteString);
		Cliente unCliente = new Cliente();
		IDatabaseRequests databaseRequests = new DatabaseRequests();
		
		try {
			unCliente = databaseRequests.obtenerClientePorId(idCliente);
		} catch (BDException e) {
			e.printStackTrace();
		}
		
		ModelAndView mv = new ModelAndView("clientes/cliente");
		mv.addObject("cliente", unCliente);
		return mv;
	}
	
	@RequestMapping(value = "anadir",method = RequestMethod.GET)
	public ModelAndView anadirCliente() throws BDException {

		ModelAndView mv = new ModelAndView("clientes/anadirCliente", "command", new Cliente());
		return mv;
	}
	@RequestMapping(value = "save",method = RequestMethod.POST)
	public String saveCliente(@ModelAttribute("cliente") Cliente cliente) throws BDException {
		
		IDatabaseRequests databaseRequests = new DatabaseRequests();
		databaseRequests.anadirCliente(cliente);
		
		return "redirect:todos";
		
	}
	
	@RequestMapping(value = "modificar",method = RequestMethod.GET)
	public ModelAndView modificarCliente(@RequestParam("id") String idClienteString) throws BDException {

		int idCliente = Integer.valueOf(idClienteString);
		IDatabaseRequests databaseRequests = new DatabaseRequests();
		Cliente unCliente = databaseRequests.obtenerClientePorId(idCliente);
		
		ModelAndView mv = new ModelAndView("clientes/modificarCliente", "command", new Cliente());
		mv.addObject("cliente", unCliente);
		return mv;
	}
	@RequestMapping(value = "modificarSave",method = RequestMethod.POST)
	public String saveModificarCliente(@ModelAttribute("cliente") Cliente cliente) throws BDException {
		
		IDatabaseRequests databaseRequests = new DatabaseRequests();
		databaseRequests.modificarCliente(cliente);
		
		return "redirect:todos";
		
	}
	
	@RequestMapping(value = "borrar",method = RequestMethod.GET)
	public String borrarCliente(@RequestParam("id") String idClienteString) throws BDException {
		int idCliente = Integer.valueOf(idClienteString);
		IDatabaseRequests databaseRequests = new DatabaseRequests();
		databaseRequests.borrarCliente(idCliente);
		
		return "redirect:todos";
	}
	
	@RequestMapping(value = "prestamos",method = RequestMethod.GET)
	public String prestamosPorClienteFichieros(@RequestParam("id") String idClienteString) throws BDException {
		
		List<ArticuloToCliente> articulosToClientePorCliente = new ArrayList<ArticuloToCliente>();
		
		int idCliente = Integer.valueOf(idClienteString);
		IDatabaseRequests databaseRequests = new DatabaseRequests();
		Cliente cliente = databaseRequests.obtenerClientePorId(idCliente);
		articulosToClientePorCliente = databaseRequests.obtenerTodosArticulosToClientePorCliente(idCliente);
		
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		Date date = new Date();
		
		String nombreFichero = "src/main/resources/"+cliente.getApellido() + cliente.getNombre() + dateFormat.format(date) +".txt";
		System.out.println(nombreFichero);
		 try{
		      PrintWriter out  = new PrintWriter(new FileWriter(nombreFichero));
		      out.println("Prestamos por el cliente "+cliente.getApellido()+" "+cliente.getNombre());
		      out.println("Fichero creado el : "+dateFormat.format(date));
		      out.println("****************\n");
		      for (ArticuloToCliente a : articulosToClientePorCliente) {
		    	  out.println("Articulo prestado "+a.getUnArticulo());
		    	  out.println("Fecha de prestamo :"+a.getFechaPrestamo());
			      out.println("Fecha de devolucion planificada "+a.getFechaPanificadaDevolucion());
			      out.println("Fecha de devolucion real : "+a.getFechaRealDevolucion());
			      out.println("-------------------");
		      }
		        
		      out.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		 
		return "redirect:todos";
		
	}

	
	
}
