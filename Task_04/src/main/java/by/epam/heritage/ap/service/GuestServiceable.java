package by.epam.heritage.ap.service;

import by.epam.heritage.ap.model.Guest;

public interface GuestServiceable extends Serviceable<Guest> {

    Guest checkGuestAuthorization(String login, String passwordToCheck) throws ServiceException;


}
