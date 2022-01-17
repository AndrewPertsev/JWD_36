package by.epam.heritage.ap.controller.impl.commands_goto;

import by.epam.heritage.ap.controller.CommandException;
import by.epam.heritage.ap.controller.Commandable;
import by.epam.heritage.ap.model.Apartment;
import by.epam.heritage.ap.model.Offer;
import by.epam.heritage.ap.model.Request;
import by.epam.heritage.ap.service.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static by.epam.heritage.ap.controller.ConstantsParametersAndAttributes.ATTRIBUTE_OFFERS;
import static by.epam.heritage.ap.controller.ConstantsParametersAndAttributes.PARAMETER_REQUEST_ID;

public class GoToOfferProjectPageCommand implements Commandable {
    private static final Logger logger = LogManager.getLogger(GoToOfferProjectPageCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException, ServletException, IOException {
        Request requestGuest;
        int idRequestGuestInt;
        String idRequestGuest;
        Offer offer = null;
        List<Offer> offers = new ArrayList<>(0);
        List<Apartment> suitableApartments = new ArrayList<>(0);

        OfferServiceable offerService = ServiceFactory.getInstance().getOfferService();
        ApartmentServiceable apartmentService = ServiceFactory.getInstance().getApartmentService();
        RequestServiceable requestService = ServiceFactory.getInstance().getRequestService();

        idRequestGuest = request.getParameter(PARAMETER_REQUEST_ID);
        idRequestGuestInt = Integer.parseInt(idRequestGuest);

        request.setAttribute(PARAMETER_REQUEST_ID, idRequestGuest);

        try {
            requestGuest =  requestService.findByid(idRequestGuestInt);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }

        try {
            suitableApartments = apartmentService.findApartmentsSuitableForRequest(requestGuest);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }

        for (int i = 0; i < suitableApartments.size(); i++) {
            int tempId = suitableApartments.get(i).getApartmentId();

            try {
                offer = offerService.assembleOfferForApartment(tempId, idRequestGuestInt);
            } catch (ServiceException e) {
                throw new CommandException(e);
            }
            offers.add(offer);
        }
        request.setAttribute(ATTRIBUTE_OFFERS, offers);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/offer_project.jsp");
        dispatcher.forward(request, response);
    }
}

