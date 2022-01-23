package by.epam.heritage.ap.service.builder;

import by.epam.heritage.ap.model.Guest;
import by.epam.heritage.ap.service.ServiceException;
import by.epam.heritage.ap.service.validator.ValidatorException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

import static by.epam.heritage.ap.controller.ConstantsParametersAndAttributes.*;

public class GuestBuilder implements Buildable {
    private static final Logger logger = LogManager.getLogger(GuestBuilder.class);

    public Guest create(HttpServletRequest request) throws ServiceException, ValidatorException {

        Guest newGuest = new Guest();

        char[] password = request.getParameter(PARAMETER_PASSWORD).toCharArray();
        char[] password2 = request.getParameter(PARAMETER_PASSWORD2).toCharArray();
        String hashPassword = BCrypt.hashpw(request.getParameter(PARAMETER_PASSWORD), BCrypt.gensalt());
        Arrays.fill(password, '0');
        Arrays.fill(password2, '0');

        newGuest.setName(request.getParameter(PARAMETER_NAME));
        newGuest.setSurname(request.getParameter(PARAMETER_SURNAME));
        newGuest.setEmail(request.getParameter(PARAMETER_EMAIL));
        newGuest.setPhone(request.getParameter(PARAMETER_PHONE));
        newGuest.setLogin(request.getParameter(PARAMETER_LOGIN));
        newGuest.setPassword(hashPassword);
        newGuest.setCountry(request.getParameter(PARAMETER_COUNTRY));
        newGuest.setPassport(Integer.parseInt(request.getParameter(PARAMETER_PASSPORT_NUMBER)));

        return newGuest;
    }


    public Guest update(HttpServletRequest request) throws ValidatorException {

        Guest newGuest = new Guest();

        newGuest.setName(request.getParameter(PARAMETER_NAME));
        newGuest.setSurname(request.getParameter(PARAMETER_SURNAME));
        newGuest.setEmail(request.getParameter(PARAMETER_EMAIL));
        newGuest.setPhone(request.getParameter(PARAMETER_PHONE));
        newGuest.setComment(request.getParameter(PARAMETER_COMMENT));
        newGuest.setRoleId(Integer.parseInt(request.getParameter(PARAMETER_ROLE_ID)));
        newGuest.setGuestId(Integer.parseInt(request.getParameter(PARAMETER_GUEST_ID)));
        newGuest.setVip(Boolean.parseBoolean(request.getParameter(PARAMETER_GUEST_IS_VIP)));
        newGuest.setNonGrata(Boolean.parseBoolean(request.getParameter(PARAMETER_GUEST_IS_NON_GRATA)));

        return newGuest;
    }

}