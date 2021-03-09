package helpers;

import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import setups.PropertyDriver;

import java.util.Arrays;

@Log4j2
public abstract class ElementsManipulator {
    @Getter
    private final WebDriver driver;

    public ElementsManipulator(PropertyDriver driver) {
        this.driver = driver;
    }

    protected void click(WebElement element) {
        log.debug("Click element '" + element.getTagName() + "'");
        element.click();
    }

    protected void sendKeys(WebElement element, String... keys) {
        log.debug("Send keys '" + Arrays.toString(keys) + "' to element '" + element.getTagName() + "'");
        element.sendKeys(keys);
    }

    protected String getText(WebElement element) {
        String elementText = element.getText();
        log.debug("Get element '" + element.getTagName() + "' text: '" + elementText + "'");
        return elementText;
    }

    protected boolean isElementDisplayed(WebElement element) {
        boolean isDisplayed = false;
        try {
            isDisplayed = element.isDisplayed();
        } catch (NoSuchElementException ignored) {
        }
        log.debug("Is element '" + element.getTagName() + "' displayed: " + isDisplayed);
        return isDisplayed;
    }

    protected boolean isElementDisplayed(WebElement element, int timeout) {
        boolean isDisplayed = false;
        try {
            PageLoadHelper.waitFor(getDriver(), ExpectedConditions.visibilityOf(element));
            isDisplayed = true;
        } catch (TimeoutException ignored) {
        }
        log.debug("Is element '" + element.getTagName() + "' displayed: " + isDisplayed);
        return isDisplayed;
    }

    protected WebElement findElementByXpath(String xpath) {
        return getDriver().findElement(By.xpath(xpath));
    }

    protected WebElement findElementById(String id) {
        return getDriver().findElement(By.id(id));
    }
}
