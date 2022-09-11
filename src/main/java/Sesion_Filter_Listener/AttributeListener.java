package Sesion_Filter_Listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;


@WebListener
public class AttributeListener implements HttpSessionAttributeListener {

    @Override
    public void attributeAdded(HttpSessionBindingEvent add) {
        System.out.println("Attribute "+add.getName()+ " is created");
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent remove) {
        System.out.println("Attribute "+remove.getName()+ " is removed");
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent replace) {
        System.out.println("Attribute "+replace.getName()+ " is replaced");
    }
}
