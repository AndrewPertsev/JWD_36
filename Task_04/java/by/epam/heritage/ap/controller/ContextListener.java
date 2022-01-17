package by.epam.heritage.ap.controller;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

//@WebListener("application context listener")
public class ContextListener implements ServletContextListener {


    @Override
    public void contextInitialized(ServletContextEvent event) {
//       init log4j
//        ServletContext context = event.getServletContext();
//        String log4jConfig = context.getInitParameter("log4j_config");
//        String path = context.getPath("") + file.separator + log4jConfig;
//        PropertyConfigurator.configure("src/main/webapp/WEB-INF/lib/log4j.xml");
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {

    }
}