package driver_factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import utils.PropertyReader;

import java.io.IOException;

@Log4j2
public class DriverFactory {
    private PropertyReader propertyReader;

    public DriverFactory(String PropertiesPath) {
        try {
            this.propertyReader = new PropertyReader(PropertiesPath);
        } catch (IOException e) {
            propertyReader = null;
        }
    }

    private WebDriver createChromeDriver() {
        WebDriverManager.chromedriver().setup();
        if (propertyReader != null) {
            log.info("Create ChromeDriver with properties: " + propertyReader.toString());
            ChromeOptions options = new ChromeOptions();
            options.setHeadless(propertyReader.getBoolean("headless"));
            if (propertyReader.getBoolean("incognito")) {
                options.addArguments("--incognito");
            }
            ChromeDriver chromeDriver = new ChromeDriver(options);
            if (propertyReader.getBoolean("maximize")) {
                chromeDriver.manage().window().maximize();
            }
            return chromeDriver;
        }
        log.error("There no properties, create simple ChromeDriver");
        return new ChromeDriver();
    }

    //TODO Add other browsers
    public WebDriver createWebDriver() {
        String browserType = "undefined";
        if (propertyReader != null) {
            browserType = propertyReader.getString("type");
        }

        switch (browserType) {
            case "chrome":
                return createChromeDriver();
            default:
                log.error("Unknown browser type '" + browserType + "', creating a Chrome");
                return createChromeDriver();
        }
    }
}
