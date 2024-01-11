package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

@Controller
public class QuizController {
	
	@GetMapping("/")
	public String inicio() {
		return "index";	
		//se muestra la pagina inicio almacenada en static
	}
	
    @GetMapping("/pregunta1")
    public String pregunta1() {
        return "pregunta1";
    }
    
    @PostMapping("/pregunta1")
    public String respuestaPregunta1(@RequestParam int respuesta1, 
    		HttpSession session) {
    	int puntuacion = (int) session.getAttribute("puntuacion");
    	puntuacion += respuesta1;
    	session.setAttribute("puntuacion", puntuacion);
		return "redirect:/pregunta2";
    	
    }
    
    @GetMapping("/pregunta2")
    public String pregunta2() {
        return "pregunta1";
    }
    
    @PostMapping("/pregunta2")
    public String respuestaPregunta2(@RequestParam int respuesta2, 
    		HttpSession session) {
    	int puntuacion = (int) session.getAttribute("puntuacion");
    	puntuacion += respuesta2;
    	session.setAttribute("puntuacion", puntuacion);
		return "redirect:/pregunta3";
    	
    }
    
    @GetMapping("/pregunta3")
    public String pregunta3() {
        return "pregunta3";
    }
    
    @PostMapping("/pregunta3")
    public String respuestaPregunta3(@RequestParam int respuesta3, 
    		HttpSession session) {
    	int puntuacion = (int) session.getAttribute("puntuacion");
    	puntuacion += respuesta3;
    	session.setAttribute("puntuacion", puntuacion);
		return "redirect:/resultado";
  	
    }
    
    

}
