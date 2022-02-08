package by.epam.heritage.ap.controller.impl;

import by.epam.heritage.ap.controller.Commandable;
import by.epam.heritage.ap.model.Guest;
import by.epam.heritage.ap.service.GuestServiceable;
import by.epam.heritage.ap.service.ServiceException;
import by.epam.heritage.ap.service.ServiceFactory;
import by.epam.heritage.ap.service.builder.BuilderFactory;
import by.epam.heritage.ap.service.validator.Validable;
import by.epam.heritage.ap.service.validator.ValidatorException;
import by.epam.heritage.ap.service.validator.ValidatorFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.epam.heritage.ap.controller.ConstantsCommandPath.*;
import static by.epam.heritage.ap.controller.ConstantsParametersAndAttributes.*;

public class UpdateGuestDataCommand implements Commandable {
    private static final Logger logger = LogManager.getLogger(UpdateGuestDataCommand.class);


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idGuest = 0;
        boolean done = false;
        Guest guestUpdated = null;
        Boolean isValidGuest = true;


        try {
            Validable validator = ValidatorFactory.getInstance().getGuestValidator();
            isValidGuest = validator.checkUpdatedEntityIsValid(request);
        } catch (ServiceException | ValidatorException e) {
            logger.error("Fail validation updated guest");
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }


        if (isValidGuest) {

            try {
                guestUpdated = BuilderFactory.getInstance().getGuestBuilder().update(request);
            } catch (ValidatorException e) {
                logger.error("Can't update data", e);
                request.setAttribute(ATTRIBUTE_MESSAGE_FAIL, MESSAGE_INVALID_DATA);
                response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            }


            try {
                GuestServiceable guestService = ServiceFactory.getInstance().getGuestService();
                idGuest = (Integer.parseInt(request.getParameter(PARAMETER_GUEST_ID)));
                done = guestService.update(guestUpdated);
            } catch (ServiceException e) {
                logger.error("Can't execute request to database", e);
                request.setAttribute(ATTRIBUTE_MESSAGE_FAIL, MESSAGE_DATABASE_ERROR);
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }

            if (done) {
                response.sendRedirect(PATH_REDIRECT_CONTROLLER_COMMAND + GO_TO_GUEST_MANAGEMENT_PAGE + "&" + PARAMETER_GUEST_ID + "=" + idGuest + PATH_MESSAGE_SUCCESS);
            } else {
                response.sendRedirect(PATH_REDIRECT_CONTROLLER_COMMAND + GO_TO_GUEST_MANAGEMENT_PAGE + PATH_MESSAGE_FAIL);
            }

        } else {
            response.sendRedirect(PATH_REDIRECT_CONTROLLER_COMMAND + GO_TO_GUEST_MANAGEMENT_PAGE + PATH_MESSAGE_FAIL);
        }
    }
}
