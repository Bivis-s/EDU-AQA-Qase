package element_decorators.modals;

import org.openqa.selenium.WebDriver;
import pageobjects.app.ProjectPage;

public class DeleteSuiteModal extends BaseModal<DeleteSuiteModal> {
    private static final String DELETE_SUITE_MODAL_TITLE = "Delete Suite";
    private static final String DELETE_BUTTON_XPATH =
            ".//*[contains(@class,'modal-footer')]//*[contains(text(),'Delete suite')]";

    public DeleteSuiteModal(WebDriver driver) {
        super(driver);
    }

    @Override
    protected String getModalTitle() {
        return DELETE_SUITE_MODAL_TITLE;
    }

    public ProjectPage clickDeleteSuiteButton() {
        click(findElementByXpath(DELETE_BUTTON_XPATH));
        waitAfterClosing();
        return new ProjectPage(getDriver()).get();
    }
}
