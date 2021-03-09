package element_decorators;

import helpers.PageLoadHelper;
import org.openqa.selenium.By;
import setups.PropertyDriver;

public class Form extends BaseElementDecorator<Form> {
    public static final String FORM_XPATH =
            "//label[text()='%s']//ancestor::*[contains(@class,'form-group')]//*[contains(@class,'ProseMirror')]";
    public final String labeledFormXpath;

    protected Form(PropertyDriver driver, String label) {
        super(driver);
        this.labeledFormXpath = String.format(FORM_XPATH, label);
    }

    @Override
    protected void initElement() {
        PageLoadHelper.waitForElementIsVisible(getDriver(), By.xpath(labeledFormXpath));
        setElement(findElementByXpath(labeledFormXpath));
    }
}
