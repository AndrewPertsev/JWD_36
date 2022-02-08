package by.epam.heritage.ap.controller.impl;

import by.epam.heritage.ap.controller.Commandable;
import by.epam.heritage.ap.model.Offer;
import by.epam.heritage.ap.service.OfferServiceable;
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

public class UpdateOfferStatusAndPriceCommand implements Commandable {
    private static final Logger logger = LogManager.getLogger(UpdateOfferStatusAndPriceCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        boolean done = false;
        boolean isValidOffer = false;
        Offer offerStatusCandidate = new Offer();

        try {
            Validable validator = ValidatorFactory.getInstance().getOfferValidator();
            isValidOffer = validator.checkUpdatedEntityIsValid(request);
        } catch (ServiceException | ValidatorException e) {
            logger.error("Fail validation updated guest");
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }

        if (isValidOffer) {

            try {
                offerStatusCandidate = BuilderFactory.getInstance().getOfferBuilder().update(request);
            } catch (ServiceException e) {
                logger.error("Can't validate incoming data", e);
                request.setAttribute(ATTRIBUTE_MESSAGE_FAIL, MESSAGE_INVALID_DATA);
                response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            }


            try {
                OfferServiceable offerService = ServiceFactory.getInstance().getOfferService();
                done = offerService.update(offerStatusCandidate);
            } catch (ServiceException e) {
                logger.error("Can't execute request to database", e);
                request.setAttribute(ATTRIBUTE_MESSAGE_FAIL, MESSAGE_DATABASE_ERROR);
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }

            if (done) {
                response.sendRedirect(PATH_REDIRECT_CONTROLLER_COMMAND + GO_TO_OFFER_MANAGEMENT_PAGE + PATH_MESSAGE_SUCCESS);
            } else {
                response.sendRedirect(PATH_REDIRECT_CONTROLLER_COMMAND + GO_TO_OFFER_MANAGEMENT_PAGE + PATH_MESSAGE_FAIL);
            }

        } else {
            response.sendRedirect(PATH_REDIRECT_CONTROLLER_COMMAND + GO_TO_OFFER_MANAGEMENT_PAGE + PATH_MESSAGE_FAIL);
        }
    }
}