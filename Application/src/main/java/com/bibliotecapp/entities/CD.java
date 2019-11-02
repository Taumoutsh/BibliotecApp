package com.bibliotecapp.entities;

import javax.persistence.Column;

public class CD extends Articulo {
	
	int numeroPistas;

	public CD() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CD(int id, String titulo, String autor, String identificador, boolean estado, int numeroPistas, Tipo unTipo, Tema unTema) {
		super(id, titulo, autor, identificador, estado, unTipo, unTema);
		this.numeroPistas = numeroPistas;
		// TODO Auto-generated constructor stub
	}

	@Column(name="Ar_numeroPistas")
	public int getNumeroPistas() {
		return numeroPistas;
	}

	public void setNumeroPistas(int nombrePistas) {
		this.numeroPistas = nombrePistas;
	}

}

