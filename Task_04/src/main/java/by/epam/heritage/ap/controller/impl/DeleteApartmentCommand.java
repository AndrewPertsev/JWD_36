package by.epam.heritage.ap.controller.impl;

import by.epam.heritage.ap.controller.Commandable;
import by.epam.heritage.ap.service.ApartmentServiceable;
import by.epam.heritage.ap.service.ServiceException;
import by.epam.heritage.ap.service.ServiceFactory;
import by.epam.heritage.ap.service.validator.Validable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.epam.heritage.ap.controller.ConstantsCommandPath.*;
import static by.epam.heritage.ap.controller.ConstantsParametersAndAttributes.*;
import static by.epam.heritage.ap.service.validator.ValidatorConstants.MAXIMUM_NUMBER_APARTMENT;
import static by.epam.heritage.ap.service.validator.ValidatorConstants.MINIMUM_ZERO;

public class DeleteApartmentCommand implements Commandable {
    private static final Logger logger = LogManager.getLogger(DeleteApartmentCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String idApartmentParam = request.getParameter(PARAMETER_APARTMENT_ID);
        boolean done = false;
        boolean validParameter = false; //TODO VALIDATOR max id

        validParameter = Validable.validateStringParameterIntegerClass(idApartmentParam, MAXIMUM_NUMBER_APARTMENT, MINIMUM_ZERO);

        if (validParameter) {


            try {
                ApartmentServiceable apartmentService = ServiceFactory.getInstance().getApartmentService();
                done = apartmentService.deleteByid(Integer.parseInt(idApartmentParam));
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
