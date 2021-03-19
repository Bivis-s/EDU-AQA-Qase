package element_decorators;

import element_decorators.modals.CloneSuiteModal;
import element_decorators.modals.DeleteSuiteModal;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import pageobjects.app.ProjectPage;

@Log4j2
public class SuiteContainer extends BaseElementDecorator<SuiteContainer> {
    private static final String SUITE_CONTAINER_XPATH = "//*[@id='suitecases-container']";
    private static final String SUITE_XPATH = SUITE_CONTAINER_XPATH +
            "//*[contains(@class,'suite-header') and contains(text(),'%s')]//ancestor::*[contains(@id,'suite-id')]";
    private static final String SUITE_CHECKBOX_XPATH = SUITE_XPATH +
            "//*[contains(@class,'suite-checkbox')]//input/following::*";
    private static final String SUITE_HEADER_XPATH = SUITE_XPATH + "//*[contains(@class,'suite-header')]";
    private static final String SUITE_DELETE_BUTTON_XPATH = SUITE_HEADER_XPATH +
            "//*[contains(@class,'trash')]//ancestor::*[contains(@class,'suite-control')]";
    private static final String SUITE_CLONE_BUTTON_XPATH = SUITE_HEADER_XPATH +
            "//*[contains(@class,'copy')]//ancestor::*[contains(@class,'suite-control')]";
    private static final String SUITE_CASE_TITLE_XPATH =
            SUITE_XPATH + "//*[contains(@class,'case-row-title')]";
    private static final String SUITE_CASE_TITLE_BY_CASE_NAME_XPATH =
            SUITE_XPATH + "//*[contains(@class,'case-row-title') and contains(text(),'%s')]";
    private static final String SUIT_CASE_ROW_XPATH = SUITE_CASE_TITLE_BY_CASE_NAME_XPATH +
            "//ancestor::*[contains(@class,'case d-flex')]";
    private static final String SUIT_CASE_CHECKBOX_XPATH =
            SUIT_CASE_ROW_XPATH + "//input/following::*";
    private static final String SUIT_CASE_CHECKBOX_CONTAINER_XPATH =
            SUIT_CASE_ROW_XPATH + "//*[contains(@class,'case-drag')]";
    private static final String TEST_CASES_WITHOUT_SUITE_TITLE = "Test cases without suite";

    public SuiteContainer(WebDriver driver) {
        super(driver);
    }

    @Override
    protected void initElement() {
        setElement(findElementByXpath(SUITE_CONTAINER_XPATH));
    }

    public ProjectPage checkSuiteCheckboxByName(String suiteName) {
        log.info("Check suite checkbox '" + suiteName + "'");
        click(findElementByXpath(String.format(SUITE_CHECKBOX_XPATH, suiteName)));
        return new ProjectPage(getDriver()).get();
    }

    // check all cases without suite
    public ProjectPage checkCasesWithoutSuiteCheckbox() {
        log.info("Check standard suite checkbox");
        click(findElementByXpath(String.format(SUITE_CHECKBOX_XPATH, TEST_CASES_WITHOUT_SUITE_TITLE)));
        return new ProjectPage(getDriver()).get();
    }

    public ProjectPage checkCaseCheckboxByName(String suiteName, String caseName) {
        log.info("Check case '" + caseName + "' checkbox in suite '" + suiteName + "'");
        // adding class 'visible' to checkbox container to make checkbox visible (and clickable)
        addClassToElement(findElementByXpath(String.format(SUIT_CASE_CHECKBOX_CONTAINER_XPATH,
                TEST_CASES_WITHOUT_SUITE_TITLE, caseName)), "visible");
        click(findElementByXpath(String.format(SUIT_CASE_CHECKBOX_XPATH, suiteName, caseName)));
        return new ProjectPage(getDriver()).get();
    }

    public ProjectPage checkCaseWithoutCheckboxByName(String caseName) {
        return checkCaseCheckboxByName(TEST_CASES_WITHOUT_SUITE_TITLE, caseName);
    }

    public ProjectPage clickCaseByName(String suiteName, String caseName) {
        log.info("Click case '" + caseName + "' in suite '" + suiteName + "'");
        click(findElementByXpath(String.format(SUITE_CASE_TITLE_BY_CASE_NAME_XPATH, suiteName, caseName)));
        return new ProjectPage(getDriver()).get();
    }

    public ProjectPage clickCaseByName(String caseName) {
        return clickCaseByName(TEST_CASES_WITHOUT_SUITE_TITLE, caseName);
    }

    public int getSuiteCountOnPage(String suiteName) {
        int suiteCount = findElementsByXpath(String.format(SUITE_XPATH, suiteName)).size();
        log.info("Get suite '" + suiteName + "' count on page: " + suiteCount);
        return suiteCount;
    }

    public int getCaseCountInSuiteBySuiteName(String suiteName) {
        return findElementsByXpath(String.format(SUITE_CASE_TITLE_XPATH, suiteName)).size();
    }

    public int getCaseWithoutSuiteCount() {
        return findElementsByXpath(String.format(SUITE_CASE_TITLE_XPATH, TEST_CASES_WITHOUT_SUITE_TITLE)).size();
    }

    public int getCaseCountOnPage(String suiteName, String caseName) {
        int caseCount = findElementsByXpath(String.format(SUITE_CASE_TITLE_BY_CASE_NAME_XPATH, suiteName, caseName)).size();
        log.info("Get case '" + caseName + "' count in '" + suiteName + "' suite on page: " + caseCount);
        return caseCount;
    }

    public int getCaseCountWithoutSuiteOnPageByCaseName(String caseName) {
        return getCaseCountOnPage(TEST_CASES_WITHOUT_SUITE_TITLE, caseName);
    }

    public DeleteSuiteModal clickDeleteSuiteButtonBySuiteName(String suiteName) {
        log.info("Click delete button in suite: '" + suiteName + "'");
        click(findElementByXpath(String.format(SUITE_DELETE_BUTTON_XPATH, suiteName)));
        return new DeleteSuiteModal(getDriver());
    }

    public CloneSuiteModal clickCloneSuiteButtonBySuiteName(String suiteName) {
        log.info("Click clone button in suite: '" + suiteName + "'");
        click(findElementByXpath(String.format(SUITE_CLONE_BUTTON_XPATH, suiteName)));
        return new CloneSuiteModal(getDriver());
    }
}
