package element_decorators.modals;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import pageobjects.app.ProjectPage;

@Log4j2
public class DeleteTestCasesModal extends BaseModal<CreateSuiteModal> {
    private static final String CREATE_SUITE_MODAL_TITLE = "Delete test cases'";
    private static final String CONFIRM_FIELD_XPATH = ".//*[contains(@name,'confirm')]";
    private static final String DELETE_BUTTON_XPATH =
            ".//*[contains(@class,'modal-footer')]//*[contains(text(),'Delete')]";

    public DeleteTestCasesModal(WebDriver driver) {
        super(driver);
    }

    @Override
    protected String getModalTitle() {
        return CREATE_SUITE_MODAL_TITLE;
    }

    public DeleteTestCasesModal enterTextIntoConfirmField(String text) {
        sendKeys(clear(findElementByXpath(CONFIRM_FIELD_XPATH)), text);
        return this;
    }

    public ProjectPage clickDeleteButton() {
        click(findElementByXpath(DELETE_BUTTON_XPATH));
        // After clicking on the delete button, modal closes instantly,
        // but project page needs some more time to render updated test case list
        try {
            Thread.sleep(750);
        } catch (InterruptedException e) {
            log.error(e.getMessage());
        }
        return new ProjectPage(getDriver()).get();
    }
}