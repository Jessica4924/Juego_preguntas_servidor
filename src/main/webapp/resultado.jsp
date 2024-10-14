<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
    <title>Resultado</title>
</head>
<body>
    <h1>${mensaje}</h1>  <!-- Muestra si ganaste o perdiste -->

    <!-- Botón para reiniciar el juego -->
    <form action="resultado" method="post">
        <button type="submit">Reiniciar Juego</button>
    </form>
</body>
</html>

