package by.epam.heritage.ap.controller.impl;

import by.epam.heritage.ap.controller.CommandException;
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
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException, IOException {
        int idRequestInt;
        int apartmentId;
        Offer offer = null;
        HttpSession session = request.getSession(true);

        OfferServiceable offerService = ServiceFactory.getInstance().getOfferService();
        RequestServiceable requestService = ServiceFactory.getInstance().getRequestService();

        String idRequest = request.getParameter(PARAMETER_REQUEST_ID);
        request.setAttribute(PARAMETER_REQUEST_ID, idRequest);

        idRequestInt = Integer.parseInt(request.getParameter(PARAMETER_REQUEST_ID));
        apartmentId = Integer.parseInt(request.getParameter(PARAMETER_APARTMENT_ID));


        try {
            offer = offerService.assembleOfferForApartment(apartmentId, idRequestInt);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }

        try {
            offerService.add(offer);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }

        try {
            requestService.setRequestIsRespondedStatus(true, idRequestInt);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }


        if (idRequest != null || idRequest != "") {
            response.sendRedirect("Controller?command=GO_TO_OFFER_MANAGEMENT_PAGE&" + PARAMETER_REQUEST_ID + "=" + idRequestInt);//+"&messageOffer=" + MESSAGE_WELCOME);
        } else {
            response.sendRedirect("Controller?command=GO_TO_REQUEST_MANAGEMENT_PAGE&errorMessage=" + MESSAGE_WRONG_DATA);
        }
    }
}

