package Sesion_Filter_Listener;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "UserLoginFilter", urlPatterns = {"/cruise_pay.jsp", "/OrderServlet", "/user_profile.jsp", "/orders.jsp", "/addbalance.jsp","/OrdersPaginationServlet"})
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
        if(httpSession.getAttribute("user") == null){
            httpServletResponse.sendRedirect("login.jsp");
        }
        else{
            chain.doFilter(request, response);
        }
    }
}
