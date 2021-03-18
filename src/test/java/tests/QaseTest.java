package tests;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import setups.PropertyDriver;
import utils.readers.DriverPropertyReader;
import world.World;

@Log4j2
@RequiredArgsConstructor
@CucumberOptions(
        features = {"classpath:features"},
        glue = {"steps"},
        plugin = {"pretty", "html:target/cucumber.html",
                "json:target/cucumber.json"}
)
public class QaseTest extends AbstractTestNGCucumberTests {
    private final World world;
    private Scenario scenario;

    @Before
    public void setup(Scenario scenario) {
        log.info("Start scenario '" + scenario.getName() + "'");
        this.scenario = scenario;
        world.setScenario(scenario);
        world.setDriver(new PropertyDriver(new DriverPropertyReader().getDriverProperties()));
    }

    @After
    public void teardown() {
        log.info("End scenario '" + scenario.getName() + "'");
        WebDriver driver = world.getDriver();
        if (driver != null) {
            driver.quit();
        }
    }
}
