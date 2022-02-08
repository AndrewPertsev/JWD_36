package by.epam.heritage.ap.service;

import by.epam.heritage.ap.model.Apartment;
import by.epam.heritage.ap.model.Request;

import java.util.List;

public interface ApartmentServiceable extends Serviceable<Apartment> {

    List<Apartment> findApartmentsSuitableForRequest(Request request) throws ServiceException;



}
