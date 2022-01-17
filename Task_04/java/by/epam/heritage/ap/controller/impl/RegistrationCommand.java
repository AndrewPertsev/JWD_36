package by.epam.heritage.ap.controller.impl;

import by.epam.heritage.ap.controller.CommandException;
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

import static by.epam.heritage.ap.controller.ConstantsParametersAndAttributes.MESSAGE_WRONG_DATA;

public class RegistrationCommand implements Commandable {
    private static final Logger logger = LogManager.getLogger(RegistrationCommand.class);

    public static final String MESSAGE_REGISTRATION_SUCCESSFUL = "You are successfully registered, please, login.";


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException, IOException {
        boolean isValidCandidate = true;
        GuestServiceable guestService = ServiceFactory.getInstance().getGuestService();

        Guest guestCandidate = null;
        try {
            guestCandidate = BuilderFactory.getInstance().getGuestBuilder().create(request);
        } catch (ServiceException e) {
            throw new CommandException(e);
        } catch (ValidatorException e) {
            throw new CommandException(e);
        }

        try {
            isValidCandidate = guestService.add(guestCandidate);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        if (isValidCandidate) {
            response.sendRedirect("Controller?command=GO_TO_HOME_PAGE&message_regist_succ=" + MESSAGE_REGISTRATION_SUCCESSFUL);
        } else {
            response.sendRedirect("Controller?command=GO_TO_REGISTRATION_PAGE&errorMessage=" + MESSAGE_WRONG_DATA);
        }
    }
}
