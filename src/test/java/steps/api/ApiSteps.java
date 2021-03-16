package steps.api;

import api.objects.CreateNewProjectRequest;
import enums.api.CreateNewProjectAccess;
import io.cucumber.java.en.And;
import lombok.RequiredArgsConstructor;
import property_objects.ProjectProperties;
import utils.DateGenerator;
import utils.RandomStringGenerator;
import world.World;
import wrappers.PropertiesWrapper;

@RequiredArgsConstructor
public class ApiSteps {
    private final World world;
    private final PropertiesWrapper<ProjectProperties> projectPropertiesWrapper;

    @And("A private project is created via api")
    public void aPrivateProjectIsCreatedViaApi() {
        CreateNewProjectRequest createNewProjectRequest = CreateNewProjectRequest.builder()
                .title(DateGenerator.getCurrentFullDate() + " | " + world.getScenario().getName())
                .code(RandomStringGenerator.createRandomLatinUppercaseString(6))
                .description(RandomStringGenerator.createRandomLatinSentence(10))
                .access(CreateNewProjectAccess.NONE)
                .build();
        ProjectProperties projectProperties = new ProjectProperties();
        projectProperties.setByApiRequest(createNewProjectRequest);
        projectPropertiesWrapper.setProperties(projectProperties);
        world.getApiAdapter().createNewProject(createNewProjectRequest);
    }
}
