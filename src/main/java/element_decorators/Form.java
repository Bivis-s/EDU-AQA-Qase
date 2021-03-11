package element_decorators;

import helpers.PageLoadHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public abstract class Form extends BaseElementDecorator<Form> {
    public static final String FORM_XPATH = "//label[text()='%s']//following-sibling::*";
    public final String labeledFormXpath;

    public Form(WebDriver driver, String label) {
        super(driver);
        this.labeledFormXpath = String.format(FORM_XPATH, label);
    }

    @Override
    protected void initElement() {
        PageLoadHelper.waitForElementIsVisible(getDriver(), By.xpath(labeledFormXpath));
        setElement(findElementByXpath(labeledFormXpath));
    }
}
