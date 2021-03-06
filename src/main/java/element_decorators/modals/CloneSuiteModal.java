package element_decorators.modals;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import pageobjects.app.ProjectPage;

@Log4j2
public class CloneSuiteModal extends BaseModal<CloneSuiteModal> {
    private static final String CLONE_SUITE_MODAL_TITLE = "Clone suite";
    private static final String CLONE_BUTTON_XPATH =
            ".//*[contains(@class,'modal-footer')]//*[contains(text(),'Clone')]";

    public CloneSuiteModal(WebDriver driver) {
        super(driver);
    }

    @Override
    protected String getModalTitle() {
        return CLONE_SUITE_MODAL_TITLE;
    }

    public ProjectPage clickCloneSuiteButton() {
        log.info("Click clone suite button");
        click(findElementByXpath(CLONE_BUTTON_XPATH));
        waitAfterClosing();
        return new ProjectPage(getDriver()).get();
    }
}
