package pageobjects;

import helpers.PageLoadHelper;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import setups.PropertyDriver;
import utils.readers.UrlPropertyReader;

import java.io.IOException;

@Log4j2
public class ProjectsPage extends BasePage<ProjectsPage> {
    private static final String CREATE_NEW_PROJECT_BUTTON_ID = "createButton";

    public ProjectsPage(PropertyDriver driver) {
        super(driver);
    }

    @Override
    protected String getPageUrl() throws IOException {
        return getUrlFromProperty(UrlPropertyReader.Page.PROJECTS);
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
