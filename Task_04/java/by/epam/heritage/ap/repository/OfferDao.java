package by.epam.heritage.ap.repository;

import by.epam.heritage.ap.model.Offer;

import java.util.List;

public interface OfferDao extends BaseDAO<Offer> {

    boolean setOfferClosedStatus(int offerId, boolean status) throws DAOException;

    boolean setOfferSentStatus(int offerId, boolean status) throws DAOException;

    boolean setOfferPaidStatus(int offerId, boolean status) throws DAOException;

    List<Offer> getOfferByRequestId(int id) throws DAOException;

    List<Offer> getOffersByGuestId(int id) throws DAOException;

}

