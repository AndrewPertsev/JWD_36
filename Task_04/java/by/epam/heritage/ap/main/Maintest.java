package by.epam.heritage.ap.main;

import by.epam.heritage.ap.model.Offer;
import by.epam.heritage.ap.repository.DAOException;
import by.epam.heritage.ap.repository.DaoFactory;
import by.epam.heritage.ap.repository.OfferDao;
import by.epam.heritage.ap.repository.connection.ConnectionPool;
import by.epam.heritage.ap.repository.connection.PoolException;
import by.epam.heritage.ap.service.OfferServiceable;
import by.epam.heritage.ap.service.ServiceException;
import by.epam.heritage.ap.service.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class Maintest {

    private static final Logger logger = LogManager.getLogger(Maintest.class);

    public static void main(String[] args) throws DAOException, PoolException, ServiceException {
        ConnectionPool.getInstance().init();


        OfferServiceable offerService = ServiceFactory.getInstance().getOfferService();
        int userId = 3;

        List<Offer> userOffers = offerService.getOffersByGuestId(userId);
        System.out.println("user ID " + userId + " array" + userOffers);

        List<Offer> offers = new ArrayList<>();
        OfferDao offerDao = DaoFactory.getInstance().getOfferDao();

        offers = offerDao.getOffersByGuestId(3);

        System.out.println("offerdao " + offers);

    }
}


