package hooks;

import io.cucumber.core.api.Scenario;
import io.cucumber.java.After;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import utils.ScreenshotUtils;
import world_context.WorldContext;

@Log4j2
@RequiredArgsConstructor
public class TeardownHook {
    private final WorldContext worldContext;

    @After
    public void teardown(Scenario scenario) {
        log.info("End scenario");
        WebDriver driver = worldContext.getDriver();
        if (scenario.isFailed()) {
            scenario.embed(ScreenshotUtils.takeScreenshot(driver), "image/png");
        }
        if (driver != null) {
            driver.quit();
        }
    }
}
