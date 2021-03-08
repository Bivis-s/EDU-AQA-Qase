package helpers;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

@Log4j2
public class PageLoadHelper {
    private static final int TIMEOUT = 10; //TODO MOVE TO PROPERTIES FILE

    public static boolean waitForElementIsClickable(WebDriver driver, By by) {
        try {
            log.debug("Wait for element '" + by + "' to become clickable for '" + TIMEOUT + "' seconds");
            new WebDriverWait(driver, TIMEOUT).until(ExpectedConditions.elementToBeClickable(by));
            return true;
        } catch (WebDriverException e) {
            log.error("Element '" + by + " does not become clickable");
            throw new Error("Element is not clickable");
        }
    }

    public static boolean waitForElementIsVisible(WebDriver driver, By by) {
        try {
            log.debug("Wait for element '" + by + "' to become visible for '" + TIMEOUT + "' seconds");
            new WebDriverWait(driver, TIMEOUT).until(ExpectedConditions.elementToBeClickable(by));
            return true;
        } catch (WebDriverException e) {
            log.error("Element '" + by + " does not become visible");
            throw new Error("Element is not visible");
        }
    }

    public static void waitFor(WebDriver driver, ExpectedCondition<WebElement> expectedConditions) {
        new WebDriverWait(driver, TIMEOUT).until(expectedConditions);
    }
}
