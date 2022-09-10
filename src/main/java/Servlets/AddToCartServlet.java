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

@WebServlet(name = "AddToCartServlet", value = "/AddToCartServlet")
public class AddToCartServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(UserLoginServlet.class);
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            ArrayList<Cart> cartList = new ArrayList<>();
            int id = Integer.parseInt(request.getParameter("id"));
            int places = Integer.parseInt(request.getParameter("places"));
            Cart cm = new Cart();
            cm.setId(id);
            cm.setQuantity(1);
            HttpSession session = request.getSession();
            ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");

            if (cart_list == null) {
                cartList.add(cm);
                out.println("cart no exist");
                session.setAttribute("cart-list", cartList);
                response.sendRedirect("index.jsp");
                logger.info("Added_to_cart");
            } else {
                cartList = cart_list;

                boolean exist = false;
                for (Cart c : cart_list) {
                     if (c.getId() == id) {
                         exist = true;
                         //out.println("<h3 style='color:crimson; text-align: center'>Item Already in Cart. <a href='cart.jsp'>GO to Cart Page</a></h3>");
                         request.setAttribute("status", "cruise_already_exist");
                         RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
                         dispatcher.forward(request, response);
                     }
                }
                if (!exist) {
                    cartList.add(cm);
                    out.println("added");
                    response.sendRedirect("index.jsp");
                }
            }
        }
    }
}
