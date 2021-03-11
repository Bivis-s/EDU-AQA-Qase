package element_decorators;

import org.openqa.selenium.WebDriver;

public class InputForm extends Form {
    public InputForm(WebDriver driver, String label) {
        super(driver, label);
    }

    public void sendKeys(String... keys) {
        sendKeys(getElement(), keys);
    }
}
