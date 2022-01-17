package by.epam.heritage.ap.controller.impl;

import by.epam.heritage.ap.controller.CommandException;
import by.epam.heritage.ap.controller.Commandable;
import by.epam.heritage.ap.model.Guest;
import by.epam.heritage.ap.service.GuestServiceable;
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

import static by.epam.heritage.ap.controller.ConstantsParametersAndAttributes.PARAMETER_GUEST_ID;

public class UpdateGuestDataCommand implements Commandable {
    private static final Logger logger = LogManager.getLogger(UpdateGuestDataCommand.class);


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException, IOException, ServletException, ServiceException {
        boolean isValidGuest = false;
        boolean done = false;
        HttpSession session = request.getSession();
        Guest guestUpdated = null;

        try {
            guestUpdated = BuilderFactory.getInstance().getGuestBuilder().update(request);
        } catch (ValidatorException e) {
            e.printStackTrace();
        }

        int idGuest = (Integer.parseInt(request.getParameter(PARAMETER_GUEST_ID)));

        GuestServiceable guestService = ServiceFactory.getInstance().getGuestService();

        done = guestService.update(guestUpdated);

        response.sendRedirect("Controller?command=GO_TO_GUEST_MANAGEMENT_PAGE&" + PARAMETER_GUEST_ID + "=" + idGuest);

    }
}




//        int roleId      =  (Integer.parseInt(request.getParameter("roleId")));;
//        String ema       =  (request.getParameter("email"));;
//        String name       = (request.getParameter("name"));
//        String surname      =  (request.getParameter("surname"));
//        String pho        =  (request.getParameter("phone"));;
//        String comment       =  (request.getParameter("comment"));;
//        boolean  isVip       =  Boolean.parseBoolean(request.getParameter("isVip"));;
//        boolean  isNonGrata      =  (Boolean.parseBoolean(request.getParameter("isNonGrata")));;
//
//        System.out.println("roleId " + roleId);
//        System.out.println("ema     " + ema    );
//        System.out.println("name    " + name   );
//        System.out.println("surname " + surname);
//        System.out.println("pho     " + pho    );
//        System.out.println("comment " + comment);
//        System.out.println("isVip   " + isVip  );
//        System.out.println("isgrata " + isNonGrata);
