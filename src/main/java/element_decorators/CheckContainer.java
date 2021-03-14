package element_decorators;

import helpers.PageLoadHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckContainer extends BaseElementDecorator<CheckContainer> {
    private static final String CHECK_CONTAINER_XPATH = "//label[contains(text(),'%s')]/..";
    private static final String CHECK_INPUT_XPATH = ".//following::*[contains(@class,'form-check')]//label[contains(text(),'%s')]//preceding-sibling::input";
    private final String labeledCheckContainerXpath;

    public CheckContainer(WebDriver driver, String container) {
        super(driver);
        labeledCheckContainerXpath = String.format(CHECK_CONTAINER_XPATH, container);
    }

    @Override
    protected void initElement() {
        PageLoadHelper.waitForElementIsVisible(getDriver(), By.xpath(labeledCheckContainerXpath));
        setElement(findElementByXpath(labeledCheckContainerXpath));
    }

    public void checkByLabel(String label) {
        click(findInnerElementByXpath(getElement(), String.format(CHECK_INPUT_XPATH, label)));
    }
}
