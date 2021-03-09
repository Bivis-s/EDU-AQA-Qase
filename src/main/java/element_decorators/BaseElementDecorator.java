package element_decorators;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import setups.PropertyDriver;

import java.util.Arrays;

@Log4j2
public abstract class BaseElementDecorator<T extends BaseElementDecorator<T>> {
    private final WebDriver driver;
    private WebElement element;

    protected BaseElementDecorator(PropertyDriver driver) {
        this.driver = driver;
    }

    protected WebDriver getDriver() {
        return driver;
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

    protected abstract void initElement();

    protected void setElement(WebElement element) {
        this.element = element;
    }

    protected String getElementLocation(WebElement element) {
        return element.getLocation().toString();
    }

    @SuppressWarnings("unchecked")
    protected T click() {
        log.debug("Click element '" + getElementLocation(getElement()) + "'");
        getElement().click();
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    protected T sendKeys(String... keys) {
        log.debug("Send keys '" + Arrays.toString(keys) + "' to element '" + getElementLocation(getElement()) + "'");
        getElement().sendKeys(keys);
        return (T) this;
    }
}
