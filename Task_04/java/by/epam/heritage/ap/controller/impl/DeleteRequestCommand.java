package by.epam.heritage.ap.controller.impl;

import by.epam.heritage.ap.controller.CommandException;
import by.epam.heritage.ap.controller.Commandable;
import by.epam.heritage.ap.service.RequestServiceable;
import by.epam.heritage.ap.service.ServiceException;
import by.epam.heritage.ap.service.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.epam.heritage.ap.controller.ConstantsParametersAndAttributes.MESSAGE_WELCOME;
import static by.epam.heritage.ap.controller.ConstantsParametersAndAttributes.PARAMETER_REQUEST_ID;

public class DeleteRequestCommand implements Commandable {
    private static final Logger logger = LogManager.getLogger(DeleteRequestCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException, IOException, ServletException, ServiceException {

        String idRequestParam = request.getParameter(PARAMETER_REQUEST_ID);

        RequestServiceable requestServiceable = ServiceFactory.getInstance().getRequestService();

        try {
            requestServiceable.deleteByid(Integer.parseInt(idRequestParam));
        } catch (ServiceException e) {
            throw new CommandException(e);
        }

        response.sendRedirect("Controller?command=GO_TO_REQUEST_MANAGEMENT_PAGE&start=" + MESSAGE_WELCOME);

    }
}
