package by.epam.heritage.ap.controller.filter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.properties.PropertiesConfiguration;
import org.apache.logging.log4j.core.config.properties.PropertiesConfigurationBuilder;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.File;


public class ContextListener implements ServletContextListener {
    private static final Logger logger = LogManager.getLogger(ContextListener.class);
//TODO listener



    @Override
    public void contextInitialized(ServletContextEvent event) {

     //   ApplicationContext.initialize();
       logger.info("Context was initialized");///////////////////////////////
       logger.error("errr Context was initialized");///////////////////////////////
//
//
//            ServletContext context = event.getServletContext();
//            String log4jConfigFile = context.getInitParameter("log4j-config-location");
//            String fullPath = context.getRealPath("") + File.separator + log4jConfigFile;
//
//           PropertiesConfigurationBuilder.newConfigurationBuilder(fullPath);
//
//        }
    }


    @Override
    public void contextDestroyed(ServletContextEvent event) {

    }
}