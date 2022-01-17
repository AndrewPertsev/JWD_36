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

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.epam.heritage.ap.controller.ConstantsParametersAndAttributes.MESSAGE_WELCOME;

public class UpdateRequestDataCommand implements Commandable {
    private static final Logger logger = LogManager.getLogger(UpdateRequestDataCommand.class);


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException, IOException, ServletException, ServiceException {
        boolean isValidRequest = false;
        HttpSession session = request.getSession();


        Request usersRequest = null;
        try {
            usersRequest = BuilderFactory.getInstance().getRequestBuilder().update(request);
        } catch (ValidatorException e) {
            throw new CommandException(e);
        }

        RequestServiceable requestService = ServiceFactory.getInstance().getRequestService();
        isValidRequest = requestService.update(usersRequest);
        if (isValidRequest) {

            response.sendRedirect("Controller?command=GO_TO_REQUEST_MANAGEMENT_PAGE&start=" + MESSAGE_WELCOME);

        }


    }
}

////        String idrequestParametertParam = request.getParameter("requestId");
////        String categoryParam = request.getParameter(   "category");
////        String capacityParam = request.getParameter(   "quantity");
////        String ddateInn = request.getParameter("start");
////        String dateOut = request.getParameter(   "end");
////        String men = request.getParameter(   "menuId");
////        String tra = request.getParameter(   "transferId");
////        System.out.println("__________UPDate apart id " + idrequestParametertParam);
////        System.out.println("capacity " + capacityParam);
////        System.out.println("category " + categoryParam);
////        System.out.println("datein " + ddateInn);
////        System.out.println("datout " + dateOut);
////        System.out.println("menu " + men);
////        System.out.println("transf " + tra);