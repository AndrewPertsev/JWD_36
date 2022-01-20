package by.epam.heritage.ap.controller.impl;

import by.epam.heritage.ap.controller.Commandable;
import by.epam.heritage.ap.model.Offer;
import by.epam.heritage.ap.service.OfferServiceable;
import by.epam.heritage.ap.service.RequestServiceable;
import by.epam.heritage.ap.service.ServiceException;
import by.epam.heritage.ap.service.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.epam.heritage.ap.controller.ConstantsParametersAndAttributes.*;

public class PushOfferToUserCommand implements Commandable {
    private static final Logger logger = LogManager.getLogger(PushOfferToUserCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Offer offer = null;
        int apartmentId;
        int idRequestInt = 0;

        OfferServiceable offerService = ServiceFactory.getInstance().getOfferService();
        RequestServiceable requestService = ServiceFactory.getInstance().getRequestService();

        String idRequest = request.getParameter(PARAMETER_REQUEST_ID);
        request.setAttribute(PARAMETER_REQUEST_ID, idRequest);


        try {
            apartmentId = Integer.parseInt(request.getParameter(PARAMETER_APARTMENT_ID));
            idRequestInt = Integer.parseInt(request.getParameter(PARAMETER_REQUEST_ID));
            offer = offerService.assembleOfferForApartment(apartmentId, idRequestInt);
        } catch (ServiceException e) {
            logger.error("Can't execute request to database", e);
            request.setAttribute(ATTRIBUTE_MESSAGE_FAIL, MESSAGE_DATABASE_ERROR);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }

        try {
            offerService.add(offer);
        } catch (ServiceException e) {
            logger.error("Can't execute request to database", e);
            request.setAttribute(ATTRIBUTE_MESSAGE_FAIL, MESSAGE_DATABASE_ERROR);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }

        try {
            requestService.setRequestIsRespondedStatus(true, idRequestInt);
        } catch (ServiceException e) {
            logger.error("Can't execute request to database", e);
            request.setAttribute(ATTRIBUTE_MESSAGE_FAIL, MESSAGE_DATABASE_ERROR);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }


        if (idRequest != null || idRequest != "") {
            response.sendRedirect("Controller?" + PARAMETER_COMMAND + "=GO_TO_OFFER_MANAGEMENT_PAGE&" + PARAMETER_REQUEST_ID + "=" + idRequestInt);//+"&messageOffer=" + MESSAGE_WELCOME);
        } else {
            response.sendRedirect("Controller?" + PARAMETER_COMMAND + "=GO_TO_REQUEST_MANAGEMENT_PAGE&errorMessage=" + MESSAGE_WRONG_DATA);
        }
    }
}

