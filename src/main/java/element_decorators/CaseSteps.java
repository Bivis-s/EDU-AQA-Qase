package element_decorators;

import helpers.PageLoadHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CaseSteps extends BaseElementDecorator<CaseSteps> {
    private static final String STEPS_XPATH = "//*[contains(@class,'suitecase-steps')]";
    private static final String STEP_XPATH = STEPS_XPATH + "/*";
    private static final String STEP_ACTION_INPUT_XPATH = ".//*[@id='actionGroup']//*[@class='ProseMirror']";
    private static final String STEP_INPUT_DATA_INPUT_XPATH = ".//*[@id='dataGroup']//*[@class='ProseMirror']";
    private static final String STEP_EXPECTED_RESULT_INPUT_XPATH = ".//*[@id='expected_resultGroup']//*[@class='ProseMirror']";

    public CaseSteps(WebDriver driver) {
        super(driver);
    }

    @Override
    protected void initElement() {
        PageLoadHelper.waitForElementIsVisible(getDriver(), By.xpath(STEPS_XPATH));
        setElement(findElementByXpath(STEPS_XPATH));
    }

    private List<WebElement> getStepList() {
        return findInnerElementsByXpath(getElement(), STEP_XPATH);
    }

    // counting starts form 1 | TODO add JavaDoc
    private void enterStepField(String fieldXpath, int stepNum, String text) {
        PageLoadHelper.waitForElementIsClickable(getDriver(), By.xpath(fieldXpath));
        sendKeys(findInnerElementByXpath(getStepList().get(stepNum - 1), fieldXpath), text);
    }

    public void enterAction(int stepNum, String action) {
        enterStepField(STEP_ACTION_INPUT_XPATH, stepNum, action);
    }

    public void enterInputData(int stepNum, String inputData) {
        enterStepField(STEP_INPUT_DATA_INPUT_XPATH, stepNum, inputData);
    }

    public void enterExpectedResult(int stepNum, String expectedResult) {
        enterStepField(STEP_EXPECTED_RESULT_INPUT_XPATH, stepNum, expectedResult);
    }
}
