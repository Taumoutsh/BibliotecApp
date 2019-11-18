package com.bibliotecapp.entities;

import javax.persistence.Column;

public class VideoJuego extends Articulo {
	
	String plataforma;

	public VideoJuego() {
		super();
	}

	public VideoJuego(int id, String titulo, String autor, String identificador, boolean estado, String plataforma, Tipo unTipo, Tema unTema) {
		super(id, titulo, autor, identificador, estado, unTipo, unTema);
		this.plataforma = plataforma;
	}

	@Column(name="Ar_plataforma")
	public String getPlataforma() {
		return plataforma;
	}

	public void setPlataforma(String plataforma) {
		this.plataforma = plataforma;
	}

}

