package Servlets;
import Dao.*;
import Entities.*;
import Enums.CruiseStatusEnum;

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
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

import static controller.security.PasswordEncrypt.ConvertImage;

@WebServlet(name = "OrderServlet", value = "/OrderServlet")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
        maxFileSize = 1024 * 1024 * 10,      // 10 MB
        maxRequestSize = 1024 * 1024 * 100   // 100 MB
)
public class OrderServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(UserLoginServlet.class);
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User auth = (User) request.getSession().getAttribute("user");
        if (auth != null) {
            HttpSession session = request.getSession();
            String cruiseId = request.getParameter("id");
            String cruiseName = request.getParameter("cruiseName");
            int cruiseQuantity = Integer.parseInt(request.getParameter("quantity"));
            int places = Integer.parseInt(request.getParameter("places"));
            String Cruise_price = request.getParameter("Cruise_price");

            request.setAttribute("cruiseID",cruiseId);
            request.setAttribute("cruiseQuantity",cruiseQuantity);
            request.setAttribute("places",places);
            request.setAttribute("cruiseName",cruiseName);
            session.setAttribute("Cruise_price",Cruise_price);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/cruise_pay.jsp");
            dispatcher.forward(request, response);
            logger.info("Move to payments");
        }
        else {
            response.sendRedirect("login.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        User user = (User) request.getSession().getAttribute("user");
        try (PrintWriter out = response.getWriter()) {

            BigDecimal balance = user.getScore();
            BigDecimal sum = new BigDecimal(request.getParameter("sum"));
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();

            String description = user.getUsername();
            Part file = request.getPart("file");
            String fileName = file.getSubmittedFileName();
            fileName = ConvertImage(fileName);
            String full_filename = description + fileName;

            String cruiseId = request.getParameter("cruiseID");
            int cruiseQuantity = Integer.parseInt( request.getParameter("cruiseQuantity"));
            int places = Integer.parseInt(request.getParameter("places"));
            String cruiseName = request.getParameter("cruiseName");

            if(request.getParameter("cruiseID")== null || session.getAttribute("Cruise_price") == null){
                RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
                dispatcher.forward(request, response);
            }

            else if(places < cruiseQuantity){
                request.setAttribute("status", "No_enough_places");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/cruise_pay.jsp");
                dispatcher.forward(request, response);
            }

            else if(!Objects.equals(fileName, ".jpg") || !Objects.equals(fileName, ".png") || !Objects.equals(fileName, ".svg")) {
                request.setAttribute("status", "documents_not_downloaded");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/cruise_pay.jsp");
                dispatcher.forward(request, response);
            }

            else if (sum.compareTo(balance) > 0) {
                request.setAttribute("status", "not_enough_balance");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/cruise_pay.jsp");
                dispatcher.forward(request, response);
            }
            else {
                FileOutputStream fos = new FileOutputStream("C:\\Users\\Влад\\IdeaProjects\\final_project2\\src\\main\\webapp\\documents_images\\" + full_filename);
                InputStream is = file.getInputStream();
                byte[] data = new byte[is.available()];
                is.read(data);
                fos.write(data);
                fos.close();
                logger.info("Documents_uploaded");

                CruiseDao.UpdateMinusCruisePlaces(Integer.parseInt(cruiseId), cruiseQuantity);
                UserOrdersDao.PayForCruise(user.getId(), sum);
                User update = UserDao.validate(String.valueOf(user.getUsername()), String.valueOf(user.getPassword()));
                request.getSession().setAttribute("user", update);

                if (cruiseQuantity <= 0) {
                    cruiseQuantity = 1;
                }
                UserOrders orderModel = new UserOrders();
                orderModel.setId(Integer.parseInt(cruiseId));
                orderModel.setU_id(user.getId());
                orderModel.setQuantity(cruiseQuantity);
                orderModel.setStatusId(CruiseStatusEnum.IN_PROGRESS);
                orderModel.setDate(formatter.format(date));
                orderModel.setPaymentAmount(sum);
                orderModel.setImages(full_filename);
                orderModel.setCruise_name(cruiseName);
                UserOrdersDao orderDao = new UserOrdersDao();
                boolean result = orderDao.insertOrder(orderModel);

                if (result) {
                    ArrayList<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");
                    if (cart_list != null) {
                        for (Cart c : cart_list) {
                            if (c.getId() == Integer.parseInt(cruiseId)) {
                                cart_list.remove(cart_list.indexOf(c));
                                break;
                            }
                        }
                    }
                    logger.info("Cruise_bought");
                    session.removeAttribute("Cruise_price");
                    response.sendRedirect("OrdersPaginationServlet?records=5&page=1");
                } else {
                    out.println("order failed");
                }
            }


        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
