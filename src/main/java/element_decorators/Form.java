package element_decorators;

import helpers.PageLoadHelper;
import org.openqa.selenium.By;
import setups.PropertyDriver;

public class Form extends BaseElementDecorator<Form> {
    public static final String FORM_XPATH =
            "//label[text()='%s']//ancestor::*[contains(@class,'form-group')]//*[contains(@class,'ProseMirror')]";
    public final By formBy;

    protected Form(PropertyDriver driver, String label) {
        super(driver);
        formBy = By.xpath(String.format(FORM_XPATH, label));
    }

    @Override
    protected void initElement() {
        PageLoadHelper.waitForElementIsVisible(getDriver(), formBy);
        setElement(getDriver().findElement(formBy));
    }
}
