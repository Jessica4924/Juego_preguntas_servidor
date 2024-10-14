package com.juego.preguntas;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.IOException;

import static org.mockito.Mockito.*;

public class PreguntaServletTest {

    @InjectMocks
    private PreguntaServlet preguntaServlet;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private HttpSession session;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Inicializa los mocks
    }

    @Test
    void testPreguntaCorrecta() throws ServletException, IOException {
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("preguntaActual")).thenReturn(0);
        when(request.getParameter("opcion")).thenReturn("Quito");

        // Simulamos el flujo correcto
        preguntaServlet.doPost(request, response);

        // Verificamos que avanza a la siguiente pregunta
        verify(session).setAttribute("preguntaActual", 1);
        verify(response).sendRedirect("pregunta");
    }

    @Test
    void testPreguntaIncorrecta() throws ServletException, IOException {
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("preguntaActual")).thenReturn(0);
        when(request.getParameter("opcion")).thenReturn("Guayaquil"); // Respuesta incorrecta

        // Simulamos el flujo de respuesta incorrecta
        preguntaServlet.doPost(request, response);

        // Verificamos que redirige al resultado con derrota
        verify(response).sendRedirect("resultado?ganado=false");
    }
    @Test
    public void testDoGet_RedireccionCorrecta() throws IOException, ServletException {
        // Crear instancias simuladas de request, response y session
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        // Configurar el comportamiento simulado
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("preguntaActual")).thenReturn(0);
        when(request.getRequestDispatcher("pregunta.jsp")).thenReturn(dispatcher);

        // Instanciar el servlet y ejecutar el método doGet
        PreguntaServlet servlet = new PreguntaServlet();
        servlet.doGet(request, response);

        // Verificar que el servlet configure los atributos y realice la redirección correctamente
        verify(request).setAttribute(eq("pregunta"), anyString());
        verify(dispatcher).forward(request, response);
    }

}

