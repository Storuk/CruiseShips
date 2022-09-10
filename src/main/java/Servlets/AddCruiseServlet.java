package Servlets;

import Dao.CruiseDao;
import Dao.ShipsDao;
import Entities.Cruise;
import Entities.Ships;
import Enums.CruiseStatusEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Objects;

import static controller.security.PasswordEncrypt.ConvertImage;

@WebServlet(name = "AddCruiseServlet", value = "/AddCruiseServlet")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
        maxFileSize = 1024 * 1024 * 10,      // 10 MB
        maxRequestSize = 1024 * 1024 * 100   // 100 MB
)
public class AddCruiseServlet extends HttpServlet {
    Ships ship = new Ships();
    Cruise cruise = new Cruise();
    private static final Logger logger = LoggerFactory.getLogger(UserLoginServlet.class);
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("date.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String price = request.getParameter("price");
        Date start_cruise_date = Date.valueOf(request.getParameter("start_cruise_date"));
        Date end_cruise_date = Date.valueOf(request.getParameter("end_cruise_date"));
        String cruise_name = request.getParameter("cruise_name");
        String duration = request.getParameter("duration");
        String ship_id = request.getParameter("ship_name");
        Part file = request.getPart("file");
        String fileName = file.getSubmittedFileName();
        fileName = ConvertImage(fileName);
        String full_filename = cruise_name + fileName;
        CruiseStatusEnum statusEnum = CruiseStatusEnum.REGISTERED;

        if(ship_id != null) {
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
        cruise.setStatuse(statusEnum);
        cruise.setImage(full_filename);

        try {
            if (!CruiseDao               .cruiseNameCheck(cruise_name)) {
                Cruise valid = CruiseDao.date_validation(start_cruise_date, end_cruise_date, Integer.parseInt(ship_id));
                if (valid == null) {
                    if (Objects.equals(fileName, ".jpg") || Objects.equals(fileName, ".png") || Objects.equals(fileName, ".svg")) {
                        FileOutputStream fos = new FileOutputStream("C:\\Users\\Влад\\IdeaProjects\\final_project2\\src\\main\\webapp\\cruises_images\\" + full_filename);
                        InputStream is = file.getInputStream();
                        byte[] data = new byte[is.available()];
                        is.read(data);
                        fos.write(data);
                        fos.close();

                        CruiseDao.addCruise(cruise);
                        logger.info("cruise added");
                        request.setAttribute("status", "Uploaded");
                        RequestDispatcher dispatcher = request.getRequestDispatcher("/date.jsp");
                        dispatcher.forward(request, response);
                    } else {
                        request.setAttribute("status", "Invalid_photo_type");
                        RequestDispatcher dispatcher = request.getRequestDispatcher("/date.jsp");
                        dispatcher.forward(request, response);
                    }
                } else {
                    request.setAttribute("dates", "(" + valid.getStart_cruise_date() + ") - (" + valid.getEnd_cruise_date() + ")");
                    request.setAttribute("status", "Invalid_dates");
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/date.jsp");
                    dispatcher.forward(request, response);
                }
            }
            else{
                request.setAttribute("status", "Cruise_name_exist");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/date.jsp");
                dispatcher.forward(request, response);
            }
        }
        catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}

