package by.epam.heritage.ap.service.validator;


public final class ValidatorFactory {

    private final static ValidatorFactory instance = new ValidatorFactory();

    private ApartmentValidator apartmentValidator = new ApartmentValidator();
    private OfferStatusValidator offerValidator = new OfferStatusValidator();
    private RequestValidator requestValidator = new RequestValidator();
    private GuestValidator guestValidator = new GuestValidator();


    private ValidatorFactory() {
    }

    public static ValidatorFactory getInstance() {
        return instance;
    }


    public ApartmentValidator getApartmentValidator() {
        return apartmentValidator;
    }

    public RequestValidator getRequestValidator() {
        return requestValidator;
    }

    public OfferStatusValidator getOfferValidator() { return offerValidator; }

    public GuestValidator getGuestValidator() {
        return guestValidator;
    }
}
