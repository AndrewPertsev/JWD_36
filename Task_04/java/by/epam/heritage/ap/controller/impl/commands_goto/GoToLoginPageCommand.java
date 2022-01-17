package by.epam.heritage.ap.controller.impl.commands_goto;

import by.epam.heritage.ap.controller.Commandable;
import by.epam.heritage.ap.repository.impl.ApartmentDaoImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Enumeration;

public class GoToLoginPageCommand implements Commandable {
    private static final Logger logger = LogManager.getLogger(GoToLoginPageCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/login.jsp");
        dispatcher.forward(request, response);

    }
}



//        HttpSession session = request.getSession();
//         String url = request.getRequestURL()+"?"+request.getQueryString()+"&user_role=1&page=login.jsp";//request url in the all pages with responses, or set it to filter
//    //    session.setAttribute("url", url );
//
//
//        System.out.println(" ser0 cont " + request.getParameter("command"));
//        System.out.println(" ser1 cont " + request.getServletContext());
//        System.out.println(" ser2 cont " + request.getServletContext().getAttribute("urlLocale"));
//        System.out.println(" ser3 cont " + request.getServletContext().getInitParameter("locale"));
//        System.out.println(" ser3p cont " + request.getServletPath());//////////////////////////
//        System.out.println(" ser4 cont " + request.getPathInfo());
//        System.out.println(" ser5 cont " + request.getContextPath());
//        System.out.println(" ser6 cont " + request.getQueryString());//////////////////////////////////
//        System.out.println(" ser7 cont " + request.getRequestedSessionId());
//        System.out.println(" ser8 cont " + request.getRequestURI());
//        System.out.println(" ser9 cont " + request.getRequestURL());
//        System.out.println(" ser10 cont " + request.getLocale());////////////////////////////////////
//        System.out.println(" se11r cont " + request.getCookies());
//        System.out.println(" ser12 cont " + request.getContentLength());
//        System.out.println(" ser13 cont " + request.getAuthType());
//        System.out.println(" ser14 cont " + request.getServletPath());
//        System.out.println(" ser15 cont " + request.toString());
//        System.out.println(" ser16 cont " + request.hashCode());
//        System.out.println(" ser17 cont " + request.getHeaderNames());
//
//        System.out.println(request.getServletPath() +"?" + request.getQueryString() +"++PARAMS");
//
//        Enumeration en = request.getAttributeNames();/////////////////////////////////////////////////////////
//        while (en.hasMoreElements()) {
//            System.out.println(" attr namesSESS : " + en.nextElement());}

//            while (request.getHeaderNames().hasMoreElements()) {
//                System.out.println("HEADERS : " + request.getHeaderNames().nextElement());}
//        session.setAttribute("urlLocale", "/controller?command=goto-edit-book-data-page&book-isbn=" + "bookIsbn");
//        session.setAttribute(SessionAttribute.URL, "/controller?command=goto-manage-users-page");
//        session.setAttribute(SessionAttribute.URL, "/controller?command=goto-catalog-page");
//        SessionAttribute {
//            public static final String USER_ID = "user_id";
//            public static final String USER_ROLE = "user_role";
//            public static final String USER_EMAIL = "user_email";
//            public static final String ERROR = "error";
//            public static final String LOCALE = "locale";
//            public static final String URL = "url";
//            public static final String USER_NAME = "user_name";
//            return new CommandResult(Page.ERROR_500_PAGE, \.FORWARD);