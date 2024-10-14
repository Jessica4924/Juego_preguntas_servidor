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
    private static final String[][] OPCIONES = {
            {"Quito", "Guayaquil", "Cuenca", "Ambato"},
            {"Azul", "Verde", "Rojo", "Amarillo"},
            {"5", "8", "7", "9"},
            {"Gato", "Perro", "Pájaro", "Ratón"},
            {"Hielo", "Vapor", "Líquido", "Gas"},
            {"Luna", "Sol", "Estrella", "Planeta"}
    };

    // Método GET: Mostrar la pregunta actual
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer preguntaActual = (Integer) session.getAttribute("preguntaActual");

        if (preguntaActual == null) {
            preguntaActual = 0;  // Si es nulo, empezamos en la primera pregunta
            session.setAttribute("preguntaActual", preguntaActual);
        }

        if (preguntaActual >= PREGUNTAS.length) {
            // Si ya respondió todas las preguntas, redirigir al resultado (ganado)
            response.sendRedirect("resultado?ganado=true");
        } else {
            // Enviar la pregunta actual al JSP
            request.setAttribute("jugador", session.getAttribute("jugador"));
            request.setAttribute("pregunta", PREGUNTAS[preguntaActual]);
            request.setAttribute("opciones", OPCIONES[preguntaActual]);
            request.getRequestDispatcher("pregunta.jsp").forward(request, response);
        }
    }

    // Método POST: Verificar la respuesta del usuario
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer preguntaActual = (Integer) session.getAttribute("preguntaActual");

        // Verificar la respuesta del usuario
        String respuestaUsuario = request.getParameter("opcion");

        if (respuestaUsuario != null && respuestaUsuario.equalsIgnoreCase(RESPUESTAS[preguntaActual])) {
            // Si la respuesta es correcta, avanzamos a la siguiente pregunta
            session.setAttribute("preguntaActual", preguntaActual + 1);
            response.sendRedirect("pregunta"); // Redirigir para mostrar la siguiente pregunta
        } else {
            // Si la respuesta es incorrecta, redirigir al resultado (juego perdido)
            response.sendRedirect("resultado?ganado=false");
        }
    }
}

