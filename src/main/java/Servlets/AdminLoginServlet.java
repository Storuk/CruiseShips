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

@WebServlet(name = "AdminLoginServlet", value = "/admin")
public class AdminLoginServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(UserLoginServlet.class);
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/adminlogin.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String adminname = request.getParameter("adminname");
        String password = request.getParameter("password");
        User user = new User();
        user.setUsername(adminname);
        user.setPassword(hashPassword(password));
        try {
            String userValidate = UserDao.authenticateUser(user);
            User admin = UserDao.validate(adminname, hashPassword(password));
            HttpSession session = request.getSession();
            if (admin!=null) {
                if(userValidate.equals("Admin_Role")){
                    session.setAttribute("Admin",adminname);
                    request.getSession().setAttribute("admin",admin);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/requests.jsp");
                    dispatcher.forward(request, response);
                    logger.info("Admin_Login_successfully");
                }
                else{
                    //session = request.getSession();
                    request.setAttribute("status", "no_admin");
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/adminlogin.jsp");
                    dispatcher.forward(request, response);
                }
            } else {
                //session = request.getSession();
                request.setAttribute("status", "invalid_log_or_pass");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/adminlogin.jsp");
                dispatcher.forward(request, response);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
