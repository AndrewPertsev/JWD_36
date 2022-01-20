package by.epam.heritage.ap.controller.impl;

import by.epam.heritage.ap.controller.Commandable;
import by.epam.heritage.ap.service.RequestServiceable;
import by.epam.heritage.ap.service.ServiceException;
import by.epam.heritage.ap.service.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.epam.heritage.ap.controller.ConstantsParametersAndAttributes.*;

public class DeleteRequestCommand implements Commandable {
    private static final Logger logger = LogManager.getLogger(DeleteRequestCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String idRequestParam = request.getParameter(PARAMETER_REQUEST_ID);

        RequestServiceable requestServiceable = ServiceFactory.getInstance().getRequestService();
        try {
            requestServiceable.deleteByid(Integer.parseInt(idRequestParam));
        } catch (ServiceException e) {
            logger.error("Can't execute request to database", e);
            request.setAttribute(ATTRIBUTE_MESSAGE_FAIL, MESSAGE_DATABASE_ERROR);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }

        response.sendRedirect("Controller?" + PARAMETER_COMMAND + "=GO_TO_REQUEST_MANAGEMENT_PAGE&start=" + MESSAGE_SUCCESS);

    }
}
