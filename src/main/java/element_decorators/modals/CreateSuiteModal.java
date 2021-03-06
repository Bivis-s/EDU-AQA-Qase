package element_decorators.modals;

import element_decorators.InputForm;
import element_decorators.SelectForm;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import pageobjects.app.ProjectPage;
import property_objects.SuiteProperties;

@Log4j2
public class CreateSuiteModal extends BaseModal<CreateSuiteModal> {
    private static final String CREATE_SUITE_MODAL_TITLE = "Create suite";
    private static final String CREATE_SUITE_BUTTON_ID = "save-suite-button";
    private static final String SUITE_NAME_INOUT_LABEL = "Suite name";
    private static final String PARENT_SUITE_SELECT_LABEL = "Parent suite";
    private static final String DESCRIPTION_INPUT_LABEL = "Description";
    private static final String PRECONDITIONS_INPUT_LABEL = "Preconditions";
    private final SuiteProperties suiteProperties;

    public CreateSuiteModal(WebDriver driver) {
        super(driver);
        suiteProperties = new SuiteProperties();
    }

    @Override
    protected String getModalTitle() {
        return CREATE_SUITE_MODAL_TITLE;
    }

    public CreateSuiteModal enterSuiteName(String suiteName) {
        log.info("Enter suite name '" + suiteName + "'");
        new InputForm(getDriver(), SUITE_NAME_INOUT_LABEL).sendKeys(suiteName);
        suiteProperties.setName(suiteName);
        return this;
    }

    public CreateSuiteModal selectParentSuiteByName(String parentSuiteName) {
        log.info("Select suite parent suite '" + parentSuiteName + "'");
        new SelectForm(getDriver(), PARENT_SUITE_SELECT_LABEL).click().clickOptionByLabel(parentSuiteName);
        suiteProperties.setParentSuiteName(parentSuiteName);
        return this;
    }

    public CreateSuiteModal enterDescription(String description) {
        log.info("Enter suite description '" + description + "'");
        new InputForm(getDriver(), DESCRIPTION_INPUT_LABEL).sendKeys(description);
        suiteProperties.setDescription(description);
        return this;
    }

    public CreateSuiteModal enterPreconditions(String preconditions) {
        log.info("Enter suite preconditions '" + preconditions + "'");
        new InputForm(getDriver(), PRECONDITIONS_INPUT_LABEL).sendKeys(preconditions);
        suiteProperties.setPrecondition(preconditions);
        return this;
    }

    public SuiteProperties getBuiltSuiteProperties() {
        return suiteProperties;
    }

    public ProjectPage clickCreateSuiteButton() {
        log.info("Click create suite button");
        click(findElementById(CREATE_SUITE_BUTTON_ID));
        waitAfterClosing();
        return new ProjectPage(getDriver()).get();
    }
}
