package pageobjects;

import helpers.PageLoadHelper;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import setups.PropertyDriver;

@Log4j2
public class LoginPage extends BasePage<LoginPage>{
    private static final String HOME_PAGE_URL = BASE_URL + "login";
    private static final String LOGIN_BUTTON_ID = "btnLogin";
    @FindBy(id = "inputEmail")
    private WebElement emailInput;
    @FindBy(id = "inputPassword")
    protected WebElement passwordInput;

    public LoginPage(PropertyDriver driver) {
        super(driver);
    }

    @Override
    protected String getPageUrl() {
        return HOME_PAGE_URL;
    }

    @Override
    protected LoginPage load() {
        open(getPageUrl());
        return this;
    }

    @Override
    public LoginPage isLoaded() throws Error {
        try {
            PageLoadHelper.waitForElementIsClickable(getDriver(), By.id(LOGIN_BUTTON_ID));
            return this;
        } catch (Error e) {
            String errorMessage = "The login page is not loaded";
            log.error(errorMessage);
            throw new Error(errorMessage);
        }
    }

    public LoginPage enterEmail(String email) {
        sendKeys(emailInput, email);
        return this;
    }

    public LoginPage enterPassword(String password) {
        sendKeys(passwordInput, password);
        return this;
    }

    public ProjectsPage clickLoginButton() {
        click(findElementById(LOGIN_BUTTON_ID));
        return new ProjectsPage((PropertyDriver) getDriver()).get();
    }
}
