package Servlets;

import Dao.UserOrdersDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "AcceptOrderServlet", value = "/AcceptOrder")
public class AcceptOrderServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(UserLoginServlet.class);
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try(PrintWriter out = response.getWriter()) {
            String id = request.getParameter("id");
            System.out.println(id);
            if(id != null) {
                UserOrdersDao orderDao = new UserOrdersDao();
                orderDao.AcceptOrder(Integer.parseInt(id));
                logger.info("Order_accepted");
            }
            response.sendRedirect("requests.jsp");
        }
    }
}
