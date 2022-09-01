package Servlets;

import Entities.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "OrderPayServlet", value = "/OrderPayServlet")
public class OrderPayServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User auth = (User) request.getSession().getAttribute("user");
        System.out.println(auth);
        if (auth != null) {
            HttpSession session = request.getSession();
            String cruiseId = request.getParameter("id");
            String cruiseName = request.getParameter("cruiseName");
            int cruiseQuantity = Integer.parseInt(request.getParameter("quantity"));
            int places = Integer.parseInt(request.getParameter("places"));
            String Cruise_price = request.getParameter("Cruise_price");

            request.setAttribute("cruiseID",cruiseId);
            request.setAttribute("cruiseQuantity",cruiseQuantity);
            request.setAttribute("places",places);
            request.setAttribute("cruiseName",cruiseName);
            session.setAttribute("Cruise_price",Cruise_price);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/cruise_pay.jsp");
            dispatcher.forward(request, response);
        }
        else {
            response.sendRedirect("login.jsp");
        }
    }
}
