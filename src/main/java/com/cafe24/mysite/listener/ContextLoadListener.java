package com.cafe24.mysite.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

//@WebListener
public class ContextLoadListener implements ServletContextListener {


    public void contextDestroyed(ServletContextEvent servletContextEvent)  { 
    	
    }


    public void contextInitialized(ServletContextEvent servletContextEvent)  { 
    	System.out.println("context init --> "+servletContextEvent.getServletContext().getInitParameter("contextConfigLocation"));
    }
	
}
