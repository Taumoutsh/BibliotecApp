<!DOCTYPE html>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
	<head>
		<title>Página principal</title>
		<meta charset="utf-8">
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	</head>
	<body>
	    <div class="d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb-3 bg-white border-bottom shadow-sm">
		  <h5 class="my-0 mr-md-auto font-weight-normal">BibliotecApp</h5>
		  <nav class="my-2 my-md-0 mr-md-3">
		    <a class="p-2 text-dark" href="${contextPath}/index">Pagina principal</a>
		    <a class="p-2 text-dark" href="#">Ver los prestados</a>
		    <a class="p-2 text-dark" href="#">Ver los articulos</a>
		    <a class="p-2 text-dark" href="${contextPath}/clientes/todos">Ver los clientes</a>
		  </nav>
		</div>
		<h1 class="jumbotron">BibliotecApp - Página principal</h1>
		
		<div class="container">
			<h2>Buscada personalisada</h2>
		</div>
		
		<div class="container">
			<h2>Todos los articulos inscritos</h2>
		</div>
		<div class="container mt-4 ">	
			<c:set var="i" value="1" />
			<c:forEach items="${todosArticulos}" var="articulo">
				<c:if test="${(i%4) == 1}">
				<div class="row">
				</c:if>
					<div class="col-sm card-deck text-center">
						<div class="card mb-4 shadow-sm">
							<div class="card-header">
								<h4><c:out value="${articulo.titulo}" /></h4>
							<c:if test="${articulo.estado eq true}">
								<span class="badge badge-success">Disponible</span>
							</c:if>
							<c:if test="${articulo.estado eq false}">
								<span class="badge badge-danger">No disponible</span>
							</c:if>
							</div>
							<div class="card-body">
								<p>De <b><c:out value="${articulo.autor}" /></b></p>
								<p>Su identificafor es <b><c:out value="${articulo.identificador}" /></b></p>				
								<p>Es un <b><c:out value="${articulo.unTipo.mensaje}" /></b>
								del tema <b><c:out value="${articulo.unTema.mensaje}" /></b></p>
								<button type="button" class="col-sm mr-2 btn btn-lg btn-block btn-primary">Modificar</button>
					     	</div>
						</div>
					</div>
				<c:if test="${(i%4) == 0}">
				</div>
				</c:if>
			<c:set var="i" value="${i+1}"/>
			</c:forEach>
		</div>
		
		
	</body>
</html>