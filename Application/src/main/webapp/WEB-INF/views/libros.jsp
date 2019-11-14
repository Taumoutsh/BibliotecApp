<!DOCTYPE html>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
	<head>
		<title>Libros</title>
		<meta charset="utf-8">
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	</head>
	<body>
	    
	    <jsp:include page="header.jsp"></jsp:include>
	    
		<h1 class="jumbotron">BibliotecApp - Libros</h1>
		
		<div class="container">
			<h2>Buscada personalisada</h2>
		</div>
		
		<div class="container">
			<h2>Todos los libros</h2>
		</div>
		<div class="container mt-4 ">	
			<c:set var="i" value="1" />
			<c:forEach items="${todosLibros}" var="libro">
				<c:if test="${(i%3) == 1}">
				<div class="row justify-content-center">
				</c:if>
					<div class="col-4 card-deck text-center">
						<div class="card mb-4 shadow-sm">
							<div class="card-header">
								<h4><c:out value="${libro.titulo}" /></h4>
							<c:if test="${libro.estado eq true}">
								<span class="badge badge-success">Disponible</span>
							</c:if>
							<c:if test="${libro.estado eq false}">
								<span class="badge badge-danger">No disponible</span>
							</c:if>
							</div>
							<div class="card-body">
								<p>De <b><c:out value="${libro.autor}" /></b></p>
								<p>Su identificafor es <b><c:out value="${libro.identificador}" /></b></p>
								<p>Es un <b><c:out value="${videoJuego.unTipo.mensaje}" /></b>
								del tema <b><c:out value="${videoJuego.unTema.mensaje}" /></b></p>
								<p>El libro tiene <b><c:out value="${libro.numeroPaginas}" /></b> paginas</p>
								<button type="button" class="col-sm mr-2 btn btn btn-block btn-primary">Modificar</button>
								<button type="button" class="col-sm mr-2 btn btn btn-block btn-danger">Borrar</button>
					     	</div>
						</div>
					</div>
				<c:if test="${(i%3) == 0}">
				</div>
				</c:if>
			<c:set var="i" value="${i+1}"/>
			</c:forEach>
		</div>
		
		
	</body>
</html>