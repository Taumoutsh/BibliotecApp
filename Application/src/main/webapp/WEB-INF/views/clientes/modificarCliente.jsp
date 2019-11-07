<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE html>
<html>
<head>
	<title>Anadir un cliente</title>
	<meta charset="utf-8">
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
		
</head>
<body>

 		<jsp:include page="../header.jsp"></jsp:include>
		<h1 class="jumbotron">BibliotecApp - Modificar cliente</h1>
  
  		<div class=container>
	       	<form:form method="post" action="modificarSave">  
	       	 <form:input type="hidden" path="id" value="${cliente.getId()}"/>  
	        	<table>    
		         <tr>    
		          <td>Nombre : </td>   
		          <td><form:input path="nombre" value="${cliente.getNombre()}" class="form-control"/></td>  
		         </tr>    
		         <tr>    
		          <td>Apellido :</td>    
		          <td><form:input path="apellido" value="${cliente.getApellido()}" class="form-control"/></td>  
		         </tr>   
		         <tr>    
		          <td>Telefono :</td>    
		          <td><form:input path="telefono" value="${cliente.getTelefono()}" class="form-control"/></td>  
		         </tr> 
		         <tr>    
		          <td>Direccion :</td>    
		          <td><form:input path="direccion" value="${cliente.getDireccion()}" class="form-control"/></td>  
		         </tr> 
		         <tr>    
		          <td>Email :</td>    
		          <td><form:input path="email" value="${cliente.getEmail()}" class="form-control"/></td>  
		         </tr> 
		         <tr>    
		          <td>Inicio suscripcion :</td>    
		          <td><form:input type="date" path="inicioSuscripcion" value="${cliente.getInicioSuscripcion()}" class="form-control"/></td>  
		         </tr> 
		         <tr>    
		          <td>Fin suscripcion :</td>    
		          <td><form:input type="date" path="finSuscripcion" value="${cliente.getFinSuscripcion()}" class="form-control"/></td>  
		         </tr>    
		          <td colspan="2"><input type="submit" value="Modificar" class="btn btn-primary mt-3"/></td>    
		         </tr>    
		        </table>    
	       </form:form> 
       </div>  
</body>
</html>