package Servlets;

import Dao.CruiseDao;
import Dao.ShipsDao;
import Dao.UserOrdersDao;
import Entities.Cruise;
import Entities.Ships;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Objects;

import static controller.security.PasswordEncrypt.ConvertImage;

@WebServlet(name = "UpdateCruiseServlet", value = "/UpdateCruiseServlet")
public class UpdateCruiseServlet extends HttpServlet {
    Cruise cruise = new Cruise();
    Ships ship = new Ships();
    private static final Logger logger = LoggerFactory.getLogger(UserLoginServlet.class);
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if((request.getParameter("id") != null)) {
            int id = Integer.parseInt(request.getParameter("id"));
            Cruise cr = CruiseDao.getSingleProduct(id);
            request.setAttribute("updateCruise", cr);
            request.setAttribute("cruiseId", id);
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/cruise_update.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

            String price = request.getParameter("price");
            Date start_cruise_date = Date.valueOf(request.getParameter("start_cruise_date"));
            System.out.println(start_cruise_date);
            Date end_cruise_date = Date.valueOf(request.getParameter("end_cruise_date"));
            System.out.println(end_cruise_date);
            String cruise_name = request.getParameter("cruise_name");
            String duration = request.getParameter("duration");
            String ship_id = request.getParameter("ship_name");
            int id = Integer.parseInt(request.getParameter("cruiseId"));

            //java.util.Date utilPackageDate = new java.util.Date();
            //java.sql.Date date = new java.sql.Date(utilPackageDate.getTime());
            //Cruise cruise = CruiseDao.getSingleProduct(id);
            //if(date.compareTo(cruise.getStart_cruise_date()) >= 0 && date.compareTo(cruise.getEnd_cruise_date()) <= 0){
            //    request.setAttribute("status", "cruise_in_progress");
            //    RequestDispatcher dispatcher = request.getRequestDispatcher("admin_cruises.jsp");
            //    dispatcher.forward(request, response);
            //}

            if (ship_id != null) {
                try {
                    ship = ShipsDao.selectShip(Integer.parseInt(ship_id));
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }

            cruise.setPrice(new BigDecimal(price));
            cruise.setStart_cruise_date(start_cruise_date);
            cruise.setEnd_cruise_date(end_cruise_date);
            cruise.setCruise_name(cruise_name);
            cruise.setDuration(Integer.parseInt(duration));
            cruise.setShip_id(Integer.parseInt(ship_id));
            cruise.setPlaces(ship.getPassenger_capacity());

            try {
                UserOrdersDao.selectOrdersAndUpdateBalance(id);
                UserOrdersDao.deleteOrders(id);
                CruiseDao.updateCruise(cruise,id);
                logger.info("Cruise updated");
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            response.sendRedirect("admin_cruises.jsp");
    }
}





















//try {
//    if (!CruiseDao.cruiseNameCheck(cruise_name)) {
//        Cruise valid = CruiseDao.date_validation(start_cruise_date, end_cruise_date, ship.getShip_name());
//        if (valid == null) {
//
//            request.setAttribute("status", "Uploaded");
//            RequestDispatcher dispatcher = request.getRequestDispatcher("cruise_update.jsp");
//            dispatcher.forward(request, response);
//        } else {
//            request.setAttribute("dates", "(" + valid.getStart_cruise_date() + ") - (" + valid.getEnd_cruise_date() + ")");
//            request.setAttribute("status", "Invalid_dates");
//            RequestDispatcher dispatcher = request.getRequestDispatcher("cruise_update.jsp");
//            dispatcher.forward(request, response);
//        }
//    }
//    else{
//        request.setAttribute("status", "Cruise_name_exist");
//        RequestDispatcher dispatcher = request.getRequestDispatcher("cruise_update.jsp");
//        dispatcher.forward(request, response);
//    }
//}
//catch (ClassNotFoundException e) {
//    throw new RuntimeException(e);
//}