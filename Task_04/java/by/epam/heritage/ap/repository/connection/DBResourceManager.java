package by.epam.heritage.ap.repository.connection;

import by.epam.heritage.ap.repository.impl.ApartmentDaoImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ResourceBundle;

public class DBResourceManager {
    private static final Logger logger = LogManager.getLogger(DBResourceManager.class);
    public  static final String BUNDLE_FILE_NAME="database";

    private final static DBResourceManager instance = new DBResourceManager();

    private ResourceBundle bundle = ResourceBundle.getBundle(BUNDLE_FILE_NAME);

    public static DBResourceManager getInstance() {
        return instance;
    }

    public String getValue(String key){
        return bundle.getString(key);
    }
}
