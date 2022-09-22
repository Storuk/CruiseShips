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
import java.math.BigDecimal;
import java.sql.SQLException;

@WebServlet(name = "AddBalanceServlet", value = "/AddBalance")
public class AddBalanceServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(UserLoginServlet.class);

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
                UserDao.AddMoney(Integer.parseInt(id), new BigDecimal(new_balance));
                User update = UserDao.validate(username, password);
                request.getSession().setAttribute("user", update);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/user_profile.jsp");
                requestDispatcher.forward(request,response);
                logger.info("Balance added");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
