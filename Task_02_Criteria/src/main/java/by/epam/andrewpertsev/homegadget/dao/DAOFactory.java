package by.epam.andrewpertsev.homegadget.dao;

import by.epam.andrewpertsev.homegadget.dao.impl.DeviceDAOImpl;

public final class DAOFactory {

    private static DAOFactory instance = null;

    private final DeviceDAO deviceDAO = new DeviceDAOImpl();

    private DAOFactory() {
    }

    public static DAOFactory getInstance() {
        if (instance == null) {
            instance = new DAOFactory();
        }
        return instance;
    }

    public DeviceDAO getDeviceDAO() {
        return deviceDAO;
    }
}
