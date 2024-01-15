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
		return "inicio";	
		//se muestra la pagina inicio almacenada en static
	}
	
//	@PostMapping("/")
//	public String iniciar (HttpSession session) {
//        session.setAttribute("puntos", 0);
//        return "redirect:/pregunta1";
//	}
	
    @GetMapping("/pregunta1")
    public String pregunta1() {
        return "pregunta1";
    }
    
    @PostMapping("/pregunta1")
    public String Pregunta1(
    		@RequestParam(name = "respuesta") String respuesta, 
    		HttpSession session,
    		Model model) {
    	
    	int puntos = 0;
    	
    	//manejar la respuesta de la pregunta 1 (radio button)
        if (respuesta.equals("gryffindor")) {
            puntos = 4;
        } else if (respuesta.equals("hufflepuff")) {
            puntos = 1;
        } else if (respuesta.equals("slytherin")) {
            puntos = 3;
        } else if (respuesta.equals("ravenclaw")) {
            puntos = 2;
        }

        // Actualizar la sesión con los puntos obtenidos
        int puntosActuales = obtenerPuntos(session);
        session.setAttribute("puntos", puntosActuales + puntos);
        
        Resultado resultado = new Resultado();
        resultado.setPuntos(puntos);

        model.addAttribute("resultado", resultado);

        return "pregunta2";
    }//preg1
    
    @GetMapping("/pregunta2")
    public String pregunta2() {
        return "pregunta2";
    }
    
    @PostMapping("/pregunta2")
    public String pregunta2(
    		@RequestParam (value = "opciones", required = false) 
    		String[] opciones, 
    		HttpSession session,
    		Model model) {
        int puntos = 0;
        // Gestión respuesta de la pregunta 2 (checkbox)
        // asignar puntos según las opciones seleccionadas
        if (opciones != null) {
            puntos = opciones.length; 
        }

        // Actualizar la sesión con los puntos obtenidos
        int puntosActuales = obtenerPuntos(session);
        session.setAttribute("puntos", puntosActuales + puntos);
        
        Resultado resultado = new Resultado();
        resultado.setPuntos(puntos);

        model.addAttribute("resultado", resultado);

        return "pregunta3";
    }
    
    @GetMapping("/pregunta3")
    public String pregunta3() {
        return "pregunta3";
    }

    @PostMapping("/pregunta3")
    public String pregunta3(
    		@RequestParam(name = "respuesta") String respuesta, 
    		HttpSession session,
    		Model model) {
        int puntos = 0;
        // Gestión respuesta de la pregunta 3 (select)
        // asignar puntos según la opción seleccionada
        if (respuesta.equals("minerva")) {
            puntos = 4;
        } else if (respuesta.equals("snape")) {
            puntos = 3;
        } else if (respuesta.equals("flitwick")) {
            puntos = 2;
        } else if (respuesta.equals("sprout")) {
            puntos = 1;
        } 

        // Actualizar la sesión con los puntos obtenidos
        int puntosActuales = obtenerPuntos(session);
        session.setAttribute("puntos", puntosActuales + puntos);
        
        Resultado resultado = new Resultado();
        resultado.setPuntos(puntos);

        model.addAttribute("resultado", resultado);

        return "pregunta4";
    }//preg3
    
    @GetMapping("/pregunta4")
    public String pregunta4() {
        return "pregunta4";
    }

    @PostMapping("/pregunta4")
    public String pregunta4(
            @RequestParam(name = "respuesta") String respuesta,
            HttpSession session,
            Model model) {
        int puntos = 0;

        // Gestión de la respuesta de la pregunta 4 (botones)
        switch (respuesta) {
            case "gloria":
                puntos = 4;
                break;
            case "poder":
                puntos = 3;
                break;
            case "sabiduria":
                puntos = 2;
                break;
            case "amor":
                puntos = 1;
                break;

            default:
                // para valores no esperados
                break;
        }

        // Actualizar la sesión con los puntos obtenidos
        int puntosActuales = obtenerPuntos(session);
        session.setAttribute("puntos", puntosActuales + puntos);
        
        Clasificacion clasificacion = calcularClasificacion(puntos);

        Resultado resultado = new Resultado();
        resultado.setClasificacion(clasificacion);
        resultado.setPuntos(puntos);

        model.addAttribute("resultado", resultado);

        // Redirigir a la página final
        return "finalResultado";
    }
    
//    @PostMapping("/finalResultado")
//    public String finalizar(
//    		HttpSession session, 
//    		Model model) {
//        int puntos = obtenerPuntos(session);
//        Clasificacion clasificacion = calcularClasificacion(puntos);
//
//        Resultado resultado = new Resultado();
//        resultado.setClasificacion(clasificacion);
//        resultado.setPuntos(puntos);
//
//        model.addAttribute("resultado", resultado);
//
//        return "finalResultado";
//    }//finalizar
    
//    private int obtenerPuntos(HttpSession session) {
//        return (int) session.getAttribute("puntos");
//    }//obtenerPts
    
//    private int obtenerPuntos(HttpSession session) {
//    	// Obtener el valor asociado con la clave "puntos" de la sesión
//        Integer puntos = (Integer) session.getAttribute("puntos");
//        
//        // Si puntos no es nulo, devuelve su valor como un entero, de lo contrario, devuelve 0
//        return (puntos != null) ? puntos.intValue() : 0;
//    }
    
    private int obtenerPuntos(HttpSession session) {
        if (session != null) {
            Integer puntos = (Integer) session.getAttribute("puntos");
            return (puntos != null) ? puntos.intValue() : 0;
        } else {
            return 0; 
        }
    }

    private Clasificacion calcularClasificacion(int puntos) {
        // determinar la clasificación según los puntos
        if (puntos >= 10) {
            return Clasificacion.gryffindor;
        } else if (puntos >= 7) {
            return Clasificacion.ravenclaw;
        } else if (puntos >= 4) {
            return Clasificacion.slytherin;
        } else {
            return Clasificacion.hufflepuff;
        }
    }//calcularClasi
}

