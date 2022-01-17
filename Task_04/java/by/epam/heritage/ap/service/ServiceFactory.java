package by.epam.heritage.ap.service;

import by.epam.heritage.ap.service.impl.*;

public final class ServiceFactory {

    private static final ServiceFactory instance = new ServiceFactory();

    private ApartmentServiceable apartmentService = new ApartmentServiceImpl();
    private ManagerServiceable managerService = new ManagerServiceImpl();
    private RequestServiceable requestService = new RequestServiceImpl();
    private GuestServiceable guestService = new GuestServiceImpl();
    private OfferServiceable offerService = new OfferServiceImpl();

    private static final TimesheetServiceable timesheetService = new TimesheetServiceImpl();

    private ServiceFactory() {
    }

    public static ServiceFactory getInstance() {
        return instance;
    }


    public ApartmentServiceable getApartmentService() {
        return apartmentService;
    }

    public TimesheetServiceable getTimesheetService() {
        return timesheetService;
    }

    public RequestServiceable getRequestService() {
        return requestService;
    }

    public GuestServiceable getGuestService() {
        return guestService;
    }

    public OfferServiceable getOfferService() { return offerService; }

    public ManagerServiceable getManagerService() {
        return managerService;
    }

}
