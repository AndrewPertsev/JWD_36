package by.epam.heritage.ap.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import java.io.IOException;

import static by.epam.heritage.ap.controller.ConstantsParametersAndAttributes.PARAMETER_FILTER_ACTIVE;

public class LocaleFilter implements Filter {
    private static final Logger logger = LogManager.getLogger(LocaleFilter.class);
    private FilterConfig filterConfig;

    public LocaleFilter() {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

       if (filterConfig.getInitParameter(PARAMETER_FILTER_ACTIVE).equalsIgnoreCase("true")) {
//            HttpServletRequest request = (HttpServletRequest) servletRequest;
//            HttpServletResponse response = (HttpServletResponse) servletResponse;
//            HttpSession session = request.getSession(true);

            filterChain.doFilter(servletRequest, servletResponse);
        }else {

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
