package setups;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.readers.DriverPropertyReader;

import java.util.List;
import java.util.Set;

@Log4j2
public class PropertyDriver implements WebDriver {
    private final WebDriver driver;

    public PropertyDriver() {
        this.driver = new PropertyDriverFactory(new DriverPropertyReader().getDriverProperties()).createWebDriver();
    }

    @Override
    public void get(String url) {
        try {
            log.debug("Get url '" + url + "'");
            driver.get(url);
        } catch (Throwable t) {
            log.error(t.getMessage());
            throw  t;
        }
    }

    @Override
    public String getCurrentUrl() {
        try {
            String currentUrl = driver.getCurrentUrl();
            log.debug("Get current url '" + currentUrl + "'");
            return currentUrl;
        } catch (Throwable t) {
            log.error(t.getMessage());
            throw  t;
        }
    }

    @Override
    public String getTitle() {
        try {
            String title = driver.getTitle();
            log.debug("Get title '" + title + "'");
            return title;
        } catch (Throwable t) {
            log.error(t.getMessage());
            throw  t;
        }
    }

    @Override
    public List<WebElement> findElements(By by) {
        try {
            log.trace("Find elementS by '" + by.toString() + "'");
            return driver.findElements(by);
        } catch (Throwable t) {
            log.error(t.getMessage());
            throw  t;
        }
    }

    @Override
    public WebElement findElement(By by) {
        try {
            log.trace("Find element by '" + by.toString() + "'");
            return driver.findElement(by);
        } catch (Throwable t) {
            log.error(t.getMessage());
            throw  t;
        }
    }

    @Override
    public String getPageSource() {
        try {
            return driver.getPageSource();
        } catch (Throwable t) {
            log.error(t.getMessage());
            throw  t;
        }
    }

    @Override
    public void close() {
        try {
            log.debug("Close WebDriver");
            driver.close();
        } catch (Throwable t) {
            log.error(t.getMessage());
            throw  t;
        }
    }

    @Override
    public void quit() {
        try {
            log.debug("Quit WebDriver");
            driver.quit();
        } catch (Throwable t) {
            log.error(t.getMessage());
            throw  t;
        }
    }

    @Override
    public Set<String> getWindowHandles() {
        try {
            return driver.getWindowHandles();
        } catch (Throwable t) {
            log.error(t.getMessage());
            throw  t;
        }
    }

    @Override
    public String getWindowHandle() {
        try {
            return driver.getWindowHandle();
        } catch (Throwable t) {
            log.error(t.getMessage());
            throw  t;
        }
    }

    @Override
    public TargetLocator switchTo() {
        try {
            return driver.switchTo();
        } catch (Throwable t) {
            log.error(t.getMessage());
            throw  t;
        }
    }

    @Override
    public Navigation navigate() {
        try {
            return driver.navigate();
        } catch (Throwable t) {
            log.error(t.getMessage());
            throw  t;
        }
    }

    @Override
    public Options manage() {
        try {
            return driver.manage();
        } catch (Throwable t) {
            log.error(t.getMessage());
            throw  t;
        }
    }
}
