<!DOCTYPE html>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
	<head>
	</head>
	<body>
		<p>Bonjour à tous</p>
		<c:forEach items="${todosTemas}" var="tema">
			<p><c:out value="${tema.mensaje}" /></p>
		</c:forEach>
		
	</body>
</html>