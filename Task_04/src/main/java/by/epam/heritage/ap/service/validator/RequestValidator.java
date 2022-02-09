package by.epam.heritage.ap.service.validator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;

import static by.epam.heritage.ap.controller.ConstantsParametersAndAttributes.*;
import static by.epam.heritage.ap.service.validator.ValidatorConstants.*;

public final class RequestValidator implements Validable {
    private static final Logger logger = LogManager.getLogger(RequestValidator.class);

    @Override
    public boolean checkNewEntityIsValid(HttpServletRequest request) {
        boolean isValid = true;
        String dateout = String.valueOf(request.getParameter(PARAMETER_CHECK_OUT_DATE)).trim();
        String dateIn = String.valueOf(request.getParameter(PARAMETER_CHECK_IN_DATE)).trim();
        String category = request.getParameter(PARAMETER_CATEGORY).trim();
        String quantity = request.getParameter(PARAMETER_QUANTITY).trim();
        String transfer = request.getParameter(PARAMETER_TRANSFER).trim();
        String menu = request.getParameter(PARAMETER_MENU).trim();


        if (!inspectIsValidRequestBookingDate(dateIn, dateout)) {
            logger.error("Fail validation date ");
            return false;
        }

        if (!Validable.validateStringParameterIntegerClass(quantity, MAXIMUM_QUANTITY_PERSONS, MINIMUM_ZERO)) {
            logger.error("Fail validation quantity ");
            return false;
        }
        if (!Validable.validateStringParameterIntegerClass(category, MAXIMUM_CATEGORY_NUMBER_APARTMENT, MINIMUM_ZERO)) {
            logger.error("Fail validation category ");
            return false;
        }
        if (!Validable.validateStringParameterIntegerClass(transfer, MAXIMUM_TRANSFER_ID, MINIMUM_ZERO)) {
            logger.error("Fail validation transfer id ");
            return false;
        }
        if (!Validable.validateStringParameterIntegerClass(menu, MAXIMUM_MENU_ID, MINIMUM_ZERO)) {
            logger.error("Fail validation menu id ");
            return false;
        }

        return isValid;

    }


    @Override
    public boolean checkUpdatedEntityIsValid(HttpServletRequest request) throws ValidatorException {

        return checkNewEntityIsValid(request);

    }


    public boolean inspectIsValidRequestBookingDate(String startDate, String endDate) {
        LocalDate start;
        LocalDate end;
        if (startDate == null || startDate == "") {
            logger.error("Service can't validate start date ");
            return false;
        }
        if (endDate == null || endDate == "") {
            logger.error("Service can't validate null end date ");
            return false;
        }

        if (!startDate.matches(PATTERN_DATE)) {
            logger.error("Service can't validate start date ");
            return false;
        }
        if (!endDate.matches(PATTERN_DATE)) {
            logger.error("Service can't validate end date ");
            return false;
        }
        start = LocalDate.parse(startDate);
        end = LocalDate.parse(endDate);

        if (end.isEqual(start)) {
            logger.error("Service can't validate equal dates ");
            return false;
        }
        if (start.isBefore(LocalDate.now())) {
            logger.error("Service can't validate start date when it's  before now");
            return false;
        }
        if (end.isAfter(LocalDate.now().plusDays(MAXIMUM_DAYS_FOR_BOOKING))) {
            logger.error("Service can't validate end date, because period greater than" + MAXIMUM_DAYS_FOR_BOOKING + " days ");
            return false;
        }

        return true;
    }


}
