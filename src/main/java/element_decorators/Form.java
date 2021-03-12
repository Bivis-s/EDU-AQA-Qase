package element_decorators;

import helpers.PageLoadHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public abstract class Form extends BaseElementDecorator<Form> {
    private static final String FORM_XPATH = "//label[contains(text(),'%s')]/following::*[(contains(@class,'form-control')) and not(contains(@class,'hide')) or (@class='ProseMirror')]";
    private final String labeledFormXpath;

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
