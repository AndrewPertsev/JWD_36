package by.epam.heritage.ap.controller.impl;

import by.epam.heritage.ap.controller.CommandException;
import by.epam.heritage.ap.controller.Commandable;
import by.epam.heritage.ap.service.ApartmentServiceable;
import by.epam.heritage.ap.service.ServiceException;
import by.epam.heritage.ap.service.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.epam.heritage.ap.controller.ConstantsParametersAndAttributes.MESSAGE_WELCOME;
import static by.epam.heritage.ap.controller.ConstantsParametersAndAttributes.PARAMETER_APARTMENT_ID;

public class DeleteApartmentCommand implements Commandable {
    private static final Logger logger = LogManager.getLogger(DeleteApartmentCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException, IOException, ServletException, ServiceException {

        String idApartmentParam = request.getParameter(PARAMETER_APARTMENT_ID);

        ApartmentServiceable apartmentService = ServiceFactory.getInstance().getApartmentService();

        try {
            System.out.println(Integer.parseInt(idApartmentParam));
            apartmentService.deleteByid(Integer.parseInt(idApartmentParam));
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        response.sendRedirect("Controller?command=GO_TO_APARTMENT_MANAGEMENT_PAGE&start=" + MESSAGE_WELCOME);

    }
}
