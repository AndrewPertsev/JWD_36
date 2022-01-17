package by.epam.heritage.ap.service;

import by.epam.heritage.ap.model.Offer;
import by.epam.heritage.ap.model.Request;

import java.math.BigDecimal;
import java.util.List;

public interface OfferServiceable extends Serviceable <Offer>{

    Offer assembleOfferForApartment(int suitableApartmentId, int requestId) throws ServiceException;

    BigDecimal calculateOfferTotalPrice(int requestId) throws ServiceException;

    List<Offer> getOffersByGuestId(int id) throws ServiceException;

}
