package helpers;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import throwables.WaitForElementConditionError;

@Log4j2
public class PageLoadHelper {
    private static final int TIMEOUT = 10; //TODO MOVE TO PROPERTIES FILE

    public static void waitForElementIsClickable(WebDriver driver, By by) {
        try {
            log.debug("Wait for element '" + by + "' to become clickable for '" + TIMEOUT + "' seconds maximum");
            new WebDriverWait(driver, TIMEOUT).until(ExpectedConditions.elementToBeClickable(by));
        } catch (WebDriverException e) {
            throw new WaitForElementConditionError("Element '" + by + "' is not clickable");
        }
    }

    public static void waitForElementIsVisible(WebDriver driver, By by) {
        try {
            log.debug("Wait for element '" + by + "' to become visible for '" + TIMEOUT + "' seconds maximum");
            new WebDriverWait(driver, TIMEOUT)
                    .until(ExpectedConditions.not(ExpectedConditions.invisibilityOfElementLocated(by)));
        } catch (WebDriverException e) {
            throw new WaitForElementConditionError("Element '" + by + "' does not become visible");
        }
    }

    public static void waitForElementIsInvisible(WebDriver driver, By by) {
        try {
            log.debug("Wait for element '" + by + "' to become visible for '" + TIMEOUT + "' seconds maximum");
            new WebDriverWait(driver, TIMEOUT)
                    .until(ExpectedConditions.invisibilityOfElementLocated(by));
        } catch (WebDriverException e) {
            throw new WaitForElementConditionError("Element '" + by + "' does not become visible");
        }
    }

    public static void waitForCondition(WebDriver driver, ExpectedCondition<WebElement> expectedConditions) {
        new WebDriverWait(driver, TIMEOUT).until(expectedConditions);
    }
}
