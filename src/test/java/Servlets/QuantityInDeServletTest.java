package Servlets;

import Entities.Cart;
import org.junit.jupiter.api.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class QuantityInDeServletTest {
    HttpServletResponse response = mock(HttpServletResponse.class);
    HttpServletRequest request = mock(HttpServletRequest.class);
    HttpSession session = mock(HttpSession.class);
    @Test
    void doGetIncrement() throws ServletException, IOException {
        String id = "1";
        String places = "10";
        String action = "inc";
        Cart cm = new Cart();
        cm.setId(Integer.parseInt(id));
        cm.setQuantity(1);
        ArrayList<Cart> cart_list = new ArrayList<>();
        cart_list.add(cm);
        when(request.getSession()).thenReturn(session);
        when(request.getParameter("id")).thenReturn(id);
        when(request.getParameter("places")).thenReturn(places);
        when(request.getParameter("action")).thenReturn(action);
        when(session.getAttribute("cart-list")).thenReturn(cart_list);
        QuantityInDeServlet quantityInDeServlet = new QuantityInDeServlet();
        quantityInDeServlet.doGet(request,response);
        verify(response).sendRedirect("cart.jsp");
    }

    @Test
    void doGetDecrement() throws ServletException, IOException {
        String id = "1";
        String places = "10";
        String action = "dec";
        Cart cm = new Cart();
        cm.setId(Integer.parseInt(id));
        cm.setQuantity(1);
        ArrayList<Cart> cart_list = new ArrayList<>();
        cart_list.add(cm);
        when(request.getSession()).thenReturn(session);
        when(request.getParameter("id")).thenReturn(id);
        when(request.getParameter("places")).thenReturn(places);
        when(request.getParameter("action")).thenReturn(action);
        when(session.getAttribute("cart-list")).thenReturn(cart_list);
        QuantityInDeServlet quantityInDeServlet = new QuantityInDeServlet();
        quantityInDeServlet.doGet(request,response);
        verify(response).sendRedirect("cart.jsp");
    }
}