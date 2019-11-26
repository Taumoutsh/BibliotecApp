package com.bibliotecapp.controller;

import java.util.ArrayList;
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
import com.bibliotecapp.entities.CD;
import com.bibliotecapp.entities.Cliente;
import com.bibliotecapp.entities.DVD;
import com.bibliotecapp.entities.Libro;
import com.bibliotecapp.entities.Tema;
import com.bibliotecapp.entities.Tipo;
import com.bibliotecapp.interfaces.IDatabaseRequests;
 
@Controller
@RequestMapping("cds")
public class CDController {
	 
	@RequestMapping("todos")
	public ModelAndView paginaPrincipal() throws BDException {
		
		List<CD> todosCDs = new ArrayList<CD>();
		IDatabaseRequests databaseRequests = new DatabaseRequests();
		
		boolean archivos = false;
		
		try {
			todosCDs = databaseRequests.obtenerTodosCDs(archivos);
		} catch (BDException e) {
			e.printStackTrace();
		}
		
		ModelAndView mv = new ModelAndView("cds/cds");
		mv.addObject("todosCDs", todosCDs);
		mv.addObject("archivos", archivos);
		return mv;
	}
	
	@RequestMapping(value = "anadir",method = RequestMethod.GET)
	public ModelAndView anadirCd() throws BDException {

		ModelAndView mv = new ModelAndView("cds/anadirCds", "command", new CD());
		
		
		return mv;
	}
	@RequestMapping(value = "anadirSave",method = RequestMethod.POST)
	public String saveAnadirCd(@ModelAttribute("cd") CD cd) throws BDException {
		
		IDatabaseRequests databaseRequests = new DatabaseRequests();
		databaseRequests.anadirCd(cd);

		return "redirect:todos";
		
	}
	
	@RequestMapping(value = "modificar",method = RequestMethod.GET)
	public ModelAndView modificarDvd(@RequestParam("id") String idCdString) throws BDException {

		int idCD = Integer.valueOf(idCdString);
		IDatabaseRequests databaseRequests = new DatabaseRequests();
		CD unCD = databaseRequests.obtenerCDPorId(idCD);
		
		List<Tipo> tipos = databaseRequests.obtenerTodosTipos();
		List<Tema> temas = databaseRequests.obtenerTodosTemas();
		
		ModelAndView mv = new ModelAndView("cds/modificarCd", "command", new CD());
		mv.addObject("temas", temas);
		mv.addObject("cd", unCD);
		return mv;
	}
	
	@RequestMapping(value = "modificarSave",method = RequestMethod.POST)
	public String saveModificarCd(@ModelAttribute("cd") CD cd) throws BDException {
		
		IDatabaseRequests databaseRequests = new DatabaseRequests();
		databaseRequests.modificarCd(cd);
		
		return "redirect:todos";
	}
	
	@RequestMapping(value = "borrar",method = RequestMethod.GET)
	public String borrarCd(@RequestParam("id") String idCdString) throws BDException {
		int idCD = Integer.valueOf(idCdString);
		IDatabaseRequests databaseRequests = new DatabaseRequests();
		databaseRequests.archivarCd(idCD);
		
		return "redirect:todos";
	}
	
	@RequestMapping("archivos")
	public ModelAndView cdArchivos() throws BDException {
		
		List<CD> todosCDs = new ArrayList<CD>();
		IDatabaseRequests databaseRequests = new DatabaseRequests();
		
		try {
			todosCDs = databaseRequests.obtenerTodosCDs(true);
		} catch (BDException e) {
			e.printStackTrace();
		}
		
		ModelAndView mv = new ModelAndView("cds/cds");
		mv.addObject("todosCDs", todosCDs);
		return mv;
	}
}
