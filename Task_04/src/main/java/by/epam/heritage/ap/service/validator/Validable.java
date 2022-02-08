package by.epam.heritage.ap.service.validator;

import by.epam.heritage.ap.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import static by.epam.heritage.ap.service.validator.ValidatorConstants.PATTERN_DIGIT;

public interface Validable {

    boolean checkNewEntityIsValid(HttpServletRequest request) throws ServiceException;

    boolean checkUpdatedEntityIsValid(HttpServletRequest request) throws ServiceException, ValidatorException;


    /**
     * @param parameter
     * @param pattern
     * @return
     */
    static boolean validateParameterStringClass(String parameter, String pattern) {
        Logger logger = LogManager.getLogger(Validable.class);
        if (parameter == null || !(parameter.matches(pattern))) {
            logger.error("Parameter \"" + parameter + "\" is not valid, or empty");
            return false;
        } else {
            return true;
        }
    }


    static boolean validateStringParameterIntegerClass(String parameterToInt,  int maximum, int minimum) {
        Logger logger = LogManager.getLogger(Validable.class);
        int parsedParameter;

        if (!Validable.validateParameterStringClass(parameterToInt, PATTERN_DIGIT)) {
            logger.error("Parameter \"" + parameterToInt + "\" is not digit ");
            return false;
        }

        parsedParameter = Integer.parseInt(parameterToInt);
        System.out.println("pp"+ parsedParameter);
        if (parsedParameter <= minimum || parsedParameter > maximum) {
            logger.error("Parameter \"" + parameterToInt + "\" is not valid ");
            return false;
        }
        return true;
    }
}
