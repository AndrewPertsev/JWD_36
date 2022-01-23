package by.epam.heritage.ap.service.validator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static by.epam.heritage.ap.service.validator.ValidatorConstants.PATTERN_DIGIT;


public class ValidatorCommon {
    private static final Logger logger = LogManager.getLogger(ValidatorCommon.class);

    public static boolean validateParameterStringClass(String parameter, String pattern) {
        if (parameter == null || !(parameter.matches(pattern))) {
            logger.error("Parameter \"" + parameter + "\" is not valid, or empty");
            return false;
        } else {
            return true;
        }
    }


    public static boolean validateStringParameterIntegerClass(String parameterToInt, int maximum, int minimum) {
        int parsedParameter;
        if (parameterToInt == null || parameterToInt == "") {
            logger.error("Parameter \"" + parameterToInt + "\" is empty ");
            return false;
        }
        if (!parameterToInt.matches(PATTERN_DIGIT)) {
            logger.error("Parameter \"" + parameterToInt + "\" is not valid ");
            return false;
        }
        parsedParameter = Integer.parseInt(parameterToInt);

        if (parsedParameter <= minimum || parsedParameter > maximum) {
            logger.error("Parameter \"" + parameterToInt + "\" is not valid ");
            return false;
        }
        return true;
    }

}



