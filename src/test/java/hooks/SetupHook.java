package hooks;

import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import setups.PropertyDriver;
import utils.readers.DriverPropertyReader;
import world.World;

@Log4j2
@RequiredArgsConstructor
public class SetupHook {
    private final World world;

    @Before(order = 1)
    public void setup(Scenario scenario) {
        log.info("Start scenario '" + scenario.getName() + "'");
        world.setScenario(scenario);
        world.setDriver(new PropertyDriver(new DriverPropertyReader().getDriverProperties()));
    }
}
