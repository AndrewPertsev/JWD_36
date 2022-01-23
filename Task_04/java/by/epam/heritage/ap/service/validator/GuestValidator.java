package by.epam.heritage.ap.service.validator;

import by.epam.heritage.ap.model.Guest;
import by.epam.heritage.ap.repository.DAOException;
import by.epam.heritage.ap.repository.DaoFactory;
import by.epam.heritage.ap.repository.GuestDao;
import by.epam.heritage.ap.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

import static by.epam.heritage.ap.controller.ConstantsParametersAndAttributes.*;
import static by.epam.heritage.ap.service.validator.ValidatorConstants.*;


public final class GuestValidator implements Validable {
    private static final Logger logger = LogManager.getLogger(GuestValidator.class);


    @Override
    public boolean checkNewEntityIsValid(HttpServletRequest request) throws ServiceException {
        String surname = request.getParameter(PARAMETER_SURNAME);
        String name = request.getParameter(PARAMETER_NAME);
        String email = request.getParameter(PARAMETER_EMAIL);
        String login = request.getParameter(PARAMETER_LOGIN);
        String phone = request.getParameter(PARAMETER_PHONE);
        String country = request.getParameter(PARAMETER_COUNTRY);
        String passport = request.getParameter(PARAMETER_PASSPORT_NUMBER);
        char[] password = (request.getParameter(PARAMETER_PASSWORD).toCharArray());
        char[] password2 = (request.getParameter(PARAMETER_PASSWORD2).toCharArray());

        if (!inspectIsValidLogin(login)) {
            logger.error("Fail validation login");
            return false;
        }
        if (!inspectIsValidPassword(password, password2)) {
            logger.error("Fail validation password ");
            return false;
        }
        if (!ValidatorCommon.validateParameterStringClass(surname, PATTERN_NAME)) {
            logger.error("Fail validation surname ");
            return false;
        }
        if (!ValidatorCommon.validateParameterStringClass(name, PATTERN_NAME)) {
            logger.error("Fail validation name ");
            return false;
        }
        if (!ValidatorCommon.validateParameterStringClass(email, PATTERN_EMAIL)) {
            logger.error("Fail validation emaily ");
            return false;
        }
        if (!ValidatorCommon.validateParameterStringClass(phone, PATTERN_PHONE)) {
            logger.error("Fail validation phone");
            return false;
        }
        if (!ValidatorCommon.validateParameterStringClass(country, PATTERN_NAME)) {
            logger.error("Fail validation country ");
            return false;
        }
        if (!ValidatorCommon.validateParameterStringClass(passport, PATTERN_PASSPORT_NUMBER)) {
            logger.error("Fail validation passport");
            return false;
        }

        return true;
    }

    @Override
    public boolean checkUpdatedEntityIsValid(HttpServletRequest request) throws ServiceException {
        String surname = request.getParameter(PARAMETER_SURNAME);
        String name = request.getParameter(PARAMETER_NAME);
        String email = request.getParameter(PARAMETER_EMAIL);
        String phone = request.getParameter(PARAMETER_PHONE);
        String roleId = request.getParameter(PARAMETER_ROLE_ID);
        String idGuest = request.getParameter(PARAMETER_GUEST_ID);
        String comment = request.getParameter(PARAMETER_COMMENT);


        if (!ValidatorCommon.validateParameterStringClass(surname, PATTERN_NAME)) {
            logger.error("Fail validation surname");
            return false;
        }
        if (!ValidatorCommon.validateParameterStringClass(name, PATTERN_NAME)) {
            logger.error("Fail validation name ");
            return false;
        }
        if (!ValidatorCommon.validateParameterStringClass(email, PATTERN_EMAIL)) {
            logger.error("Fail validation email ");
            return false;
        }
        if (!ValidatorCommon.validateParameterStringClass(phone, PATTERN_PHONE)) {
            logger.error("Fail validation phone ");
            return false;
        }
        if (!ValidatorCommon.validateParameterStringClass(comment, PATTERN_COMMENT)) {
            logger.error("Fail validation comment ");
            return false;
        }
        if (!ValidatorCommon.validateStringParameterIntegerClass(roleId, MAXIMUM_ROLE_ID, MINIMUM_ZERO - 1)) {
            logger.error("Fail validation role id");
            return false;
        }
        if (!ValidatorCommon.validateStringParameterIntegerClass(idGuest, MAXIMUM_NUMBER_USER_ID, MINIMUM_ZERO)) {
            logger.error("Fail validation guest id ");
            return false;
        }

        return true;
    }


    public boolean inspectIsValidLogin(String login) throws ServiceException {
        Guest guest;

        if (!ValidatorCommon.validateParameterStringClass(login, PATTERN_PASSWORD)) {
            logger.error("Login is not valid ");
            return false;
        } else {
            GuestDao guestDao = DaoFactory.getInstance().getGuestDao();
            try {
                guest = guestDao.findGuestByLogin(login);
            } catch (DAOException e) {
                logger.error("login is not valid ", e);
                throw new ServiceException(e);
            }
            if (guest.getGuestId() != 0) {
                logger.error("Guest is already exists");
                return false;
            } else {
                return true;
            }
        }
    }


    public boolean inspectIsValidPassword(char[] password, char[] password2) {

        if (!ValidatorCommon.validateParameterStringClass(password.toString(), PATTERN_PASSWORD)) {
            logger.error("Password array is equal 0 or is not valid ");
            return false;
        }
        if (!Arrays.equals(password2, password)) {
            logger.error("Password arrays are not equal ");
            return false;
        } else {
            return true;
        }
    }


}