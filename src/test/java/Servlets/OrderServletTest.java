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
import static org.mockito.Mockito.mock;

class OrderServletTest {
    HttpServletResponse response = mock(HttpServletResponse.class);
    HttpServletRequest request = mock(HttpServletRequest.class);
    HttpSession session = mock(HttpSession.class);
    @Test
    void doGet() throws ServletException, IOException {
        User user = new User();
        String username = "storuk";
        String cruiseId = "6";
        String cruiseName = "Allure";
        String quantity = "1";
        String places = "133";
        String cruisePrice = "250";
        user.setUsername(username);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("user")).thenReturn(user);
        when(request.getParameter("id")).thenReturn(cruiseId);
        when(request.getParameter("cruiseName")).thenReturn(cruiseName);
        when(request.getParameter("quantity")).thenReturn(quantity);
        when(request.getParameter("places")).thenReturn(places);
        when(request.getParameter("Cruise_price")).thenReturn(cruisePrice);
        RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);
        when(request.getRequestDispatcher("/cruise_pay.jsp")).thenReturn(requestDispatcher);
        OrderServlet orderServlet = new OrderServlet();
        orderServlet.doGet(request,response);
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    void doPost() {

    }
}