package steps.prelogin;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import pageobjects.HomePage;
import pageobjects.LoginPage;
import property_objects.AccountProperties;
import utils.readers.AccountPropertyReader;
import world.World;

import static org.testng.Assert.assertNotNull;

@Log4j2
@RequiredArgsConstructor
public class PreLoginSteps {
    private final World world;
    private AccountProperties accountProperties;
    private HomePage homePage;
    private LoginPage loginPage;

    @Before
    public void initPages() {
        WebDriver driver = world.getDriver();
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
    }

    @Given("The {string} was created")
    public void theUserWasCreated(String username) {
        accountProperties = new AccountPropertyReader(username).getAccountsProperties();
    }

    @Given("Home page is opened")
    public void homePageIsOpened() {
        homePage.openPageByUrl();
    }

    @When("Click the login button in the upper right corner")
    public void clickTheLoginButtonInTheUpperRightCorner() {
        homePage.clickLoginButton();
    }

    @When("Enter email to the email field")
    public void enterUserEmailToTheEmailField() {
        loginPage.enterEmail(accountProperties.getLogin());
    }

    @When("Enter password to the field")
    public void enterUserPasswordToTheField() {
        loginPage.enterPassword(accountProperties.getPassword());
    }

    @When("Click the login button under fields")
    public void clickTheLoginButtonUnderFields() {
        loginPage.clickLoginButton();
    }

    @Given("The user is logged in")
    public void loginUnderUserData() {
        homePageIsOpened();
        clickTheLoginButtonInTheUpperRightCorner();
        enterUserEmailToTheEmailField();
        enterUserPasswordToTheField();
        clickTheLoginButtonUnderFields();
    }

    @Then("Login page is opened")
    public void loginPageIsOpened() {
        assertNotNull(loginPage.isLoaded());
    }
}
