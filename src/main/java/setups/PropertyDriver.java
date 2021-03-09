package setups;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import static org.testng.Assert.fail;

@Log4j2
public class PropertyDriver implements WebDriver {
    private final WebDriver driver;

    public PropertyDriver() {
        try {
            this.driver = new DriverPropertyFactory(new DriverPropertyReader().getDriverProperties()).createWebDriver();
        } catch (IOException e) {
            String errorMessage = "Driver property file not found";
            log.error(errorMessage);
            fail(errorMessage);
            throw new Error(errorMessage);
        }
    }

    @Override
    public void get(String url) {
        log.debug("Get url '" + url + "'");
        driver.get(url);
    }

    @Override
    public String getCurrentUrl() {
        String currentUrl = driver.getCurrentUrl();
        log.debug("Get current url '" + currentUrl + "'");
        return currentUrl;
    }

    @Override
    public String getTitle() {
        String title = driver.getTitle();
        log.debug("Get title '" + title + "'");
        return title;
    }

    @Override
    public List<WebElement> findElements(By by) {
        log.trace("Find elementS by '" + by.toString() + "'");
        return driver.findElements(by);
    }

    @Override
    public WebElement findElement(By by) {
        log.trace("Find element by '" + by.toString() + "'");
        return driver.findElement(by);
    }

    @Override
    public String getPageSource() {
        return driver.getPageSource();
    }

    @Override
    public void close() {
        log.debug("Close WebDriver");
        driver.close();
    }

    @Override
    public void quit() {
        log.debug("Quit WebDriver");
        driver.quit();
    }

    @Override
    public Set<String> getWindowHandles() {
        return driver.getWindowHandles();
    }

    @Override
    public String getWindowHandle() {
        return driver.getWindowHandle();
    }

    @Override
    public TargetLocator switchTo() {
        return driver.switchTo();
    }

    @Override
    public Navigation navigate() {
        return driver.navigate();
    }

    @Override
    public Options manage() {
        return driver.manage();
    }
}
