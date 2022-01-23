package by.epam.heritage.ap.service.validator;

import by.epam.heritage.ap.service.ServiceException;

import javax.servlet.http.HttpServletRequest;

public class OfferStatusValidator implements Validable{
    @Override
    public boolean checkNewEntityIsValid(HttpServletRequest request) throws ServiceException {
        return false;
    }

    @Override
    public boolean checkUpdatedEntityIsValid(HttpServletRequest request) throws ServiceException, ValidatorException {
        return false;
    }
}
