package by.epam.heritage.ap.service.validator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class ValidatorCommon {
    private static final Logger logger = LogManager.getLogger(ValidatorCommon.class);

    public static boolean validateParameterStringClass(String parameter, String pattern) {
        if (parameter == null || !(parameter.matches(pattern))) {
            logger.error("Parameter \"" + parameter + "\" is not valid ");
            return false;
        } else {
            return true;
        }
    }


    public static boolean validateStrinqParameterIntegerClass(String parameterToInt, int maximum, int minimum) {
        if (parameterToInt == null || parameterToInt == "") {
            logger.error("Parameter \"" + parameterToInt + "\" is not valid ");
            return false;
        } else {
            int parsedParameter = Integer.parseInt(parameterToInt);
//                System.out.println("VALIDATOR parsed-" + parsedParameter + " min-" + minimum);////////////////////////////////////////////////

            if (parsedParameter <= minimum || parsedParameter > maximum) {
                logger.error("Parameter \"" + parameterToInt + "\" is not valid ");
                return false;
            }
        }
        return true;
    }


}
