package by.epam.heritage.ap.controller.impl.commands_goto;

import by.epam.heritage.ap.controller.Commandable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.epam.heritage.ap.controller.ConstantsCommandPath.PATH_GO_TO_LOGIN_PAGE;

public class GoToLoginPageCommand implements Commandable {
    private static final Logger logger = LogManager.getLogger(GoToLoginPageCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher dispatcher = request.getRequestDispatcher(PATH_GO_TO_LOGIN_PAGE);
        dispatcher.forward(request, response);

    }
}




//response.setIntHeader("Refresh",60);
//Date current=new Date();
//request.setAttribute("dateAuto", current);

//        Enumeration en = request.getAttributeNames();
//        while (en.hasMoreElements()) {
//            System.out.println("gotoreqSESS : " + en.nextElement());
//        }
// String url = request.getRequestURL() + "?" + request.getQueryString() + "&user_role=1&page=login.jsp";//request url in the all pages with responses, or set it to filter
//        //    session.setAttribute("url", url );
//        System.out.println(" gtlp 1 /Controller   ::::   " + request.getRequestURI());
//        System.out.println(" gtlp 2/Controller serv path  ::::   " + request.getServletPath());//////////////////////////
//        System.out.println(" gtlp 3 PARcommand  ::::   " + request.getParameter("command"));
//        System.out.println(" gtlp 4 query string::::   " + request.getQueryString());//////////////////////////////////
//        System.out.println("MY BuILdeR :::" + request.getServletPath() + "?" + request.getQueryString() + "++++++++");
//        System.out.println(" ser10 cont ::::   " + request.getLocale());////////////////////////////////////
//        System.out.println(" ser17 cont ::::   " + request.getMethod());
//        System.out.println(" ser9  cont ::::   " + request.getRequestURL());
//        System.out.println(" gtlp 5 context path  empty?::::   " + request.getContextPath());
//        System.out.println(" ser4 null cont ::::   " + request.getPathInfo());
//        System.out.println(" ser12 -1 cont ::::   " + request.getContentLength());
//        System.out.println(" ser13null cont ::::   " + request.getAuthType());
//        System.out.println(" ser18 cont ::::   " + request.getContentType());