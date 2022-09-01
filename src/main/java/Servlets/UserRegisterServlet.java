package Servlets;

import Dao.UserDao;
import Entities.User;
import Enums.UserRoleEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;


import static Dao.UserDao.check;
import static controller.security.PasswordEncrypt.hashPassword;

@WebServlet(name = "UserServlet", value = "/register")
public class UserRegisterServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(UserLoginServlet.class);
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/userregister.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String re_password = request.getParameter("re_password");
        String score = "0";
        UserRoleEnum role = UserRoleEnum.ADMIN;
        double quantity = Double.parseDouble(score);

        User user = new User();
        user.setUsername(username);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPassword(hashPassword(password));
        user.setScore(quantity);
        user.setRole(role);

        if(password.length() < 8){
            request.setAttribute("status", "invalid_password_type");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
            dispatcher.forward(request, response);
        }

        try {
            if (!check(user)) {
                try {
                    if (re_password.equals(password)) {
                        HttpSession session = request.getSession();
                        request.setAttribute("status", "success");
                        UserDao.registerUser(user);
                        session.setAttribute("User",username);
                        logger.info("User_Registered_successfully");
                    } else {
                        request.setAttribute("status", "invalid_pass");
                    }
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                RequestDispatcher dispatcher = request.getRequestDispatcher("/userregister.jsp");
                dispatcher.forward(request, response);
            }
            else {
                request.setAttribute("status", "invalid_login");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/userregister.jsp");
                dispatcher.forward(request, response);
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/userregister.jsp");
        dispatcher.forward(request, response);
    }
}