package by.epam.heritage.ap.controller.impl;

import by.epam.heritage.ap.controller.Commandable;
import by.epam.heritage.ap.model.Guest;
import by.epam.heritage.ap.service.GuestServiceable;
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

public class RegistrationCommand implements Commandable {
    private static final Logger logger = LogManager.getLogger(RegistrationCommand.class);


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Guest guestCandidate = null;
        boolean isValidCandidate = true;
        GuestServiceable guestService = ServiceFactory.getInstance().getGuestService();

        try {
            guestCandidate = BuilderFactory.getInstance().getGuestBuilder().create(request);
        } catch (ServiceException | ValidatorException e) {
            logger.error("Can't validate incoming data", e);
            request.setAttribute(ATTRIBUTE_MESSAGE_FAIL, MESSAGE_INVALID_DATA);
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }

        try {
            isValidCandidate = guestService.add(guestCandidate);
        } catch (ServiceException e) {
            logger.error("Can't execute request to database", e);
            request.setAttribute(ATTRIBUTE_MESSAGE_FAIL, MESSAGE_DATABASE_ERROR);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
        if (isValidCandidate) {
            response.sendRedirect("Controller?" + PARAMETER_COMMAND + "=GO_TO_HOME_PAGE&message_regist_succ=" + MESSAGE_REGISTRATION_SUCCESSFUL);
        } else {
            response.sendRedirect("Controller?" + PARAMETER_COMMAND + "=GO_TO_REGISTRATION_PAGE&errorMessage=" + MESSAGE_WRONG_DATA);
        }
    }
}
