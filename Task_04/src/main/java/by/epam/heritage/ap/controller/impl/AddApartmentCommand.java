package by.epam.heritage.ap.controller.impl;

import by.epam.heritage.ap.controller.Commandable;
import by.epam.heritage.ap.model.Apartment;
import by.epam.heritage.ap.service.ServiceException;
import by.epam.heritage.ap.service.ServiceFactory;
import by.epam.heritage.ap.service.Serviceable;
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

public class AddApartmentCommand implements Commandable {
    private static final Logger logger = LogManager.getLogger(AddApartmentCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        boolean done = false;
        Boolean isValidApartment = false;
        Apartment apartmentValidated = null;

       Validable validator = ValidatorFactory.getInstance().getApartmentValidator();
        try {
            isValidApartment = validator.checkNewEntityIsValid(request);
        } catch (ServiceException e) {
            logger.error("Can't validate incoming data", e);
            request.setAttribute(ATTRIBUTE_MESSAGE_FAIL, MESSAGE_INVALID_DATA);
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }

        if (isValidApartment) {


            try {
                apartmentValidated = BuilderFactory.getInstance().getApartmentBuilder().create(request);
            } catch (ValidatorException e) {
                logger.error("Can't validate incoming data", e);
                request.setAttribute(ATTRIBUTE_MESSAGE_FAIL, MESSAGE_INVALID_DATA);
                response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            }


            try {
                Serviceable apartmentService = ServiceFactory.getInstance().getApartmentService();
                done = apartmentService.add(apartmentValidated);
            } catch (ServiceException e) {
                logger.error("Can't execute request to database", e);
                request.setAttribute(ATTRIBUTE_MESSAGE_FAIL, MESSAGE_DATABASE_ERROR);
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }


            if (done) {
                response.sendRedirect(PATH_REDIRECT_CONTROLLER_COMMAND + GO_TO_APARTMENT_MANAGEMENT_PAGE + PATH_MESSAGE_SUCCESS);
            } else {
                response.sendRedirect(PATH_REDIRECT_CONTROLLER_COMMAND + GO_TO_APARTMENT_MANAGEMENT_PAGE + PATH_MESSAGE_FAIL);
            }

        } else {
            response.sendRedirect(PATH_REDIRECT_CONTROLLER_COMMAND + GO_TO_APARTMENT_MANAGEMENT_PAGE + PATH_MESSAGE_FAIL);
        }
    }
}

