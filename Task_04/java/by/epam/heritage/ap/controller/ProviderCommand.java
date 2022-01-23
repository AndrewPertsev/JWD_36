package by.epam.heritage.ap.controller;

import by.epam.heritage.ap.controller.impl.*;
import by.epam.heritage.ap.controller.impl.commands_goto.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

import static by.epam.heritage.ap.controller.ConstantsCommandPath.*;

public final class ProviderCommand {
    private static final Logger logger = LogManager.getLogger(ProviderCommand.class);

    private final Map<String, Commandable> commands = new HashMap<>();

    public ProviderCommand() {
        commands.put(OFFER, new OfferCommand());
        commands.put(LOG_IN, new LoginCommand());
        commands.put(REQUEST, new RequestCommand());
        commands.put(SHOW_GUEST_DATA, new ShowGuestData());
        commands.put(REGISTRATION, new RegistrationCommand());
        commands.put(DELETE_REQUEST, new DeleteRequestCommand());
        commands.put(ADD_APARTMENT, new AddApartmentCommand());
        commands.put(UPDATE_GUEST, new UpdateGuestDataCommand());
        commands.put(DELETE_APARTMENT, new DeleteApartmentCommand());
        commands.put(UPDATE_REQUEST, new UpdateRequestDataCommand());
        commands.put(PUSH_OFFER_TO_USER, new PushOfferToUserCommand());
        commands.put(CLOSE_GUEST_SESSION, new CloseGuestSessionCommand());
        commands.put(UPDATE_APARTMENT, new UpdateApartmentDataCommand());
        commands.put(UPDATE_OFFER_STATUS, new UpdateOfferStatusAndPriceCommand());
        commands.put(FIND_REQUESTS_UNRESPONDED_STATUS_ONLY, new FindUnrespondedRequestsCommand());


        commands.put(GO_TO_MAIN_PAGE, new GoToMainPageCommand());
        commands.put(GO_TO_HOME_PAGE, new GoToHomePageCommand());
        commands.put(GO_TO_LOGIN_PAGE, new GoToLoginPageCommand());
        commands.put(GO_TO_REQUEST_PAGE, new GoToRequestPageCommand());
        commands.put(GO_TO_GUEST_ROOM_PAGE, new GoToGuestRoomCommand());
        commands.put(GO_TO_REGISTRATION_PAGE, new GoToRegistrationPageCommand());
        commands.put(GO_TO_OFFER_PROJECT_PAGE, new GoToOfferProjectPageCommand());
        commands.put(GO_TO_OFFER_MANAGEMENT_PAGE, new GoToOfferManagementPageCommand());
        commands.put(GO_TO_GUEST_MANAGEMENT_PAGE, new GoToGuestManagementPageCommand());
        commands.put(GO_TO_REQUEST_MANAGEMENT_PAGE, new GoToRequestManagementPageCommand());
        commands.put(GO_TO_TIMESHEET_MANAGEMENT_PAGE, new GoToTimesheetManagementPageCommand());
        commands.put(GO_TO_APARTMENT_MANAGEMENT_PAGE, new GoToApartmentManagementPageCommand());

    }


    public final Commandable getCommands(String command) {
        Commandable commandable = commands.get(command);
        if (command == null) {
            logger.error("No such command ");//TODO EXCEPTION

        }
        return commandable;
    }
}
