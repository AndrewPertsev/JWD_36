package by.epam.heritage.ap.service.validator;

import by.epam.heritage.ap.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import static by.epam.heritage.ap.controller.ConstantsParametersAndAttributes.*;
import static by.epam.heritage.ap.service.validator.ValidatorConstants.*;

public final class ApartmentValidator implements Validable {
    private static final Logger logger = LogManager.getLogger(ApartmentValidator.class);


    @Override
    public boolean checkNewEntityIsValid(HttpServletRequest request) {
        String idApartmentParam = request.getParameter(PARAMETER_APARTMENT_ID);
        String categoryParam = request.getParameter(PARAMETER_CATEGORY);
        String capacityParam = request.getParameter(PARAMETER_CAPACITY);
        String description = request.getParameter(PARAMETER_DESCRIPTION);
        String pathToPicture = request.getParameter(PARAMETER_PATH_TO_PICTURE);
        // .setPrice(BigDecimal.valueOf(Long.valueOf(request.getParameter("price")));

        if (!ValidatorCommon.validateStrinqParameterIntegerClass(idApartmentParam, MAXIMUM_NUMBER_APARTMENT, MINIMUM_ZERO)) {
            logger.error("Fail validation id apartment ");
            return false;
        }
        if (!ValidatorCommon.validateStrinqParameterIntegerClass(capacityParam, MAXIMUM_CAPACITY_APARTMENT, MINIMUM_ZERO)) {
            logger.error("Fail validation capacity ");
            return false;
        }
        if (!ValidatorCommon.validateStrinqParameterIntegerClass(categoryParam, MAXIMUM_CATEGORY_NUMBER_APARTMENT, MINIMUM_ZERO)) {
            logger.error("Fail validation category ");
            return false;
        }
        if (!ValidatorCommon.validateParameterStringClass(pathToPicture, PATTERN_PICTURE_JPG)) {
            logger.error("Fail validation picture path ");
            return false;
        }
        if (!ValidatorCommon.validateParameterStringClass(description, PATTERN_DESCRIPTION)) {
            logger.error("Fail validation description ");
            return false;
        }

        return true;
    }

    @Override
    public boolean checkUpdatedEntityIsValid(HttpServletRequest request) throws ServiceException {
        return new ApartmentValidator().checkNewEntityIsValid(request);
    }


}
