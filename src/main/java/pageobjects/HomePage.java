package pageobjects;

import enums.UrlPageName;
import helpers.PageLoadHelper;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Log4j2
public class HomePage extends BasePage<HomePage> {
    private static final String SIGN_IN_BUTTON_ID = "signin";

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public HomePage isLoaded() {
        PageLoadHelper.waitForElementIsClickable(getDriver(), By.id(SIGN_IN_BUTTON_ID));
        return this;
    }

    @Override
    protected String getPageUrl() {
        return getUrlFromProperty(UrlPageName.HOME);
    }

    public LoginPage clickLoginButton() {
        log.info("Click login button");
        click(findElementById(SIGN_IN_BUTTON_ID));
        return new LoginPage(getDriver()).get();
    }
}
