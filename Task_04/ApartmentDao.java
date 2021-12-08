package by.epam.ap.hotelapp.repository.dao;

import by.epam.ap.hotelapp.model.impl.Apartment;
import by.epam.ap.hotelapp.model.impl.Guest;

public interface ApartmentDao extends BaseDAO < Apartment> {
    Apartment updateDescription(String description);
}
