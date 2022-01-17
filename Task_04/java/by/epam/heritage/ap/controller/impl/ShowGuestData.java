package by.epam.heritage.ap.controller.impl;

import by.epam.heritage.ap.controller.CommandException;
import by.epam.heritage.ap.controller.Commandable;
import by.epam.heritage.ap.model.Guest;
import by.epam.heritage.ap.service.GuestServiceable;
import by.epam.heritage.ap.service.ServiceException;
import by.epam.heritage.ap.service.ServiceFactory;
import by.epam.heritage.ap.service.builder.BuilderFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.epam.heritage.ap.controller.ConstantsParametersAndAttributes.PARAMETER_GUEST_ID;

public class ShowGuestData implements Commandable
{private static final Logger logger = LogManager.getLogger(ShowGuestData.class);


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException, IOException, ServletException, ServiceException {

        int idGuest         =(Integer.parseInt(request.getParameter(PARAMETER_GUEST_ID)));

        response.sendRedirect("Controller?command=GO_TO_GUEST_MANAGEMENT_PAGE&"+PARAMETER_GUEST_ID+"="+ idGuest);

    }
}
