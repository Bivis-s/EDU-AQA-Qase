package world_context;

import api.adapters.QaseApiAdapter;
import io.cucumber.core.api.Scenario;
import lombok.Data;
import org.openqa.selenium.WebDriver;

@Data
public class WorldContext {
    private WebDriver driver;
    private Scenario scenario;
    private QaseApiAdapter apiAdapter;
}
