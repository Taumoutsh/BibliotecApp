<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<div class="d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb-3 bg-white border-bottom shadow-sm">
	<h5 class="my-0 mr-md-auto font-weight-normal">BibliotecApp</h5>
	<nav class="my-2 my-md-0 mr-md-3">
		<a class="p-2 text-dark" href="${contextPath}/index">Pagina principal</a>
		<a class="p-2 text-dark" href="${contextPath}/articulosToClientes/todos">Ver los prestados</a>
		<a class="p-2 text-dark" href="${contextPath}/videojuegos/todos">Video juegos</a>
		<a class="p-2 text-dark" href="${contextPath}/libros/todos">Libros</a>
		<a class="p-2 text-dark" href="${contextPath}/cds/todos">CDs</a>
		<a class="p-2 text-dark" href="${contextPath}/dvds/todos">DVDs</a>
		<a class="p-2 text-dark" href="${contextPath}/clientes/todos">Clientes</a>
	</nav>
</div>