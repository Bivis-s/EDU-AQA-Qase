package pageobjects;

import helpers.PageLoadHelper;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import setups.PropertyDriver;

@Log4j2
public class HomePage extends BasePage<HomePage> {
    private static final String HOME_PAGE_URL = "";
    private static final String SIGN_IN_BUTTON_ID = "signin";

    public HomePage(PropertyDriver driver) {
        super(driver);
    }

    @Override
    protected HomePage load() {
        return refreshPage();
    }

    @Override
    public HomePage isLoaded() throws Error {
        try {
            PageLoadHelper.waitForElementIsClickable(getDriver(), By.id(SIGN_IN_BUTTON_ID));
            return this;
        } catch (Error e) {
            String errorMessage = "The home page is not loaded";
            log.error(errorMessage);
            throw new Error(errorMessage);
        }
    }

    @Override
    protected String getPageUrl() {
        return HOME_PAGE_URL;
    }

    public LoginPage clickLoginButton() {
        click(findElementById(SIGN_IN_BUTTON_ID));
        return new LoginPage((PropertyDriver) getDriver()).get();
    }
}
