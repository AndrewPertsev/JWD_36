package by.epam.heritage.ap.controller.impl;

import by.epam.heritage.ap.controller.Commandable;
import by.epam.heritage.ap.model.Request;
import by.epam.heritage.ap.service.RequestServiceable;
import by.epam.heritage.ap.service.ServiceException;
import by.epam.heritage.ap.service.ServiceFactory;
import by.epam.heritage.ap.service.builder.BuilderFactory;
import by.epam.heritage.ap.service.validator.ValidatorException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.epam.heritage.ap.controller.ConstantsParametersAndAttributes.*;

public class RequestCommand implements Commandable {
    private static final Logger logger = LogManager.getLogger(RequestCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        boolean isValidRequest = true;
        Request requestUsers = null;


        try {
            requestUsers = BuilderFactory.getInstance().getRequestBuilder().create(request);
        } catch (ValidatorException e) {
            logger.error("Can't validate incoming data", e);
            request.setAttribute(ATTRIBUTE_MESSAGE_FAIL, MESSAGE_INVALID_DATA);
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }

        RequestServiceable requestService = ServiceFactory.getInstance().getRequestService();

        if (isValidRequest) {
            try {
                requestService.add(requestUsers);
            } catch (ServiceException e) {
                logger.error("Can't execute request to database", e);
                request.setAttribute(ATTRIBUTE_MESSAGE_FAIL, MESSAGE_DATABASE_ERROR);
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }

            response.sendRedirect("Controller?" + PARAMETER_COMMAND + "=GO_TO_MAIN_PAGE&start=" + MESSAGE_SUCCESS);
        } else { response.sendRedirect("Controller?" + PARAMETER_COMMAND + "=GO_TO_REQUEST_PAGE&errorMessage=" + MESSAGE_WRONG_DATA);
        }
    }
}