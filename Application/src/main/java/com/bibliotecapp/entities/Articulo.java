package com.bibliotecapp.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Articulo")
public class Articulo implements Serializable  {
	
	private int id;
	private String titulo;
	private String autor;
	private String identificador;
	private boolean estado;
	private Tipo unTipo;
	private Tema unTema;
	private boolean archivo;
	
	public Articulo() {
	}
	
	public Articulo(int id, String titulo, String autor, String identificador, boolean estado, Tipo unTipo,
			Tema unTema, boolean archivo) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.autor = autor;
		this.identificador = identificador;
		this.estado = estado;
		this.unTipo = unTipo;
		this.unTema = unTema;
		this.archivo = archivo;
	}
	
	@Id
    @GeneratedValue()
    @Column(name="Ar_id")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name="Ar_titulo")
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	@Column(name="Ar_autor")
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	
	@Column(name="Ar_identificador")
	public String getIdentificador() {
		return identificador;
	}
	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}
	
	@Column(name="Ar_estado")
	public boolean isEstado() {
		return estado;
	}
	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_tema")
	public Tema getUnTema() {
		return unTema;
	}
	public void setUnTema(Tema unTema) {
		this.unTema = unTema;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_tipo")
	public Tipo getUnTipo() {
		return unTipo;
	}
	public void setUnTipo(Tipo unTipo) {
		this.unTipo = unTipo;
	}
	
	@Column(name="Ar_archivo")
	public boolean isArchivo() {
		return archivo;
	}

	public void setArchivo(boolean archivo) {
		this.archivo = archivo;
	}
	
	
}
