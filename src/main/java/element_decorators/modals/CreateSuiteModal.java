package element_decorators.modals;

import element_decorators.InputForm;
import element_decorators.SelectForm;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import pageobjects.app.ProjectPage;

@Log4j2
public class CreateSuiteModal extends BaseModal<CreateSuiteModal> {
    private static final String CREATE_SUITE_MODAL_TITLE = "Create suite";
    private static final String CREATE_SUITE_BUTTON_ID = "save-suite-button";
    private static final String SUITE_NAME_INOUT_LABEL = "Suite name";
    private static final String PARENT_SUITE_SELECT_LABEL = "Parent suite";
    private static final String DESCRIPTION_INPUT_LABEL = "Description";
    private static final String PRECONDITIONS_INPUT_LABEL = "Preconditions";

    public CreateSuiteModal(WebDriver driver) {
        super(driver);
    }

    @Override
    protected String getModalTitle() {
        return CREATE_SUITE_MODAL_TITLE;
    }

    public CreateSuiteModal enterSuiteName(String suiteName) {
        new InputForm(getDriver(), SUITE_NAME_INOUT_LABEL).sendKeys(suiteName);
        return this;
    }

    public CreateSuiteModal selectParentSuiteByName(String parentSuiteName) {
        new SelectForm(getDriver(), PARENT_SUITE_SELECT_LABEL).click().clickOptionByLabel(parentSuiteName);
        return this;
    }

    public CreateSuiteModal enterDescription(String description) {
        new InputForm(getDriver(), DESCRIPTION_INPUT_LABEL).sendKeys(description);
        return this;
    }

    public CreateSuiteModal enterPreconditions(String preconditions) {
        new InputForm(getDriver(), PRECONDITIONS_INPUT_LABEL).sendKeys(preconditions);
        return this;
    }

    public ProjectPage clickCreateSuiteButton() {
        click(findElementById(CREATE_SUITE_BUTTON_ID));
        try {
            Thread.sleep(750);
        } catch (InterruptedException ignored) {
        }
        return new ProjectPage(getDriver()).get();
    }
}
