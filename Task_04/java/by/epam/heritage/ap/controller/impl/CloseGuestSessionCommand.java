package by.epam.heritage.ap.controller.impl;

import by.epam.heritage.ap.controller.Commandable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.epam.heritage.ap.controller.ConstantsCommandPath.GO_TO_LOGIN_PAGE;
import static by.epam.heritage.ap.controller.ConstantsCommandPath.PATH_REDIRECT_CONTROLLER_COMMAND;
import static by.epam.heritage.ap.controller.ConstantsParametersAndAttributes.*;

public class CloseGuestSessionCommand implements Commandable {
    private static final Logger logger = LogManager.getLogger(CloseGuestSessionCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession(true);

        session.removeAttribute(SESSION_ATTRIBUTE_GUEST_ID);
        session.removeAttribute(SESSION_ATTRIBUTE_GUEST_ROLE_ID);
        session.removeAttribute(SESSION_ATTRIBUTE_GUEST_NAME);
        session.removeAttribute(SESSION_ATTRIBUTE_GUEST_SUR_NAME);
        session.removeAttribute(SESSION_ATTRIBUTE_GUEST_EMAIL);
        session.removeAttribute(SESSION_ATTRIBUTE_GUEST_PHONE);
        session.removeAttribute(SESSION_ATTRIBUTE_GUEST_VIP);
        session.invalidate();

        response.sendRedirect(PATH_REDIRECT_CONTROLLER_COMMAND + GO_TO_LOGIN_PAGE);

    }
}
