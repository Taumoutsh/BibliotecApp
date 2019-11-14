<!DOCTYPE html>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
	<head>
		<title>Todos los prestamos</title>
		<meta charset="utf-8">
		<script src="https://kit.fontawesome.com/7d402c1f4f.js" crossorigin="anonymous"></script>
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	</head>
	<body>
	    <jsp:include page="../header.jsp"></jsp:include>
		<h1 class="jumbotron">BibliotecApp - Todos los préstamos</h1>
		
		<div class="container">
			<h2>Buscada personalisada</h2>
			<p>//TODO</p>
			<p>Archivar y borrar aren't programmed yet</p>
		</div>
		
		<div class="container">
			<h2>Todos los préstamos</h2>
			<a class="mb-4" href="${contextPath}/articulosToClientes/anadir"><i class="fas fa-plus"></i> Anadir un préstamo</a>
		</div>
		<div class="container mt-4 ">
			<table class="table table-striped">
				<tr>
					<th>Cliente</th>
					<th>Articulo</th>
					<th>Fecha de préstamo</th>
					<th>Fecha planificada de devolución</th>
					<th>Fecha real de devolución</th>
					<th>Acciones</th>
				</tr>
			<c:forEach items="${todosArticulosToClientes}" var="articuloToCliente">
				<tr>
					<td><a href="${contextPath}/clientes/cliente/?id=${articuloToCliente.unCliente.id}">${articuloToCliente.unCliente.nombre} ${articuloToCliente.unCliente.apellido}</a></td>
					<td><a href="${contextPath}/articulos/articulo/?id=${articuloToCliente.unArticulo.id}">${articuloToCliente.unArticulo.titulo}</a></td>
					<td>${articuloToCliente.fechaPrestamo}</td>
					<td>${articuloToCliente.fechaPanificadaDevolucion}</td>
					<td>${articuloToCliente.fechaRealDevolucion}</td>
					<td class="text-center"><a href="${contextPath}/articulosToClientes/modificar/?id=${articuloToCliente.id}"><i class="fas fa-edit"></i></a> | 
					<a href="${contextPath}/articulosToClientes/archivar/?id=${articuloToCliente.id}"><i class="fas fa-archive"></i></a> | 
					<a href="${contextPath}/articulosToClientes/borrar/?id=${articuloToCliente.id}"><i class="fas fa-trash-alt"></i></a></td>
				</tr>
			</c:forEach>
			</table>
		</div>
		
		
	</body>
</html>