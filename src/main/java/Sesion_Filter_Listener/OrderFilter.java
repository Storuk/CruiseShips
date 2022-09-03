package Sesion_Filter_Listener;

import Entities.Cart;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebFilter(filterName = "OrderFilter", urlPatterns = {"/cruise_pay.jsp"})
public class OrderFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        HttpSession httpSession = httpServletRequest.getSession();
        if (httpSession.getAttribute("Cruise_price") == null){
            httpServletResponse.sendRedirect("orders.jsp");
        }
        else {
            chain.doFilter(request, response);
        }
    }
}
