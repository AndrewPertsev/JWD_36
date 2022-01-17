package by.epam.heritage.ap.controller.impl;

import by.epam.heritage.ap.controller.CommandException;
import by.epam.heritage.ap.controller.Commandable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.epam.heritage.ap.controller.ConstantsParametersAndAttributes.ATTRIBUTE_URL;
import static by.epam.heritage.ap.controller.ConstantsParametersAndAttributes.PARAMETER_LOCALE_COMMON;

public class SwitchLanguageCommand implements Commandable {
    private static final Logger logger = LogManager.getLogger(SwitchLanguageCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException, IOException, ServletException {
        HttpSession session = request.getSession();

        String locale = request.getParameter(PARAMETER_LOCALE_COMMON);
        request.getSession().setAttribute(PARAMETER_LOCALE_COMMON, locale);

        String urlLocale = (String) request.getSession().getAttribute(ATTRIBUTE_URL);
        response.sendRedirect(urlLocale);

        //session.setAttribute(SessionAttribute.LOCALE, request.getParameter(RequestParameter.LOCALE));
        //return new CommandResult((String)session.getAttribute(SessionAttribute.URL), RoutingType.REDIRECT);
    }
}

