package com.bibliotecapp.interfaces;

import java.util.ArrayList;

import com.bibliotecapp.database.BDException;
import com.bibliotecapp.entities.Articulo;
import com.bibliotecapp.entities.ArticuloToCliente;
import com.bibliotecapp.entities.CD;
import com.bibliotecapp.entities.Cliente;
import com.bibliotecapp.entities.DVD;
import com.bibliotecapp.entities.Libro;
import com.bibliotecapp.entities.Tema;
import com.bibliotecapp.entities.Tipo;
import com.bibliotecapp.entities.VideoJuego;

public interface IDatabaseRequests {
	
	//Interface of all the requests calling the database
	//From the class DatabaseRequests
	
	public ArrayList<Tema> obtenerTodosTemas() throws BDException;
	public ArrayList<Tipo> obtenerTodosTipos() throws BDException;
	public ArrayList<Articulo> obtenerTodosArticulos() throws BDException;
	public ArrayList<VideoJuego> obtenerTodosVideoJuegos() throws BDException;
	public ArrayList<Libro> obtenerTodosLibros() throws BDException;
	public ArrayList<DVD> obtenerTodosDVDs() throws BDException;
	public ArrayList<CD> obtenerTodosCDs() throws BDException;
	public ArrayList<Cliente> obtenerTodosClientes() throws BDException;
	
	public ArrayList<ArticuloToCliente> obtenerTodosArticulosToClientes() throws BDException;
	public ArrayList<Articulo> obtenerTodosArticulosPorTema(int idTema) throws BDException;
	public ArrayList<Articulo> obtenerTodosArticulosPorTipo(int idTipo) throws BDException;
	public ArrayList<Articulo> obtenerTodosArticulosPorEstado(boolean estado) throws BDException;
	public ArrayList<Articulo> obtenerTodosArticulosPorTemaYTipo(int idTema, int idTipo) throws BDException;
	
	public ArrayList<ArticuloToCliente> obtenerTodosArticulosToClientePorCliente(int idCliente) throws BDException;
	public ArrayList<ArticuloToCliente> obtenerTodosArticulosToClientePorArticulo(int idArticulo) throws BDException;
	public ArrayList<ArticuloToCliente> obtenerTodosArticulosToClientePorArticuloYCliente(int idArticulo, int idCliente) throws BDException;
	
	public Tema obtenerTemaPorId(int idTema) throws BDException;
	public Tipo obtenerTipoPorId(int idTipo) throws BDException;
	public Articulo obtenerArticuloPorId(int idArticulo) throws BDException;
	public VideoJuego obtenerVideoJuegoPorId(int idVideoJuego) throws BDException;
	public Libro obtenerLibroPorId(int idLibro) throws BDException;
	public DVD obtenerDVDPorId(int idDVD) throws BDException;
	public CD obtenerCDPorId(int idCD) throws BDException;
	public Cliente obtenerClientePorId(int idCliente) throws BDException;
	public ArticuloToCliente obtenerArticuloToClientePorId(int idArticuloToCliente) throws BDException;
	
	public void anadirCliente(Cliente cliente) throws BDException;
	public void modificarCliente(Cliente cliente) throws BDException;
	public void borrarCliente(int idCliente) throws BDException;
	
	public void borrarDvd(int idDVD) throws BDException;
	public void modificarDvd(DVD dvd) throws BDException;
	
	public void borrarCd(int idCD) throws BDException;
	public void modificarCd(CD cd) throws BDException;
	
	public void anadirPrestacion(ArticuloToCliente atc) throws BDException;
	public void modificarPrestacion(ArticuloToCliente atc) throws BDException;
	

}
