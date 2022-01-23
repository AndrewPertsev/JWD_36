package by.epam.heritage.ap.controller.impl;

import by.epam.heritage.ap.controller.Commandable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.epam.heritage.ap.controller.ConstantsCommandPath.GO_TO_REQUEST_MANAGEMENT_PAGE;
import static by.epam.heritage.ap.controller.ConstantsCommandPath.PATH_REDIRECT_CONTROLLER_COMMAND;
import static by.epam.heritage.ap.controller.ConstantsParametersAndAttributes.ATTRIBUTE_FIND_UNRESPONDED_REQUESTS;

public class FindUnrespondedRequestsCommand implements Commandable {
    private static final Logger logger = LogManager.getLogger(FindUnrespondedRequestsCommand.class);


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Boolean unresponded = true;
        request.setAttribute(ATTRIBUTE_FIND_UNRESPONDED_REQUESTS, unresponded);

        response.sendRedirect(PATH_REDIRECT_CONTROLLER_COMMAND + GO_TO_REQUEST_MANAGEMENT_PAGE
                + "&" + ATTRIBUTE_FIND_UNRESPONDED_REQUESTS + "=" + unresponded
        );
    }
}
