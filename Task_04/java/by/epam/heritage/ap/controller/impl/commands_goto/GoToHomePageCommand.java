package by.epam.heritage.ap.controller.impl.commands_goto;

import by.epam.heritage.ap.controller.Commandable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GoToHomePageCommand implements Commandable {
    private static final Logger logger = LogManager.getLogger(GoToHomePageCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/view/homepage.jsp");
        requestDispatcher.forward(request, response);
    }
}


//        HttpSession session = request.getSession();
//        String url = request.getRequestURL()+"?"+request.getQueryString()+"&user_role=1&page=homepage.jsp";//request url in the all pages with responses, or set it to filter
//   //     session.setAttribute("url", url );
//// enter to db. set data to context ,jsp part from request