package tests;

import enums.UrlPageName;
import lombok.extern.log4j.Log4j2;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageobjects.HomePage;
import pageobjects.LoginPage;
import pageobjects.ProjectsPage;
import property_objects.AccountProperties;
import property_objects.UrlProperties;
import setups.PropertyDriver;
import utils.readers.AccountPropertyReader;
import utils.readers.UrlPropertyReader;

import static org.testng.Assert.*;

@Log4j2
public class TemporaryTestsForTests {
    private PropertyDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        driver = new PropertyDriver();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void accountPropertiesReaderTest() {
        AccountProperties accountProperties =
                new AccountPropertyReader("existing-user").getAccountsProperties();
        assertNotEquals(accountProperties.getLogin(), "");
        assertNotEquals(accountProperties.getPassword(), "");
    }

    @Test
    public void urlsPropertiesReaderTest() {
        UrlProperties urlProperties = new UrlPropertyReader().getPageUrl(UrlPageName.PROJECTS);
        assertEquals(urlProperties.getUrl(), "https://qase.io/projects");
    }

    @Test
    public void driverFactoryTest() {
        String url = "https://www.onliner.by/";
        driver.get(url);
        assertEquals(driver.getCurrentUrl(), url);
    }

    @Test
    public void clickLoginButtonTest() {
        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = homePage
                .openPage()
                .clickLoginButton();
        assertNotNull(loginPage.isLoaded());
    }

    @Test
    public void loginTest() {
        HomePage homePage = new HomePage(driver);
        AccountProperties accountProperties =
                new AccountPropertyReader("existing-user").getAccountsProperties();
        ProjectsPage projectsPage = homePage
                .openPage()
                .clickLoginButton()
                .enterEmail(accountProperties.getLogin())
                .enterPassword(accountProperties.getPassword())
                .clickLoginButton();
        log.info("Assert " + projectsPage.getClass().getSimpleName() + " is loaded");
        assertNotNull(projectsPage.isLoaded());
    }
}
