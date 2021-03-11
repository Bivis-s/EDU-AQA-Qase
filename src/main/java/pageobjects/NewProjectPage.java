package pageobjects;

import helpers.PageLoadHelper;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import throwables.PropertyError;

@Log4j2
public class NewProjectPage extends BasePage<NewProjectPage> {
    private static final String CREATE_PROJECT_BUTTON_XPATH =
            "//*[id='common']/../following-sibling::*//*[contains(@class,'btn')]";

    public NewProjectPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected String getPageUrl() {
        throw new PropertyError("There is no static url for new project page");
    }

    @Override
    public NewProjectPage isLoaded() {
        PageLoadHelper.waitForElementIsClickable(getDriver(), By.xpath(CREATE_PROJECT_BUTTON_XPATH));
        return this;
    }
}
