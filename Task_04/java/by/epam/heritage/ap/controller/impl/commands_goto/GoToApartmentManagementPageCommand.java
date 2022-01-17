package by.epam.heritage.ap.controller.impl.commands_goto;

import by.epam.heritage.ap.controller.CommandException;
import by.epam.heritage.ap.controller.Commandable;
import by.epam.heritage.ap.controller.ConstantsParametersAndAttributes;
import by.epam.heritage.ap.model.Apartment;
import by.epam.heritage.ap.service.ApartmentServiceable;
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

public class GoToApartmentManagementPageCommand implements Commandable {
    private static final Logger logger = LogManager.getLogger(GoToApartmentManagementPageCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException, ServletException, IOException {
        List<Apartment> roomList = new ArrayList<>();

        ApartmentServiceable apartmentService = ServiceFactory.getInstance().getApartmentService();
        try {
            roomList = apartmentService.findAll();
        } catch (ServiceException e) {
            throw new CommandException(e);

        }
        request.setAttribute(ConstantsParametersAndAttributes.ATTRIBUTE_ROOM_LIST, roomList);

        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/view/apartment_management.jsp");
        dispatcher.forward(request, response);
    }
}
