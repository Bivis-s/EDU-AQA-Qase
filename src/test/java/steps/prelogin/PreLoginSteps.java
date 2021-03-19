package steps.prelogin;

import api.adapters.QaseApiAdapter;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import pageobjects.HomePage;
import pageobjects.LoginPage;
import property_objects.AccountProperties;
import utils.readers.AccountPropertyReader;
import utils.readers.ApiUrlsPropertyReader;
import world_context.WorldContext;

import static org.testng.Assert.assertNotNull;

@Log4j2
@RequiredArgsConstructor
public class PreLoginSteps {
    private final WorldContext worldContext;
    private AccountProperties accountProperties;
    private HomePage homePage;
    private LoginPage loginPage;

    @Before
    public void initPages() {
        homePage = new HomePage(worldContext.getDriver());
    }

    @Given("The {string} was created")
    public void theUserWasCreated(String username) {
        accountProperties = new AccountPropertyReader(username).getAccountsProperties();
        worldContext.setApiAdapter(new QaseApiAdapter(
                accountProperties,
                new ApiUrlsPropertyReader().getApiUrlProperties()));
    }

    @And("Home page is opened")
    public void homePageIsOpened() {
        homePage.openPageByUrl();
    }

    @And("Click the login button in the upper right corner")
    public void clickTheLoginButtonInTheUpperRightCorner() {
        loginPage = homePage.clickLoginButton();
    }

    @And("Enter email to the email field")
    public void enterUserEmailToTheEmailField() {
        loginPage.enterEmail(accountProperties.getLogin());
    }

    @And("Enter password to the field")
    public void enterUserPasswordToTheField() {
        loginPage.enterPassword(accountProperties.getPassword());
    }

    @When("Click the login button under fields")
    public void clickTheLoginButtonUnderFields() {
        loginPage.clickLoginButton();
    }

    @And("The user is logged in")
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
