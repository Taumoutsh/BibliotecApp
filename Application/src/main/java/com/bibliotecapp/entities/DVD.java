package com.bibliotecapp.entities;

import javax.persistence.Column;

public class DVD extends Articulo {
	
	String qualidad;

	public DVD() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DVD(int id, String titulo, String autor, String identificador, boolean estado, String qualidad, Tipo unTipo, Tema unTema, boolean archivo) {
		super(id, titulo, autor, identificador, estado, unTipo, unTema, archivo);
		this.qualidad = qualidad;
		// TODO Auto-generated constructor stub
	}

	@Column(name="Ar_qualidad")
	public String getQualidad() {
		return qualidad;
	}

	public void setQualidad(String qualidad) {
		this.qualidad = qualidad;
	}

}

