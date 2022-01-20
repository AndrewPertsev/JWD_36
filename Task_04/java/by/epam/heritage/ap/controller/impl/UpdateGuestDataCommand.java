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

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.epam.heritage.ap.controller.ConstantsParametersAndAttributes.*;

public class UpdateGuestDataCommand implements Commandable {
    private static final Logger logger = LogManager.getLogger(UpdateGuestDataCommand.class);


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean done = false;
        int idGuest = 0;
        Guest guestUpdated = null;


        try {
            guestUpdated = BuilderFactory.getInstance().getGuestBuilder().update(request);
        } catch (ValidatorException e) {
            logger.error("Can't validate incoming data", e);
            request.setAttribute(ATTRIBUTE_MESSAGE_FAIL, MESSAGE_INVALID_DATA);
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }

        GuestServiceable guestService = ServiceFactory.getInstance().getGuestService();

        try {
            idGuest = (Integer.parseInt(request.getParameter(PARAMETER_GUEST_ID)));
            done = guestService.update(guestUpdated);
        } catch (ServiceException e) {
            logger.error("Can't execute request to database", e);
            request.setAttribute(ATTRIBUTE_MESSAGE_FAIL, MESSAGE_DATABASE_ERROR);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }

        response.sendRedirect("Controller?" + PARAMETER_COMMAND + "=GO_TO_GUEST_MANAGEMENT_PAGE&" + PARAMETER_GUEST_ID + "=" + idGuest);

    }
}

