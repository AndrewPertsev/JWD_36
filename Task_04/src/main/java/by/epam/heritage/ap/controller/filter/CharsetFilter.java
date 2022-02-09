package by.epam.heritage.ap.controller.filter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import java.io.IOException;

import static by.epam.heritage.ap.controller.ConstantsParametersAndAttributes.ENCODING_UTF8;
import static by.epam.heritage.ap.controller.ConstantsParametersAndAttributes.PARAMETER_REQUEST_ENCODING;


public class CharsetFilter implements Filter {
    private static final Logger logger = LogManager.getLogger(CharsetFilter.class);

    private String encoding;
    private ServletContext context;

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        request.setCharacterEncoding(encoding);
        chain.doFilter(request, response);

    }

    public void init(FilterConfig config) {
        encoding = config.getInitParameter(PARAMETER_REQUEST_ENCODING);

        if (encoding == null) {
            encoding = ENCODING_UTF8;
        }
        context = config.getServletContext();
    }

    public void destroy() {
    }
}