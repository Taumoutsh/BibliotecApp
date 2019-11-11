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
import com.bibliotecapp.entities.CD;
import com.bibliotecapp.entities.Cliente;
import com.bibliotecapp.entities.DVD;
import com.bibliotecapp.entities.Tema;
import com.bibliotecapp.entities.Tipo;
 
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
		
		ModelAndView mv = new ModelAndView("cds/cds");
		mv.addObject("todosCDs", todosCDs);
		return mv;
	}
	
	@RequestMapping(value = "modificar",method = RequestMethod.GET)
	public ModelAndView modificarDvd(@RequestParam("id") String idCdString) throws BDException {

		int idCD = Integer.valueOf(idCdString);
		DatabaseRequests databaseRequests = new DatabaseRequests();
		CD unCD = databaseRequests.obtenerCDPorId(idCD);
		
		ArrayList<Tipo> tipos = databaseRequests.obtenerTodosTipos();
		ArrayList<Tema> temas = databaseRequests.obtenerTodosTemas();
		
		ModelAndView mv = new ModelAndView("cds/modificarcd", "command", new CD());
		mv.addObject("temas", temas);
		mv.addObject("cd", unCD);
		return mv;
	}
	
	@RequestMapping(value = "modificarSave",method = RequestMethod.POST)
	public String saveModificarCd(@ModelAttribute("cd") CD cd) throws BDException {
		
		DatabaseRequests databaseRequests = new DatabaseRequests();
		databaseRequests.modificarCd(cd);
		
		return "redirect:todos";
	}
	
	@RequestMapping(value = "borrar",method = RequestMethod.GET)
	public String borrarCd(@RequestParam("id") String idCdString) throws BDException {
		int idCD = Integer.valueOf(idCdString);
		DatabaseRequests databaseRequests = new DatabaseRequests();
		databaseRequests.borrarCd(idCD);
		
		return "redirect:todos";
	}
}
