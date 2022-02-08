package by.epam.heritage.ap.service.validator;

import by.epam.heritage.ap.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import static by.epam.heritage.ap.controller.ConstantsParametersAndAttributes.PARAMETER_OFFER_ID;
import static by.epam.heritage.ap.controller.ConstantsParametersAndAttributes.PARAMETER_OFFER_PRICE;
import static by.epam.heritage.ap.service.validator.ValidatorConstants.MINIMUM_ZERO;

public class OfferStatusValidator implements Validable {
    private static final Logger logger = LogManager.getLogger(OfferStatusValidator.class);


    @Override
    public boolean checkUpdatedEntityIsValid(HttpServletRequest request) throws ServiceException, ValidatorException {

        String idOfferParam = request.getParameter(PARAMETER_OFFER_ID).trim();
        String priceOfferParam = request.getParameter(PARAMETER_OFFER_PRICE).trim();


        if (!Validable.validateStringParameterIntegerClass(priceOfferParam, 100000, MINIMUM_ZERO + 1)) {
            logger.error("Fail validation price offer");
            return false;
        }
        if (!Validable.validateStringParameterIntegerClass(idOfferParam, 100000, MINIMUM_ZERO)) {
            logger.error("Fail validation offer id");
            return false;
        }
        return true;
    }

    @Override
    public boolean checkNewEntityIsValid(HttpServletRequest request) throws ServiceException {
        return false;
    }
}

//    String isSentParam = request.getParameter(PARAMETER_OFFER_SENT).trim();
//    String isPaidParam = request.getParameter(PARAMETER_OFFER_PAID).trim();
//    String isClosedParam = request.getParameter(PARAMETER_OFFER_CLOSED).trim();
