package by.epam.heritage.ap.service;

import by.epam.heritage.ap.model.Request;

public interface RequestServiceable extends Serviceable <Request>{

    boolean setRequestIsRespondedStatus(boolean isResponded, int idRequest) throws ServiceException;

    int findMaximumRequestid() throws ServiceException;
}
