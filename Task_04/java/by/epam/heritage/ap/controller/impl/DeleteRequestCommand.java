package by.epam.heritage.ap.controller.impl;

import by.epam.heritage.ap.controller.Commandable;
import by.epam.heritage.ap.service.RequestServiceable;
import by.epam.heritage.ap.service.ServiceException;
import by.epam.heritage.ap.service.ServiceFactory;
import by.epam.heritage.ap.service.validator.RequestValidator;
import by.epam.heritage.ap.service.validator.ValidatorException;
import by.epam.heritage.ap.service.validator.ValidatorFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.epam.heritage.ap.controller.ConstantsCommandPath.*;
import static by.epam.heritage.ap.controller.ConstantsParametersAndAttributes.*;

public class DeleteRequestCommand implements Commandable {
    private static final Logger logger = LogManager.getLogger(DeleteRequestCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String idRequestParam = request.getParameter(PARAMETER_REQUEST_ID);
        boolean isValidId = false;
        boolean done = false;

        try {
            RequestValidator validatorCommon = ValidatorFactory.getInstance().getRequestValidator();
            isValidId = validatorCommon.inspectIsValidRequestIdToUpdateRequest(idRequestParam);
        } catch (ValidatorException e) {
            logger.error("Can't validate request id", e);
            request.setAttribute(ATTRIBUTE_MESSAGE_FAIL, MESSAGE_DATABASE_ERROR);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }

        if (isValidId) {


            try {
                RequestServiceable requestServiceable = ServiceFactory.getInstance().getRequestService();
                done = requestServiceable.deleteByid(Integer.parseInt(idRequestParam));
            } catch (ServiceException e) {
                logger.error("Can't execute request to database", e);
                request.setAttribute(ATTRIBUTE_MESSAGE_FAIL, MESSAGE_DATABASE_ERROR);
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }

            if (done) {
                response.sendRedirect(PATH_REDIRECT_CONTROLLER_COMMAND + GO_TO_REQUEST_MANAGEMENT_PAGE + PATH_MESSAGE_SUCCESS);
            } else {
                response.sendRedirect(PATH_REDIRECT_CONTROLLER_COMMAND + GO_TO_REQUEST_MANAGEMENT_PAGE + PATH_MESSAGE_FAIL);
            }
        } else {
            response.sendRedirect(PATH_REDIRECT_CONTROLLER_COMMAND + GO_TO_REQUEST_MANAGEMENT_PAGE + PATH_MESSAGE_FAIL);
        }
    }
}
