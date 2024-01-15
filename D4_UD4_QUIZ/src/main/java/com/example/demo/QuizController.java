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
	
    @GetMapping("/pregunta1")
    public String pregunta1(HttpSession session, Model model) {
    	// Reiniciar los puntos:
        Resultado resultado = new Resultado();
        session.setAttribute("resultado", resultado);
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

        // Obtener el objeto Resultado de la sesión
        Resultado resultado = obtenerResultado(session);

        // Actualizar los puntos acumulados en el objeto Resultado
        resultado.setPuntos(resultado.getPuntos() + puntos);

        // Agregar el objeto Resultado actualizado al modelo
        model.addAttribute("resultado", resultado);

        return "pregunta2";
    }//preg1
    
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
        Resultado resultado = obtenerResultado(session);
        resultado.setPuntos(resultado.getPuntos() + puntos);
        model.addAttribute("resultado", resultado);

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
        Resultado resultado = obtenerResultado(session);
        resultado.setPuntos(resultado.getPuntos() + puntos);
        model.addAttribute("resultado", resultado);

        return "pregunta4";
    }//preg3
    

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
        Resultado resultado = obtenerResultado(session);
        resultado.setPuntos(resultado.getPuntos() + puntos);
        resultado.setClasificacion(calcularClasificacion(resultado.getPuntos()));

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
    
    private Resultado obtenerResultado(HttpSession session) {
    	Resultado resultado = (Resultado) session.getAttribute("resultado");
        if (resultado == null) {
            // Si no existe en la sesión, crear uno nuevo y guardarlo en la sesión
            resultado = new Resultado();
            session.setAttribute("resultado", resultado);
        }
        return resultado;
    }//obtenerResultado
    
    private Clasificacion calcularClasificacion(int puntos) {
        // determinar la clasificación según los puntos
        if (puntos >= 10) {
            return Clasificacion.GRYFFINDOR;
        } else if (puntos >= 7) {
            return Clasificacion.RAVENCLAW;
        } else if (puntos >= 4) {
            return Clasificacion.SLYTHERIN;
        } else {
            return Clasificacion.HUFFLEPUFF;
        }
    }//calcularClasif
}

