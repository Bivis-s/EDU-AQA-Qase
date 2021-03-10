package setups;

import helpers.ElementsManipulator;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;

@Log4j2
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
            log.error(e.getMessage());
            load().isLoaded();
            return (T) this;
        }
    }

    protected abstract T load();

    public abstract T isLoaded() throws Error;
}
