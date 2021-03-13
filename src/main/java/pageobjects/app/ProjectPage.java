package pageobjects.app;

import element_decorators.SuiteContainer;
import element_decorators.modals.CreateSuiteModal;
import element_decorators.modals.DeleteTestCasesModal;
import helpers.PageLoadHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import throwables.PropertyError;

public class ProjectPage extends LoadableAppPage<ProjectPage> {
    private static final String STANDARD_REPOSITORY_TITLE_XPATH = "//*[text()='Test repository']";
    private static final String CREATE_NEW_CASE_BUTTON_WHEN_PROJECT_IS_EMPTY_XPATH =
            "//*[contains(@class,'btn') and contains(text(),'Create new case')] | //*[@id='create-case-button']";
    private static final String CREATE_NEW_SUITE_BUTTON_WHEN_PROJECT_IS_EMPTY_XPATH =
            "//*[contains(@class,'btn') and contains(text(),'Create new suite')] | //*[@id='create-suite-button']";
    private static final String SEARCH_FOR_CASES_FIELD_XPATH = "//*[contains(@name,'title')]";
    private static final String DELETE_TEST_CASES_BUTTON_XPATH =
            "//*[contains(@class,'controls-block-buttons')]//*[contains(text(),'Delete')]";

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
        PageLoadHelper.waitForElementIsClickable(getDriver(), By.xpath(CREATE_NEW_CASE_BUTTON_WHEN_PROJECT_IS_EMPTY_XPATH));
        click(findElementByXpath(CREATE_NEW_CASE_BUTTON_WHEN_PROJECT_IS_EMPTY_XPATH));
        return new CreateTestCasePage(getDriver()).get();
    }

    public CreateSuiteModal clickCreateNewSuiteButton() {
        PageLoadHelper.waitForElementIsClickable(getDriver(), By.xpath(CREATE_NEW_SUITE_BUTTON_WHEN_PROJECT_IS_EMPTY_XPATH));
        click(findElementByXpath(CREATE_NEW_SUITE_BUTTON_WHEN_PROJECT_IS_EMPTY_XPATH));
        return new CreateSuiteModal(getDriver());
    }

    public SuiteContainer getSuiteContainer() {
        return new SuiteContainer(getDriver());
    }

    public ProjectPage enterTextInSearchCaseField(String caseName) {
        return enterTextIntoSearchField(findElementByXpath(SEARCH_FOR_CASES_FIELD_XPATH), caseName);
    }

    public DeleteTestCasesModal clickDeleteTestCasesButton() {
        PageLoadHelper.waitForElementIsVisible(getDriver(), By.xpath(DELETE_TEST_CASES_BUTTON_XPATH));
        click(findElementByXpath(DELETE_TEST_CASES_BUTTON_XPATH));
        return new DeleteTestCasesModal(getDriver());
    }
}
