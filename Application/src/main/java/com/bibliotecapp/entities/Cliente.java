package com.bibliotecapp.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Cliente")
public class Cliente implements Serializable  {

	private int id;
	private String nombre;
	private String apellido;
	private int telefono;
	private String direccion;
	private String email;
	private String inicioSuscripcion;
	private String finSuscripcion;
	
	public Cliente() {
	}
	
	public Cliente(int id, String nombre, String apellido, int telefono, String direccion, String email,
			String inicioSuscripcion, String finSuscripcion) {
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.telefono = telefono;
		this.direccion = direccion;
		this.email = email;
		this.inicioSuscripcion = inicioSuscripcion;
		this.finSuscripcion = finSuscripcion;
	}
	
	@Id
    @GeneratedValue()
    @Column(name="Cl_id")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

    @Column(name="Cl_nombre")
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
    @Column(name="Cl_apellido")
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	
    @Column(name="Cl_telefono")
	public int getTelefono() {
		return telefono;
	}
	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}
	
    @Column(name="Cl_direccion")
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
    @Column(name="Cl_email")
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
    @Column(name="Cl_inicioSuscripcion")
	public String getInicioSuscripcion() {
		return inicioSuscripcion;
	}
	public void setInicioSuscripcion(String inicioSuscripcion) {
		this.inicioSuscripcion = inicioSuscripcion;
	}
	
    @Column(name="Cl_finSuscripcion")
	public String getFinSuscripcion() {
		return finSuscripcion;
	}
	public void setFinSuscripcion(String finSuscripcion) {
		this.finSuscripcion = finSuscripcion;
	}
	
	

	
}
