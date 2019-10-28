package com.bibliotecapp.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name="Tema")
public class Tema implements Serializable {
	
	private int id;
	private String mensaje;
	
	public Tema() {
	}
	
	public Tema(int id, String mensaje) {
		this.id = id;
		this.mensaje = mensaje;
	}
	
	@Id
	@GeneratedValue()
	@Column(name="Te_id")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name="Te_mensaje")
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

}
