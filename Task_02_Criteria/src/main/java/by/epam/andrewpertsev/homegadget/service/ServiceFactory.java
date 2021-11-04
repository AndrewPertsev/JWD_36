package by.epam.andrewpertsev.homegadget.service;

import by.epam.andrewpertsev.homegadget.service.impl.DeviceServiceImpl;

public final class ServiceFactory {

    private static ServiceFactory instance = null;

    private final DeviceServiceIface deviceService = new DeviceServiceImpl();

    private ServiceFactory() {
    }

    public DeviceServiceIface getDeviceService() {

        return deviceService;
    }

    public static ServiceFactory getInstance() {
        if (instance == null) {
            instance = new ServiceFactory();
        }
        return instance;
    }

}
