package by.epam.heritage.ap.repository.connection;

import java.util.ResourceBundle;

public class DBResourceManager {
    public static final String BUNDLE_FILE_NAME = "database";

    private final static DBResourceManager instance = new DBResourceManager();

    private ResourceBundle bundle = ResourceBundle.getBundle(BUNDLE_FILE_NAME);

    public static DBResourceManager getInstance() {
        return instance;
    }

    public String getValue(String key) {
        return bundle.getString(key);
    }
}
