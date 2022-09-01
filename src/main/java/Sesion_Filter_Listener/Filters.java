package Sesion_Filter_Listener;


import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import java.io.IOException;

public class Filters implements Filter {

    private FilterConfig filterConfig;

    public FilterConfig getFilterConfig() {
        System.err.println("...getFilterConfig...");
        return filterConfig;
    }

    public void setFilterConfig(FilterConfig filterConfig) {
        System.err.println("...setFilterConfig...");
        this.filterConfig = filterConfig;
    }

    /* (non-Javadoc)
     * @see javax.servlet.Filter#destroy()
     */
    @Override
    public void destroy() {
        System.err.println("...filter destroy...");
    }

    /* (non-Javadoc)
     * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        System.err.println("...doFilter...");
        chain.doFilter(request, response);// Видим это, пока проходит следующий Фильтр
    }

    /* (non-Javadoc)
     * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.err.println("...init Filter...");
        this.filterConfig = filterConfig;
    }
}
