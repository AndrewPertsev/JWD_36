package by.epam.heritage.ap.controller.impl;

import by.epam.heritage.ap.controller.Commandable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.epam.heritage.ap.controller.ConstantsCommandPath.*;
import static by.epam.heritage.ap.controller.ConstantsParametersAndAttributes.PARAMETER_REQUEST_ID;

public class OfferCommand implements Commandable {
    private static final Logger logger = LogManager.getLogger(OfferCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String idRequest;

        idRequest = request.getParameter(PARAMETER_REQUEST_ID);
        request.setAttribute(PARAMETER_REQUEST_ID, idRequest);

        if (idRequest != null || idRequest != "") {
            response.sendRedirect(PATH_REDIRECT_CONTROLLER_COMMAND + GO_TO_OFFER_PROJECT_PAGE + "&" + PARAMETER_REQUEST_ID + "=" + idRequest);
        } else {
            response.sendRedirect(PATH_REDIRECT_CONTROLLER_COMMAND + GO_TO_REQUEST_MANAGEMENT_PAGE + PATH_MESSAGE_FAIL);
        }

    }
}
