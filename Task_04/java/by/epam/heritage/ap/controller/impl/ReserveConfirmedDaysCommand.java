package by.epam.heritage.ap.controller.impl;

import by.epam.heritage.ap.controller.CommandException;
import by.epam.heritage.ap.controller.Commandable;
import by.epam.heritage.ap.service.ServiceException;
import by.epam.heritage.ap.service.ServiceFactory;
import by.epam.heritage.ap.service.TimesheetServiceable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;

import static by.epam.heritage.ap.controller.ConstantsParametersAndAttributes.*;

public class ReserveConfirmedDaysCommand implements Commandable {
    private static final Logger logger = LogManager.getLogger(ReserveConfirmedDaysCommand.class);
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException, IOException {
        HttpSession session = request.getSession(true);
        int apartmentId;
        LocalDate bookedBefore;
        LocalDate bookedFrom;

        bookedFrom = LocalDate.parse(request.getParameter(PARAMETER_CHECK_IN_DATE));
        bookedBefore = LocalDate.parse(request.getParameter(PARAMETER_CHECK_OUT_DATE));

        TimesheetServiceable timesheetService = ServiceFactory.getInstance().getTimesheetService();
        apartmentId = Integer.parseInt(request.getParameter(PARAMETER_APARTMENT_ID));

        try {
            timesheetService.reserveConfirmedDaysByOffer(apartmentId, bookedFrom, bookedBefore);//////////////////////////TODO
        } catch (ServiceException e) {
            throw new CommandException(e);
        }


        boolean flag = true;///////TODO
        if (flag) {
            response.sendRedirect("Controller?command=GO_TO_OFFER_MANAGEMENT_PAGE&messageOffer=" + MESSAGE_WELCOME);
        } else {
        }

    }

}
