package by.epam.heritage.ap.controller.impl.commands_goto;

import by.epam.heritage.ap.controller.CommandException;
import by.epam.heritage.ap.controller.Commandable;
import by.epam.heritage.ap.model.Request;
import by.epam.heritage.ap.repository.impl.ApartmentDaoImpl;
import by.epam.heritage.ap.service.RequestServiceable;
import by.epam.heritage.ap.service.ServiceException;
import by.epam.heritage.ap.service.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static by.epam.heritage.ap.controller.ConstantsParametersAndAttributes.ATTRIBUTE_OFFERS;
import static by.epam.heritage.ap.controller.ConstantsParametersAndAttributes.ATTRIBUTE_REQUESTS;

public class GoToRequestManagementPageCommand implements Commandable {
    private static final Logger logger = LogManager.getLogger(GoToRequestManagementPageCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException, ServletException, IOException {
        List<Request> requests = new ArrayList<>(0);

        RequestServiceable requestService = ServiceFactory.getInstance().getRequestService();

        try {
            requests = requestService.findAll();
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        request.setAttribute(ATTRIBUTE_REQUESTS, requests);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/request_management.jsp");
        dispatcher.forward(request, response);

    }
}


//response.setIntHeader("Refresh",60);
//Date current=new Date();
//request.setAttribute("dateAuto", current);

//        Enumeration en = request.getAttributeNames();
//        while (en.hasMoreElements()) {
//            System.out.println("gotoreqSESS : " + en.nextElement());
//        }