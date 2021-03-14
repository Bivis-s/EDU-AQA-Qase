package pageobjects.app;

import element_decorators.UserMenu;
import helpers.PageLoadHelper;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageobjects.BasePage;
import throwables.WaitForElementConditionError;


/**
 * This class overrides isLoaded() method and adds waitForPageLoaded() method for sure that the page has loaded and the loading message has disappeared
 */
@Log4j2
public abstract class LoadableAppPage<T extends LoadableAppPage<T>> extends BasePage<T> {
    private static final String CONTENT_LOADING_MESSAGE_XPATH = "//*[@class='loading']";
    private final UserMenu userMenu;

    public LoadableAppPage(WebDriver driver) {
        super(driver);
        userMenu = new UserMenu(getDriver());
    }

    public UserMenu getUserMenu() {
        return userMenu;
    }

    protected abstract void waitForPageLoaded();

    protected void waitForLoadingMessageAppear() {
        try {
            PageLoadHelper.waitForElementIsVisible(getDriver(), By.xpath(CONTENT_LOADING_MESSAGE_XPATH));
        } catch (WaitForElementConditionError e) {
            log.error("Ignore " + e.getMessage());
        }
    }

    protected void waitForLoadingMessageDisappear() {
        PageLoadHelper.waitForElementIsInvisible(getDriver(), By.xpath(CONTENT_LOADING_MESSAGE_XPATH));
    }

    @Override
    @SuppressWarnings("unchecked")
    public T isLoaded() {
        waitForPageLoaded();
        waitForLoadingMessageDisappear();
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    protected T enterTextIntoSearchField(WebElement searchField, String text) {
        sendKeys(clear(searchField), text);
        // after entering text into search field the loading message appears not immediately, so wait for message appear
        waitForLoadingMessageAppear();
        // then disappear
        waitForLoadingMessageDisappear();
        return (T) this;
    }
}
