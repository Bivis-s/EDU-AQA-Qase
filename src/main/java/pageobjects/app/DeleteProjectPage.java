package pageobjects.app;

import helpers.PageLoadHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import throwables.PropertyError;

public class DeleteProjectPage extends LoadableAppPage<DeleteProjectPage> {
    private static final String DELETE_PROJECT_BUTTON_XPATH = "//*[contains(@class,'btn-cancel') and contains(text(),'Delete project')]";

    public DeleteProjectPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected void waitForPageLoaded() {
        PageLoadHelper.waitForElementIsVisible(getDriver(), By.xpath(DELETE_PROJECT_BUTTON_XPATH));
    }

    @Override
    protected String getPageUrl() {
        throw new PropertyError("There is no static url for delete project page page");
    }

    public ProjectsPage clickDeleteProjectButton() {
        click(findElementByXpath(DELETE_PROJECT_BUTTON_XPATH));
        return new ProjectsPage(getDriver()).get();
    }
}
