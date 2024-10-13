package com.juego.preguntas;

import java.io.IOException;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/pregunta")
public class PreguntaServlet extends HttpServlet {
    private static final String[] PREGUNTAS = {
            "¿Capital de Ecuador?",
            "¿Color del cielo?",
            "¿5 + 3?",
            "¿Animal que ladra?",
            "¿Agua congelada es?",
            "¿Nombre del satélite natural de la Tierra?"
    };
    private static final String[] RESPUESTAS = {"Quito", "Azul", "8", "Perro", "Hielo", "Luna"};

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        int preguntaActual = (int) session.getAttribute("preguntaActual");

        if (preguntaActual >= PREGUNTAS.length) {
            response.sendRedirect("resultado?ganado=true");
        } else {
            request.setAttribute("pregunta", PREGUNTAS[preguntaActual]);
            request.getRequestDispatcher("pregunta.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        int preguntaActual = (int) session.getAttribute("preguntaActual");
        String respuesta = request.getParameter("respuesta");

        if (RESPUESTAS[preguntaActual].equalsIgnoreCase(respuesta)) {
            session.setAttribute("preguntaActual", preguntaActual + 1);
            response.sendRedirect("pregunta");
        } else {
            response.sendRedirect("resultado?ganado=false");
        }
    }
}

