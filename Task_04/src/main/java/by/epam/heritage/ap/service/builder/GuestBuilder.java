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
    private static final char FILLER = '0';

    public Guest create(HttpServletRequest request) throws ServiceException, ValidatorException {

        Guest newGuest = new Guest();

        char[] password = request.getParameter(PARAMETER_PASSWORD).toCharArray();
        char[] password2 = request.getParameter(PARAMETER_PASSWORD2).toCharArray();
        String hashPassword = BCrypt.hashpw(request.getParameter(PARAMETER_PASSWORD), BCrypt.gensalt());
        Arrays.fill(password, FILLER);
        Arrays.fill(password2, FILLER);

        newGuest.setName(request.getParameter(PARAMETER_NAME).trim());
        newGuest.setSurname(request.getParameter(PARAMETER_SURNAME).trim());
        newGuest.setEmail(request.getParameter(PARAMETER_EMAIL).trim());
        newGuest.setPhone(request.getParameter(PARAMETER_PHONE).trim());
        newGuest.setLogin(request.getParameter(PARAMETER_LOGIN).trim());
        newGuest.setPassword(hashPassword);
        newGuest.setCountry(request.getParameter(PARAMETER_COUNTRY).trim());
        newGuest.setPassport(Integer.parseInt(request.getParameter(PARAMETER_PASSPORT_NUMBER).trim()));

        return newGuest;
    }


    public Guest update(HttpServletRequest request) throws ValidatorException {

        Guest newGuest = new Guest();

        newGuest.setName(request.getParameter(PARAMETER_NAME).trim());
        newGuest.setSurname(request.getParameter(PARAMETER_SURNAME).trim());
        newGuest.setEmail(request.getParameter(PARAMETER_EMAIL).trim());
        newGuest.setPhone(request.getParameter(PARAMETER_PHONE).trim());
        newGuest.setComment(request.getParameter(PARAMETER_COMMENT).trim());
        newGuest.setRoleId(Integer.parseInt(request.getParameter(PARAMETER_ROLE_ID).trim()));
        newGuest.setGuestId(Integer.parseInt(request.getParameter(PARAMETER_GUEST_ID).trim()));
        newGuest.setVip(Boolean.parseBoolean(request.getParameter(PARAMETER_GUEST_IS_VIP).trim()));
        newGuest.setNonGrata(Boolean.parseBoolean(request.getParameter(PARAMETER_GUEST_IS_NON_GRATA).trim()));

        return newGuest;
    }

}