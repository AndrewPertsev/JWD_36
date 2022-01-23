package by.epam.heritage.ap.controller.filter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static by.epam.heritage.ap.controller.ConstantsCommandPath.*;
import static by.epam.heritage.ap.controller.ConstantsParametersAndAttributes.*;

@WebFilter("/AccessCommandFilter")
public class AccessCommandFilter implements Filter {
    private static final Logger logger = LogManager.getLogger(AccessCommandFilter.class);

    private final Map<String, Integer> commandAccess = new HashMap<>();
    private FilterConfig filterConfig;

    public AccessCommandFilter() {
    }

    @Override
    public void init(FilterConfig fConfig) {
        filterConfig = fConfig;

        commandAccess.put(LOG_IN, 0);
        commandAccess.put(REGISTRATION, 0);
        commandAccess.put(GO_TO_MAIN_PAGE, 0);
        commandAccess.put(GO_TO_HOME_PAGE, 0);
        commandAccess.put(GO_TO_LOGIN_PAGE, 0);
        commandAccess.put(GO_TO_REGISTRATION_PAGE, 0);
        commandAccess.put(REQUEST, 1);
        commandAccess.put(CLOSE_GUEST_SESSION, 1);
        commandAccess.put(GO_TO_REQUEST_PAGE, 1);
        commandAccess.put(GO_TO_GUEST_ROOM_PAGE, 1);
        commandAccess.put(OFFER, 2);
        commandAccess.put(UPDATE_GUEST, 2);
        commandAccess.put(DELETE_REQUEST, 2);
        commandAccess.put(ADD_APARTMENT, 2);
        commandAccess.put(UPDATE_REQUEST, 2);
        commandAccess.put(SHOW_GUEST_DATA, 2);
        commandAccess.put(DELETE_APARTMENT, 2);
        commandAccess.put(UPDATE_APARTMENT, 2);
        commandAccess.put(PUSH_OFFER_TO_USER, 2);
        commandAccess.put(UPDATE_OFFER_STATUS, 2);
        commandAccess.put(GO_TO_OFFER_PROJECT_PAGE, 2);
        commandAccess.put(GO_TO_OFFER_MANAGEMENT_PAGE, 2);
        commandAccess.put(GO_TO_GUEST_MANAGEMENT_PAGE, 2);
        commandAccess.put(GO_TO_REQUEST_MANAGEMENT_PAGE, 2);
        commandAccess.put(GO_TO_TIMESHEET_MANAGEMENT_PAGE, 2);
        commandAccess.put(GO_TO_APARTMENT_MANAGEMENT_PAGE, 2);
        commandAccess.put(FIND_REQUESTS_UNRESPONDED_STATUS_ONLY, 2);


    }

    @Override
    public void doFilter(ServletRequest requestServlet, ServletResponse responseServlet, FilterChain filterChain) throws IOException, ServletException {

        int roleInt = 0;
        String command = "";
        String guestRoleId = "";
        int commandAccessRole;
        HttpServletRequest request = (HttpServletRequest) requestServlet;
        HttpServletResponse response = (HttpServletResponse) responseServlet;
        HttpSession session = request.getSession(true);

        if (filterConfig.getInitParameter(PARAMETER_FILTER_ACTIVE).equalsIgnoreCase("true")) {

            command = request.getParameter(PARAMETER_COMMAND);
            commandAccessRole = commandAccess.get(command);
            guestRoleId = (String) session.getAttribute(SESSION_ATTRIBUTE_GUEST_ROLE_ID);

            if ((guestRoleId == null || guestRoleId == "")) {
                if (commandAccessRole == 0) {
                    filterChain.doFilter(requestServlet, responseServlet);

                } else {
                    RequestDispatcher dispatcher = request.getRequestDispatcher(PATH_GO_TO_REGISTRATION_PAGE);
                    dispatcher.forward(request, response);
                }

            } else {
                roleInt = Integer.parseInt(guestRoleId);
                if (commandAccessRole <= roleInt) {
                    filterChain.doFilter(requestServlet, responseServlet);
                } else {
                    response.sendError(HttpServletResponse.SC_FORBIDDEN, "You have not rights to visit this page");
                }
            }
        } else {
            filterChain.doFilter(requestServlet, responseServlet);
        }

    }

    @Override
    public void destroy() {
        filterConfig = null;
    }
}





