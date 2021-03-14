package element_decorators.modals;

import element_decorators.BaseElementDecorator;
import helpers.PageLoadHelper;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Log4j2
public abstract class BaseModal<T extends BaseModal<T>> extends BaseElementDecorator<T> {
    private static final String MODAL_XPATH = "//*[contains(text(),'%s')]//ancestor::*[contains(@class,'modal-case')]";

    public BaseModal(WebDriver driver) {
        super(driver);
    }

    @Override
    protected void initElement() {
        PageLoadHelper.waitForElementIsVisible(getDriver(), By.xpath(getModalXpath()));
        setElement(findElementByXpath(getModalXpath()));
    }

    protected String getModalXpath() {
        return String.format(MODAL_XPATH, getModalTitle());
    }

    protected abstract String getModalTitle();

    // After clicking on the close button, modal closes instantly,
    // but the page needs some more time to render updated page
    protected void waitAfterClosing() {
        int timeout = 2000;
        log.trace("Wait after modal closing for time '" + timeout + "millis");
        try {
            Thread.sleep(timeout);
        } catch (InterruptedException e) {
            log.error(e.getMessage());
        }
    }
}
