package steps.app;

import enums.ProjectAccessType;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import pageobjects.app.DeleteProjectPage;
import pageobjects.app.NewProjectPage;
import pageobjects.app.ProjectsPage;
import property_objects.ProjectProperties;
import utils.DateGenerator;
import utils.RandomStringGenerator;
import world.World;
import wrappers.PropertiesWrapper;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.not;
import static org.testng.Assert.assertNotNull;

@Log4j2
@RequiredArgsConstructor
public class ProjectsSteps {
    private final World world;
    private final PropertiesWrapper<ProjectProperties> projectPropertiesWrapper;
    private ProjectsPage projectsPage;
    private NewProjectPage newProjectPage;
    private DeleteProjectPage deleteProjectPage;

    @Before
    public void initProjectsPage() {
        projectsPage = new ProjectsPage(world.getDriver());
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
        newProjectPage = projectsPage.clickCreateNewProjectButton();
    }

    @And("Enter a valid project name")
    public void enterAValidProjectName() {
        newProjectPage.enterProjectName(DateGenerator.getCurrentFullDate() + " | " + world.getScenario().getName());
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

    @And("Enter a project name into search project field")
    public void enterAProjectNameIntoSearchProjectField() {
        projectsPage.enterTextInSearchProjectField(projectPropertiesWrapper.getProperties().getProjectName());
    }

    @When("Click the three-dotted button to the right of project name on the projects page")
    public void clickTheThreeDottedButtonToTheRightOfProjectNameOnTheProjectsPage() {
        projectsPage
                .getProjectRowByName(projectPropertiesWrapper.getProperties().getProjectName())
                .clickDropdownButton();
    }

    @And("Click the 'Delete' text in the drop-down under three-dotted button")
    public void clickTheDeleteTextInTheDropDownUnderThreeDottedButton() {
        deleteProjectPage = projectsPage
                .getProjectRowByName(projectPropertiesWrapper.getProperties().getProjectName())
                .clickDropdownDeleteButton();
    }

    @And("Click the 'Delete project' button on the delete project page")
    public void clickTheDeleteProjectButtonOnTheDeleteProjectPage() {
        deleteProjectPage.clickDeleteProjectButton();
    }

    @And("There is no such project on the projects page")
    public void thereIsNoSuchProjectOnTheProjectsPage() {
        String projectName = projectPropertiesWrapper.getProperties().getProjectName();
        assertThat(projectsPage.getProjectNamesList(), not(hasItem(projectName)));
    }
}
