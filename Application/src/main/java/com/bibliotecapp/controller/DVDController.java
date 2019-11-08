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
		
		ModelAndView mv = new ModelAndView("dvds/dvds");
		mv.addObject("todosDVDs", todosDVDs);
		return mv;
	}
	
	@RequestMapping(value = "modificar",method = RequestMethod.GET)
	public ModelAndView modificarDvd(@RequestParam("id") String idDvdString) throws BDException {

		int idDVD = Integer.valueOf(idDvdString);
		DatabaseRequests databaseRequests = new DatabaseRequests();
		DVD unDVD = databaseRequests.obtenerDVDPorId(idDVD);
		
		ModelAndView mv = new ModelAndView("dvds/modificardvd", "command", new DVD());
		mv.addObject("dvd", unDVD);
		return mv;
	}
	
	@RequestMapping(value = "modificarSave",method = RequestMethod.POST)
	public String saveModificarDvd(@ModelAttribute("dvd") DVD dvd) throws BDException {
		
		DatabaseRequests databaseRequests = new DatabaseRequests();
		databaseRequests.modificarDvd(dvd);
		
		return "redirect:todos";
	}
	
	@RequestMapping(value = "borrar",method = RequestMethod.GET)
	public String borrarDvd(@RequestParam("id") String idDvdString) throws BDException {
		int idDVD = Integer.valueOf(idDvdString);
		DatabaseRequests databaseRequests = new DatabaseRequests();
		databaseRequests.borrarDvd(idDVD);
		
		return "redirect:todos";
	}
}
