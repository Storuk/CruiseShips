package Sesion_Filter_Listener;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class Listener implements HttpSessionListener,
        ServletContextListener, ServletContextAttributeListener   {
    private long onlineUserCount = 0;

    public long getOnlineUserCount() {
        return onlineUserCount;
    }


    @Override
    public void attributeAdded(ServletContextAttributeEvent arg0) {
        System.err.println("...attributeAdded...");
    }


    @Override
    public void attributeRemoved(ServletContextAttributeEvent arg0) {
        System.err.println("...attributeRemoved...");
    }


    @Override
    public void attributeReplaced(ServletContextAttributeEvent attributeEvent) {
        System.err.println("...attributeReplaced...");
    }


    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
        System.err.println("...contextDestroyed...");
    }


    @Override
    public void contextInitialized(ServletContextEvent arg0) {
        System.err.println("...contextInitialized...");
    }


    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        onlineUserCount ++;
        toUpdateCount(httpSessionEvent);
    }


    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        onlineUserCount --;
        toUpdateCount(httpSessionEvent);
    }

    private void toUpdateCount(HttpSessionEvent httpSessionEvent){
        httpSessionEvent.getSession().setAttribute("onlineUserCount", onlineUserCount);
    }
}
