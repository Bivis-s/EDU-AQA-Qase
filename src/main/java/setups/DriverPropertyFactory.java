package setups;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import property_objects.DriverProperties;

@Log4j2
public class DriverPropertyFactory {
    private final DriverProperties properties;

    public enum Type {
        CHROME("chrome"),
        FIREFOX("firefox");

        private final String value;

        Type(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    public DriverPropertyFactory(DriverProperties properties) {
        this.properties = properties;
    }

    private WebDriver createChromeDriver() {
        WebDriverManager.chromedriver().setup();
        log.info("Create ChromeDriver with properties: " + properties.toString());
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(properties.isHeadless());
        if (properties.isIncognito()) {
            options.addArguments("--incognito");
        }
        ChromeDriver chromeDriver = new ChromeDriver(options);
        if (properties.isMaximize()) {
            chromeDriver.manage().window().maximize();
        }
        return chromeDriver;
    }

    private WebDriver createFirefoxDriver() {
        WebDriverManager.firefoxdriver().setup();
        log.info("Create FirefoxDriver with properties: " + properties.toString());
        FirefoxOptions options = new FirefoxOptions();
        options.setHeadless(properties.isHeadless());
        if (properties.isIncognito()) {
            options.addArguments("-private");
        }
        FirefoxDriver firefoxDriver = new FirefoxDriver(options);
        if (properties.isMaximize()) {
            firefoxDriver.manage().window().maximize();
        }
        return firefoxDriver;
    }

    public WebDriver createWebDriver() {
        Type driverType = properties.getType();
        switch (driverType) {
            case CHROME:
                return createChromeDriver();
            case FIREFOX:
                return createFirefoxDriver();
        }
        throw new Error("Cannot create WebDriver, unknown driver type '" + driverType.getValue() + "'");
    }
}
