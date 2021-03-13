package tests;

import element_decorators.SuiteContainer;
import enums.ProjectAccessType;
import enums.UrlPageName;
import enums.create_case.CreateCaseField;
import enums.create_case.CreateCaseSelect;
import enums.create_case.select_options.*;
import lombok.extern.log4j.Log4j2;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageobjects.HomePage;
import pageobjects.LoginPage;
import pageobjects.app.CreateTestCasePage;
import pageobjects.app.NewProjectPage;
import pageobjects.app.ProjectPage;
import pageobjects.app.ProjectsPage;
import property_objects.AccountProperties;
import property_objects.ProjectProperties;
import property_objects.UrlProperties;
import setups.PropertyDriver;
import utils.readers.AccountPropertyReader;
import utils.readers.UrlPropertyReader;

import java.util.Random;

import static org.testng.Assert.*;

@Log4j2
public class TemporaryTestsForTests {
    private PropertyDriver driver;
    private Random random;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        driver = new PropertyDriver();
        random = new Random();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void accountPropertiesReaderTest() {
        AccountProperties accountProperties =
                new AccountPropertyReader("existing-user").getAccountsProperties();
        assertNotEquals(accountProperties.getLogin(), "");
        assertNotEquals(accountProperties.getPassword(), "");
    }

    @Test
    public void urlsPropertiesReaderTest() {
        UrlProperties urlProperties = new UrlPropertyReader().getPageUrl(UrlPageName.PROJECTS);
        assertEquals(urlProperties.getUrl(), "https://app.qase.io/projects");
    }

    @Test
    public void driverFactoryTest() {
        String url = "https://www.onliner.by/";
        driver.get(url);
        assertEquals(driver.getCurrentUrl(), url);
    }

    @Test
    public void clickLoginButtonTest() {
        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = homePage
                .openPage()
                .clickLoginButton();
        assertNotNull(loginPage.isLoaded());
    }

    @Test
    public void loginTest() {
        HomePage homePage = new HomePage(driver);
        AccountProperties accountProperties =
                new AccountPropertyReader("existing-user").getAccountsProperties();
        ProjectsPage projectsPage = homePage
                .openPage()
                .clickLoginButton()
                .enterEmail(accountProperties.getLogin())
                .enterPassword(accountProperties.getPassword())
                .clickLoginButton();
        log.info("Assert " + projectsPage.getClass().getSimpleName() + " is loaded");
        assertNotNull(projectsPage.isLoaded());
    }

    @Test
    public void createNewProjectTest() {
        HomePage homePage = new HomePage(driver);
        AccountProperties accountProperties =
                new AccountPropertyReader("existing-user").getAccountsProperties();
        ProjectProperties projectProperties = new ProjectProperties();
        projectProperties.setProjectName("Hohohohhdf");
        projectProperties.setProjectCode("HOAH3");
        projectProperties.setDescription("This is Description");
        projectProperties.setProjectAccessType(ProjectAccessType.PUBLIC);
        NewProjectPage newProjectPage = homePage
                .openPage()
                .clickLoginButton()
                .enterEmail(accountProperties.getLogin())
                .enterPassword(accountProperties.getPassword())
                .clickLoginButton()
                .clickCreateNewProjectButton()
                .enterProjectName(projectProperties.getProjectName())
                .enterProjectCode(projectProperties.getProjectCode())
                .enterDescription(projectProperties.getDescription())
                .setProjectAccessType(projectProperties.getProjectAccessType());
        ProjectProperties builtProjectProperties = newProjectPage.getBuiltProjectProperties();
        ProjectPage projectPage = newProjectPage.clickCreateProjectButton();
        assertNotNull(projectPage.isLoaded());
        assertEquals(projectProperties, builtProjectProperties);
    }

    @Test
    public void createTestCaseTest() {
        HomePage homePage = new HomePage(driver);
        AccountProperties accountProperties =
                new AccountPropertyReader("existing-user").getAccountsProperties();
        CreateTestCasePage createTestCasePage = homePage
                .openPage()
                .clickLoginButton()
                .enterEmail(accountProperties.getLogin())
                .enterPassword(accountProperties.getPassword())
                .clickLoginButton()
                .clickCreateNewProjectButton()
                .enterProjectName("Create test case " + random.nextInt(9999))
                .enterProjectCode("C" + random.nextInt(9999))
                .enterDescription("DESCRIPTION" + random.nextInt(9999))
                .setProjectAccessType(ProjectAccessType.PRIVATE)
                .clickCreateProjectButton()
                .clickCreateNewCaseButton()
                .enterFieldByName(CreateCaseField.TITLE, "This is title")
                .enterFieldByName(CreateCaseField.DESCRIPTION, "This is description")
                .enterFieldByName(CreateCaseField.PRE_CONDITIONS, "This is pre-condition")
                .enterFieldByName(CreateCaseField.POST_CONDITIONS, "This is post-condition")
                .selectOptionByName(CreateCaseSelect.STATUS, CaseStatusOption.DRAFT)
                .selectOptionByName(CreateCaseSelect.SUITE, null) // for test testing
                .selectOptionByName(CreateCaseSelect.SEVERITY, CaseSeverityOption.MINOR)
                .selectOptionByName(CreateCaseSelect.PRIORITY, CasePriorityOption.LOW)
                .selectOptionByName(CreateCaseSelect.TYPE, CaseTypeOption.EXPLORATORY)
                .selectOptionByName(CreateCaseSelect.MILESTONE, null) // for test testing
                .selectOptionByName(CreateCaseSelect.BEHAVIOR, CaseBehaviorOption.POSITIVE)
                .selectOptionByName(CreateCaseSelect.AUTOMATION_STATUS, CaseAutomationStatusOption.NOT_AUTOMATED)
                .clickAddStepButtonForTimes(2)
                .enterActionIntoStepByNumber(1, "COWABUNGA")
                .enterInputDataIntoStepByNumber(1, "Input data!")
                .enterExpectedResultIntoStepByNumber(1, "Expecteddd")
                .enterActionIntoStepByNumber(2, "Second step action")
                .enterInputDataIntoStepByNumber(2, "Second step input data")
                .enterExpectedResultIntoStepByNumber(2, "Second expected result");
        log.info(createTestCasePage.getBuiltCaseProperties().toString());
        ProjectPage projectPage = createTestCasePage.clickSaveCaseButton();
        assertNotNull(projectPage.isLoaded());
    }

    @Test
    public void signOutTest() {
        HomePage homePage = new HomePage(driver);
        AccountProperties accountProperties =
                new AccountPropertyReader("existing-user").getAccountsProperties();
        LoginPage loginPage = homePage
                .openPage()
                .clickLoginButton()
                .enterEmail(accountProperties.getLogin())
                .enterPassword(accountProperties.getPassword())
                .clickLoginButton()
                .getUserMenu()
                .open()
                .clickSignOutButton();
        assertNotNull(loginPage.isLoaded());
    }

    @Test
    void deleteProjectTest() {
        // Creating
        HomePage homePage = new HomePage(driver);
        AccountProperties accountProperties =
                new AccountPropertyReader("existing-user").getAccountsProperties();
        ProjectProperties projectProperties = new ProjectProperties();
        projectProperties.setProjectName("Test" + random.nextInt(9999));
        projectProperties.setProjectCode("H" + random.nextInt(9999));
        projectProperties.setDescription("This is Description");
        projectProperties.setProjectAccessType(ProjectAccessType.PUBLIC);
        ProjectPage projectPage = homePage
                .openPage()
                .clickLoginButton()
                .enterEmail(accountProperties.getLogin())
                .enterPassword(accountProperties.getPassword())
                .clickLoginButton()
                .clickCreateNewProjectButton()
                .enterProjectName(projectProperties.getProjectName())
                .enterProjectCode(projectProperties.getProjectCode())
                .enterDescription(projectProperties.getDescription())
                .setProjectAccessType(projectProperties.getProjectAccessType())
                .clickCreateProjectButton();

        // Deleting
        ProjectsPage projectsPage = new ProjectsPage(driver).openPage();
        assertTrue(projectsPage.enterTextInSearchProjectField(projectProperties.getProjectName()).createProjectNameList().contains(projectProperties.getProjectName()));
        projectsPage
                .getProjectRowByName(projectProperties.getProjectName())
                .clickDropdownButton()
                .clickDropdownDeleteButton()
                .clickDeleteProjectButton();
        assertFalse(projectsPage.enterTextInSearchProjectField(projectProperties.getProjectName()).createProjectNameList().contains(projectProperties.getProjectName()));
    }

    @Test
    public void createTwoTestCaseTest() {
        HomePage homePage = new HomePage(driver);
        AccountProperties accountProperties =
                new AccountPropertyReader("existing-user").getAccountsProperties();
        int caseCountWithoutSuiteOnPage = homePage
                .openPage()
                .clickLoginButton()
                .enterEmail(accountProperties.getLogin())
                .enterPassword(accountProperties.getPassword())
                .clickLoginButton()
                .clickCreateNewProjectButton()
                .enterProjectName("Create test case " + random.nextInt(9999))
                .enterProjectCode("C" + random.nextInt(9999))
                .enterDescription("DESCRIPTION" + random.nextInt(9999))
                .setProjectAccessType(ProjectAccessType.PRIVATE)
                .clickCreateProjectButton()
                .clickCreateNewCaseButton()
                .enterFieldByName(CreateCaseField.TITLE, "This is title")
                .enterFieldByName(CreateCaseField.DESCRIPTION, "This is description")
                .enterFieldByName(CreateCaseField.PRE_CONDITIONS, "This is pre-condition")
                .enterFieldByName(CreateCaseField.POST_CONDITIONS, "This is post-condition")
                .selectOptionByName(CreateCaseSelect.STATUS, CaseStatusOption.DRAFT)
                .selectOptionByName(CreateCaseSelect.SUITE, null) // for test testing
                .selectOptionByName(CreateCaseSelect.SEVERITY, CaseSeverityOption.MINOR)
                .selectOptionByName(CreateCaseSelect.PRIORITY, CasePriorityOption.LOW)
                .selectOptionByName(CreateCaseSelect.TYPE, CaseTypeOption.EXPLORATORY)
                .selectOptionByName(CreateCaseSelect.MILESTONE, null) // for test testing
                .selectOptionByName(CreateCaseSelect.BEHAVIOR, CaseBehaviorOption.POSITIVE)
                .selectOptionByName(CreateCaseSelect.AUTOMATION_STATUS, CaseAutomationStatusOption.NOT_AUTOMATED)
                .clickAddStepButtonForTimes(2)
                .enterActionIntoStepByNumber(1, "COWABUNGA")
                .enterInputDataIntoStepByNumber(1, "Input data!")
                .enterExpectedResultIntoStepByNumber(1, "Expecteddd")
                .enterActionIntoStepByNumber(2, "Second step action")
                .enterInputDataIntoStepByNumber(2, "Second step input data")
                .enterExpectedResultIntoStepByNumber(2, "Second expected result")
                .clickSaveCaseButton()
                .clickCreateNewCaseButton()
                .enterFieldByName(CreateCaseField.TITLE, "This is title")
                .enterFieldByName(CreateCaseField.DESCRIPTION, "This is description")
                .enterFieldByName(CreateCaseField.PRE_CONDITIONS, "This is pre-condition")
                .enterFieldByName(CreateCaseField.POST_CONDITIONS, "This is post-condition")
                .selectOptionByName(CreateCaseSelect.STATUS, CaseStatusOption.DRAFT)
                .selectOptionByName(CreateCaseSelect.SUITE, null) // for test testing
                .selectOptionByName(CreateCaseSelect.SEVERITY, CaseSeverityOption.MINOR)
                .selectOptionByName(CreateCaseSelect.PRIORITY, CasePriorityOption.LOW)
                .selectOptionByName(CreateCaseSelect.TYPE, CaseTypeOption.EXPLORATORY)
                .selectOptionByName(CreateCaseSelect.MILESTONE, null) // for test testing
                .selectOptionByName(CreateCaseSelect.BEHAVIOR, CaseBehaviorOption.POSITIVE)
                .selectOptionByName(CreateCaseSelect.AUTOMATION_STATUS, CaseAutomationStatusOption.NOT_AUTOMATED)
                .clickAddStepButtonForTimes(2)
                .enterActionIntoStepByNumber(1, "COWABUNGA")
                .enterInputDataIntoStepByNumber(1, "Input data!")
                .enterExpectedResultIntoStepByNumber(1, "Expecteddd")
                .enterActionIntoStepByNumber(2, "Second step action")
                .enterInputDataIntoStepByNumber(2, "Second step input data")
                .enterExpectedResultIntoStepByNumber(2, "Second expected result")
                .clickSaveCaseButton()
                .enterTextInSearchCaseField("This is title")
                .getSuiteContainer()
                .getCaseCountWithoutSuiteOnPage("This is title");
        assertEquals(caseCountWithoutSuiteOnPage, 2);
    }

    @Test
    public void createTwoSuiteTest() {
        HomePage homePage = new HomePage(driver);
        AccountProperties accountProperties =
                new AccountPropertyReader("existing-user").getAccountsProperties();
        ProjectPage projectPage = homePage
                .openPage()
                .clickLoginButton()
                .enterEmail(accountProperties.getLogin())
                .enterPassword(accountProperties.getPassword())
                .clickLoginButton()
                .clickCreateNewProjectButton()
                .enterProjectName("Create test case " + random.nextInt(9999))
                .enterProjectCode("C" + random.nextInt(9999))
                .enterDescription("DESCRIPTION" + random.nextInt(9999))
                .setProjectAccessType(ProjectAccessType.PUBLIC)
                .clickCreateProjectButton()
                .clickCreateNewSuiteButton()
                .enterSuiteName("WAV")
                .enterDescription("Description")
                .enterPreconditions("Preconditions")
                .clickCreateSuiteButton()
                .clickCreateNewSuiteButton()
                .enterSuiteName("WAV_inner")
                .enterDescription("Description")
                .enterPreconditions("Preconditions")
                .selectParentSuiteByName("WAV")
                .clickCreateSuiteButton();
        assertNotNull(projectPage.isLoaded());
    }

    @Test
    public void createSuiteAndAssertItOnPageTest() {
        HomePage homePage = new HomePage(driver);
        AccountProperties accountProperties =
                new AccountPropertyReader("existing-user").getAccountsProperties();
        int suiteCountOnPage = homePage
                .openPage()
                .clickLoginButton()
                .enterEmail(accountProperties.getLogin())
                .enterPassword(accountProperties.getPassword())
                .clickLoginButton()
                .clickCreateNewProjectButton()
                .enterProjectName("Create test case " + random.nextInt(9999))
                .enterProjectCode("C" + random.nextInt(9999))
                .enterDescription("DESCRIPTION" + random.nextInt(9999))
                .setProjectAccessType(ProjectAccessType.PUBLIC)
                .clickCreateProjectButton()
                .clickCreateNewSuiteButton()
                .enterSuiteName("WAV")
                .enterDescription("Description")
                .enterPreconditions("Preconditions")
                .clickCreateSuiteButton()
                .getSuiteContainer()
                .getSuiteCountOnPage("WAV");
        assertEquals(suiteCountOnPage, 1);
    }

    @Test
    public void deleteTestCaseTest() {
        HomePage homePage = new HomePage(driver);
        AccountProperties accountProperties =
                new AccountPropertyReader("existing-user").getAccountsProperties();
        int caseCountWithoutSuiteOnPage = homePage
                .openPage()
                .clickLoginButton()
                .enterEmail(accountProperties.getLogin())
                .enterPassword(accountProperties.getPassword())
                .clickLoginButton()
                .clickCreateNewProjectButton()
                .enterProjectName("Create test case " + random.nextInt(9999))
                .enterProjectCode("C" + random.nextInt(9999))
                .enterDescription("DESCRIPTION" + random.nextInt(9999))
                .setProjectAccessType(ProjectAccessType.PRIVATE)
                .clickCreateProjectButton()
                .clickCreateNewCaseButton()
                .enterFieldByName(CreateCaseField.TITLE, "This is title")
                .enterFieldByName(CreateCaseField.DESCRIPTION, "This is description")
                .enterFieldByName(CreateCaseField.PRE_CONDITIONS, "This is pre-condition")
                .enterFieldByName(CreateCaseField.POST_CONDITIONS, "This is post-condition")
                .selectOptionByName(CreateCaseSelect.STATUS, CaseStatusOption.DRAFT)
                .selectOptionByName(CreateCaseSelect.SUITE, null) // for test testing
                .selectOptionByName(CreateCaseSelect.SEVERITY, CaseSeverityOption.MINOR)
                .selectOptionByName(CreateCaseSelect.PRIORITY, CasePriorityOption.LOW)
                .selectOptionByName(CreateCaseSelect.TYPE, CaseTypeOption.EXPLORATORY)
                .selectOptionByName(CreateCaseSelect.MILESTONE, null) // for test testing
                .selectOptionByName(CreateCaseSelect.BEHAVIOR, CaseBehaviorOption.POSITIVE)
                .selectOptionByName(CreateCaseSelect.AUTOMATION_STATUS, CaseAutomationStatusOption.NOT_AUTOMATED)
                .clickAddStepButtonForTimes(2)
                .enterActionIntoStepByNumber(1, "COWABUNGA")
                .enterInputDataIntoStepByNumber(1, "Input data!")
                .enterExpectedResultIntoStepByNumber(1, "Expecteddd")
                .enterActionIntoStepByNumber(2, "Second step action")
                .enterInputDataIntoStepByNumber(2, "Second step input data")
                .enterExpectedResultIntoStepByNumber(2, "Second expected result")
                .clickSaveCaseButton()
                .getSuiteContainer()
                .checkCaseCheckboxByName("This is title")
                .clickDeleteTestCasesButton()
                .enterTextIntoConfirmField("CONFIRM")
                .clickDeleteCasesButton()
                .getSuiteContainer()
                .getCaseCountWithoutSuiteOnPage("This is title");
        assertEquals(caseCountWithoutSuiteOnPage, 0);
    }

    @Test
    public void deleteAnEmptySuite() {
        HomePage homePage = new HomePage(driver);
        AccountProperties accountProperties =
                new AccountPropertyReader("existing-user").getAccountsProperties();
        int suiteCountOnPage = homePage
                .openPage()
                .clickLoginButton()
                .enterEmail(accountProperties.getLogin())
                .enterPassword(accountProperties.getPassword())
                .clickLoginButton()
                .clickCreateNewProjectButton()
                .enterProjectName("Create test case " + random.nextInt(9999))
                .enterProjectCode("C" + random.nextInt(9999))
                .enterDescription("DESCRIPTION" + random.nextInt(9999))
                .setProjectAccessType(ProjectAccessType.PUBLIC)
                .clickCreateProjectButton()
                .clickCreateNewSuiteButton()
                .enterSuiteName("WAV")
                .enterDescription("Description")
                .enterPreconditions("Preconditions")
                .clickCreateSuiteButton()
                .getSuiteContainer()
                .clickDeleteSuiteButtonBySuiteName("WAV")
                .clickDeleteSuiteButton()
                .getSuiteContainer()
                .getSuiteCountOnPage("WAV");
        assertEquals(suiteCountOnPage, 0);
    }

    @Test
    public void deleteTwoCasesByClickingASuiteCheckox() {
        HomePage homePage = new HomePage(driver);
        AccountProperties accountProperties =
                new AccountPropertyReader("existing-user").getAccountsProperties();
        SuiteContainer suiteContainer = homePage
                .openPage()
                .clickLoginButton()
                .enterEmail(accountProperties.getLogin())
                .enterPassword(accountProperties.getPassword())
                .clickLoginButton()
                .clickCreateNewProjectButton()
                .enterProjectName("Create test case " + random.nextInt(9999))
                .enterProjectCode("C" + random.nextInt(9999))
                .enterDescription("DESCRIPTION" + random.nextInt(9999))
                .setProjectAccessType(ProjectAccessType.PRIVATE)
                .clickCreateProjectButton()
                .clickCreateNewCaseButton()
                .enterFieldByName(CreateCaseField.TITLE, "This is title")
                .enterFieldByName(CreateCaseField.DESCRIPTION, "This is description")
                .enterFieldByName(CreateCaseField.PRE_CONDITIONS, "This is pre-condition")
                .enterFieldByName(CreateCaseField.POST_CONDITIONS, "This is post-condition")
                .selectOptionByName(CreateCaseSelect.STATUS, CaseStatusOption.DRAFT)
                .selectOptionByName(CreateCaseSelect.SUITE, null) // for test testing
                .selectOptionByName(CreateCaseSelect.SEVERITY, CaseSeverityOption.MINOR)
                .selectOptionByName(CreateCaseSelect.PRIORITY, CasePriorityOption.LOW)
                .selectOptionByName(CreateCaseSelect.TYPE, CaseTypeOption.EXPLORATORY)
                .selectOptionByName(CreateCaseSelect.MILESTONE, null) // for test testing
                .selectOptionByName(CreateCaseSelect.BEHAVIOR, CaseBehaviorOption.POSITIVE)
                .selectOptionByName(CreateCaseSelect.AUTOMATION_STATUS, CaseAutomationStatusOption.NOT_AUTOMATED)
                .clickAddStepButtonForTimes(2)
                .enterActionIntoStepByNumber(1, "COWABUNGA")
                .enterInputDataIntoStepByNumber(1, "Input data!")
                .enterExpectedResultIntoStepByNumber(1, "Expecteddd")
                .enterActionIntoStepByNumber(2, "Second step action")
                .enterInputDataIntoStepByNumber(2, "Second step input data")
                .enterExpectedResultIntoStepByNumber(2, "Second expected result")
                .clickSaveCaseButton()
                .clickCreateNewCaseButton()
                .enterFieldByName(CreateCaseField.TITLE, "This is title")
                .enterFieldByName(CreateCaseField.DESCRIPTION, "This is description")
                .enterFieldByName(CreateCaseField.PRE_CONDITIONS, "This is pre-condition")
                .enterFieldByName(CreateCaseField.POST_CONDITIONS, "This is post-condition")
                .selectOptionByName(CreateCaseSelect.STATUS, CaseStatusOption.DRAFT)
                .selectOptionByName(CreateCaseSelect.SUITE, null) // for test testing
                .selectOptionByName(CreateCaseSelect.SEVERITY, CaseSeverityOption.MINOR)
                .selectOptionByName(CreateCaseSelect.PRIORITY, CasePriorityOption.LOW)
                .selectOptionByName(CreateCaseSelect.TYPE, CaseTypeOption.EXPLORATORY)
                .selectOptionByName(CreateCaseSelect.MILESTONE, null) // for test testing
                .selectOptionByName(CreateCaseSelect.BEHAVIOR, CaseBehaviorOption.POSITIVE)
                .selectOptionByName(CreateCaseSelect.AUTOMATION_STATUS, CaseAutomationStatusOption.NOT_AUTOMATED)
                .clickAddStepButtonForTimes(2)
                .enterActionIntoStepByNumber(1, "COWABUNGA")
                .enterInputDataIntoStepByNumber(1, "Input data!")
                .enterExpectedResultIntoStepByNumber(1, "Expecteddd")
                .enterActionIntoStepByNumber(2, "Second step action")
                .enterInputDataIntoStepByNumber(2, "Second step input data")
                .enterExpectedResultIntoStepByNumber(2, "Second expected result")
                .clickSaveCaseButton()
                .getSuiteContainer()
                .checkStandardSuiteCheckbox()
                .clickDeleteTestCasesButton()
                .enterTextIntoConfirmField("CONFIRM")
                .clickDeleteCasesButton()
                .getSuiteContainer();
        assertEquals(suiteContainer.getCaseCountWithoutSuiteOnPage("This is title"), 0);
    }

    @Test
    public void cloneSuiteTest() {
        HomePage homePage = new HomePage(driver);
        AccountProperties accountProperties =
                new AccountPropertyReader("existing-user").getAccountsProperties();
        int suiteCountOnPage = homePage
                .openPage()
                .clickLoginButton()
                .enterEmail(accountProperties.getLogin())
                .enterPassword(accountProperties.getPassword())
                .clickLoginButton()
                .clickCreateNewProjectButton()
                .enterProjectName("Clone suite " + random.nextInt(9999))
                .enterProjectCode("CS" + random.nextInt(9999))
                .enterDescription("Description!!! " + random.nextInt(9999))
                .setProjectAccessType(ProjectAccessType.PUBLIC)
                .clickCreateProjectButton()
                .clickCreateNewSuiteButton()
                .enterSuiteName("WAV")
                .enterDescription("Description")
                .enterPreconditions("Preconditions")
                .clickCreateSuiteButton()
                .getSuiteContainer()
                .clickCloneSuiteButtonBySuiteName("WAV")
                .clickCloneSuiteButton()
                .getSuiteContainer()
                .getSuiteCountOnPage("WAV");
        assertEquals(suiteCountOnPage, 2);
    }

    @Test
    public void openProjectPageViaUrl() {
        String projectCode = "CS" + random.nextInt(9999);
        HomePage homePage = new HomePage(driver);
        AccountProperties accountProperties =
                new AccountPropertyReader("existing-user").getAccountsProperties();
        ProjectPage projectPage = homePage
                .openPage()
                .clickLoginButton()
                .enterEmail(accountProperties.getLogin())
                .enterPassword(accountProperties.getPassword())
                .clickLoginButton()
                .clickCreateNewProjectButton()
                .enterProjectName("Clone suite " + random.nextInt(9999))
                .enterProjectCode(projectCode)
                .enterDescription("Description!!! " + random.nextInt(9999))
                .setProjectAccessType(ProjectAccessType.PUBLIC)
                .clickCreateProjectButton()
                .openPage(projectCode);
        assertNotNull(projectPage.isLoaded());
    }
}
