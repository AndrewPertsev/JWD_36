package by.epam.heritage.ap.service.validator;

import by.epam.heritage.ap.service.RequestServiceable;
import by.epam.heritage.ap.service.ServiceException;
import by.epam.heritage.ap.service.ServiceFactory;
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
        String dateout = String.valueOf(request.getParameter(PARAMETER_CHECK_OUT_DATE));
        String dateIn = String.valueOf(request.getParameter(PARAMETER_CHECK_IN_DATE));
        String category = request.getParameter(PARAMETER_CATEGORY);
        String quantity = request.getParameter(PARAMETER_QUANTITY);
        String transfer = request.getParameter(PARAMETER_TRANSFER);
        String menu = request.getParameter(PARAMETER_MENU);


        if (! inspectIsValidRequestBookingDate( dateIn, dateout)) {
            logger.error("Fail validation date ");
            return false;
        }

        if ( ! ValidatorCommon.validateStrinqParameterIntegerClass ( quantity, MAXIMUM_QUANTITY_PERSONS, MINIMUM_ZERO)) {
            logger.error("Fail validation quantity ");
            return false;
        }
        if ( ! ValidatorCommon.validateStrinqParameterIntegerClass ( category, MAXIMUM_CATEGORY_NUMBER_APARTMENT, MINIMUM_ZERO)) {
            logger.error("Fail validation category ");
            return false;
        }
        if ( ! ValidatorCommon.validateStrinqParameterIntegerClass ( transfer, MAXIMUM_TRANSFER_ID, MINIMUM_ZERO)) {
            logger.error("Fail validation transfer id ");
            return false;
        }
        if ( ! ValidatorCommon.validateStrinqParameterIntegerClass ( menu, MAXIMUM_MENU_ID, MINIMUM_ZERO)) {
            logger.error("Fail validation menu id ");
            return false;
        }

        return isValid;

    }


    @Override
    public boolean checkUpdatedEntityIsValid(HttpServletRequest request) throws ValidatorException {
        String idRequest = request.getParameter(PARAMETER_REQUEST_ID);
        boolean isValid = false;

        isValid = inspectIsValidRequestIdToUpdateRequest( idRequest);
        if ( ! isValid) {
            logger.error("Fail validation request id ");
            return false;
        }

        return checkNewEntityIsValid(request);

    }


    public boolean inspectIsValidRequestIdToUpdateRequest(String idRequest) throws ValidatorException {
        int maximumRequestId;
        RequestServiceable requestService = ServiceFactory.getInstance().getRequestService();

        try {
            maximumRequestId = requestService.findMaximumRequestid();
        } catch (ServiceException e) {
            logger.error("Service can't find max id request ");
            throw new ValidatorException(e);
        }
        if ( ! ValidatorCommon.validateStrinqParameterIntegerClass ( idRequest, maximumRequestId, MINIMUM_ZERO)) {
            return false;
        }

        return true;
    }


    public boolean inspectIsValidRequestBookingDate(String startDate, String endDate) {
        LocalDate start;
        LocalDate end;

        if (startDate == null || startDate == "") {
            logger.error("Service can't validate start date ");
            return false;
        }
        if (endDate == null || endDate == "") {
            logger.error("Service can't validate start date ");
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
