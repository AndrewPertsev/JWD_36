package by.epam.heritage.ap.controller.impl;

import by.epam.heritage.ap.controller.CommandException;
import by.epam.heritage.ap.controller.Commandable;
import by.epam.heritage.ap.model.Guest;
import by.epam.heritage.ap.service.GuestServiceable;
import by.epam.heritage.ap.service.ServiceException;
import by.epam.heritage.ap.service.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.epam.heritage.ap.controller.ConstantsParametersAndAttributes.*;

public class LoginCommand implements Commandable {
    private static final Logger logger = LogManager.getLogger(LoginCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException, IOException {
        String login;
        String password;
        Guest authorizedGuest;
        String authorizedGuestId;
        String authorizedGuestRoleId;
        String authorizedGuestEmail;
        String authorizedGuestPhone;
        String authorizedGuestName;
        String authorizedGuestSurName;
        boolean authorizedGuestVIP;

        HttpSession session = request.getSession(true);
        login = request.getParameter(PARAMETER_LOGIN);
        password = request.getParameter(PARAMETER_PASSWORD);

        GuestServiceable guestService = ServiceFactory.getInstance().getGuestService();

        try {
            authorizedGuest = guestService.checkGuestAuthorization(login, password);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }

        if (authorizedGuest != null) {

            authorizedGuestId = String.valueOf(authorizedGuest.getGuestId());
            authorizedGuestRoleId = String.valueOf(authorizedGuest.getRoleId());
            authorizedGuestName = authorizedGuest.getName();
            authorizedGuestSurName = authorizedGuest.getSurname();
            authorizedGuestEmail = authorizedGuest.getEmail();
            authorizedGuestPhone = authorizedGuest.getPhone();
            authorizedGuestVIP = authorizedGuest.isVip();

            session.setAttribute(SESSION_ATTRIBUTE_GUEST_ID, authorizedGuestId);
            session.setAttribute(SESSION_ATTRIBUTE_GUEST_ROLE_ID, authorizedGuestRoleId);
            session.setAttribute(SESSION_ATTRIBUTE_GUEST_NAME, authorizedGuestName);
            session.setAttribute(SESSION_ATTRIBUTE_GUEST_SUR_NAME, authorizedGuestSurName);
            session.setAttribute(SESSION_ATTRIBUTE_GUEST_EMAIL, authorizedGuestEmail);
            session.setAttribute(SESSION_ATTRIBUTE_GUEST_PHONE, authorizedGuestPhone);
            session.setAttribute(SESSION_ATTRIBUTE_GUEST_VIP, authorizedGuestVIP);

            request.setAttribute(MESSAGE_LOGIN, "in the login command ");///////todo

            response.sendRedirect("Controller?command=GO_TO_MAIN_PAGE&" + MESSAGE_LOGIN + "=" + MESSAGE_WELCOME);
        } else {
            response.sendRedirect("Controller?command=GO_TO_LOGIN_PAGE&" + MESSAGE_LOGIN + "=" + MESSAGE_WRONG_DATA);
        }
    }
}

//  if (!"".equals(role)) {   todo UNCOMMENT





