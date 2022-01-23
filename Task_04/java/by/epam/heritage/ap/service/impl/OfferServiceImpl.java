package by.epam.heritage.ap.service.impl;

import by.epam.heritage.ap.model.Offer;
import by.epam.heritage.ap.model.Request;
import by.epam.heritage.ap.repository.DAOException;
import by.epam.heritage.ap.repository.DaoFactory;
import by.epam.heritage.ap.repository.OfferDao;
import by.epam.heritage.ap.repository.RequestDao;
import by.epam.heritage.ap.service.OfferServiceable;
import by.epam.heritage.ap.service.ServiceException;
import by.epam.heritage.ap.service.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;

public class OfferServiceImpl implements OfferServiceable {
    private static final Logger logger = LogManager.getLogger(OfferServiceImpl.class);


    @Override
    public boolean add(Offer entity) throws ServiceException {
        boolean done = true;
        OfferDao offerDao = DaoFactory.getInstance().getOfferDao();
        try {
            done = offerDao.add(entity);
        } catch (DAOException e) {
            logger.error("Service can't add element ", e);
            throw new ServiceException(e);
        }
        return done;
    }

    public BigDecimal calculateOfferTotalPrice(int requestId) throws ServiceException {
        RequestDao requestDao = DaoFactory.getInstance().getRequestDao();
        BigDecimal total = BigDecimal.valueOf(1);

        Request request = null;
        try {
            request = requestDao.findByid(requestId);
        } catch (DAOException e) {
            logger.error("Service can't find element ", e);
            throw new ServiceException(e);
        }
        if (request != null) {
            BigDecimal periodBooking = BigDecimal.valueOf(DAYS.between(request.getStart(), request.getEnd()));
            BigDecimal quantityPersons = BigDecimal.valueOf(request.getQuantity());
            BigDecimal priceApartment = null;
            try {
                priceApartment = DaoFactory.getInstance().getCategoryDao().getCategoryPrice(request.getCategory());
            } catch (DAOException e) {
                logger.error("Service can't calculate price apartment ", e);
                throw new ServiceException(e);
            }
            BigDecimal priceMenu = null;
            try {
                priceMenu = DaoFactory.getInstance().getMenuDao().findByid(request.getMenu()).getPrice();
            } catch (DAOException e) {
                logger.error("Service can't calculate price menu ", e);
                throw new ServiceException(e);
            }
            BigDecimal priceTransfer1km = null;
            try {
                priceTransfer1km = DaoFactory.getInstance().getTransferDao().findByid(request.getTransfer()).getPrice();
            } catch (DAOException e) {
                logger.error("Service can't calculate price transfer ", e);
                throw new ServiceException(e);
            }
            BigDecimal distance = new BigDecimal(request.getDistance()); //////////////////////////////HARDCODED !!!!!!!!
            BigDecimal priceTransfer = priceTransfer1km.multiply(distance);
            BigDecimal quantityPersonDays = periodBooking.multiply(quantityPersons);
            BigDecimal priceSingleServicePerDay = priceApartment.add(priceMenu);
            BigDecimal personDaysWithDailyServices = quantityPersonDays.multiply(priceSingleServicePerDay);
            total = personDaysWithDailyServices.add(priceTransfer);
        }
        return total;
    }


    @Override
    public List<Offer> getOffersByGuestId(int id) throws ServiceException {
        List<Offer> offers = new ArrayList<>();
        OfferDao offerDao = DaoFactory.getInstance().getOfferDao();
        try {
            offers = offerDao.getOffersByGuestId(id);
        } catch (DAOException e) {
            logger.error("Service can't get element ", e);
            throw new ServiceException(e);
        }
        return offers;
    }


    @Override
    public List<Offer> findAll() throws ServiceException {
        List<Offer> offers = new ArrayList<>();
        OfferDao offerDao = DaoFactory.getInstance().getOfferDao();
        try {
            offers = offerDao.findAll();
        } catch (DAOException e) {
            logger.error("Service can't find element ", e);
            throw new ServiceException(e);
        }
        return offers;
    }

    @Override
    public boolean update(Offer entity) throws ServiceException {
        boolean done = true;
        OfferDao offerDao = DaoFactory.getInstance().getOfferDao();
        try {
            done = offerDao.update(entity);
        } catch (DAOException e) {
            logger.error("Service can't update element ", e);
            throw new ServiceException(e);
        }
        return done;
    }


    @Override
    public Offer assembleOfferForApartment(int suitableApartmentId, int requestId) throws ServiceException {
        RequestDao requestDao = DaoFactory.getInstance().getRequestDao();
        OfferServiceable offerService = ServiceFactory.getInstance().getOfferService();
        Offer offer = new Offer();

        Request request = null;
        try {
            request = requestDao.findByid(requestId);
        } catch (DAOException e) {
            logger.error("Service can't assemble offer", e);
            throw new ServiceException(e);
        }
        BigDecimal offerPrice = offerService.calculateOfferTotalPrice(requestId);

        offer.setRequestId(requestId);
        offer.setQuantity(request.getQuantity());
        offer.setTransfer(request.getTransfer());
        offer.setMenu(request.getMenu());
        offer.setApartmentId(suitableApartmentId);
        offer.setStart(request.getStart());
        offer.setEnd(request.getEnd());
        offer.setDateSent(LocalDate.now());
        offer.setDatePaid(LocalDate.now());
        offer.setDateClosed(LocalDate.now());
        offer.setSent(false);
        offer.setPaid(false);
        offer.setClosed(false);
        offer.setPriceOffer(offerPrice);

        return offer;
    }


    @Override
    public Offer findByid(int id) throws ServiceException {
        return null;
    }

    @Override
    public boolean deleteByid(int id) throws ServiceException {
        boolean done = true;
        return false;
    }


}
