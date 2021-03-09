package element_decorators;

import helpers.ElementsManipulator;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebElement;
import setups.PropertyDriver;

@Log4j2
public abstract class BaseElementDecorator<T extends BaseElementDecorator<T>> extends ElementsManipulator {
    private WebElement element;

    protected BaseElementDecorator(PropertyDriver driver) {
        super(driver);
    }

    public WebElement getElement() {
        if (element == null) {
            initElement();
            if (element == null) {
                throw new Error("'" + getClass().getSimpleName() + "' has not been initialized");
            }
        }
        return element;
    }

    protected void setElement(WebElement element) {
        this.element = element;
    }

    protected abstract void initElement();

    protected void click() {
        super.click(getElement());
    }

    protected void sendKeys(String... keys) {
        super.sendKeys(getElement(), keys);
    }

    protected String getText() {
        return super.getText(getElement());
    }

    protected boolean isElementDisplayed() {
        return super.isElementDisplayed(getElement());
    }

    protected boolean isElementDisplayed(int timeout) {
        return super.isElementDisplayed(getElement(), timeout);
    }
}
