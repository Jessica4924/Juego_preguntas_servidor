<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Resultado</title>
</head>
<body>
    <c:choose>
        <c:when test="${param.ganado eq 'true'}">
            <h2>¡Felicidades! Has ganado el juego.</h2>
        </c:when>
        <c:otherwise>
            <h2>Lo siento, has perdido. Inténtalo de nuevo.</h2>
        </c:otherwise>
    </c:choose>

    <form action="inicio" method="post">
        <button type="submit">Reiniciar Juego</button>
    </form>
</body>
</html>

