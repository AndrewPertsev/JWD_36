package by.epam.heritage.ap.service.builder;

import by.epam.heritage.ap.model.Entity;
import by.epam.heritage.ap.model.Offer;
import by.epam.heritage.ap.service.ServiceException;
import by.epam.heritage.ap.service.validator.ValidatorException;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

import static by.epam.heritage.ap.controller.ConstantsParametersAndAttributes.PARAMETER_OFFER_ID;

public class OfferStatusBuilder implements Buildable {


    public Offer update(HttpServletRequest request) throws ServiceException {

        Offer offer = new Offer();
        int offerId;
        Boolean isSent;
        Boolean isPaid;
        Boolean isClosed;
        BigDecimal priceOffer;

        offerId = Integer.parseInt(String.valueOf(request.getParameter(PARAMETER_OFFER_ID)));
        isSent = Boolean.parseBoolean(request.getParameter("sent"));
        isPaid = Boolean.parseBoolean(request.getParameter("paid"));
        isClosed = Boolean.parseBoolean(request.getParameter("closed"));
        priceOffer = BigDecimal.valueOf(Long.parseLong(request.getParameter("priceOffer")));

        offer.setSent(isSent);
        offer.setPaid(isPaid);
        offer.setClosed(isClosed);
        offer.setOfferId(offerId);
        offer.setPriceOffer(priceOffer);

        return offer;
    }

    @Override
    public Entity create(HttpServletRequest request) throws ServiceException, ValidatorException {
        return null;
    }
}
