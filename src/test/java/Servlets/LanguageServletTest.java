package Servlets;

import org.junit.jupiter.api.Test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LanguageServletTest {
    HttpServletResponse response = mock(HttpServletResponse.class);
    HttpServletRequest request = mock(HttpServletRequest.class);
    HttpSession session = mock(HttpSession.class);
    @Test
    void doGet() throws ServletException, IOException {
        String language = "en";
        String responsePage = "index.jsp";
        when(request.getParameter("language")).thenReturn(language);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("responsePage")).thenReturn(responsePage);
        LanguageServlet languageServlet = new LanguageServlet();
        languageServlet.doGet(request,response);
        verify(response).sendRedirect(responsePage);
    }
}