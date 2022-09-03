package Servlets;

import Dao.CruiseDao;
import Dao.UserDao;
import Dao.UserOrdersDao;
import Entities.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.SQLException;

@WebServlet(name = "DeleteOrderServlet", value = "/DeleteOrder")
public class DeleteOrderServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(UserLoginServlet.class);
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        String username = String.valueOf(user.getUsername());
        String password = String.valueOf(user.getPassword());
        try(PrintWriter out = response.getWriter()) {
            String id = request.getParameter("id");
            String userId = request.getParameter("userId");
            String money = request.getParameter("money");
            int cruiseId = Integer.parseInt(request.getParameter("cruiseId"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            if(id != null) {
                UserOrdersDao orderDao = new UserOrdersDao();
                orderDao.deleteOrder(Integer.parseInt(id));
                CruiseDao.UpdatePlusCruisePlaces(cruiseId, quantity);
                UserDao.AddMoney(Integer.parseInt(userId), new BigDecimal(money));
                User update = UserDao.validate(username, password);
                request.getSession().setAttribute("user", update);
                logger.info("Order Deleted");
            }
            response.sendRedirect("orders.jsp");
        } catch (ClassNotFoundException | SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
