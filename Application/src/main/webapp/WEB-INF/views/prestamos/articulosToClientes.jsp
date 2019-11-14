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
		<h1 class="jumbotron">BibliotecApp - Todos los prestamos</h1>
		
		<div class="container">
			<h2>Buscada personalisada</h2>
			<p>//TODO</p>
		</div>
		
		<div class="container">
			<h2>Todos los prestamos</h2>
			<a class="mb-4" href="${contextPath}/articulosToClientes/anadir"><i class="fas fa-plus"></i> Anadir un prestamo</a>
		</div>
		<div class="container mt-4 ">
			<c:forEach items="${todosArticulosToClientes}" var="articuloToCliente">
				<p><a href="${contextPath}/clientes/cliente/?id=${articuloToCliente.unCliente.id}">${articuloToCliente.unCliente.nombre} ${articuloToCliente.unCliente.apellido}</a> tomó prestado del articulo
				<a href="${contextPath}/articulos/articulo/?id=${articuloToCliente.unArticulo.id}">${articuloToCliente.unArticulo.titulo}</a> desde el ${articuloToCliente.fechaPrestamo}
				la fecha de devolucion planificada es el ${articuloToCliente.fechaPanificadaDevolucion}
				<a href="${contextPath}/articulosToClientes/modificar/?id=${articuloToCliente.id}"><i class="fas fa-edit"></i> Cambiar un prestamo</a></p>
			</c:forEach>
		</div>
		
		
	</body>
</html>