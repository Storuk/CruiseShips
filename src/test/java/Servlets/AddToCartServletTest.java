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
import static org.mockito.Mockito.mock;

class AddToCartServletTest {
    HttpServletResponse response = mock(HttpServletResponse.class);
    HttpServletRequest request = mock(HttpServletRequest.class);
    HttpSession session = mock(HttpSession.class);
    @Test
    void doGetCruiseAlreadyExist() throws ServletException, IOException {
        int id = 1;
        Cart cm = new Cart();
        cm.setId(id);
        cm.setQuantity(1);
        ArrayList<Cart> cart_list = new ArrayList<>();
        cart_list.add(cm);
        when(request.getSession()).thenReturn(session);
        when(request.getParameter("id")).thenReturn(String.valueOf(id));
        when(session.getAttribute("cart-list")).thenReturn(cart_list);
        RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);
        when(request.getRequestDispatcher("/index.jsp")).thenReturn(requestDispatcher);
        AddToCartServlet addToCartServlet = new AddToCartServlet();
        addToCartServlet.doGet(request,response);
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    void doGetCruiseAreNotExist() throws ServletException, IOException {
        int id = 1;
        Cart cm = new Cart();
        cm.setId(2);
        cm.setQuantity(1);
        ArrayList<Cart> cart_list = new ArrayList<>();
        cart_list.add(cm);
        when(request.getSession()).thenReturn(session);
        when(request.getParameter("id")).thenReturn(String.valueOf(id));
        when(session.getAttribute("cart-list")).thenReturn(cart_list);
        AddToCartServlet addToCartServlet = new AddToCartServlet();
        addToCartServlet.doGet(request,response);
        verify(response).sendRedirect("index.jsp");
    }
}