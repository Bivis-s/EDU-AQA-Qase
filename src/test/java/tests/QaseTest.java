package tests;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        features = {"classpath:features"},
        glue = {"tests", "steps", "hooks"},
        plugin = {"pretty", "html:target/cucumber.html", "json:target/cucumber.json",
                "io.qameta.allure.cucumber4jvm.AllureCucumber4Jvm"}
)
public class QaseTest extends AbstractTestNGCucumberTests {
    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
