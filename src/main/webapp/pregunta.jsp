<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
</head>
<body>
    <h1>¡Bienvenido, <c:out value="${jugador}" />!</h1>

    <form action="pregunta" method="post">
        <p><strong>Pregunta:</strong> <c:out value="${pregunta}" /></p>

        <c:forEach var="opcion" items="${opciones}">
            <input type="radio" name="opcion" value="${opcion}" required>
            <c:out value="${opcion}" /><br>
        </c:forEach>

        <button type="submit">Responder</button>
    </form>
</body>
</html>

