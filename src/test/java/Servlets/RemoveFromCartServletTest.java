package Servlets;

import Entities.Cart;
import org.junit.jupiter.api.Test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RemoveFromCartServletTest {
    HttpServletResponse response = mock(HttpServletResponse.class);
    HttpServletRequest request = mock(HttpServletRequest.class);
    HttpSession session = mock(HttpSession.class);
    @Test
    void doGet() throws ServletException, IOException {
        int id = 1;
        Cart cm = new Cart();
        cm.setId(id);
        cm.setQuantity(1);
        ArrayList<Cart> cart_list = new ArrayList<>();
        cart_list.add(cm);
        when(request.getSession()).thenReturn(session);
        when(request.getParameter("id")).thenReturn(String.valueOf(id));
        when(session.getAttribute("cart-list")).thenReturn(cart_list);
        RemoveFromCartServlet removeFromCartServlet = new RemoveFromCartServlet();
        removeFromCartServlet.doGet(request,response);
        verify(response).sendRedirect("cart.jsp");
    }
}