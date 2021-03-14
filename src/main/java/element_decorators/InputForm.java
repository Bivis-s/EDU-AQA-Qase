package element_decorators;

import org.openqa.selenium.WebDriver;

public class InputForm extends Form {
    public InputForm(WebDriver driver, String label) {
        super(driver, label);
    }

    public InputForm clear() {
        clear(getElement());
        return this;
    }

    public void sendKeys(CharSequence... keys) {
        sendKeys(getElement(), keys);
    }
}
