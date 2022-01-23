package by.epam.heritage.ap.controller.filter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.epam.heritage.ap.controller.ConstantsParametersAndAttributes.*;

public class LocaleFilter implements Filter {
    private static final Logger logger = LogManager.getLogger(LocaleFilter.class);
    private FilterConfig filterConfig;

    public LocaleFilter() {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String command;
        String newLocale;

        if (filterConfig.getInitParameter(PARAMETER_FILTER_ACTIVE).equalsIgnoreCase("TRUE")) {

            HttpServletRequest request = (HttpServletRequest) servletRequest;
            HttpSession session = request.getSession(true);

            command = request.getParameter(PARAMETER_COMMAND);
            newLocale = request.getParameter(PARAMETER_LOCALE_COMMON);

            if (session.getAttribute(PARAMETER_LOCALE_COMMON) == null) {
                session.setAttribute(PARAMETER_LOCALE_COMMON, PARAMETER_LOCALE_DEFAULT_EN);

            } else if (newLocale != null && command != null) {
                session.setAttribute(PARAMETER_LOCALE_COMMON, newLocale);

            }
            filterChain.doFilter(servletRequest, servletResponse);

        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {
        filterConfig = null;
    }

    @Override
    public void init(FilterConfig fConfig) {
        filterConfig = fConfig;
    }
}
