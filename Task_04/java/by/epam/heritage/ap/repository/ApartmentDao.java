package by.epam.heritage.ap.repository;

import by.epam.heritage.ap.model.Apartment;
import by.epam.heritage.ap.model.Request;

import java.util.List;

public interface ApartmentDao extends BaseDAO<Apartment> {

    List<Apartment> findApartmentsSuitableForRequest(Request request) throws DAOException;

    boolean updateApartment(Apartment apartment) throws DAOException;

}

