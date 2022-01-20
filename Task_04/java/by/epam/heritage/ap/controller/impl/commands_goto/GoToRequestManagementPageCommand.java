package by.epam.heritage.ap.controller.impl.commands_goto;

import by.epam.heritage.ap.controller.Commandable;
import by.epam.heritage.ap.model.Request;
import by.epam.heritage.ap.service.RequestServiceable;
import by.epam.heritage.ap.service.ServiceException;
import by.epam.heritage.ap.service.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static by.epam.heritage.ap.controller.ConstantsCommandPath.PATH_GO_TO_REQUEST_MANAGEMENT_PAGE;
import static by.epam.heritage.ap.controller.ConstantsParametersAndAttributes.*;

public class GoToRequestManagementPageCommand implements Commandable {
    private static final Logger logger = LogManager.getLogger(GoToRequestManagementPageCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Request> requests = new ArrayList<>(0);

        RequestServiceable requestService = ServiceFactory.getInstance().getRequestService();

        try {
            requests = requestService.findAll();
        } catch (ServiceException e) {
            logger.error("Can't execute request to database", e);
            request.setAttribute(ATTRIBUTE_MESSAGE_FAIL, MESSAGE_DATABASE_ERROR);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }

        request.setAttribute(ATTRIBUTE_REQUESTS, requests);

        RequestDispatcher dispatcher = request.getRequestDispatcher(PATH_GO_TO_REQUEST_MANAGEMENT_PAGE);
        dispatcher.forward(request, response);

    }
}
