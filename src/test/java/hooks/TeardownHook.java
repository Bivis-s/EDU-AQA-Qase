package hooks;

import io.cucumber.java.After;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import world.World;

@Log4j2
@RequiredArgsConstructor
public class TeardownHook {
    private final World world;

    @After
    public void teardown() {
        log.info("End scenario '" + world.getScenario().getName() + "'");
        WebDriver driver = world.getDriver();
        if (driver != null) {
            driver.quit();
        }
    }
}
