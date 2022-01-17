package by.epam.heritage.ap.controller.impl.commands_goto;


import by.epam.heritage.ap.controller.Commandable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class GoToRegistrationPageCommand implements Commandable {
    private static final Logger logger = LogManager.getLogger(GoToRegistrationPageCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/registration.jsp");
        dispatcher.forward(request, response);


    }
}
