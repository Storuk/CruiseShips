package Servlets;

import Dao.UserOrdersDao;
import Entities.User;
import Entities.UserOrders;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "OrdersPaginationServlet", value = "/OrdersPaginationServlet")
public class OrdersPaginationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        int rows = 0;
        int currentPage = Integer.parseInt(request.getParameter("page"));
        int recordsPerPage = Integer.parseInt(request.getParameter("records"));

        List<UserOrders> list;
        UserOrdersDao orderDao = new UserOrdersDao();
        list = orderDao.userOrderPagination(user.getId(),currentPage,recordsPerPage);
        request.getSession().setAttribute("paymentList",list);
        rows = orderDao.getNumberOfRows(user.getId());
        int nOfPages = rows / recordsPerPage;
        if (nOfPages % recordsPerPage > 0) nOfPages++;

        request.getSession().setAttribute("noOfPages", nOfPages);
        request.getSession().setAttribute("currentPage", currentPage);
        request.getSession().setAttribute("recordsPerPage", recordsPerPage);

        response.sendRedirect("orders.jsp");
    }
}
