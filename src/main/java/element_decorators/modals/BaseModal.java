package element_decorators.modals;

import element_decorators.BaseElementDecorator;
import helpers.PageLoadHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

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
}
