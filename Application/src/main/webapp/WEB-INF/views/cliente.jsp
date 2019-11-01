<!DOCTYPE html>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
	<head>
		<title>Ver un cliente</title>
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
		<h1 class="jumbotron">BibliotecApp - Ver un cliente</h1>
		
		<div class="container">
			<h2>Ver un cliente</h2>
		</div>
		<div class="container mt-4 ">	
			<div class="col-sm card-deck text-center">
				<div class="card mb-4 shadow-sm">
					<div class="card-header">
						<h4><c:out value="${cliente.nombre}" /> <c:out value="${cliente.apellido}" /></h4>
					</div>
					<div class="card-body">
						<p>Tel : <b><c:out value="${cliente.telefono}" /></b></p>
						<p>Vive en el <b><c:out value="${cliente.direccion}" /></b></p>				
						<p>Mail :  <b><c:out value="${cliente.email}" /></b></p>
						<p>Su suscripcion empezo el <b><c:out value="${cliente.inicioSuscripcion}" /></b>
						y terminara el <b><c:out value="${cliente.finSuscripcion}" /></b></p>
						<button type="button" class="col-sm mr-2 btn btn-lg btn-block btn-primary">Modificar</button>
					</div>
				</div>
			</div>
		</div>
		
		
	</body>
</html>