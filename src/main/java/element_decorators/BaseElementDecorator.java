package element_decorators;

import helpers.ElementsManipulator;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import throwables.ElementIsNullError;

@Log4j2
public abstract class BaseElementDecorator<T extends BaseElementDecorator<T>> extends ElementsManipulator {
    private WebElement element;

    public BaseElementDecorator(WebDriver driver) {
        super(driver);
    }

    protected WebElement getElement() {
        if (element == null) {
            initElement();
            if (element == null) {
                throw new ElementIsNullError("'" + getClass().getSimpleName() + "' has not been initialized");
            }
        }
        return element;
    }

    protected void setElement(WebElement element) {
        this.element = element;
    }

    protected abstract void initElement();
}
