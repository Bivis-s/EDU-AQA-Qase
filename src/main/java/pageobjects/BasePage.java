package pageobjects;

import helpers.PageLoadHelper;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import setups.CustomLoadableComponent;

import java.util.Arrays;

@Log4j2
public abstract class BasePage<T extends CustomLoadableComponent<T>> extends CustomLoadableComponent<T> {
    protected static final String BASE_URL = "https://qase.io/";
    private final WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    protected WebDriver getDriver() {
        return driver;
    }

    public abstract String getPageUrl();

    @SuppressWarnings("unchecked")
    public T openPage() {
        T page = (T) PageFactory.initElements(getDriver(), getClass());
        getDriver().get(BASE_URL + getPageUrl());
        return page.get();
    }

    protected String getElementLocation(WebElement element) {
        return element.getLocation().toString();
    }

    protected WebElement findElementByXpath(String xpath) {
        log.debug("Find element by xpath '" + xpath + "'");
        return getDriver().findElement(By.xpath(xpath));
    }

    protected WebElement findElementById(String id) {
        log.debug("Find element by id '" + id + "'");
        return getDriver().findElement(By.id(id));
    }

    @SuppressWarnings("unchecked")
    protected T click(WebElement element) {
        log.debug("Click element '" + getElementLocation(element) + "'");
        element.click();
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    protected T sendKeys(WebElement element, String... keys) {
        log.debug("Send keys '" + Arrays.toString(keys) + "' to element '" + getElementLocation(element) + "'");
        element.sendKeys(keys);
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    protected T refreshPage() {
        log.info("Refresh " + this.getClass().getSimpleName() + " page, URL: '" + getDriver().getCurrentUrl() +"'");
        getDriver().navigate().refresh();
        return (T) this;
    }

    protected boolean isElementDisplayed(WebElement element) {
        boolean isDisplayed = false;
        try {
            isDisplayed = element.isDisplayed();
        } catch (NoSuchElementException ignored) {
        }
        log.debug("Is element '" + getElementLocation(element) + "' displayed: " + isDisplayed);
        return isDisplayed;
    }

    protected boolean isElementDisplayed(WebElement element, int timeout) {
        boolean isDisplayed = false;
        try {
            PageLoadHelper.waitFor(getDriver(), ExpectedConditions.visibilityOf(element));
            isDisplayed = true;
        } catch (TimeoutException ignored) {
        }
        log.debug("Is element '" + getElementLocation(element) + "' displayed: " + isDisplayed);
        return isDisplayed;
    }
}
