package steps.api;

import api.objects.CreateNewProjectRequest;
import api.objects.CreateNewSuiteRequest;
import enums.api.CreateNewProjectAccess;
import io.cucumber.java.en.And;
import lombok.RequiredArgsConstructor;
import property_objects.ProjectProperties;
import property_objects.SuiteProperties;
import property_objects.wrappers.ProjectPropertiesWrapper;
import property_objects.wrappers.SuitePropertiesWrapper;
import utils.DateGenerator;
import utils.RandomStringGenerator;
import world_context.WorldContext;

@RequiredArgsConstructor
public class ApiSteps {
    private final WorldContext worldContext;
    private final ProjectPropertiesWrapper projectPropertiesWrapper;
    private final SuitePropertiesWrapper suitePropertiesWrapper;

    @And("A private project is created via api")
    public void aPrivateProjectIsCreatedViaApi() {
        CreateNewProjectRequest createNewProjectRequest = CreateNewProjectRequest.builder()
                .title(DateGenerator.getCurrentFullDate() + " | " + worldContext.getScenario().getName())
                .code(RandomStringGenerator.createRandomLatinUppercaseString(6))
                .description(RandomStringGenerator.createRandomLatinSentence(10))
                .access(CreateNewProjectAccess.NONE)
                .build();
        ProjectProperties projectProperties = new ProjectProperties();
        projectProperties.setByApiRequest(createNewProjectRequest);
        projectPropertiesWrapper.setProjectProperties(projectProperties);
        worldContext.getApiAdapter().createNewProject(createNewProjectRequest);
    }

    @And("A suite is created in the project via api")
    public void aSuiteIsCreatedInTheProjectViaGui() {
        CreateNewSuiteRequest createNewSuiteRequest = CreateNewSuiteRequest.builder()
                .title(RandomStringGenerator.createCurrentDateAndWords(2))
                .description(RandomStringGenerator.createRandomLatinSentence(5))
                .preconditions(RandomStringGenerator.createRandomLatinSentence(5))
                .build();
        SuiteProperties suiteProperties = new SuiteProperties();
        suiteProperties.setByApiRequest(createNewSuiteRequest);
        suitePropertiesWrapper.setSuiteProperties(suiteProperties);
        worldContext.getApiAdapter().createNewSuite(projectPropertiesWrapper.getProjectCode(), createNewSuiteRequest);
    }
}
