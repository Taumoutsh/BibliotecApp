<!DOCTYPE html>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
	<head>
		<title>Ver un cliente</title>
		<meta charset="utf-8">
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	</head>
	<body>
	    <jsp:include page="header.jsp"></jsp:include>
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