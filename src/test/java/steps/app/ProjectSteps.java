package steps.app;

import element_decorators.modals.CloneSuiteModal;
import element_decorators.modals.CreateSuiteModal;
import element_decorators.modals.DeleteSuiteModal;
import element_decorators.modals.DeleteTestCasesModal;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import pageobjects.app.ProjectPage;
import property_objects.wrappers.CasePropertiesWrapper;
import property_objects.wrappers.ProjectPropertiesWrapper;
import property_objects.wrappers.SuitePropertiesWrapper;
import utils.RandomStringGenerator;
import world_context.WorldContext;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

@Log4j2
@RequiredArgsConstructor
public class ProjectSteps {
    private final WorldContext worldContext;
    private final ProjectPropertiesWrapper projectPropertiesWrapper;
    private final CasePropertiesWrapper casePropertiesWrapper;
    private final SuitePropertiesWrapper suitePropertiesWrapper;
    private ProjectPage projectPage;
    private DeleteTestCasesModal deleteTestCasesModal;
    private CreateSuiteModal createSuiteModal;
    private DeleteSuiteModal deleteSuiteModal;
    private CloneSuiteModal cloneSuiteModal;

    @Before
    public void initPages() {
        projectPage = new ProjectPage(worldContext.getDriver());
    }

    @Then("The project is opened")
    public void theProjectIsOpened() {
        assertNotNull(projectPage.isLoaded());
    }

    @Then("Open the (created )project")
    public void openTheProject() {
        projectPage.openPageByProjectCode(projectPropertiesWrapper.getProjectCode());
    }

    @When("Click the `Create new case` button")
    public void clickTheCreateNewCaseButton() {
        projectPage.clickCreateNewCaseButton();
    }

    @And("Enter a case name into search case field")
    public void enterACaseNameIntoSearchCaseField() {
        projectPage.enterTextInSearchCaseField(casePropertiesWrapper.getCaseTitle());
    }

    @Then("There is/are {int} (the )case(s) without suite on the project page")
    public void thereIsTheCaseOnProjectPage(int expectedCountOfCases) {
        int actualCountOfCases =
                projectPage.getSuiteContainer().getCaseWithoutSuiteCount();
        assertEquals(actualCountOfCases, expectedCountOfCases);
    }

    @And("Check the checkbox near the case name")
    public void checkTheCheckboxNearTheCaseName() {
        projectPage.getSuiteContainer().checkCaseWithoutCheckboxByName(casePropertiesWrapper.getCaseTitle());
    }

    @And("Click the `Delete` button in the controls block at the top")
    public void clickTheDeleteButtonInTheControlsBlockAtTheTop() {
        deleteTestCasesModal = projectPage.clickDeleteTestCasesButton();
    }

    @And("Enter {string} into field in the delete test case modal")
    public void enterCONFIRMIntoFieldInTheModal(String text) {
        deleteTestCasesModal.enterTextIntoConfirmField(text);
    }

    @When("Click the `Delete` button in the delete test case modal")
    public void clickTheDeleteButtonInTheModal() {
        deleteTestCasesModal.clickDeleteCasesButton();
    }

    @And("Click the `Create new suite` button")
    public void clickTheCreateNewSuiteButton() {
        createSuiteModal = projectPage.clickCreateNewSuiteButton();
    }

    @And("Fill out the suite name on create suite modal with valid data")
    public void fillOutTheSuiteNameOnCreateSuiteModalWithValidData() {
        createSuiteModal.enterSuiteName(RandomStringGenerator.createRandomLatinSentence(5));
    }

    @And("Fill out the suite description on create suite modal with valid data")
    public void fillOutTheSuiteDescriptionOnCreateSuiteModalWithValidData() {
        createSuiteModal.enterDescription(RandomStringGenerator.createRandomLatinSentence(10));
    }

    @And("Fill out the suite precondition on create suite modal with valid data")
    public void fillOutTheSuitePreconditionOnCreateSuiteModalWithValidData() {
        createSuiteModal.enterPreconditions(RandomStringGenerator.createRandomLatinSentence(2));
    }

    @And("Fill out suite name, description, precondition on create suite modal with valid data")
    public void fillOutSuiteNameDescriptionAndPreconditionOnCreateSuiteModalWithValidData() {
        fillOutTheSuiteNameOnCreateSuiteModalWithValidData();
        fillOutTheSuiteDescriptionOnCreateSuiteModalWithValidData();
        fillOutTheSuitePreconditionOnCreateSuiteModalWithValidData();
    }

    @When("Click the `Create` button on create suite modal")
    public void clickTheCreateButtonOnCreateSuiteModal() {
        createSuiteModal.clickCreateSuiteButton();
        suitePropertiesWrapper.setSuiteProperties(createSuiteModal.getBuiltSuiteProperties());
    }

    @And("There are/is {int} (the )suite(s) on the project page( with same name)")
    public void thereAreSuitesWithoutSuiteOnTheProjectPage(int expectedCountOfSuites) {
        int actualCountOfSuites =
                projectPage.getSuiteContainer().getSuiteCountOnPage(suitePropertiesWrapper.getSuiteName());
        assertEquals(actualCountOfSuites, expectedCountOfSuites);
    }

    @And("Hover over the suite name, then click the trash icon to the right of the suite name")
    public void hoverOverTheSuiteNameThenClickTheTrashIconToTheRightOfTheSuiteName() {
        deleteSuiteModal = projectPage
                .getSuiteContainer()
                .clickDeleteSuiteButtonBySuiteName(suitePropertiesWrapper.getSuiteName());
    }

    @When("Click the `Delete suite` button in the delete suite modal")
    public void clickTheDeleteSuiteButtonInTheModal() {
        deleteSuiteModal.clickDeleteSuiteButton();
    }

    @And("Check the checkbox near the suite name `Test cases without suite`")
    public void checkTheCheckboxNearTheSuiteNameTestCasesWithoutSuite() {
        projectPage.getSuiteContainer().checkCasesWithoutSuiteCheckbox();
    }

    @And("Hover over the suite name, then click the copy icon to the right of the suite name")
    public void hoverOverTheSuiteNameThenClickTheCopyIconToTheRightOfTheSuiteName() {
        cloneSuiteModal = projectPage
                .getSuiteContainer()
                .clickCloneSuiteButtonBySuiteName(suitePropertiesWrapper.getSuiteName());
    }

    @When("Click the `Clone` button in the clone suite modal")
    public void clickTheCloneButtonInTheModal() {
        cloneSuiteModal.clickCloneSuiteButton();
    }
}
