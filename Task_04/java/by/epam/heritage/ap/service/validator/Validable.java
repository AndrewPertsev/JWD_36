package by.epam.heritage.ap.service.validator;

import by.epam.heritage.ap.model.Entity;
import by.epam.heritage.ap.service.ServiceException;

import javax.servlet.http.HttpServletRequest;

public interface Validable <T extends Entity> {

     boolean checkNewEntityIsValid(HttpServletRequest request) throws ServiceException;
     boolean checkUpdatedEntityIsValid(HttpServletRequest request) throws ServiceException, ValidatorException;
}
