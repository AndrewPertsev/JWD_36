package by.epam.heritage.ap.controller.impl.commands_goto;

import by.epam.heritage.ap.controller.CommandException;
import by.epam.heritage.ap.controller.Commandable;
import by.epam.heritage.ap.model.Offer;
import by.epam.heritage.ap.repository.impl.ApartmentDaoImpl;
import by.epam.heritage.ap.service.OfferServiceable;
import by.epam.heritage.ap.service.ServiceException;
import by.epam.heritage.ap.service.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import static by.epam.heritage.ap.controller.ConstantsParametersAndAttributes.ATTRIBUTE_USER_OFFERS;
import static by.epam.heritage.ap.controller.ConstantsParametersAndAttributes.SESSION_ATTRIBUTE_GUEST_ID;

public class GoToGuestRoomCommand implements Commandable {
    private static final Logger logger = LogManager.getLogger(GoToGuestRoomCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException, ServletException, IOException {
        int userId;
        List<Offer> userOffers;
        HttpSession session = request.getSession();
        OfferServiceable offerService = ServiceFactory.getInstance().getOfferService();

        userId = Integer.parseInt(String.valueOf(session.getAttribute( SESSION_ATTRIBUTE_GUEST_ID)));

        try {
            userOffers = offerService.getOffersByGuestId(userId);
        } catch (ServiceException e) {
            throw new CommandException(e);

        }
        request.setAttribute(ATTRIBUTE_USER_OFFERS, userOffers);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/guest_room.jsp");
        dispatcher.forward(request, response);
    }
}
