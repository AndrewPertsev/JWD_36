package by.epam.heritage.ap.controller.impl.commands_goto;

import by.epam.heritage.ap.controller.Commandable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.epam.heritage.ap.controller.ConstantsCommandPath.PATH_GO_TO_HOME_PAGE;

public class GoToHomePageCommand implements Commandable {
    private static final Logger logger = LogManager.getLogger(GoToHomePageCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher requestDispatcher = request.getRequestDispatcher(PATH_GO_TO_HOME_PAGE);
        requestDispatcher.forward(request, response);
    }
}

