package Servlets;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "LanguageServlet", value = "/Language")
public class LanguageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String language = request.getParameter("language");
        String a = (String) request.getSession().getAttribute("responsePage");
        request.getSession().setAttribute("language",language);
        response.sendRedirect((String) request.getSession().getAttribute("responsePage"));
    }
}
