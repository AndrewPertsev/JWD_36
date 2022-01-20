package by.epam.heritage.ap.controller.impl;

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

import static by.epam.heritage.ap.controller.ConstantsParametersAndAttributes.*;

public class DeleteApartmentCommand implements Commandable {
    private static final Logger logger = LogManager.getLogger(DeleteApartmentCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String idApartmentParam = request.getParameter(PARAMETER_APARTMENT_ID);

        ApartmentServiceable apartmentService = ServiceFactory.getInstance().getApartmentService();
        try {
            apartmentService.deleteByid(Integer.parseInt(idApartmentParam));
        } catch (ServiceException e) {
            logger.error("Can't execute request to database", e);
            request.setAttribute(ATTRIBUTE_MESSAGE_FAIL, MESSAGE_DATABASE_ERROR);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }


        response.sendRedirect("Controller?" + PARAMETER_COMMAND + "=GO_TO_APARTMENT_MANAGEMENT_PAGE&start=" + MESSAGE_SUCCESS);

    }
}
