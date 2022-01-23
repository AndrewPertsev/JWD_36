package by.epam.heritage.ap.service.builder;

import by.epam.heritage.ap.model.Entity;
import by.epam.heritage.ap.service.ServiceException;
import by.epam.heritage.ap.service.validator.ValidatorException;

import javax.servlet.http.HttpServletRequest;

public interface Buildable<T extends Entity> {

    T create(HttpServletRequest request) throws ServiceException, ValidatorException;

    T update(HttpServletRequest request) throws ServiceException, ValidatorException;
}
