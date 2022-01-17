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


//    boolean deleteOfferByRequestId(Request id, Date date_from) throws DAOException;
//    boolean deleteOfferByGuestId(int id) throws DAOException;
//    boolean setOfferPaidDate(int offerId, LocalDate date) throws DAOException;
//    boolean setOfferClosedDate(int offerId, LocalDate date) throws DAOException;
//    boolean setOfferSentDate(int offerId, LocalDate date) throws DAOException;
//    void sortOfferByPrice() throws DAOException;