package pageobjects;

import element_decorators.ProjectRow;
import enums.UrlPageName;
import helpers.PageLoadHelper;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Log4j2
public class ProjectsPage extends BasePage<ProjectsPage> {
    private static final String CREATE_NEW_PROJECT_BUTTON_ID = "createButton";

    public ProjectsPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected String getPageUrl() {
        return getUrlFromProperty(UrlPageName.PROJECTS);
    }

    @Override
    public ProjectsPage isLoaded() {
        PageLoadHelper.waitForElementIsClickable(getDriver(), By.id(CREATE_NEW_PROJECT_BUTTON_ID));
        return this;
    }

    public NewProjectPage clickCreateNewProjectButton() {
        click(findElementByXpath(CREATE_NEW_PROJECT_BUTTON_ID));
        return new NewProjectPage(getDriver()).get();
    }

    public ProjectRow getProjectRow(String projectName) {
        return new ProjectRow(getDriver(), projectName);
    }
}
