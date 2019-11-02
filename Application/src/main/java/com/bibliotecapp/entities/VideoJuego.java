package com.bibliotecapp.entities;

import javax.persistence.Column;

public class VideoJuego extends Articulo {
	
	String plataforma;

	public VideoJuego() {
		super();
		// TODO Auto-generated constructor stub
	}

	public VideoJuego(int id, String titulo, String autor, String identificador, boolean estado, String plataforma, Tipo unTipo, Tema unTema) {
		super(id, titulo, autor, identificador, estado, unTipo, unTema);
		this.plataforma = plataforma;
		// TODO Auto-generated constructor stub
	}

	@Column(name="Ar_plataforma")
	public String getPlataforma() {
		return plataforma;
	}

	public void setPlataforma(String plataforma) {
		this.plataforma = plataforma;
	}

}

