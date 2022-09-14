package Servlets;

import org.junit.jupiter.api.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AdminLoginServletTest {
    HttpServletResponse response = mock(HttpServletResponse.class);
    HttpServletRequest request = mock(HttpServletRequest.class);
    HttpSession session = mock(HttpSession.class);
    @Test
    void doPostTestCorrect() throws ServletException, IOException {
        String adminname = "storuk";
        String password = "sumsungj3";
        when(request.getParameter("adminname")).thenReturn(adminname);
        when(request.getParameter("password")).thenReturn(password);
        when(request.getSession()).thenReturn(session);
        RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);
        when(request.getRequestDispatcher("/requests.jsp")).thenReturn(requestDispatcher);
        AdminLoginServlet adminLoginServlet = new AdminLoginServlet();
        adminLoginServlet.doPost(request,response);
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    void doPostTestNoSuchUser() throws ServletException, IOException {
        String adminname = "storu";
        String password = "sumsungj3";
        when(request.getParameter("adminname")).thenReturn(adminname);
        when(request.getParameter("password")).thenReturn(password);
        when(request.getSession()).thenReturn(session);
        RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);
        when(request.getRequestDispatcher("/adminlogin.jsp")).thenReturn(requestDispatcher);
        AdminLoginServlet adminLoginServlet = new AdminLoginServlet();
        adminLoginServlet.doPost(request,response);
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    void doPostTestYouAreNotAdmin() throws ServletException, IOException {
        String adminname = "vlados";
        String password = "12345678";
        when(request.getParameter("adminname")).thenReturn(adminname);
        when(request.getParameter("password")).thenReturn(password);
        when(request.getSession()).thenReturn(session);
        RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);
        when(request.getRequestDispatcher("/adminlogin.jsp")).thenReturn(requestDispatcher);
        AdminLoginServlet adminLoginServlet = new AdminLoginServlet();
        adminLoginServlet.doPost(request,response);
        verify(requestDispatcher).forward(request,response);
    }
}