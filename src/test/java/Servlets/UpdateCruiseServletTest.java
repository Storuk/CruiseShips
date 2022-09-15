package Servlets;

import Dao.CruiseDao;
import Entities.Cruise;
import org.junit.jupiter.api.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UpdateCruiseServletTest {
    HttpServletResponse response = mock(HttpServletResponse.class);
    HttpServletRequest request = mock(HttpServletRequest.class);
    HttpSession session = mock(HttpSession.class);
    @Test
    void doGet() throws ServletException, IOException {
        String id = "1";
        when(request.getParameter("id")).thenReturn(id);
        RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);
        when(request.getRequestDispatcher("/cruise_update.jsp")).thenReturn(requestDispatcher);
        UpdateCruiseServlet updateCruiseServlet = new UpdateCruiseServlet();
        updateCruiseServlet.doGet(request,response);
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    void doPost() throws ServletException, IOException {
        String price = "250";
        Date start_cruise_date = Date.valueOf("2022-09-20");
        Date end_cruise_date = Date.valueOf("2022-09-29");
        String cruise_name = "Harmony";
        String duration = "9";
        String ship_id = "2";
        String cruiseId = "1";
        when(request.getParameter("price")).thenReturn(price);
        when(request.getParameter("start_cruise_date")).thenReturn(String.valueOf(start_cruise_date));
        when(request.getParameter("end_cruise_date")).thenReturn(String.valueOf(end_cruise_date));
        when(request.getParameter("cruise_name")).thenReturn(cruise_name);
        when(request.getParameter("duration")).thenReturn(duration);
        when(request.getParameter("ship_name")).thenReturn(ship_id);
        when(request.getParameter("cruiseId")).thenReturn(cruiseId);
        UpdateCruiseServlet updateCruiseServlet = new UpdateCruiseServlet();
        updateCruiseServlet.doPost(request,response);
        verify(response).sendRedirect("admin_cruises.jsp");
    }

}