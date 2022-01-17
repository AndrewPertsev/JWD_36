package by.epam.heritage.ap.controller.impl;

import by.epam.heritage.ap.controller.CommandException;
import by.epam.heritage.ap.controller.Commandable;
import by.epam.heritage.ap.repository.impl.ApartmentDaoImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SetOfferPaidStatusCommand implements Commandable {
    private static final Logger logger = LogManager.getLogger(SetOfferPaidStatusCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {


    }
}
