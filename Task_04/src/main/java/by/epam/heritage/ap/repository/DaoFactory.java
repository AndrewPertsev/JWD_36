package by.epam.heritage.ap.repository;

import by.epam.heritage.ap.repository.connection.PoolException;
import by.epam.heritage.ap.repository.impl.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class DaoFactory {
    private static final Logger logger = LogManager.getLogger(DaoFactory.class);

    private static DaoFactory INSTANCE = null;

    static {
        try {
            INSTANCE = new DaoFactory();
        } catch (DAOException | PoolException e) {
            throw new RuntimeException("Pool is not created", e);
        }
    }

    private ApartmentDao apartmentDao = new ApartmentDaoImpl();
    private TimesheetDao timesheetDao = new TimesheetDaoImpl();
    private CategoryDao categoryDao = new CategoryDaoImpl();
    private ManagerDao managerDao = new ManagerDaoImpl();
    private TransferDao transferDao = new TransferDaoImpl();
    private RequestDao requestDao = new RequestDaoImpl();
    private MenuDao menuDao = new MenuDaoImpl();
    private GuestDao guestDao = new GuestDaoImpl();
    private OfferDao offerDao = new OfferDaoImpl();
    private RoleDao roleDao = new RoleDaoImpl();

    private DaoFactory() throws DAOException, PoolException {
    }

    public static DaoFactory getInstance() {
        return INSTANCE;
    }

    public ApartmentDao getApartmentDao() {
        return apartmentDao;
    }

    public TimesheetDao getTimesheetDao() {
        return timesheetDao;
    }

    public CategoryDao getCategoryDao() {
        return categoryDao;
    }

    public TransferDao getTransferDao() {
        return transferDao;
    }

    public RequestDao getRequestDao() {
        return requestDao;
    }

    public MenuDao getMenuDao() {
        return menuDao;
    }

    public GuestDao getGuestDao() {
        return guestDao;
    }

    public OfferDao getOfferDao() {
        return offerDao;
    }

    public ManagerDao getManagerDao() {
        return managerDao;
    }

    public RoleDao getRoleDao() {
        return roleDao;
    }

}
