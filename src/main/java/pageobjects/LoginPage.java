package pageobjects;

import element_decorators.InputForm;
import enums.UrlPageName;
import helpers.PageLoadHelper;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pageobjects.app.ProjectsPage;

@Log4j2
public class LoginPage extends BasePage<LoginPage> {
    private static final String LOGIN_BUTTON_ID = "btnLogin";
    private static final String PASSWORD_INPUT_LABEL = "Password";
    private static final String EMAIL_INPUT_LABEL = "Email";

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected String getPageUrl() {
        return getUrlFromProperty(UrlPageName.LOGIN);
    }

    @Override
    public LoginPage isLoaded() {
        PageLoadHelper.waitForElementIsClickable(getDriver(), By.id(LOGIN_BUTTON_ID));
        return this;
    }

    public LoginPage enterEmail(String email) {
        new InputForm(getDriver(), EMAIL_INPUT_LABEL).sendKeys(email);
        return this;
    }

    public LoginPage enterPassword(String password) {
        new InputForm(getDriver(), PASSWORD_INPUT_LABEL).sendKeys(password);
        return this;
    }

    public ProjectsPage clickLoginButton() {
        click(findElementById(LOGIN_BUTTON_ID));
        return new ProjectsPage(getDriver()).get();
    }
}
