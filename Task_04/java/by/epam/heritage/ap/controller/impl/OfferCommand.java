package by.epam.heritage.ap.controller.impl;

import by.epam.heritage.ap.controller.Commandable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.epam.heritage.ap.controller.ConstantsParametersAndAttributes.MESSAGE_WRONG_DATA;
import static by.epam.heritage.ap.controller.ConstantsParametersAndAttributes.PARAMETER_REQUEST_ID;

public class OfferCommand implements Commandable {
    private static final Logger logger = LogManager.getLogger(OfferCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String idRequest;
        HttpSession session = request.getSession(true);

        idRequest = request.getParameter(PARAMETER_REQUEST_ID);
        request.setAttribute(PARAMETER_REQUEST_ID, idRequest);

        if (idRequest != null || idRequest != "") {
            response.sendRedirect("Controller?command=GO_TO_OFFER_PROJECT_PAGE&" + PARAMETER_REQUEST_ID + "=" + idRequest);
        } else {
            response.sendRedirect("Controller?command=GO_TO_REQUEST_MANAGEMENT_PAGE&errorMessage=" + MESSAGE_WRONG_DATA);
        }

    }
}
