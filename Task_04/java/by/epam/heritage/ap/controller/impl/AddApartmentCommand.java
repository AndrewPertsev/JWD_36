package by.epam.heritage.ap.controller.impl;

import by.epam.heritage.ap.controller.CommandException;
import by.epam.heritage.ap.controller.Commandable;
import by.epam.heritage.ap.model.Apartment;
import by.epam.heritage.ap.service.ServiceException;
import by.epam.heritage.ap.service.ServiceFactory;
import by.epam.heritage.ap.service.Serviceable;
import by.epam.heritage.ap.service.builder.BuilderFactory;
import by.epam.heritage.ap.service.validator.ValidatorException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.epam.heritage.ap.controller.ConstantsParametersAndAttributes.MESSAGE_WELCOME;

public class AddApartmentCommand implements Commandable {
    private static final Logger logger = LogManager.getLogger(AddApartmentCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException, IOException, ServletException, ServiceException {

        Apartment apartmentValidated = null;

        try {
            apartmentValidated = BuilderFactory.getInstance().getApartmentBuilder().create(request);
        } catch (ValidatorException e) {
            throw new CommandException(e);
        }

        Serviceable apartmentService = ServiceFactory.getInstance().getApartmentService();
        apartmentService.add(apartmentValidated);
        response.sendRedirect("Controller?command=GO_TO_APARTMENT_MANAGEMENT_PAGE&start=" + MESSAGE_WELCOME);

    }
}

//        String idApartmentParam = request.getParameter("apartmentId");/////////////////////////
//        String categoryParam = request.getParameter("category");
//        String capacityParam = request.getParameter("capacity");
//        String description = request.getParameter("description");
//        String pathToPicture = request.getParameter("pathToPicture");
//        System.out.println("__________UPDate apart id " + idApartmentParam);
//        System.out.println("capacity " + capacityParam);
//        System.out.println("category " + categoryParam);
//        System.out.println("pathToPicture " + pathToPicture);
//        System.out.println("description " + description);
