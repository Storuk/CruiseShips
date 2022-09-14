package Servlets;
import static org.mockito.Mockito.*;
import connection.ConnectionManager;
import org.junit.jupiter.api.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


class UserLoginServletTest {
    HttpServletResponse response = mock(HttpServletResponse.class);
    HttpServletRequest request = mock(HttpServletRequest.class);
    HttpSession session = mock(HttpSession.class);
    @Test
    void doPostTestTrue() throws ServletException, IOException {
        String username = "storuk";
        String password = "sumsungj3";
        when(request.getParameter("username")).thenReturn(username);
        when(request.getParameter("password")).thenReturn(password);
        when(request.getSession()).thenReturn(session);
        UserLoginServlet userLoginServlet = new UserLoginServlet();
        userLoginServlet.doPost(request,response);
        verify(response).sendRedirect("user_profile.jsp");
    }

    @Test
    void doPostTestFailed() throws ServletException, IOException {
        String username = "storuk1";
        String password = "sumsungj35";
        when(request.getParameter("username")).thenReturn(username);
        when(request.getParameter("password")).thenReturn(password);
        when(request.getSession()).thenReturn(session);
        RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);
        when(request.getRequestDispatcher("/login.jsp")).thenReturn(requestDispatcher);
        UserLoginServlet userLoginServlet = new UserLoginServlet();
        userLoginServlet.doPost(request,response);
        verify(requestDispatcher).forward(request,response);
    }
}