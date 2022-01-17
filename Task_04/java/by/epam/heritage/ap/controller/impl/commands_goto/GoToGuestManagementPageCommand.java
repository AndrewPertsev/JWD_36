package by.epam.heritage.ap.controller.impl.commands_goto;

import by.epam.heritage.ap.controller.CommandException;
import by.epam.heritage.ap.controller.Commandable;
import by.epam.heritage.ap.model.Guest;
import by.epam.heritage.ap.service.GuestServiceable;
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

import static by.epam.heritage.ap.controller.ConstantsParametersAndAttributes.ATTRIBUTE_GUEST_LIST;
import static by.epam.heritage.ap.controller.ConstantsParametersAndAttributes.PARAMETER_GUEST_ID;

public class GoToGuestManagementPageCommand implements Commandable {
    private static final Logger logger = LogManager.getLogger(GoToGuestManagementPageCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException, ServletException, IOException {
        List<Guest> guestList = new ArrayList<>();

        Guest guest = null;
        GuestServiceable guestService = ServiceFactory.getInstance().getGuestService();

        if (request.getParameter(PARAMETER_GUEST_ID) != null) {
            int idGuest = Integer.parseInt(request.getParameter(PARAMETER_GUEST_ID));
            try {
                guest = guestService.findByid(idGuest);
            } catch (ServiceException e) {
                throw new CommandException(e);
            }
            guestList.add(guest);

        } else {
            try {
                guestList = guestService.findAll();
            } catch (ServiceException e) {
                throw new CommandException(e);

            }
        }
        request.setAttribute(ATTRIBUTE_GUEST_LIST, guestList);

        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/view/guest_management.jsp");
        dispatcher.forward(request, response);
    }
}