package by.epam.heritage.ap.service.builder;

import by.epam.heritage.ap.model.Request;
import by.epam.heritage.ap.service.validator.ValidatorException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;

import static by.epam.heritage.ap.controller.ConstantsParametersAndAttributes.*;

public class RequestUserBuilder implements Buildable {
    private static final Logger logger = LogManager.getLogger(RequestUserBuilder.class);

    public Request create(HttpServletRequest request) throws ValidatorException {

        Request newRequestUser = new Request();

        HttpSession session = request.getSession(true);
        var userId = session.getAttribute(PARAMETER_USER_ID);
        newRequestUser.setGuestId(Integer.parseInt(String.valueOf(userId).trim()));

        newRequestUser.setStart(LocalDate.parse(request.getParameter(PARAMETER_CHECK_IN_DATE).trim()));
        newRequestUser.setEnd(LocalDate.parse(request.getParameter(PARAMETER_CHECK_OUT_DATE).trim()));
        newRequestUser.setCategory(Integer.parseInt(request.getParameter(PARAMETER_CATEGORY).trim()));
        newRequestUser.setQuantity(Integer.parseInt(request.getParameter(PARAMETER_QUANTITY).trim()));
        newRequestUser.setTransfer(Integer.parseInt(request.getParameter(PARAMETER_TRANSFER).trim()));
        newRequestUser.setMenu(Integer.parseInt(request.getParameter(PARAMETER_MENU).trim()));
        newRequestUser.setDistance(DEFAULT_DISTANCE);
        newRequestUser.setDateRequest(LocalDate.now());

        return newRequestUser;
    }


    public Request update(HttpServletRequest request) throws ValidatorException {

        Request newRequestUser = new Request();

        newRequestUser.setGuestId(Integer.parseInt(request.getParameter(PARAMETER_GUEST_ID).trim()));
        newRequestUser.setRequestId(Integer.parseInt(request.getParameter(PARAMETER_REQUEST_ID).trim()));

        newRequestUser.setStart(LocalDate.parse(request.getParameter(PARAMETER_CHECK_IN_DATE).trim()));
        newRequestUser.setEnd(LocalDate.parse(request.getParameter(PARAMETER_CHECK_OUT_DATE).trim()));
        newRequestUser.setCategory(Integer.parseInt(request.getParameter(PARAMETER_CATEGORY).trim()));
        newRequestUser.setQuantity(Integer.parseInt(request.getParameter(PARAMETER_QUANTITY).trim()));
        newRequestUser.setTransfer(Integer.parseInt(request.getParameter(PARAMETER_TRANSFER).trim()));
        newRequestUser.setMenu(Integer.parseInt(request.getParameter(PARAMETER_MENU).trim()));
        newRequestUser.setDistance(DEFAULT_DISTANCE);
        newRequestUser.setDateRequest(LocalDate.now());

        return newRequestUser;
    }

}

