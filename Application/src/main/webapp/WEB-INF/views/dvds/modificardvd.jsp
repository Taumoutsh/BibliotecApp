<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE html>
<html>
<head>
	<title>Anadir un Articulo</title>
	<meta charset="utf-8">
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
		
</head>
<body>

 		<jsp:include page="../header.jsp"></jsp:include>
		<h1 class="jumbotron">BibliotecApp - Modificar articulo</h1>
  
  		<div class=container>
	       	<form:form method="post" action="modificarSave">  
	       	 <form:input type="hidden" path="id" value="${dvd.getId()}"/>  
	        	<table>    
		         <tr>    
		          <td>Nombre : </td>   
		          <td><form:input path="titulo" value="${dvd.getTitulo()}" class="form-control"/></td>  
		         </tr>    
		         <tr>    
		          <td>Autor :</td>    
		          <td><form:input path="autor" value="${dvd.getAutor()}" class="form-control"/></td>  
		         </tr>   
		         <tr>    
		          <td>identificator :</td>    
		          <td><form:input path="identificador" value="${dvd.getIdentificador()}" class="form-control"/></td>  
		         </tr> 
		         <tr>    
		          <td>Estado :</td>    
		          <td><form:input path="estado" value="${dvd.isEstado()}" class="form-control"/></td>  
		         </tr> 
		         <tr>    
		          <td>Tipo :</td>    
		          <td><form:input path="unTipo.id" value="${dvd.getUnTipo().getId()}" class="form-control"/></td>  
		         </tr>
		         <tr>    
		          <td>Tema :</td>    
		          <td><form:input path="unTema.id" value="${dvd.getUnTema().getId()}" class="form-control"/></td>  
		         </tr> 
		         <tr>   
		         <tr>    
		          <td>Qualidad :</td>    
		          <td><form:input path="qualidad" value="${dvd.getQualidad()}" class="form-control"/></td>  
		         </tr> 
		         <tr>     
		          <td colspan="2"><input type="submit" value="Modificar" class="btn btn-primary mt-3"/></td>    
		         </tr>    
		        </table>    
	       </form:form> 
       </div>  
</body>
</html>