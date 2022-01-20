package by.epam.heritage.ap.controller.impl;

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

import static by.epam.heritage.ap.controller.ConstantsCommandPath.GO_TO_LOGIN_PAGE;
import static by.epam.heritage.ap.controller.ConstantsCommandPath.GO_TO_MAIN_PAGE;
import static by.epam.heritage.ap.controller.ConstantsParametersAndAttributes.*;

public class LoginCommand implements Commandable {
    private static final Logger logger = LogManager.getLogger(LoginCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String login;
        String password;
        String authorizedGuestId;
        String authorizedGuestName;
        String authorizedGuestSurName;
        String authorizedGuestRoleId;
        String authorizedGuestPhone;
        String authorizedGuestEmail;
        boolean authorizedGuestVIP;
        Guest authorizedGuest = null;

        HttpSession session = request.getSession(true);
        login = request.getParameter(PARAMETER_LOGIN);
        password = request.getParameter(PARAMETER_PASSWORD);


        GuestServiceable guestService = ServiceFactory.getInstance().getGuestService();
        try {
            authorizedGuest = guestService.checkGuestAuthorization(login, password);
        } catch (ServiceException e) {
            logger.error("Can't execute request to database", e);
            request.setAttribute(ATTRIBUTE_MESSAGE_FAIL, MESSAGE_DATABASE_ERROR);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
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


            response.sendRedirect(request.getServletPath() + "?" + PARAMETER_COMMAND + "=" + GO_TO_MAIN_PAGE + "&" + ATTRIBUTE_MESSAGE_SUCCESS + "=" + MESSAGE_SUCCESS);
        } else {
            response.sendRedirect(request.getServletPath() + "?" + PARAMETER_COMMAND + "=" + GO_TO_LOGIN_PAGE + "&" + ATTRIBUTE_MESSAGE_FAIL + "=" + MESSAGE_WRONG_DATA);
        }
    }
}






