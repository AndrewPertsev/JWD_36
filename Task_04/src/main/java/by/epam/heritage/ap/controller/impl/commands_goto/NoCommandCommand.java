package by.epam.heritage.ap.controller.impl.commands_goto;

import by.epam.heritage.ap.controller.Commandable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.epam.heritage.ap.controller.ConstantsParametersAndAttributes.ATTRIBUTE_MESSAGE_FAIL;
import static by.epam.heritage.ap.controller.ConstantsParametersAndAttributes.MESSAGE_INVALID_DATA;

public class NoCommandCommand implements Commandable {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setAttribute(ATTRIBUTE_MESSAGE_FAIL, MESSAGE_INVALID_DATA);
        response.sendError(HttpServletResponse.SC_BAD_REQUEST);
    }
}
