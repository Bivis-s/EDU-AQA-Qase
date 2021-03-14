package helpers;

import element_decorators.InputForm;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

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

    protected boolean isElementOnPage(By by) {
        boolean isOnPage = false;
        try {
            findElement(by);
            isOnPage = true;
        } catch (NoSuchElementException ignored) {
        } catch (Throwable t) {
            log.error(t.getMessage());
            throw t;
        }
        log.debug("Is element '" + by + "' on page: " + isOnPage);
        return isOnPage;
    }

    protected boolean isElementBecomeVisible(WebElement element) {
        boolean isDisplayed = false;
        try {
            PageLoadHelper.waitForCondition(getDriver(), ExpectedConditions.visibilityOf(element));
            isDisplayed = true;
        } catch (TimeoutException ignored) {
        } catch (Throwable t) {
            log.error(t.getMessage());
            throw t;
        }

        log.debug("Is element '" + element.getTagName() + "' displayed: " + isDisplayed);
        return isDisplayed;
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
            return element.findElement(By.xpath(xpath));
        } catch (Throwable t) {
            log.error(t.getMessage());
            throw t;
        }
    }

    protected List<WebElement> findInnerElementsByXpath(WebElement element, String xpath) {
        try {
            return element.findElements(By.xpath(xpath));
        } catch (Throwable t) {
            log.error(t.getMessage());
            throw t;
        }
    }

    protected WebElement scrollToElement(WebElement element) {
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
        return element;
    }

    protected WebElement addClassToElement(WebElement element, String className) {
        ((JavascriptExecutor) getDriver()).executeScript(String.format("arguments[0].classList.add('%s');", className), element);
        return element;
    }
}
