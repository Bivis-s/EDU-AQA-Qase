package pageobjects;

import helpers.PageLoadHelper;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import setups.PropertyDriver;

@Log4j2
public class ProjectsPage extends BasePage<ProjectsPage> {
    private static final String PROJECTS_PAGE_URL = BASE_URL + "projects";
    private static final String CREATE_NEW_PROJECT_BUTTON_ID = "createButton";

    public ProjectsPage(PropertyDriver driver) {
        super(driver);
    }

    @Override
    protected String getPageUrl() {
        return PROJECTS_PAGE_URL;
    }

    @Override
    protected ProjectsPage load() {
        open(getPageUrl());
        return this;
    }

    @Override
    public ProjectsPage isLoaded() throws Error {
        try {
            PageLoadHelper.waitForElementIsClickable(getDriver(), By.id(CREATE_NEW_PROJECT_BUTTON_ID));
            return this;
        } catch (Error e) {
            String errorMessage = "The projects page is not loaded";
            log.error(errorMessage);
            throw new Error(errorMessage);
        }
    }
}
