package setups;

import lombok.extern.log4j.Log4j2;
import utils.PropertyReader;

import java.io.IOException;

@Log4j2
public class DriverPropertyReader extends PropertyReader {
    private static final String ACCOUNTS_PROPERTIES_FILE = "src/main/resources/driver.properties";

    public DriverPropertyReader() throws IOException {
        super(ACCOUNTS_PROPERTIES_FILE);
    }

    public DriverProperties getDriverProperties() {
        DriverProperties driverProperties = new DriverProperties();
        String type = getString("type");
        if (DriverType.CHROME.getValue().equals(type)) {
            driverProperties.setType(DriverType.CHROME);
        } else if (DriverType.FIREFOX.getValue().equals(type)) {
            driverProperties.setType(DriverType.FIREFOX);
        } else {
            log.error("Unknown driverType '" + type + "', set ChromeDriver");
            driverProperties.setType(DriverType.CHROME);
        }
        driverProperties.setHeadless(getBoolean("headless"));
        driverProperties.setIncognito(getBoolean("incognito"));
        driverProperties.setMaximize(getBoolean("maximize"));
        return driverProperties;
    }
}
