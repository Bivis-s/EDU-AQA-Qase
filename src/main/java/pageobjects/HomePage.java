package pageobjects;

import helpers.PageLoadHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage<HomePage> {
    private static final String HOME_PAGE_URL = "";
    private static final String SIGN_IN_BUTTON_ID = "signin";

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected HomePage load() {
        return refreshPage();
    }

    @Override
    public HomePage isLoaded() throws Error {
        if (PageLoadHelper.waitForElementIsClickable(getDriver(), By.id(SIGN_IN_BUTTON_ID))) {
            return this;
        } else {
            throw new Error("The home page is not loaded");
        }
    }

    @Override
    protected String getPageUrl() {
        return HOME_PAGE_URL;
    }

    public LoginPage clickLoginButton() {
        click(findElementById(SIGN_IN_BUTTON_ID));
        return new LoginPage(getDriver()).get();
    }
}
