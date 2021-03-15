package helpers;

import element_decorators.InputForm;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Arrays;
import java.util.List;

@Log4j2
public abstract class ElementsManipulator {
    @Getter
    private final WebDriver driver;

    public ElementsManipulator(WebDriver driver) {
        this.driver = driver;
    }

    protected void click(WebElement element) {
        log.debug("Click element '" + element.getTagName() + "'");
        try {
            element.click();
        } catch (Throwable t) {
            log.error(t.getMessage());
            throw t;
        }
    }

    protected WebElement clear(WebElement element) {
        log.debug("Clear element: '" + element.getTagName() + "'");
        try {
            element.clear();
        } catch (Throwable t) {
            log.error(t.getMessage());
            throw t;
        }
        return element;
    }

    protected void sendKeys(WebElement element, CharSequence... keys) {
        log.debug("Send keys '" + Arrays.toString(keys) + "' to element '" + element.getTagName() + "'");
        try {
            element.sendKeys(keys);
        } catch (Throwable t) {
            log.error(t.getMessage());
            throw t;
        }
    }

    protected void sendKeysToInputFormByLabel(String inputFormLabel, CharSequence... value) {
        try {
            new InputForm(getDriver(), inputFormLabel).clear().sendKeys(value);
        } catch (Throwable t) {
            log.error(t.getMessage());
            throw t;
        }
    }

    protected String getText(WebElement element) {
        String elementText;
        try {
            elementText = element.getText();
        } catch (Throwable t) {
            log.error(t.getMessage());
            throw t;
        }
        log.debug("Get element '" + element.getTagName() + "' text: '" + elementText + "'");
        return elementText;
    }

    protected WebElement findElement(By by) {
        try {
            return getDriver().findElement(by);
        } catch (Throwable t) {
            log.error(t.getMessage());
            throw t;
        }
    }

    protected WebElement findElementByXpath(String xpath) {
        try {
            return findElement(By.xpath(xpath));
        } catch (Throwable t) {
            log.error(t.getMessage());
            throw t;
        }
    }

    protected WebElement findElementById(String id) {
        try {
            return findElement(By.id(id));
        } catch (Throwable t) {
            log.error(t.getMessage());
            throw t;
        }
    }

    protected List<WebElement> findElementsByXpath(String xpath) {
        try {
            return getDriver().findElements(By.xpath(xpath));
        } catch (Throwable t) {
            log.error(t.getMessage());
            throw t;
        }
    }

    protected WebElement findInnerElementByXpath(WebElement element, String xpath) {
        try {
            log.debug("Find inner element in '" + element.getTagName() + "' by xpath '" + xpath + "'");
            return element.findElement(By.xpath(xpath));
        } catch (Throwable t) {
            log.error(t.getMessage());
            throw t;
        }
    }

    protected List<WebElement> findInnerElementsByXpath(WebElement element, String xpath) {
        try {
            log.debug("Find inner elementS in '" + element.getTagName() + "' by xpath '" + xpath + "'");
            return element.findElements(By.xpath(xpath));
        } catch (Throwable t) {
            log.error(t.getMessage());
            throw t;
        }
    }

    protected void executeJavaScript(String script) {
        log.debug("Execute script '" + script + "'");
        ((JavascriptExecutor) getDriver()).executeScript(script);
    }

    protected WebElement executeJavaScriptToElement(String script, WebElement element) {
        log.debug("Execute script '" + script + "' to element '" + element.getTagName() + "'");
        ((JavascriptExecutor) getDriver()).executeScript(script, element);
        return element;
    }

    protected WebElement scrollToElement(WebElement element) {
        executeJavaScriptToElement("arguments[0].scrollIntoView(false);", element);
        // Sometimes browser has no time to handle scrolling
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            log.error(e.getMessage());
        }
        return element;
    }

    protected void addClassToElement(WebElement element, String className) {
        log.debug("Add class '" + className + "' to element '" + element.getTagName() + "'");
        executeJavaScriptToElement(String.format("arguments[0].classList.add('%s');", className), element);
    }
}
