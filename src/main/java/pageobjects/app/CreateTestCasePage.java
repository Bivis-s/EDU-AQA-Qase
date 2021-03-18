package pageobjects.app;

import element_decorators.CaseSteps;
import element_decorators.InputForm;
import element_decorators.SelectForm;
import enums.create_case.CreateCaseField;
import enums.create_case.CreateCaseSelect;
import enums.create_case.select_options.SelectOption;
import helpers.PageLoadHelper;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import property_objects.CaseProperties;
import property_objects.CaseStepProperties;
import throwables.PropertyError;

@Log4j2
public class CreateTestCasePage extends LoadableAppPage<CreateTestCasePage> {
    private static final String STANDARD_CREATE_TEST_CASE_TITLE_XPATH = "//*[text()='Create test case']";
    private static final String ADD_STEP_BUTTON_ID = "add-step";
    private static final String SAVE_CASE_BUTTON_ID = "save-case";
    private final CaseProperties caseProperties;
    private final CaseSteps caseSteps;

    public CreateTestCasePage(WebDriver driver) {
        super(driver);
        caseProperties = new CaseProperties();
        caseSteps = new CaseSteps(getDriver());
    }

    @Override
    protected void waitForPageLoaded() {
        PageLoadHelper.waitForElementIsVisible(getDriver(), By.xpath(STANDARD_CREATE_TEST_CASE_TITLE_XPATH));
    }

    @Override
    protected String getPageUrl() {
        throw new PropertyError("There is no static url for create test case page");
    }

    public CreateTestCasePage enterFieldByName(CreateCaseField fieldName, String text) {
        if (text != null) {
            log.info("Enter text '" + text + "' in field '" + fieldName.getValue() + "'");
            new InputForm(getDriver(), fieldName.getValue()).sendKeys(text);
            caseProperties.addTextField(fieldName, text);
        } else {
            log.error(fieldName.getValue() + " field has not been entered, because text is null");
        }
        return this;
    }

    public CreateTestCasePage selectOptionByName(CreateCaseSelect selectName, SelectOption option) {
        if (option != null) {
            log.info("Select option '" + option.getValue() + "' in select '" + selectName.getValue() + "'");
            new SelectForm(getDriver(), selectName.getValue())
                    .click()
                    .clickOptionByLabel(option.getValue());
            caseProperties.addSelect(selectName, option);
        } else {
            log.error(selectName.getValue() + " select has not been set, because option is null");
        }
        return this;
    }

    public CreateTestCasePage clickAddStepButtonForTimes(int timesNumber) {
        log.info("Click add step button for '" + timesNumber + "' times");
        WebElement element = findElementById(ADD_STEP_BUTTON_ID);
        for (int i = 0; i < timesNumber; i++) {
            PageLoadHelper.waitForElementIsClickable(getDriver(), By.id(ADD_STEP_BUTTON_ID));
            try {
                scrollPageDown();
                click(element);
                // sometimes it need to scroll page down twice to click add step button
            } catch (ElementClickInterceptedException e) {
                scrollPageDown();
                click(element);
            }
            caseProperties.addStep(new CaseStepProperties());
        }
        return this;
    }

    public CreateTestCasePage enterActionIntoStepByNumber(int stepNum, String action) {
        caseSteps.enterAction(stepNum, action);
        caseProperties.getSteps().get(stepNum - 1).setAction(action);
        return this;
    }

    public CreateTestCasePage enterInputDataIntoStepByNumber(int stepNum, String inputData) {
        caseSteps.enterInputData(stepNum, inputData);
        caseProperties.getSteps().get(stepNum - 1).setInputData(inputData);
        return this;
    }

    public CreateTestCasePage enterExpectedResultIntoStepByNumber(int stepNum, String enterExpectedResult) {
        caseSteps.enterExpectedResult(stepNum, enterExpectedResult);
        caseProperties.getSteps().get(stepNum - 1).setExpectedResult(enterExpectedResult);
        return this;
    }

    public CaseProperties getBuiltCaseProperties() {
        return caseProperties;
    }

    public ProjectPage clickSaveCaseButton() {
        log.info("Click save case button");
        PageLoadHelper.waitForElementIsClickable(getDriver(), By.id(SAVE_CASE_BUTTON_ID));
        click(findElementById(SAVE_CASE_BUTTON_ID));
        return new ProjectPage(getDriver()).get();
    }
}
