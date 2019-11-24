<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

		<jsp:include page="../header.jsp"></jsp:include>
		<h1 class="jumbotron">BibliotecApp - Anadir un prestamo</h1>
  
  		<div class="container">
	       	<form:form method="post" action="saveAnadir">
	        	<table>    
		         <tr>    
		          <td>Fecha de préstamo (DD-MM-YYYY) : </td>   
		          <td><form:input path="fechaPrestamo" class="form-control"/></td>  
		         </tr>    
		         <tr>    
		          <td>Fecha planificada de devolucion (DD-MM-YYYY) :</td>    
		          <td><form:input path="fechaPanificadaDevolucion" class="form-control"/></td>  
		         </tr>   
		         <tr>    
		          <td>Articulo prestado :</td> 
		          <td>   
		          <form:select path="unArticulo.id" class="form-control">
		         	<c:forEach items="${articulos}" var="articulo">
				    <form:option value="${articulo.id}" label="${articulo.titulo} - ${articulo.unTipo.mensaje}" />
				    </c:forEach>
				 </form:select> 
				 </td> 
		         </tr>
		         <tr>
		         <td>Cliente :</td>
		         <td>
		         <form:select path="unCliente.id" class="form-control">
		         	<c:forEach items="${clientes}" var="cliente">
				    <form:option value="${cliente.id}" label="${cliente.nombre} ${cliente.apellido}" />
				    </c:forEach>
				 </form:select>
				 </td> 
		         <tr>     
		          <td colspan="2"><input type="submit" value="Anadir el préstamo" class="btn btn-primary mt-3"/></td>    
		         </tr>    
		        </table>    
	       </form:form> 
       </div>  

</body>
</html>