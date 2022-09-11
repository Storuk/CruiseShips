package Servlets;

import Dao.CruiseDao;
import Entities.Cruise;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@WebServlet(name = "CruisesFilterServlet", value = "/CruisesFilterServlet")
public class CruisesFilterServlet extends HttpServlet {
    CruiseDao cruiseDao = new CruiseDao();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String date = request.getParameter("date");
        String duration = request.getParameter("duration");
        String min_price = request.getParameter("min_price");
        String max_price = request.getParameter("max_price");

        List<Cruise> cruises = new ArrayList<>();

        BigDecimal min_priceNumber;
        BigDecimal max_priceNumber;
        Date dateFrom;
        int durationDB;

        if(Objects.equals(min_price, "")){
            min_priceNumber = CruiseDao.minPrice();
        }else{
            min_priceNumber = new BigDecimal((request.getParameter("min_price")));
        }

        if(Objects.equals(max_price, "")){
            max_priceNumber = CruiseDao.maxPrice();
        }else{
            max_priceNumber = new BigDecimal(request.getParameter("max_price"));
        }

        if(Objects.equals(duration, "")){
            durationDB = 1;
        }else{
            durationDB = Integer.parseInt(request.getParameter("duration"));
        }

        if(Objects.equals(date, "")){
            dateFrom = new Date(-1900,1,1);
            cruises = cruiseDao.getCruisesByFilters(min_priceNumber,
                    max_priceNumber,dateFrom, durationDB);
        }else{
            dateFrom = Date.valueOf(date);
            cruises = cruiseDao.getCruisesByFilters(min_priceNumber,
                    max_priceNumber,dateFrom, durationDB);
        }
        session.setAttribute("filtered_cruises", cruises);

        RequestDispatcher dispatcher = request.getRequestDispatcher((String) request.getSession().getAttribute("responsePage"));
        dispatcher.forward(request, response);

    }
}
