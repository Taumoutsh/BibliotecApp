<!DOCTYPE html>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
	<head>
		<title>Video juegos</title>
		<meta charset="utf-8">
		<script src="https://kit.fontawesome.com/7d402c1f4f.js" crossorigin="anonymous"></script>
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	</head>
	<body>
	    
	    <jsp:include page="../header.jsp"></jsp:include>
	    
		<h1 class="jumbotron">BibliotecApp - Video juegos</h1>

		<div class="container">
			<h2>Todos los video juegos</h2>
			<div class="row justify-content-center mt-4">
				<div class="col-md-3">
					<p><a class="mb-4" href="${contextPath}/videojuegos/anadir"><i class="fas fa-user-plus"></i> Anadir un video juego</a></p>
				</div>
			<c:choose>
		         <c:when test = "${archivos == false}">
		            <div class="col-md-3 offset-md-6">
						<a class="mb-4" href="${contextPath}/videojuegos/todosArchivos"><i class="fas fa-archive"></i> Ver los video juegos archivados</a>
					</div>
		         </c:when>
		         <c:when test = "${archivos == true}">
		            <div class="col-md-3 offset-md-6">
						<a class="mb-4" href="${contextPath}/videojuegos/todos"><i class="fas fa-arrow-left"></i> Volver a los video juegos</a>
					</div>
		         </c:when>
     		 </c:choose>
      		</div>
		</div>
		<div class="container mt-4 ">	
			<c:set var="i" value="1" />
			<c:forEach items="${todosVideoJuegos}" var="videoJuego">
				<c:if test="${(i%3) == 1}">
				<div class="row justify-content-center">
				</c:if>
					<div class="col-4 card-deck text-center">
						<div class="card mb-4 shadow-sm">
							<div class="card-header">
								<h4><c:out value="${videoJuego.titulo}" /></h4>
							<c:if test="${videoJuego.estado eq true}">
								<span class="badge badge-success">Disponible</span>
							</c:if>
							<c:if test="${videoJuego.estado eq false}">
								<span class="badge badge-danger">No disponible</span>
							</c:if>
							</div>
							<div class="card-body">
								<p>De <b><c:out value="${videoJuego.autor}" /></b></p>
								<p>Su identificafor es <b><c:out value="${videoJuego.identificador}" /></b></p>
								<p>Es un <b><c:out value="${videoJuego.unTipo.mensaje}" /></b>
								del tema <b><c:out value="${videoJuego.unTema.mensaje}" /></b></p>
								<p>La plataforma compatible es <b><c:out value="${videoJuego.plataforma}" /></b></p>
								<button type="button" onclick="window.location.href='${contextPath}/videojuegos/modificar?id=<c:out value='${videoJuego.id}'/>'" class="col-sm mr-2 btn btn btn-block btn-primary">Modificar</button>
								<button type="button" onclick="window.location.href='${contextPath}/videojuegos/borrar?id=<c:out value='${videoJuego.id}'/>'" class="col-sm mr-2 btn btn btn-block btn-danger">Borrar</button>
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