package setups;

import helpers.ElementsManipulator;
import org.openqa.selenium.WebDriver;

public abstract class CustomLoadableComponent<T extends CustomLoadableComponent<T>> extends ElementsManipulator {

    public CustomLoadableComponent(WebDriver driver) {
        super(driver);
    }

    @SuppressWarnings("unchecked")
    public T get() {
        try {
            isLoaded();
            return (T) this;
        } catch (Error e) {
            load().isLoaded();
            return (T) this;
        }
    }

    protected abstract T load();

    public abstract T isLoaded();
}
