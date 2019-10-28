package com.bibliotecapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.bibliotecapp.database.DatabaseManager;
 
@Controller
public class MainController {
	String message = "Welcome to Spring MVC!";
	 
	@RequestMapping("/hello.htm")
	public ModelAndView showMessage(
			@RequestParam(value = "name", required = false, defaultValue = "World") String name) {
		System.out.println("in controller");
		
		DatabaseManager db = new DatabaseManager();
		
		db.connectDatabase();
		
		ModelAndView mv = new ModelAndView("helloworld");
		mv.addObject("message", message);
		mv.addObject("name", name);
		return mv;
	}
}
