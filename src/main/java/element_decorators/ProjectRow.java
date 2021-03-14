package element_decorators;

import helpers.PageLoadHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageobjects.app.DeleteProjectPage;
import pageobjects.app.ProjectPage;

public class ProjectRow extends BaseElementDecorator<ProjectRow> {
    private final WebElement rowElement;
    public final static String DROPDOWN_BUTTON_XPATH = "./*[contains(@class,'dropdown')]//*[contains(@class,'btn')]";
    public final static String DROPDOWN_ITEM_XPATH = "./following::*[contains(@class,'dropdown-item')]//*[contains(text(),'%s')]"; // //*[contains(@class,'project-row')]//*[contains(@class,'dropdown')]//*[contains(@class,'btn')]/following::*[contains(@class,'dropdown-item')]//*[contains(text(),'Delete')]
    public final static String PROJECT_TITLE_XPATH = "./*[contains(@class,'title')";
    public final static String DROPDOWN_DELETE_BUTTON_TEXT = "Delete";

    public ProjectRow(WebDriver driver, WebElement rowElement) {
        super(driver);
        this.rowElement = rowElement;
    }

    @Override
    protected void initElement() {
        setElement(rowElement);
    }

    public String getProjectName() {
        return getText(findInnerElementByXpath(getElement(), PROJECT_TITLE_XPATH));
    }

    public ProjectRow clickDropdownButton() {
        click(findInnerElementByXpath(getElement(), DROPDOWN_BUTTON_XPATH));
        return this;
    }

    public DeleteProjectPage clickDropdownDeleteButton() {
        String dropdownDeleteButtonXpath = String.format(DROPDOWN_ITEM_XPATH, DROPDOWN_DELETE_BUTTON_TEXT);
        PageLoadHelper.waitForElementIsVisible(getDriver(), By.xpath(dropdownDeleteButtonXpath));
        click(findElementByXpath(dropdownDeleteButtonXpath));
        return new DeleteProjectPage(getDriver()).get();
    }

    public ProjectPage openProjectByClickingTitle() {
        click(findInnerElementByXpath(getElement(), PROJECT_TITLE_XPATH));
        return new ProjectPage(getDriver()).get();
    }
}
