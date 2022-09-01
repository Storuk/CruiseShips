package Servlets;

import Dao.CruiseDao;
import Dao.UserOrdersDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "DeleteCruiseServlet", value = "/DeleteCruiseServlet")
public class DeleteCruiseServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(UserLoginServlet.class);
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (PrintWriter out = response.getWriter()) {
            if((request.getParameter("id") != null)) {
                int id = Integer.parseInt(request.getParameter("id"));
                CruiseDao.deleteCruise(id);
                logger.info("Cruise deleted");
                UserOrdersDao.updateDeletedStatusOrders(id);
                //UserOrdersDao.deleteOrders(id);
            }
            RequestDispatcher dispatcher = request.getRequestDispatcher("/admin_cruises.jsp");
            dispatcher.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
