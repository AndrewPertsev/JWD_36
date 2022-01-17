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
import java.util.ArrayList;
import java.util.List;

import static by.epam.heritage.ap.controller.ConstantsParametersAndAttributes.ATTRIBUTE_OFFERS;
import static by.epam.heritage.ap.controller.ConstantsParametersAndAttributes.ATTRIBUTE_URL;

public class GoToOfferManagementPageCommand implements Commandable {
    private static final Logger logger = LogManager.getLogger(GoToOfferManagementPageCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException, ServletException, IOException {
        HttpSession session = request.getSession();
        List<Offer> offers = new ArrayList<>(0);
        OfferServiceable offerService = ServiceFactory.getInstance().getOfferService();

        String url = request.getRequestURL()+"?"+request.getQueryString()+"&user_role=1&page=offer_management.jsp";//request url in the all pages with responses, or set it to filter
        session.setAttribute(ATTRIBUTE_URL, url );

        try {
            offers = offerService.findAll();
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        request.setAttribute(ATTRIBUTE_OFFERS, offers);


        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/offer_management.jsp");
        dispatcher.forward(request, response);
    }
}
