package com.juego.preguntas;
import java.io.IOException;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/inicio")
public class InicioServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nombre = request.getParameter("nombre");
        HttpSession session = request.getSession();
        session.setAttribute("jugador", nombre);
        session.setAttribute("preguntaActual", 0);
        Integer juegos = (Integer) session.getAttribute("juegos");
        if (juegos == null) {
            juegos = 0;
        }
        session.setAttribute("juegos", juegos + 1);

        session.setAttribute("ip", request.getRemoteAddr());
        response.sendRedirect("pregunta");
    }
}
