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
@Table(name="ArticuloToCliente")
public class ArticuloToCliente implements Serializable  {
	
	private int id;
	private String fechaPrestamo;
	private String fechaPanificadaDevolucion;
	private String fechaRealDevolucion;
	private Cliente unCliente;
	private Articulo unArticulo;
	private boolean archivo;
	
	public ArticuloToCliente() {
		super();
	}

	public ArticuloToCliente(int id, String fechaPrestamo, String fechaPanificadaDevolucion, String fechaRealDevolucion,
			Cliente unCliente, Articulo unArticulo, boolean archivo) {
		super();
		this.id = id;
		this.fechaPrestamo = fechaPrestamo;
		this.fechaPanificadaDevolucion = fechaPanificadaDevolucion;
		this.fechaRealDevolucion = fechaRealDevolucion;
		this.unCliente = unCliente;
		this.unArticulo = unArticulo;
		this.archivo = archivo;
	}

	@Id
    @GeneratedValue()
    @Column(name="At_id")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name="At_fechaPrestamo")
	public String getFechaPrestamo() {
		return fechaPrestamo;
	}

	public void setFechaPrestamo(String fechaPrestamo) {
		this.fechaPrestamo = fechaPrestamo;
	}

	@Column(name="At_fechaPanificadaDevolucion")
	public String getFechaPanificadaDevolucion() {
		return fechaPanificadaDevolucion;
	}

	public void setFechaPanificadaDevolucion(String fechaPanificadaDevolucion) {
		this.fechaPanificadaDevolucion = fechaPanificadaDevolucion;
	}

	@Column(name="At_fechaRealDevolucion")
	public String getFechaRealDevolucion() {
		return fechaRealDevolucion;
	}

	public void setFechaRealDevolucion(String fechaRealDevolucion) {
		this.fechaRealDevolucion = fechaRealDevolucion;
	}
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_cliente")
	public Cliente getUnCliente() {
		return unCliente;
	}

	public void setUnCliente(Cliente unCliente) {
		this.unCliente = unCliente;
	}

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_articulo")
	public Articulo getUnArticulo() {
		return unArticulo;
	}

	public void setUnArticulo(Articulo unArticulo) {
		this.unArticulo = unArticulo;
	}

	public boolean isArchivo() {
		return archivo;
	}

	public void setArchivo(boolean archivo) {
		this.archivo = archivo;
	}
	
	
}
