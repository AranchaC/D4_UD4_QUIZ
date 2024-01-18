package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class ResultadoRepositorio {

    private List<Resultado> resultados = new ArrayList<>();

    public void guardarResultado(Resultado resultado) {
        resultados.add(resultado);
    }

    public List<Resultado> obtenerTodosLosResultados() {
        return resultados;
    }	
}
