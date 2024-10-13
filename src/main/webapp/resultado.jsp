<c:choose>
    <c:when test="${ganado}">
        <h2>¡Felicidades, ganaste!</h2>
    </c:when>
    <c:otherwise>
        <h2>Lo siento, perdiste.</h2>
    </c:otherwise>
</c:choose>
<a href="index.jsp">Reiniciar Juego</a>
