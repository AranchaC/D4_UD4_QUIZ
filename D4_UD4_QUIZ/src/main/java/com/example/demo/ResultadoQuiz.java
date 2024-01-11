package com.example.demo;

public class ResultadoQuiz {
	private Clasificacion clasificacion;

	public Clasificacion getClasificacion() {
		return clasificacion;
	}

	public void setClasificacion(Clasificacion clasificacion) {
		this.clasificacion = clasificacion;
	}

	public ResultadoQuiz(Clasificacion clasificacion) {
		super();
		this.clasificacion = clasificacion;
	}
	

}
