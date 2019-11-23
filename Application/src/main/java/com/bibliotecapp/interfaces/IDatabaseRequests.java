package com.bibliotecapp.interfaces;

import java.util.List;

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
	
	public List<Tema> obtenerTodosTemas() throws BDException;
	public List<Tipo> obtenerTodosTipos() throws BDException;
	public List<Articulo> obtenerTodosArticulos(boolean archivo) throws BDException;
	public List<VideoJuego> obtenerTodosVideoJuegos(boolean archivo) throws BDException;
	public List<Libro> obtenerTodosLibros(boolean archivo) throws BDException;
	public List<DVD> obtenerTodosDVDs(boolean archivo) throws BDException;
	public List<CD> obtenerTodosCDs(boolean archivo) throws BDException;
	public List<Cliente> obtenerTodosClientes(boolean archivo) throws BDException;
	
	public List<ArticuloToCliente> obtenerTodosArticulosToClientes(boolean archivo) throws BDException;
	public List<Articulo> obtenerTodosArticulosPorTema(int idTema, boolean archivo) throws BDException;
	public List<Articulo> obtenerTodosArticulosPorTipo(int idTipo, boolean archivo) throws BDException;
	public List<Articulo> obtenerTodosArticulosPorEstado(boolean estado, boolean archivo) throws BDException;
	public List<Articulo> obtenerTodosArticulosPorTemaYTipo(int idTema, int idTipo, boolean archivo) throws BDException;
	
	public List<ArticuloToCliente> obtenerTodosArticulosToClientePorCliente(int idCliente, boolean archivo) throws BDException;
	public List<ArticuloToCliente> obtenerTodosArticulosToClientePorArticulo(int idArticulo, boolean archivo) throws BDException;
	public List<ArticuloToCliente> obtenerTodosArticulosToClientePorArticuloYCliente(int idArticulo, int idCliente, boolean archivo) throws BDException;
	
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
	public void archivarCliente(int idCliente) throws BDException;
	
	public void archivarDvd(int idDVD) throws BDException;
	public void modificarDvd(DVD dvd) throws BDException;
	
	public void archivarCd(int idCD) throws BDException;
	public void modificarCd(CD cd) throws BDException;
	
	public void anadirPrestacion(ArticuloToCliente atc) throws BDException;
	public void modificarPrestacion(ArticuloToCliente atc) throws BDException;
	

}
