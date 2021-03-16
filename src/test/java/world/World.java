package world;

import api.adapters.QaseApiAdapter;
import io.cucumber.java.Scenario;
import lombok.Data;
import org.openqa.selenium.WebDriver;

@Data
public class World {
    private WebDriver driver;
    private Scenario scenario;
    private QaseApiAdapter apiAdapter;
}
