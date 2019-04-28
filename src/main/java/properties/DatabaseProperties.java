package properties;

import java.io.IOException;
import java.util.Properties;

public enum DatabaseProperties {

    DATABASE_PROPERTIES;

    private static final String FILE_NAME = "database.properties";
    private static final String JDBC_DRIVER = "jdbc.driver";
    private static final String DB_URL = "db.url";
    private static final String DB_USER = "db.user";
    private static final String DB_PASS = "db.pass";

    private Properties properties = new Properties();

    private String getProperty(String propertyName) {
        if (properties.isEmpty()) {
            try {
                properties.load(this.getClass().getClassLoader().getResourceAsStream(FILE_NAME));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return properties.getProperty(propertyName);
    }

    public String getJdbcDriver() {
        return getProperty(JDBC_DRIVER);
    }

    public String getDbUrl() {
        return getProperty(DB_URL);
    }

    public String getDbUser() {
        return getProperty(DB_USER);
    }

    public String getDbPass() {
        return getProperty(DB_PASS);
    }
}
