<!DOCTYPE html>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
	<head>
		<title>CDs</title>
		<meta charset="utf-8">
		<script src="https://kit.fontawesome.com/7d402c1f4f.js" crossorigin="anonymous"></script>
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	</head>
	<body>
	    
	    <jsp:include page="../header.jsp"></jsp:include>
	    
		<c:choose>
         <c:when test = "${archivos == true}">
            <h1 class="jumbotron">BibliotecApp - Todos los CDs archivados</h1>
         </c:when>
         <c:when test = "${archivos == false}">
            <h1 class="jumbotron">BibliotecApp - Todos los CDs</h1>
         </c:when>
        </c:choose>
		
		<div class="container">
			<div class="row justify-content-center mt-4">
				<div class="col-md-3">
					<p><a class="mb-4" href="${contextPath}/cds/anadir"><i class="fas fa-plus"></i> Anadir un CD</a></p>
				</div>
			<c:choose>
		         <c:when test = "${archivos == false}">
		            <div class="col-md-3 offset-md-6">
						<a class="mb-4" href="${contextPath}/cds/todosArchivos"><i class="fas fa-archive"></i> Ver los CDs archivados</a>
					</div>
		         </c:when>
		         <c:when test = "${archivos == true}">
		            <div class="col-md-3 offset-md-6">
						<a class="mb-4" href="${contextPath}/cds/todos"><i class="fas fa-arrow-left"></i> Volver a los CDs</a>
					</div>
		         </c:when>
     		 </c:choose>
		</div>
		<div class="container mt-4 ">	
			<c:set var="i" value="1" />
			<c:forEach items="${todosCDs}" var="cd">
				<c:if test="${(i%3) == 1}">
				<div class="row justify-content-center">
				</c:if>
					<div class="col-4 card-deck text-center">
						<div class="card mb-4 shadow-sm">
							<div class="card-header">
								<h4><c:out value="${cd.titulo}" /></h4>
							<c:if test="${cd.estado eq true}">
								<span class="badge badge-success">Disponible</span>
							</c:if>
							<c:if test="${cd.estado eq false}">
								<span class="badge badge-danger">No disponible</span>
							</c:if>
							</div>
							<div class="card-body">
								<p>De <b><c:out value="${cd.autor}" /></b></p>
								<p>Su identificafor es <b><c:out value="${cd.identificador}" /></b></p>
								<p>Es un <b><c:out value="${cd.unTipo.mensaje}" /></b>
								del tema <b><c:out value="${cd.unTema.mensaje}" /></b></p>
								<p>El CD tiene <b><c:out value="${cd.numeroPistas}" /></b> pistas</p>
								<button type="button" onclick="window.location.href='${contextPath}/cds/modificar?id=<c:out value='${cd.id}'/>'" class="col-sm mr-2 btn btn btn-block btn-primary">Modificar</button>
								<button type="button" onclick="window.location.href='${contextPath}/cds/borrar?id=<c:out value='${cd.id}'/>'" class="col-sm mr-2 btn btn btn-block btn-danger">Borrar</button>
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