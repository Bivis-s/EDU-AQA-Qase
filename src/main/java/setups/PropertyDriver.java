package setups;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.*;
import property_objects.DriverProperties;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Log4j2
public class PropertyDriver implements WebDriver, JavascriptExecutor, TakesScreenshot {
    private final WebDriver driver;

    public PropertyDriver(DriverProperties driverProperties) {
        this.driver = new PropertyDriverFactory(driverProperties).createWebDriver();
    }

    @Override
    public void get(String url) {
        try {
            log.debug("Get url '" + url + "'");
            driver.get(url);
        } catch (Throwable t) {
            log.trace(t.getMessage());
            throw t;
        }
    }

    @Override
    public String getCurrentUrl() {
        try {
            String currentUrl = driver.getCurrentUrl();
            log.debug("Get current url '" + currentUrl + "'");
            return currentUrl;
        } catch (Throwable t) {
            log.trace(t.getMessage());
            throw t;
        }
    }

    @Override
    public String getTitle() {
        try {
            String title = driver.getTitle();
            log.debug("Get title '" + title + "'");
            return title;
        } catch (Throwable t) {
            log.trace(t.getMessage());
            throw t;
        }
    }

    @Override
    public List<WebElement> findElements(By by) {
        try {
            log.trace("Find elementS by '" + by.toString() + "'");
            return driver.findElements(by);
        } catch (Throwable t) {
            log.trace(t.getMessage());
            throw t;
        }
    }

    @Override
    public WebElement findElement(By by) {
        try {
            log.trace("Find element by '" + by.toString() + "'");
            return driver.findElement(by);
        } catch (Throwable t) {
            log.trace(t.getMessage());
            throw t;
        }
    }

    @Override
    public String getPageSource() {
        try {
            return driver.getPageSource();
        } catch (Throwable t) {
            log.trace(t.getMessage());
            throw t;
        }
    }

    @Override
    public void close() {
        try {
            log.debug("Close WebDriver");
            driver.close();
        } catch (Throwable t) {
            log.trace(t.getMessage());
            throw t;
        }
    }

    @Override
    public void quit() {
        try {
            log.debug("Quit WebDriver");
            driver.quit();
        } catch (Throwable t) {
            log.trace(t.getMessage());
            throw t;
        }
    }

    @Override
    public Set<String> getWindowHandles() {
        try {
            return driver.getWindowHandles();
        } catch (Throwable t) {
            log.trace(t.getMessage());
            throw t;
        }
    }

    @Override
    public String getWindowHandle() {
        try {
            return driver.getWindowHandle();
        } catch (Throwable t) {
            log.trace(t.getMessage());
            throw t;
        }
    }

    @Override
    public TargetLocator switchTo() {
        try {
            return driver.switchTo();
        } catch (Throwable t) {
            log.trace(t.getMessage());
            throw t;
        }
    }

    @Override
    public Navigation navigate() {
        try {
            return driver.navigate();
        } catch (Throwable t) {
            log.trace(t.getMessage());
            throw t;
        }
    }

    @Override
    public Options manage() {
        try {
            return driver.manage();
        } catch (Throwable t) {
            log.trace(t.getMessage());
            throw t;
        }
    }

    @Override
    public Object executeScript(String script, Object... args) {
        try {
            log.debug("Execute js script '" + script + "' with args: '" + Arrays.toString(args) + "'");
            return ((JavascriptExecutor) driver).executeScript(script, args);
        } catch (Throwable t) {
            log.trace(t.getMessage());
            throw t;
        }
    }

    @Override
    public Object executeAsyncScript(String script, Object... args) {
        try {
            log.debug("Execute js async script '" + script + "' with args: '" + Arrays.toString(args) + "'");
            return ((JavascriptExecutor) driver).executeAsyncScript(script, args);
        } catch (Throwable t) {
            log.trace(t.getMessage());
            throw t;
        }
    }

    @Override
    public <X> X getScreenshotAs(OutputType<X> target) throws WebDriverException {
        log.debug("Take screenshot " + target.toString());
        return ((TakesScreenshot) driver).getScreenshotAs(target);
    }
}
