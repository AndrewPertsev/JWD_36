package by.epam.heritage.ap.controller.impl;

import by.epam.heritage.ap.controller.Commandable;
import by.epam.heritage.ap.model.Offer;
import by.epam.heritage.ap.model.Request;
import by.epam.heritage.ap.service.*;
import by.epam.heritage.ap.service.validator.ValidatorCommon;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

import static by.epam.heritage.ap.controller.ConstantsCommandPath.*;
import static by.epam.heritage.ap.controller.ConstantsParametersAndAttributes.*;
import static by.epam.heritage.ap.service.validator.ValidatorConstants.*;

public class PushOfferToUserCommand implements Commandable {
    private static final Logger logger = LogManager.getLogger(PushOfferToUserCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Offer offer = null;
        int apartmentId = 0;
        int idRequestInt = 0;
        boolean done = false;
        LocalDate bookedBefore;
        LocalDate bookedFrom;

        OfferServiceable offerService = ServiceFactory.getInstance().getOfferService();
        RequestServiceable requestService = ServiceFactory.getInstance().getRequestService();
        TimesheetServiceable timesheetService = ServiceFactory.getInstance().getTimesheetService();

        String idRequest = request.getParameter(PARAMETER_REQUEST_ID);
        String idApartment = request.getParameter(PARAMETER_APARTMENT_ID);
        boolean validIdRequest = ValidatorCommon.validateStringParameterIntegerClass(idRequest, MAXIMUM_NUMBER_REQUEST, MINIMUM_ZERO);
        boolean validIdApartment = ValidatorCommon.validateStringParameterIntegerClass(idRequest, MAXIMUM_NUMBER_APARTMENT, MINIMUM_ZERO);


        if (validIdRequest && validIdApartment) {

            request.setAttribute(PARAMETER_REQUEST_ID, idRequest);

            try {
                apartmentId = Integer.parseInt(idApartment);
                idRequestInt = Integer.parseInt(idRequest);
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

            try {
                Request usersRequest = requestService.findByid(idRequestInt);
                bookedFrom = usersRequest.getStart();
                bookedBefore = usersRequest.getEnd();
                done = timesheetService.reserveConfirmedDaysByOffer(apartmentId, bookedFrom, bookedBefore);
            } catch (ServiceException e) {
                logger.error("Can't execute request to database", e);
                request.setAttribute(ATTRIBUTE_MESSAGE_FAIL, MESSAGE_DATABASE_ERROR);
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }

            if (done) {
                response.sendRedirect(PATH_REDIRECT_CONTROLLER_COMMAND + GO_TO_OFFER_MANAGEMENT_PAGE + "&" + PARAMETER_REQUEST_ID + "=" + idRequestInt + PATH_MESSAGE_SUCCESS);
            } else {
                response.sendRedirect(PATH_REDIRECT_CONTROLLER_COMMAND + GO_TO_REQUEST_MANAGEMENT_PAGE + PATH_MESSAGE_FAIL);
            }
        } else {
            response.sendRedirect(PATH_REDIRECT_CONTROLLER_COMMAND + GO_TO_REQUEST_MANAGEMENT_PAGE + PATH_MESSAGE_FAIL);
        }
    }
}

