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
import org.openqa.selenium.WebDriver;
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

    public CreateTestCasePage enterFieldByName(CreateCaseField name, String text) {
        if (text != null) {
            new InputForm(getDriver(), name.getValue()).sendKeys(text);
            caseProperties.addTextField(name, text);
        } else {
            log.error(name.getValue() + " field has not been entered, because text is null");
        }
        return this;
    }

    public CreateTestCasePage selectOptionByName(CreateCaseSelect name, SelectOption option) {
        if (option != null) {
            new SelectForm(getDriver(), name.getValue())
                    .click()
                    .clickOptionByLabel(option.getValue());
            caseProperties.addSelect(name, option);
        } else {
            log.error(name.getValue() + " select has not been set, because option is null");
        }
        return this;
    }

    public CreateTestCasePage clickAddStepButtonForTimes(int timesNumber) {
        for (int i = 0; i < timesNumber; i++) {
            scrollDown();
            PageLoadHelper.waitForElementIsClickable(getDriver(), By.id(ADD_STEP_BUTTON_ID));
            click(findElementById(ADD_STEP_BUTTON_ID));
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
        PageLoadHelper.waitForElementIsClickable(getDriver(), By.id(SAVE_CASE_BUTTON_ID));
        click(findElementById(SAVE_CASE_BUTTON_ID));
        return new ProjectPage(getDriver()).get();
    }
}
