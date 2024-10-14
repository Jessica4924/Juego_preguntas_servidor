package com.juego.preguntas;

import java.io.IOException;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/resultado")
public class ResultadoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        boolean ganado = Boolean.parseBoolean(request.getParameter("ganado"));

        if (!ganado) {
            request.setAttribute("mensaje", "Perdiste. ¡Intenta de nuevo!");
        } else {
            request.setAttribute("mensaje", "¡Felicidades! Completaste todas las preguntas.");
        }

        request.getRequestDispatcher("resultado.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Invalida la sesión para reiniciar desde cero
        HttpSession session = request.getSession();
        session.invalidate();

        // Redirige al formulario de inicio
        response.sendRedirect("index.jsp");
    }
}
