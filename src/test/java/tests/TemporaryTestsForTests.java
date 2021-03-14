package tests;

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

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        driver = new PropertyDriver();
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
        assertEquals(urlProperties.getUrl(), "https://qase.io/projects");
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
        projectProperties.setProjectName("Hohohoh");
        projectProperties.setProjectCode("HOH");
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
        Random random = new Random();
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
        log.info(createTestCasePage.getBuiltCaseProperties());
        ProjectPage projectPage = createTestCasePage.clickSaveCaseButton();
        assertNotNull(projectPage.isLoaded());
    }
}
