package element_decorators;

import helpers.PageLoadHelper;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pageobjects.LoginPage;

@Log4j2
public class UserMenu extends BaseElementDecorator<UserMenu> {
    private static final String USER_MENU_XPATH = "//*[@class='user-menu']";
    private static final String USER_MENU_BUTTON_XPATH = ".//a[contains(text(),'%s')]";
    private static final String SIGN_OUT_BUTTON_XPATH = String.format(USER_MENU_BUTTON_XPATH, "Sign out");

    public UserMenu(WebDriver driver) {
        super(driver);
    }

    @Override
    protected void initElement() {
        PageLoadHelper.waitForElementIsVisible(getDriver(), By.xpath(USER_MENU_XPATH));
        setElement(findElementByXpath(USER_MENU_XPATH));
    }

    public UserMenu open() {
        log.info("Click on user pic to open user menu, element: '" + getElement().getTagName() + "'");
        super.click(getElement());
        return this;
    }

    public LoginPage clickSignOutButton() {
        log.info("Click sign out button in user menu, xpath: '" + SIGN_OUT_BUTTON_XPATH + "'");
        PageLoadHelper.waitForElementIsClickable(getDriver(), By.xpath(SIGN_OUT_BUTTON_XPATH));
        click(findInnerElementByXpath(getElement(), SIGN_OUT_BUTTON_XPATH));
        return new LoginPage(getDriver()).get();
    }
}
