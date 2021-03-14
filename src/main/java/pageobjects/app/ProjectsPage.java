package pageobjects.app;

import element_decorators.ProjectRow;
import enums.UrlPageName;
import helpers.PageLoadHelper;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import throwables.NoSuchElementError;

import java.util.ArrayList;
import java.util.List;

@Log4j2
public class ProjectsPage extends LoadableAppPage<ProjectsPage> {
    private static final String CREATE_NEW_PROJECT_BUTTON_ID = "createButton";
    private static final String PROJECT_ROW_XPATH = "//*[contains(@class,'project-row')]";
    private static final String SEARCH_PROJECT_FIELD_XPATH =
            "//*[contains(@class,'filters-block')]//*[contains(@name,'title')]";

    public ProjectsPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected void waitForPageLoaded() {
        PageLoadHelper.waitForElementIsClickable(getDriver(), By.id(CREATE_NEW_PROJECT_BUTTON_ID));
    }

    @Override
    protected String getPageUrl() {
        return getUrlFromProperty(UrlPageName.PROJECTS);
    }

    public ProjectsPage enterTextInSearchProjectField(String projectName) {
        return enterTextIntoSearchField(findElementByXpath(SEARCH_PROJECT_FIELD_XPATH), projectName);
    }

    public NewProjectPage clickCreateNewProjectButton() {
        click(findElementById(CREATE_NEW_PROJECT_BUTTON_ID));
        return new NewProjectPage(getDriver()).get();
    }

    public List<ProjectRow> getProjectRowList() {
        List<ProjectRow> projectRowList = new ArrayList<>();
        for (WebElement rowElement : findElementsByXpath(PROJECT_ROW_XPATH)) {
            projectRowList.add(new ProjectRow(getDriver(), rowElement));
        }
        return projectRowList;
    }

    public ProjectRow getProjectRowByName(String projectName) {
        for (ProjectRow projectRow : getProjectRowList()) {
            if (projectName.equals(projectRow.getProjectName())) {
                return projectRow;
            }
        }
        throw new NoSuchElementError("There is no project with name: '" + projectName + "'");
    }

    public List<String> createProjectNameList() {
        List<String> projectNameList = new ArrayList<>();
        for (ProjectRow projectRow : getProjectRowList()) {
            projectNameList.add(projectRow.getProjectName());
        }
        return projectNameList;
    }
}
