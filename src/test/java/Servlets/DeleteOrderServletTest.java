package Servlets;

import Entities.User;
import org.junit.jupiter.api.Test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.mock;

class DeleteOrderServletTest {
    HttpServletResponse response = mock(HttpServletResponse.class);
    HttpServletRequest request = mock(HttpServletRequest.class);
    HttpSession session = mock(HttpSession.class);
    User user = new User();
    @Test
    void doGet() throws ServletException, IOException {

        String orderId = "15";
        String userId = "1";
        String money = "200";
        int quantity = 1;
        int cruiseId = 9;
        String userName = "storuk";
        String password = "sumsungj3";
        user.setUsername(userName);
        user.setPassword(password);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("user")).thenReturn(user);
        when(request.getParameter("id")).thenReturn(orderId);
        when(request.getParameter("userId")).thenReturn(userId);
        when(request.getParameter("money")).thenReturn(money);
        when(request.getParameter("quantity")).thenReturn(String.valueOf(quantity));
        when(request.getParameter("cruiseId")).thenReturn(String.valueOf(cruiseId));
        DeleteOrderServlet deleteOrderServlet = new DeleteOrderServlet();
        deleteOrderServlet.doGet(request,response);
        verify(response).sendRedirect("OrdersPaginationServlet?records=5&page=1");
    }
}