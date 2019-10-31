<!DOCTYPE html>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
	<head>
		<title>Página principal</title>
		<meta charset="utf-8">
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	</head>
	<body>
		<h1 class="jumbotron">BibliotecApp - Página principal</h1>
		<div class="container">
			<h2>Todos los articulos inscritos</h2>
			<c:forEach items="${todosArticulos}" var="articulo">
				<h3>Articulo numero <c:out value="${articulo.id}" />
					<c:if test="${articulo.estado eq true}">
					<span class="badge badge-success">Disponible</span>
				</c:if>
				<c:if test="${articulo.estado eq false}">
					<span class="badge badge-danger">No disponible</span>
				</c:if>
				</h3>
				<p><c:out value="${articulo.titulo}" /> de <c:out value="${articulo.autor}" /></p>
				<p>ID : <c:out value="${articulo.identificador}" /><p>

				<p>Tema : <c:out value="${articulo.unTema.mensaje}" /></p>
				<p>Tipo : <c:out value="${articulo.unTipo.mensaje}" /></p>
			</c:forEach>
		</div>
		
		
	</body>
</html>