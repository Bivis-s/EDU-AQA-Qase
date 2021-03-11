package utils.readers;

import enums.PropertyDriverType;
import lombok.extern.log4j.Log4j2;
import property_objects.DriverProperties;

@Log4j2
public class DriverPropertyReader extends PropertyReader {
    private static final String ACCOUNTS_PROPERTIES_FILE_PATH = "src/main/resources/driver.properties";

    public DriverPropertyReader() {
        super(ACCOUNTS_PROPERTIES_FILE_PATH);
    }

    public DriverProperties getDriverProperties() {
        DriverProperties driverProperties = new DriverProperties();
        String type = getString("type");
        if (PropertyDriverType.CHROME.getValue().equals(type)) {
            driverProperties.setType(PropertyDriverType.CHROME);
        } else if (PropertyDriverType.FIREFOX.getValue().equals(type)) {
            driverProperties.setType(PropertyDriverType.FIREFOX);
        } else {
            log.error("Unknown driverType '" + type + "', set ChromeDriver");
            driverProperties.setType(PropertyDriverType.CHROME);
        }
        driverProperties.setHeadless(getBoolean("headless"));
        driverProperties.setIncognito(getBoolean("incognito"));
        driverProperties.setMaximize(getBoolean("maximize"));
        return driverProperties;
    }
}
