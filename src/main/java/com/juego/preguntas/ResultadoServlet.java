package com.juego.preguntas;

import java.io.IOException;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/resultado")
public class ResultadoServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String ganado = request.getParameter("ganado");
        request.setAttribute("ganado", "true".equals(ganado));
        request.getRequestDispatcher("resultado.jsp").forward(request, response);
    }
}

