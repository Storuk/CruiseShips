package Servlets;

import Dao.UserDao;
import Entities.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

import static controller.security.PasswordEncrypt.hashPassword;

@WebServlet(name = "UserLoginServlet", value = "/login")
public class UserLoginServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(UserLoginServlet.class);
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            if(username!=null || password != null){
                HttpSession session = request.getSession();
                User user = UserDao.validate(username, hashPassword(password));
                if (user != null) {
                    logger.info("User_Login_successfully");
                    session.setAttribute("User",username);
                    request.getSession().setAttribute("user", user);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/user_profile.jsp");
                    dispatcher.forward(request, response);
                } else {
                    request.setAttribute("status", "invalid_log_or_pass");
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
                    dispatcher.forward(request, response);
                }
            }
            RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
            dispatcher.forward(request, response);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
