package Servlets;


import Dao.UserDao;
import Entities.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "AddBalanceServlet", value = "/AddBalance")
public class AddBalanceServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(UserLoginServlet.class);
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/addbalance.jsp");
        requestDispatcher.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        User user = (User) request.getSession().getAttribute("user");

        try (PrintWriter out = response.getWriter()) {
            String id = String.valueOf(user.getId());
            String username = String.valueOf(user.getUsername());
            String password = String.valueOf(user.getPassword());
            String new_balance = request.getParameter("balance");
            String card_number = request.getParameter("card_number");
            if(card_number.length()<16){
                request.setAttribute("status", "invalid_card_format");
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/addbalance.jsp");
                requestDispatcher.forward(request,response);
            }
            else {
                try {
                    UserDao.AddMoney(Integer.parseInt(id), Double.parseDouble(new_balance));
                    User update = UserDao.validate(username, password);
                    request.getSession().setAttribute("user", update);
                    logger.info("Balance added");
                } catch (ClassNotFoundException e) {
                    request.setAttribute("status", "invalid_balance_format");
                    logger.info("Invalid input type for balance");
                    throw new RuntimeException(e);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/user_profile.jsp");
            requestDispatcher.forward(request,response);
        }
    }
}
