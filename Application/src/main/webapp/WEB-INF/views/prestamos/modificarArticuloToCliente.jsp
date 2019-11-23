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
		<h1 class="jumbotron">BibliotecApp - Modificar CD</h1>
  
  		<div class="container">
	       	<form:form method="post" action="../saveModificar">
	       		<form:input path="id" value="${ArticuloToCliente.id}" type="hidden"/>
	        	<table>    
		         <tr>    
		          <td>Fecha de prestamo (YYYY-MM-DD) : </td>   
		          <td><form:input path="fechaPrestamo" value="${ArticuloToCliente.fechaPrestamo}" class="form-control"/></td>  
		         </tr>    
		         <tr>    
		          <td>Fecha planificada de devolucion (YYYY-MM-DD) :</td>    
		          <td><form:input path="fechaPanificadaDevolucion" value="${ArticuloToCliente.fechaPanificadaDevolucion}" class="form-control"/></td>  
		         </tr> 
		         <tr>    
		          <td>Fecha real de devolucion (YYYY-MM-DD) :</td>    
		          <td><form:input path="fechaRealDevolucion" value="${ArticuloToCliente.fechaRealDevolucion}" class="form-control"/></td>  
		         </tr>  
		         <tr>    
		          <td>Articulo prestado :</td> 
		          <td>   
		          <form:select path="unArticulo.id" class="form-control">
		          	<form:option value="${ArticuloToCliente.unArticulo.id}" label="${ArticuloToCliente.unArticulo.titulo} - ${ArticuloToCliente.unArticulo.unTipo.mensaje}" selected="true" />
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
		         	<form:option value="${ArticuloToCliente.unCliente.id}" label="${ArticuloToCliente.unCliente.nombre} ${ArticuloToCliente.unCliente.apellido}" selected="true" />
		         	<c:forEach items="${clientes}" var="cliente">
				    <form:option value="${cliente.id}" label="${cliente.nombre} ${cliente.apellido}" />
				    </c:forEach>
				 </form:select>
				 </td>
				 </tr>
				 <tr>    
		          <td>Archivar :</td>
		          <td>
		          <c:choose>
					    <c:when test="${ArticuloToCliente.isArchivo() == true}">
					        <form:radiobutton path="archivo" value="1" checked="checked" />Si /
					  		<form:radiobutton path="archivo" value="0"/>No
					    </c:when>    
					    <c:otherwise>
					        <form:radiobutton path="archivo" value="1"/>Si /
					  		<form:radiobutton path="archivo" value="0" checked="checked" />No 
					    </c:otherwise>
					</c:choose>
				  </td> 
		         </tr>
		         <tr>     
		          <td colspan="2"><input type="submit" value="Modificar" class="btn btn-primary mt-3"/></td>    
		         </tr>    
		        </table>    
	       </form:form> 
       </div>  

</body>
</html>