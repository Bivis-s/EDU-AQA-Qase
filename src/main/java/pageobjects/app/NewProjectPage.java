package pageobjects.app;

import element_decorators.CheckContainer;
import enums.ProjectAccessType;
import helpers.PageLoadHelper;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import property_objects.ProjectProperties;
import throwables.PropertyError;

@Log4j2
public class NewProjectPage extends LoadableAppPage<NewProjectPage> {
    private static final String CREATE_PROJECT_BUTTON_XPATH =
            "//*[contains(@class,'form-create-project')]//*[contains(@class,'btn') and @type='submit']";
    //TODO Move to enum
    private static final String PROJECT_NAME_INPUT_LABEL = "Project name";
    private static final String PROJECT_CODE_INPUT_LABEL = "Project Code";
    private static final String DESCRIPTION_INPUT_LABEL = "Description";
    private static final String PROJECT_ACCESS_TYPE_CHECK_LABEL = "Project access type";
    private final ProjectProperties projectProperties;

    public NewProjectPage(WebDriver driver) {
        super(driver);
        projectProperties = new ProjectProperties();
    }

    @Override
    protected void waitForPageLoaded() {
        PageLoadHelper.waitForElementIsClickable(getDriver(), By.xpath(CREATE_PROJECT_BUTTON_XPATH));
    }

    @Override
    protected String getPageUrl() {
        throw new PropertyError("There is no static url for new project page");
    }

    public NewProjectPage enterProjectName(String projectName) {
        sendKeysToInputFormByLabel(PROJECT_NAME_INPUT_LABEL, projectName);
        projectProperties.setProjectName(projectName);
        return this;
    }

    public NewProjectPage enterProjectCode(String projectCode) {
        sendKeysToInputFormByLabel(PROJECT_CODE_INPUT_LABEL, projectCode);
        projectProperties.setProjectCode(projectCode);
        return this;
    }

    public NewProjectPage enterDescription(String description) {
        sendKeysToInputFormByLabel(DESCRIPTION_INPUT_LABEL, description);
        projectProperties.setDescription(description);
        return this;
    }

    public NewProjectPage setProjectAccessType(ProjectAccessType projectAccessType) {
        new CheckContainer(getDriver(), PROJECT_ACCESS_TYPE_CHECK_LABEL).checkByLabel(projectAccessType.getValue());
        projectProperties.setProjectAccessType(projectAccessType);
        return this;
    }

    public ProjectProperties getBuiltProjectProperties() {
        return projectProperties;
    }

    public ProjectPage clickCreateProjectButton() {
        click(findElementByXpath(CREATE_PROJECT_BUTTON_XPATH));
        return new ProjectPage(getDriver()).get();
    }
}
