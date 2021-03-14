package pageobjects.app;

import helpers.PageLoadHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import throwables.PropertyError;

public class ProjectPage extends LoadableAppPage<ProjectPage> {
    private static final String STANDARD_REPOSITORY_TITLE_XPATH = "//*[text()='Test repository']";
    private static final String CREATE_NEW_CASE_BUTTON_WHEN_PROJECT_IS_EMPTY_XPATH = "//*[contains(@class,'btn') and contains(text(),'Create new case')]";
    private static final String CREATE_NEW_CASE_BUTTON_WHEN_PROJECT_IS_NOT_EMPTY_ID = "create-case-button";

    public ProjectPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected void waitForPageLoaded() {
        PageLoadHelper.waitForElementIsVisible(getDriver(), By.xpath(STANDARD_REPOSITORY_TITLE_XPATH));
    }

    @Override
    protected String getPageUrl() {
        throw new PropertyError("There is no static url for project page");
    }

    public CreateTestCasePage clickCreateNewCaseButton() {
        try {
            click(findElementById(CREATE_NEW_CASE_BUTTON_WHEN_PROJECT_IS_NOT_EMPTY_ID));
        } catch (NoSuchElementException e) {
            click(findElementByXpath(CREATE_NEW_CASE_BUTTON_WHEN_PROJECT_IS_EMPTY_XPATH));
        }
        return new CreateTestCasePage(getDriver());
    }

    //TODO Implement clickCreateNewSuiteButton and manipulations with project content
}
