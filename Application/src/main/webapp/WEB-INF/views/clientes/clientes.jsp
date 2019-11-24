<!DOCTYPE html>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
	<head>
		<title>Ver los clientes</title>
		<meta charset="utf-8">
		<script src="https://kit.fontawesome.com/7d402c1f4f.js" crossorigin="anonymous"></script>
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	</head>
	<body>
	    <jsp:include page="../header.jsp"></jsp:include>
		<h1 class="jumbotron">BibliotecApp - Todos los clientes</h1>

		<div class="container">
			<h2>Todos los clientes</h2>
			<hr>
			<div class="row justify-content-center mt-4">
				<div class="col-md-3">
					<p><a class="mb-4" href="${contextPath}/clientes/anadir"><i class="fas fa-user-plus"></i> Anadir un cliente</a></p>
				</div>
			<c:choose>
		         <c:when test = "${archivos == false}">
		            <div class="col-md-3 offset-md-6">
						<a class="mb-4" href="${contextPath}/clientes/todosArchivos"><i class="fas fa-archive"></i> Ver los clientes archivados</a>
					</div>
		         </c:when>
		         <c:when test = "${archivos == true}">
		            <div class="col-md-3 offset-md-6">
						<a class="mb-4" href="${contextPath}/clientes/todos"><i class="fas fa-arrow-left"></i> Volver a los clientes</a>
					</div>
		         </c:when>
     		 </c:choose>
      		</div>
		</div>
		<div class="container mt-4 ">	
			<c:set var="i" value="1" />
			<c:forEach items="${todosClientes}" var="cliente">
				<c:if test="${(i%2) == 1}">
					<div class="row justify-content-center">
				</c:if>
						<div class="col-6 card-deck text-center">
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
									<c:set var="idString">${cliente.id}</c:set>
									<a href="${contextPath}/clientes/cliente?id=<c:out value="${idString}" />"><i class="fas fa-search-plus"></i> Ver</a> |
									<a href="${contextPath}/clientes/modificar?id=<c:out value="${idString}" />"><i class="fas fa-user-edit"></i> Modificar</a> 
									<c:if test = "${archivos == false}">
								  | <a href="${contextPath}/clientes/archivar?id=<c:out value="${idString}" />"><i class="fas fa-user-minus"></i> Archivar</a> 
								</c:if>
								</div>
							</div>
						</div>
				<c:if test="${(i%2) == 0}">
					</div>
				</c:if>
			<c:set var="i" value="${i+1}"/>
			</c:forEach>
		</div>
		
		
	</body>
</html>