package by.epam.heritage.ap.controller.impl.commands_goto;

import by.epam.heritage.ap.controller.Commandable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GoToMainPageCommand implements Commandable {
    private static final Logger logger = LogManager.getLogger(GoToMainPageCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/view/mainafterreg.jsp");
        dispatcher.forward(request, response);

    }
}
// enter to db. set data to context
//        HttpSession session = request.getSession();
//    String URL = "app/controller? gggff=778";//request url in the all pages with responses, or set it to filter
// session.setAttribute("urlLocale", UL );
//        System.out.println(" GT main page comand servlet path"+request.getServletPath() + "________?_-____" + request.getQueryString() + "+++PARAMS");///////////////
