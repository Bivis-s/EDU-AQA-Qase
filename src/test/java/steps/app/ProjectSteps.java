package steps.app;

import enums.create_case.CreateCaseField;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import pageobjects.app.ProjectPage;
import property_objects.wrappers.CasePropertiesWrapper;
import property_objects.wrappers.ProjectPropertiesWrapper;
import world.World;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.not;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

@Log4j2
@RequiredArgsConstructor
public class ProjectSteps {
    private final World world;
    private final ProjectPropertiesWrapper projectPropertiesWrapper;
    private final CasePropertiesWrapper casePropertiesWrapper;
    private ProjectPage projectPage;

    @Before
    public void initPages() {
        projectPage = new ProjectPage(world.getDriver());
    }

    @Then("The project is opened")
    public void theProjectIsOpened() {
        String projectCode = projectPropertiesWrapper.getProjectProperties().getProjectCode();
        assertNotNull(projectPage.openPage(projectCode));
    }

    @When("Click the `Create new case` button")
    public void clickTheCreateNewCaseButton() {
        projectPage.clickCreateNewCaseButton();
    }

    @And("Enter a case name into search case field")
    public void enterACaseNameIntoSearchCaseField() {
        String caseName = casePropertiesWrapper.getCaseProperties().getTextFieldMap().get(CreateCaseField.TITLE);
        projectPage.enterTextInSearchCaseField(caseName);
    }

    @Then("There is/are {int} (the )case(s) without suite on the project page")
    public void thereIsTheCaseOnProjectPage(int countOfCases) {
        String caseName = casePropertiesWrapper.getCaseProperties().getTextFieldMap().get(CreateCaseField.TITLE);
        assertEquals(projectPage.getSuiteContainer().getCaseCountWithoutSuiteOnPage(caseName), countOfCases);
    }
}
