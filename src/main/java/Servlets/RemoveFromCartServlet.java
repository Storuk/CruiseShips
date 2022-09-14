package Servlets;

import Entities.Cart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "RemoveFromCartServlet", value = "/RemoveFromCartServlet")
public class RemoveFromCartServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(UserLoginServlet.class);
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String bookId = request.getParameter("id");
            if (bookId != null) {
                ArrayList<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");
                if (cart_list != null) {
                    for (Cart c : cart_list) {
                        if (c.getId() == Integer.parseInt(bookId)) {
                            cart_list.remove(cart_list.indexOf(c));
                            break;
                        }
                    }
                }
                response.sendRedirect("cart.jsp");

            } else {
                response.sendRedirect("cart.jsp");
            }

        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
