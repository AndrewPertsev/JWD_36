package by.epam.heritage.ap.controller;

import by.epam.heritage.ap.controller.impl.*;
import by.epam.heritage.ap.controller.impl.commands_goto.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

import static by.epam.heritage.ap.controller.Commands.*;

public final class ProviderCommand {
    private static final Logger logger = LogManager.getLogger(ProviderCommand.class);

    private final Map<String, Commandable> commands = new HashMap<>();

    public ProviderCommand() {
        commands.put(OFFER, new OfferCommand());
        commands.put(LOG_IN, new LoginCommand());
        commands.put(REQUEST, new RequestCommand());
        commands.put(REGISTRATION, new RegistrationCommand());
        commands.put(UPDATE_GUEST, new UpdateGuestDataCommand());
        commands.put(DELETE_REQUEST, new DeleteRequestCommand());
        commands.put(ADD_APARTMENT, new AddApartmentCommand());
        commands.put(UPDATE_REQUEST, new UpdateRequestDataCommand());
        commands.put(SWITCH_LANGUAGE, new SwitchLanguageCommand());
        commands.put(SHOW_GUEST_DATA, new ShowGuestData());
        commands.put(DELETE_APARTMENT, new DeleteApartmentCommand());
        commands.put(UPDATE_APARTMENT, new UpdateApartmentDataCommand());
        commands.put(PUSH_OFFER_TO_USER, new PushOfferToUserCommand());
        commands.put(SET_OFFER_PAID_STATUS, new SetOfferPaidStatusCommand());
        commands.put(RESERVE_CONFIRMED_DAYS, new ReserveConfirmedDaysCommand());
        commands.put(SET_OFFER_CLOSED_STATUS, new SetOfferClosedStatusCommand());


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
        commands.put(GO_TO_APARTMENT_MANAGEMENT_PAGE, new GoToApartmentManagementPageCommand());

    }


    public final Commandable getCommands(String command) throws CommandException {
        Commandable commandable = commands.get(command);
        if (command == null) { logger.error("No such command ");
            throw new CommandException("No such command");
        }
        return commandable;
    }
}
