package pageobjects.app;

import helpers.PageLoadHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pageobjects.BasePage;


/**
 * This class overrides isLoaded() method and adds waitForPageLoaded() method for sure that the page has loaded and the loading message has disappeared
 */
public abstract class LoadableAppPage<T extends LoadableAppPage<T>> extends BasePage<T> {
    private static final String CONTENT_LOADING_MESSAGE_XPATH = "//*[@class='loading']";

    public LoadableAppPage(WebDriver driver) {
        super(driver);
    }

    protected abstract void waitForPageLoaded();

    @Override
    @SuppressWarnings("unchecked")
    public T isLoaded() {
        waitForPageLoaded();
        PageLoadHelper.waitForElementIsInvisible(getDriver(), By.xpath(CONTENT_LOADING_MESSAGE_XPATH));
        return (T) this;
    }
}
