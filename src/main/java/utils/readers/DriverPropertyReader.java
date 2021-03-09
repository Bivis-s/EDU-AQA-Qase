package utils.readers;

import lombok.extern.log4j.Log4j2;
import property_objects.DriverProperties;
import setups.DriverPropertyFactory;

import java.io.IOException;

@Log4j2
public class DriverPropertyReader extends PropertyReader {
    private static final String ACCOUNTS_PROPERTIES_FILE_PATH = "src/main/resources/driver.properties";

    public DriverPropertyReader() throws IOException {
        super(ACCOUNTS_PROPERTIES_FILE_PATH);
    }

    public DriverProperties getDriverProperties() {
        DriverProperties driverProperties = new DriverProperties();
        String type = getString("type");
        if (DriverPropertyFactory.Type.CHROME.getValue().equals(type)) {
            driverProperties.setType(DriverPropertyFactory.Type.CHROME);
        } else if (DriverPropertyFactory.Type.FIREFOX.getValue().equals(type)) {
            driverProperties.setType(DriverPropertyFactory.Type.FIREFOX);
        } else {
            log.error("Unknown driverType '" + type + "', set ChromeDriver");
            driverProperties.setType(DriverPropertyFactory.Type.CHROME);
        }
        driverProperties.setHeadless(getBoolean("headless"));
        driverProperties.setIncognito(getBoolean("incognito"));
        driverProperties.setMaximize(getBoolean("maximize"));
        return driverProperties;
    }
}
