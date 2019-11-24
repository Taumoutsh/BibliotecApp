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
	    <c:choose>
         <c:when test = "${archivos == true}">
            <h1 class="jumbotron">BibliotecApp - Todos los préstamos archivados</h1>
         </c:when>
         <c:when test = "${archivos == false}">
            <h1 class="jumbotron">BibliotecApp - Todos los préstamos</h1>
         </c:when>
      </c:choose>

		<div class="container">
			<h2>Todos los préstamos</h2>
			<div class="row justify-content-center">
				<div class="col-md-3">
					<a class="mb-4" href="${contextPath}/articulosToClientes/anadir"><i class="fas fa-plus"></i> Anadir un préstamo</a>
				</div>
		<c:choose>
         <c:when test = "${archivos == false}">
            <div class="col-md-3 offset-md-6">
				<a class="mb-4" href="${contextPath}/articulosToClientes/todosArchivos"><i class="fas fa-archive"></i> Ver los prestamos archivados</a>
			</div>
         </c:when>
         <c:when test = "${archivos == true}">
            <div class="col-md-3 offset-md-6">
				<a class="mb-4" href="${contextPath}/articulosToClientes/todos"><i class="fas fa-arrow-left"></i> Volver a los prestamos</a>
			</div>
         </c:when>
      </c:choose>
			</div>
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
					<td class="text-center"><a href="${contextPath}/articulosToClientes/modificar/?id=${articuloToCliente.id}"><i class="fas fa-edit"></i></a> 
				<c:if test = "${archivos == false}">
					| <a href="${contextPath}/articulosToClientes/archivar/?id=${articuloToCliente.id}"><i class="fas fa-archive"></i></a> 
				</c:if>
				</tr>
			</c:forEach>
			</table>
		</div>
		
		
	</body>
</html>