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
		<h1 class="jumbotron">BibliotecApp - Modificar CD</h1>
  
  		<div class=container>
	       	<form:form method="post" action="modificarSave">  
	       	 <form:input type="hidden" path="id" value="${cd.getId()}"/>  
	        	<table>    
		         <tr>    
		          <td>Nombre : </td>   
		          <td><form:input path="titulo" value="${cd.getTitulo()}" class="form-control"/></td>  
		         </tr>    
		         <tr>    
		          <td>Autor :</td>    
		          <td><form:input path="autor" value="${cd.getAutor()}" class="form-control"/></td>  
		         </tr>   
		         <tr>    
		          <td>identificator :</td>    
		          <td><form:input path="identificador" value="${cd.getIdentificador()}" class="form-control"/></td>  
		         </tr>
		         <tr>
		         <td>Tema :</td>
		         <td>
		         <form:select path="unTema.id" class="form-control">
		         	<c:forEach items="${temas}" var="tema">
				    <form:option value="${tema.id}" label="${tema.mensaje}" />
				    </c:forEach>
				 </form:select>
				 </td>
		         </tr>
		         <tr>   
		         <tr>    
		          <td>Numero pistas :</td>    
		          <td><form:input path="numeroPistas" value="${cd.getNumeroPistas()}" class="form-control"/></td>  
		         </tr> 
		         <tr>     
		          <td colspan="2"><input type="submit" value="Modificar" class="btn btn-primary mt-3"/></td>    
		         </tr>    
		        </table>    
	       </form:form> 
       </div>  
</body>
</html>