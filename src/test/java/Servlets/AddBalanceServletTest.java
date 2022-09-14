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

class AddBalanceServletTest {
    HttpServletResponse response = mock(HttpServletResponse.class);
    HttpServletRequest request = mock(HttpServletRequest.class);
    HttpSession session = mock(HttpSession.class);
    @Test
    void doPostFalse() throws ServletException, IOException {
        User user = new User();
        int id = 1;
        String username = "storuk";
        String password = "sumsungj3";
        String new_balance = "200";
        String card_number = "123443215678876";
        user.setId(id);
        user.setUsername(username);
        user.setPassword(password);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("user")).thenReturn(user);
        when(request.getParameter("balance")).thenReturn(new_balance);
        when(request.getParameter("card_number")).thenReturn(card_number);
        RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);
        when(request.getRequestDispatcher("/addbalance.jsp")).thenReturn(requestDispatcher);
        AddBalanceServlet addBalanceServlet = new AddBalanceServlet();
        addBalanceServlet.doPost(request,response);
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    void doPostTrue() throws ServletException, IOException {
        User user = new User();
        int id = 1;
        String username = "storuk";
        String password = "sumsungj3";
        String new_balance = "200";
        String card_number = "1234432156788765";
        user.setId(id);
        user.setUsername(username);
        user.setPassword(password);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("user")).thenReturn(user);
        when(request.getParameter("balance")).thenReturn(new_balance);
        when(request.getParameter("card_number")).thenReturn(card_number);
        RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);
        when(request.getRequestDispatcher("/user_profile.jsp")).thenReturn(requestDispatcher);
        AddBalanceServlet addBalanceServlet = new AddBalanceServlet();
        addBalanceServlet.doPost(request,response);
        verify(requestDispatcher).forward(request,response);
    }
}