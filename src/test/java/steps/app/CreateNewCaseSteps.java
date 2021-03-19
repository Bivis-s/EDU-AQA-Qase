package steps.app;

import enums.create_case.CreateCaseField;
import enums.create_case.CreateCaseSelect;
import enums.create_case.select_options.*;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import pageobjects.app.CreateTestCasePage;
import property_objects.CaseProperties;
import property_objects.wrappers.CasePropertiesWrapper;
import property_objects.wrappers.ProjectPropertiesWrapper;
import utils.EnumUtils;
import utils.RandomStringGenerator;
import world_context.WorldContext;

@Log4j2
@RequiredArgsConstructor
public class CreateNewCaseSteps {
    private final WorldContext worldContext;
    private final CasePropertiesWrapper casePropertiesWrapper;
    private final ProjectPropertiesWrapper projectPropertiesWrapper;
    private CreateTestCasePage casePage;

    @Before
    public void initPage() {
        casePage = new CreateTestCasePage(worldContext.getDriver());
    }

    private void openCreateCasePageByProjectCode(String projectCode) {
        casePage.openPageByProjectCode(projectCode);
    }

    @And("Fill out the case title field with valid data")
    public void fillOutTheCaseTitleFieldWithValidData() {
        casePage.enterFieldByName(CreateCaseField.TITLE,
                RandomStringGenerator.createCurrentDateAndWords(2));
    }

    @And("Fill out the case description field with valid data")
    public void fillOutTheCaseDescriptionFieldWithValidData() {
        casePage.enterFieldByName(CreateCaseField.DESCRIPTION,
                RandomStringGenerator.createRandomLatinSentence(10));
    }

    @And("Fill out the case pre-conditions field with valid data")
    public void fillOutTheCasePreConditionsFieldWithValidData() {
        casePage.enterFieldByName(CreateCaseField.PRE_CONDITIONS,
                RandomStringGenerator.createRandomLatinSentence(10));
    }

    @And("Fill out the case post-conditions field with valid data")
    public void fillOutTheCasePostConditionsFieldWithValidData() {
        casePage.enterFieldByName(CreateCaseField.POST_CONDITIONS,
                RandomStringGenerator.createRandomLatinSentence(10));
    }

    @And("Select random status")
    public void selectRandomStatus() {
        casePage.selectOptionByName(CreateCaseSelect.STATUS, EnumUtils.getRandomValue(CaseStatusOption.class));
    }

    @And("Select random severity")
    public void selectRandomSeverity() {
        casePage.selectOptionByName(CreateCaseSelect.SEVERITY, EnumUtils.getRandomValue(CaseSeverityOption.class));
    }

    @And("Select random priority")
    public void selectRandomPriority() {
        casePage.selectOptionByName(CreateCaseSelect.PRIORITY, EnumUtils.getRandomValue(CasePriorityOption.class));
    }

    @And("Select random type")
    public void selectRandomType() {
        casePage.selectOptionByName(CreateCaseSelect.TYPE, EnumUtils.getRandomValue(CaseTypeOption.class));
    }

    @And("Select random behavior")
    public void selectRandomBehavior() {
        casePage.selectOptionByName(CreateCaseSelect.BEHAVIOR, EnumUtils.getRandomValue(CaseBehaviorOption.class));
    }

    @And("Select random automation status")
    public void selectRandomAutomationStatus() {
        casePage.selectOptionByName(CreateCaseSelect.AUTOMATION_STATUS,
                EnumUtils.getRandomValue(CaseAutomationStatusOption.class));
    }

    @And("Click `Add step` button {int} times")
    public void clickAddStepButtonTimes(int numberOfClicks) {
        casePage.clickAddStepButtonForTimes(numberOfClicks);
    }

    @And("Fill out step number {int} action with valid data")
    public void fillOutStepActionWithValidData(int stepNumber) {
        casePage.enterActionIntoStepByNumber(stepNumber,
                RandomStringGenerator.createRandomLatinSentence(5));
    }

    @And("Fill out step number {int} input data with valid data")
    public void fillOutStepInputDataWithValidData(int stepNumber) {
        casePage.enterInputDataIntoStepByNumber(stepNumber,
                RandomStringGenerator.createRandomLatinSentence(5));
    }

    @And("Fill out step number {int} expected result with valid data")
    public void fillOutStepExpectedResultWithValidData(int stepNumber) {
        casePage.enterExpectedResultIntoStepByNumber(stepNumber,
                RandomStringGenerator.createRandomLatinSentence(5));
    }

    @And("Click the `Save` button")
    public void clickTheSaveButton() {
        CaseProperties caseProperties = casePage.getBuiltCaseProperties();
        casePropertiesWrapper.setCaseProperties(caseProperties);
        casePage.clickSaveCaseButton();
    }

    @And("A case without suite is created in the project via gui")
    public void aCaseWithoutSuiteIsCreatedInTheProjectViaGui() {
        openCreateCasePageByProjectCode(projectPropertiesWrapper.getProjectProperties().getProjectCode());
        fillOutTheCaseTitleFieldWithValidData();
        clickTheSaveButton();
    }
}
