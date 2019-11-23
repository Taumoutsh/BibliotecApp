package com.bibliotecapp.entities;

import javax.persistence.Column;

public class Libro extends Articulo {
	
	int numeroPaginas;

	public Libro() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Libro(int id, String titulo, String autor, String identificador, boolean estado, int numeroPaginas, Tipo unTipo, Tema unTema, boolean archivo) {
		super(id, titulo, autor, identificador, estado, unTipo, unTema, archivo);
		this.numeroPaginas = numeroPaginas;
		// TODO Auto-generated constructor stub
	}

	@Column(name="Ar_numeroPaginas")
	public int getNumeroPaginas() {
		return numeroPaginas;
	}

	public void setNumeroPaginas(int numeroPaginas) {
		this.numeroPaginas = numeroPaginas;
	}

}
