package element_decorators;

import helpers.PageLoadHelper;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Log4j2
public class SelectForm extends BaseElementDecorator<SelectForm> {
    private static final String SELECT_XPATH = "//label[contains(text(),'%s')]/following::*[contains(@class,'control')]";
    private static final String OPTION_XPATH = "//*[contains(@class,'menu')]//*[text()='%s']";
    private final String labeledSelectXpath;

    public SelectForm(WebDriver driver, String label) {
        super(driver);
        labeledSelectXpath = String.format(SELECT_XPATH, label);
    }

    @Override
    protected void initElement() {
        PageLoadHelper.waitForElementIsClickable(getDriver(), By.xpath(labeledSelectXpath));
        setElement(findElementByXpath(labeledSelectXpath));
    }

    public SelectForm click() {
        click(getElement());
        return this;
    }

    public void clickOptionByLabel(String optionLabel) {
        String labeledOptionXpath = String.format(OPTION_XPATH, optionLabel);
        PageLoadHelper.waitForElementIsVisible(getDriver(), By.xpath(labeledOptionXpath));
        click(findInnerElementByXpath(getElement(), labeledOptionXpath));
    }
}
