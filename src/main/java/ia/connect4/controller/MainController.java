package ia.connect4.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * Controlador de las páginas.
 *
 */
@Controller
public class MainController {
	
	//Devuelve el template index
	@RequestMapping("/")
	public String index() {
		return "index";
	}
}
