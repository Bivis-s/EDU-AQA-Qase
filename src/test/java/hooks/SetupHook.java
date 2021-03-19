package hooks;

import io.cucumber.core.api.Scenario;
import io.cucumber.java.Before;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import setups.PropertyDriver;
import utils.readers.DriverPropertyReader;
import world_context.WorldContext;

@Log4j2
@RequiredArgsConstructor
public class SetupHook {
    private final WorldContext worldContext;

    @Before(order = 1)
    public void setup(Scenario scenario) {
        log.info("Start scenario '" + scenario.getName() + "'");
        worldContext.setScenario(scenario);
        worldContext.setDriver(new PropertyDriver(new DriverPropertyReader().getDriverProperties()));
    }
}
