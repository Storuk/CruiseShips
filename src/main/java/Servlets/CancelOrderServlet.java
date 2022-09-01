package Servlets;

import Dao.CruiseDao;
import Dao.UserDao;
import Dao.UserOrdersDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "CancelOrderServlet", value = "/CancelOrder")
public class CancelOrderServlet extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(UserLoginServlet.class);
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try(PrintWriter out = response.getWriter()) {
            String id = request.getParameter("id");
            String userId = request.getParameter("userId");
            String money = request.getParameter("money");
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            int cruiseId = Integer.parseInt(request.getParameter("cruiseId"));
            //System.out.println(id);
            if(id != null) {
                logger.info("Order_cancelled");
                UserOrdersDao orderDao = new UserOrdersDao();
                orderDao.CancelOrder(Integer.parseInt(id));
                CruiseDao.UpdatePlusCruisePlaces(cruiseId, quantity);
                UserDao.AddMoney(Integer.parseInt(userId), Double.parseDouble(money));
            }
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/requests.jsp");
            requestDispatcher.forward(request,response);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
