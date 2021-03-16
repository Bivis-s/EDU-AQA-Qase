package steps.app;

import enums.ProjectAccessType;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import pageobjects.app.NewProjectPage;
import pageobjects.app.ProjectsPage;
import property_objects.ProjectProperties;
import utils.DataGenerator;
import utils.RandomStringGenerator;
import world.World;
import wrappers.PropertiesWrapper;

import static org.testng.Assert.assertNotNull;

@Log4j2
@RequiredArgsConstructor
public class ProjectsSteps {
    private final World world;
    private final PropertiesWrapper<ProjectProperties> projectPropertiesWrapper;
    private ProjectsPage projectsPage;
    private NewProjectPage newProjectPage;

    @Before
    public void initPages() {
        WebDriver driver = world.getDriver();
        projectsPage = new ProjectsPage(driver);
        newProjectPage = new NewProjectPage(driver);
    }

    @Then("The projects page is opened")
    public void theProjectsPageIsOpened() {
        assertNotNull(projectsPage.isLoaded());
    }

    @When("Open the projects page")
    public void openTheProjectsPage() {
        projectsPage.openPageByUrl();
    }

    @And("Click the user's avatar in the upper right corner")
    public void clickTheUserAvatarInTheUpperRightCorner() {
        projectsPage.getUserMenu().clickUserAvatar();
    }

    @And("Click the 'Sign out' text")
    public void clickTheSignOutText() {
        projectsPage.getUserMenu().clickSignOutButton();
    }

    @When("Click the 'Create new project' button in the upper left corner")
    public void clickTheCreateNewProjectButtonInTheUpperLeftCorner() {
        projectsPage.clickCreateNewProjectButton();
    }

    @And("Enter a valid project name")
    public void enterAValidProjectName() {
        newProjectPage.enterProjectName(DataGenerator.getCurrentFullData() + " | " + world.getScenario().getName());
    }

    @And("Enter a valid project code")
    public void enterAValidProjectCode() {
        newProjectPage.enterProjectCode(RandomStringGenerator.createRandomLatinUppercaseString(6));
    }

    @And("Enter a valid description")
    public void enterAValidDescription() {
        newProjectPage.enterDescription(RandomStringGenerator.createRandomLatinSentence(10));
    }

    @And("Set the project access type 'private'")
    public void setTheProjectAccessTypePrivate() {
        newProjectPage.setProjectAccessType(ProjectAccessType.PRIVATE);
    }

    @And("Click the 'Create project' button")
    public void clickTheCreateProjectButton() {
        projectPropertiesWrapper.setProperties(newProjectPage.getBuiltProjectProperties());
        newProjectPage.clickCreateProjectButton();
    }
}
