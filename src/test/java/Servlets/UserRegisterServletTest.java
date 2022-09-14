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

class UserRegisterServletTest {
    HttpServletResponse response = mock(HttpServletResponse.class);
    HttpServletRequest request = mock(HttpServletRequest.class);
    HttpSession session = mock(HttpSession.class);
    @Test
    void doPostTrue() throws ServletException, IOException {
        String username = "vlados";
        String firstName = "vlad";
        String lastName = "storoshchuk";
        String email = "vladstor@gmail.com";
        String password = "12345678";
        String re_password = "12345678";
        when(request.getParameter("username")).thenReturn(username);
        when(request.getParameter("firstName")).thenReturn(firstName);
        when(request.getParameter("lastName")).thenReturn(lastName);
        when(request.getParameter("email")).thenReturn(email);
        when(request.getParameter("password")).thenReturn(password);
        when(request.getParameter("re_password")).thenReturn(re_password);
        when(request.getSession()).thenReturn(session);
        RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);
        when(request.getRequestDispatcher("/userregister.jsp")).thenReturn(requestDispatcher);
        UserRegisterServlet userLoginServlet = new UserRegisterServlet();
        userLoginServlet.doPost(request,response);
        verify(requestDispatcher).forward(request,response);
    }
}