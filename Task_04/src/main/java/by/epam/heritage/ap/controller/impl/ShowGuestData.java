package by.epam.heritage.ap.controller.impl;

import by.epam.heritage.ap.controller.Commandable;
import by.epam.heritage.ap.service.validator.Validable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.epam.heritage.ap.controller.ConstantsCommandPath.*;
import static by.epam.heritage.ap.controller.ConstantsParametersAndAttributes.PARAMETER_GUEST_ID;
import static by.epam.heritage.ap.service.validator.ValidatorConstants.MAXIMUM_NUMBER_APARTMENT;
import static by.epam.heritage.ap.service.validator.ValidatorConstants.MINIMUM_ZERO;

public class ShowGuestData implements Commandable {
    private static final Logger logger = LogManager.getLogger(ShowGuestData.class);


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        boolean validParameter = false;
        String idGuestParam = request.getParameter(PARAMETER_GUEST_ID);

        validParameter = Validable.validateStringParameterIntegerClass(idGuestParam, MAXIMUM_NUMBER_APARTMENT, MINIMUM_ZERO);

        if (validParameter) {
            int idGuest = (Integer.parseInt(idGuestParam));
            response.sendRedirect(PATH_REDIRECT_CONTROLLER_COMMAND + GO_TO_GUEST_MANAGEMENT_PAGE + "&" + PARAMETER_GUEST_ID + "=" + idGuest);
        } else {
            response.sendRedirect(PATH_REDIRECT_CONTROLLER_COMMAND + GO_TO_GUEST_MANAGEMENT_PAGE + PATH_MESSAGE_FAIL);
        }
    }
}