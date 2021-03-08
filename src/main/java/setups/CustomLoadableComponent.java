package setups;

import lombok.extern.log4j.Log4j2;

@Log4j2
public abstract class CustomLoadableComponent<T extends CustomLoadableComponent<T>> {

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

    protected abstract T isLoaded() throws Error;
}
