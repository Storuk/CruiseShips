package Servlets;

import Entities.User;
import org.junit.jupiter.api.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrdersPaginationServletTest {
    HttpServletResponse response = mock(HttpServletResponse.class);
    HttpServletRequest request = mock(HttpServletRequest.class);
    HttpSession session = mock(HttpSession.class);
    @Test
    void doGet() throws IOException, ServletException {
        User user = new User();
        int id = 1;
        String page = "1";
        String records = "5";
        user.setId(id);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("user")).thenReturn(user);
        when(request.getParameter("page")).thenReturn(page);
        when(request.getParameter("records")).thenReturn(records);
        OrdersPaginationServlet ordersPaginationServlet = new OrdersPaginationServlet();
        ordersPaginationServlet.doGet(request,response);
        verify(response).sendRedirect("orders.jsp");
    }
}