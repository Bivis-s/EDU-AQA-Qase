package steps.app;

import io.cucumber.java.Before;
import io.cucumber.java.en.Then;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import pageobjects.app.ProjectPage;
import property_objects.ProjectProperties;
import world.World;
import wrappers.PropertiesWrapper;

import static org.testng.Assert.assertNotNull;

@Log4j2
@RequiredArgsConstructor
public class ProjectSteps {
    private final World world;
    private final PropertiesWrapper<ProjectProperties> projectPropertiesWrapper;
    private ProjectPage projectPage;

    @Before
    public void initPages() {
        projectPage = new ProjectPage(world.getDriver());
    }

    @Then("The project is opened")
    public void theProjectIsOpened() {
        String projectCode = projectPropertiesWrapper.getProperties().getProjectCode();
        assertNotNull(projectPage.openPage(projectCode));
    }
}
