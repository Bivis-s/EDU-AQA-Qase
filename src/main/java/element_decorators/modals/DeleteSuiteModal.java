package element_decorators.modals;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import pageobjects.app.ProjectPage;

@Log4j2
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
        log.info("Click delete suite button");
        click(findElementByXpath(DELETE_BUTTON_XPATH));
        waitAfterClosing();
        return new ProjectPage(getDriver()).get();
    }
}
