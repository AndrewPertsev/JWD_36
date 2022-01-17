package by.epam.heritage.ap.controller;

import by.epam.heritage.ap.repository.connection.ConnectionPool;
import by.epam.heritage.ap.repository.connection.PoolException;
import by.epam.heritage.ap.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.epam.heritage.ap.controller.ConstantsParametersAndAttributes.PARAMETER_COMMAND;
import static by.epam.heritage.ap.controller.ConstantsParametersAndAttributes.PARAMETER_LOCALE_COMMON;

public class Controller extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(Controller.class);
    private final ProviderCommand providerCommand = new ProviderCommand();


    @Override
    public void init() {
        try {
            ConnectionPool.getInstance().init();
        } catch (PoolException e) {
            throw new RuntimeException("Connection pool not initialized ");
        }
    }


    @Override
    public void destroy() {
        ConnectionPool.getInstance().clearConnectionQueue();
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

        request.getSession(true).setAttribute(PARAMETER_LOCALE_COMMON, request.getParameter(PARAMETER_LOCALE_COMMON));

        try {
            process(request, response);
        } catch (ControllerException e) {
            logger.error("Element does not found ", e);
            e.printStackTrace();
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {

        try {
            process(request, response);
        } catch (ControllerException e) {
            e.printStackTrace();
        }

    }

    private void process(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
        Commandable command = null;
        String commandName = request.getParameter(PARAMETER_COMMAND);

        try {
            command = providerCommand.getCommands(commandName);
        } catch (CommandException e) {
            throw new ControllerException(e);
        }


        try {
            command.execute(request, response);
        } catch (CommandException e) {
            e.printStackTrace();
        } catch (IOException | ServletException | ServiceException e) {
            e.printStackTrace();
        }
    }
}