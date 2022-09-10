package Servlets;

import Dao.CruiseDao;
import Dao.UserOrdersDao;
import Entities.Cruise;
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
        try {
            if((request.getParameter("id") != null)) {
                int id = Integer.parseInt(request.getParameter("id"));
                java.util.Date utilPackageDate = new java.util.Date();
                java.sql.Date date = new java.sql.Date(utilPackageDate.getTime());
                Cruise cruise = CruiseDao.getSingleProduct(id);
                if(date.compareTo(cruise.getStart_cruise_date()) >= 0 && date.compareTo(cruise.getEnd_cruise_date()) <= 0){
                    request.setAttribute("status", "cruise_in_progress");
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/admin_cruises.jsp");
                    dispatcher.forward(request, response);
                }
                else if(date.compareTo(cruise.getStart_cruise_date()) < 0){
                    UserOrdersDao.selectOrdersAndUpdateBalance(id);
                    UserOrdersDao.deleteOrders(id);
                    CruiseDao.deleteCruise(id);
                    response.sendRedirect("admin_cruises.jsp");
                    logger.info("Cruise deleted");
                }
                else{
                    UserOrdersDao.deleteOrders(id);
                    CruiseDao.deleteCruise(id);
                    response.sendRedirect("admin_cruises.jsp");
                    logger.info("Cruise deleted");
                }
                //UserOrdersDao.updateDeletedStatusOrders(id);

            }
            else {
                RequestDispatcher dispatcher = request.getRequestDispatcher("/admin_cruises.jsp");
                dispatcher.forward(request, response);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
