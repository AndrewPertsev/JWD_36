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
import static java.lang.Boolean.TRUE;

@WebFilter("/AccessCommandFilter")
public class AccessCommandFilter implements Filter {
    private static final Logger logger = LogManager.getLogger(AccessCommandFilter.class);
    private static final String ACCESS_COMMON = "0";
    private static final String ACCESS_MIN_GUEST = "1";
    private static final String ACCESS_MIN_MANAGER = "2";

    private final Map<String, String> commandAccess = new HashMap<>();
    private FilterConfig filterConfig;

    public AccessCommandFilter() {
    }

    @Override
    public void destroy() {
        filterConfig = null;
    }

    @Override
    public void init(FilterConfig fConfig) {
        filterConfig = fConfig;

        commandAccess.put(LOG_IN, ACCESS_COMMON);
        commandAccess.put(REGISTRATION, ACCESS_COMMON);
        commandAccess.put(NO_COMMAND, ACCESS_COMMON);
        commandAccess.put(GO_TO_HOME_PAGE, ACCESS_COMMON);
        commandAccess.put(GO_TO_LOGIN_PAGE, ACCESS_COMMON);
        commandAccess.put(GO_TO_GALLERY_PAGE, ACCESS_COMMON);
        commandAccess.put(GO_TO_REGISTRATION_PAGE, ACCESS_COMMON);
        commandAccess.put(GO_TO_MAIN_PAGE, ACCESS_MIN_GUEST);
        commandAccess.put(REQUEST, ACCESS_MIN_GUEST);
        commandAccess.put(CLOSE_GUEST_SESSION, ACCESS_MIN_GUEST);
        commandAccess.put(GO_TO_REQUEST_PAGE, ACCESS_MIN_GUEST);
        commandAccess.put(GO_TO_GUEST_ROOM_PAGE, ACCESS_MIN_GUEST);
        commandAccess.put(OFFER, ACCESS_MIN_MANAGER);
        commandAccess.put(UPDATE_GUEST, ACCESS_MIN_MANAGER);
        commandAccess.put(DELETE_REQUEST, ACCESS_MIN_MANAGER);
        commandAccess.put(ADD_APARTMENT, ACCESS_MIN_MANAGER);
        commandAccess.put(UPDATE_REQUEST, ACCESS_MIN_MANAGER);
        commandAccess.put(SHOW_GUEST_DATA, ACCESS_MIN_MANAGER);
        commandAccess.put(DELETE_APARTMENT, ACCESS_MIN_MANAGER);
        commandAccess.put(UPDATE_APARTMENT, ACCESS_MIN_MANAGER);
        commandAccess.put(PUSH_OFFER_TO_USER, ACCESS_MIN_MANAGER);
        commandAccess.put(UPDATE_OFFER_STATUS, ACCESS_MIN_MANAGER);
        commandAccess.put(GO_TO_OFFER_PROJECT_PAGE, ACCESS_MIN_MANAGER);
        commandAccess.put(GO_TO_OFFER_MANAGEMENT_PAGE, ACCESS_MIN_MANAGER);
        commandAccess.put(GO_TO_GUEST_MANAGEMENT_PAGE, ACCESS_MIN_MANAGER);
        commandAccess.put(GO_TO_REQUEST_MANAGEMENT_PAGE, ACCESS_MIN_MANAGER);
        commandAccess.put(GO_TO_TIMESHEET_MANAGEMENT_PAGE, ACCESS_MIN_MANAGER);
        commandAccess.put(GO_TO_APARTMENT_MANAGEMENT_PAGE, ACCESS_MIN_MANAGER);
        commandAccess.put(FIND_REQUESTS_UNRESPONDED_STATUS_ONLY, ACCESS_MIN_MANAGER);

    }

    @Override
    public void doFilter(ServletRequest requestServlet, ServletResponse responseServlet, FilterChain filterChain) throws IOException, ServletException {

        int roleInt = 0;
        String command = "";
        String guestRoleId = "";
        String commandAccessRole;
        HttpServletRequest request = (HttpServletRequest) requestServlet;
        HttpServletResponse response = (HttpServletResponse) responseServlet;
        HttpSession session = request.getSession(true);

        if (filterConfig.getInitParameter(PARAMETER_FILTER_ACTIVE).equalsIgnoreCase(TRUE.toString())) {
            command = request.getParameter(PARAMETER_COMMAND);
            commandAccessRole = commandAccess.get(command);
            guestRoleId = (String) session.getAttribute(SESSION_ATTRIBUTE_GUEST_ROLE_ID);


            if (command == null) {
                filterChain.doFilter(requestServlet, responseServlet);
            } else {

                if (guestRoleId == null || guestRoleId == "") {
                    guestRoleId = ACCESS_COMMON;
                }

                roleInt = Integer.parseInt(guestRoleId);
                if (Integer.parseInt(commandAccessRole) <= roleInt) {
                    filterChain.doFilter(requestServlet, responseServlet);
                } else {
                    response.sendRedirect(PATH_REDIRECT_CONTROLLER_COMMAND + GO_TO_LOGIN_PAGE);
                }
            }

        } else {
            filterChain.doFilter(requestServlet, responseServlet);
        }

    }
}


