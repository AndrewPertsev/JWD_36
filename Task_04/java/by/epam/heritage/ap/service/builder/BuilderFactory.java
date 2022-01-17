package by.epam.heritage.ap.service.builder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class BuilderFactory {
    private static final Logger logger = LogManager.getLogger(BuilderFactory.class);

    private final static BuilderFactory instance = new BuilderFactory();

    private RequestUserBuilder requestBuilder = new RequestUserBuilder();
    private ApartmentBuilder apartmentBuilder = new ApartmentBuilder();
    private GuestBuilder guestBuilder = new GuestBuilder();

    private BuilderFactory() {
    }

    public static BuilderFactory getInstance() {
        return instance;
    }


    public ApartmentBuilder getApartmentBuilder() {
        return apartmentBuilder;
    }

    public RequestUserBuilder getRequestBuilder() {
        return requestBuilder;
    }

    public GuestBuilder getGuestBuilder() {
        return guestBuilder;
    }


}
