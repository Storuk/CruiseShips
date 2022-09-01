package Sesion_Filter_Listener;

import Entities.Cart;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebFilter(filterName = "UserLoginFilter", urlPatterns = {"/cruise_pay.jsp", "/OrderServlet"})
public class UserLoginFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        HttpSession httpSession = httpServletRequest.getSession();
        ArrayList<Cart> cart_list = (ArrayList<Cart>) httpSession.getAttribute("cart-list");
        System.out.println(cart_list.size());
        if(httpSession.getAttribute("user") == null){
            httpServletResponse.sendRedirect("login.jsp");
        }
        else if (cart_list.size() < 1){
            httpServletResponse.sendRedirect("index.jsp");
        }
        else{
            chain.doFilter(request, response);
        }
    }
}
