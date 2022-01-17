package by.epam.heritage.ap.controller.impl;

import by.epam.heritage.ap.controller.CommandException;
import by.epam.heritage.ap.controller.Commandable;
import by.epam.heritage.ap.model.Request;
import by.epam.heritage.ap.service.RequestServiceable;
import by.epam.heritage.ap.service.ServiceException;
import by.epam.heritage.ap.service.ServiceFactory;
import by.epam.heritage.ap.service.builder.BuilderFactory;
import by.epam.heritage.ap.service.validator.ValidatorException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.epam.heritage.ap.controller.ConstantsParametersAndAttributes.MESSAGE_WELCOME;

public class RequestCommand implements Commandable {
    private static final Logger logger = LogManager.getLogger(RequestCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException, IOException {

        boolean isValidRequest = true;
        Request requestUsers = null;
        try {
            requestUsers = BuilderFactory.getInstance().getRequestBuilder().create(request);
        } catch (ValidatorException e) {
            throw new CommandException(e);
        }

        RequestServiceable requestService = ServiceFactory.getInstance().getRequestService();

        if (isValidRequest) {
            try {
                requestService.add(requestUsers);
            } catch (ServiceException e) {
                throw new CommandException(e);
            }

            response.sendRedirect("Controller?command=GO_TO_MAIN_PAGE&start=" + MESSAGE_WELCOME);
        } else { //TODO
        }
    }
}