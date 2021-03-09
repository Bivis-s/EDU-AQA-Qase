package pageobjects;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.support.PageFactory;
import setups.CustomLoadableComponent;
import setups.PropertyDriver;
import utils.readers.UrlPropertyReader;

import java.io.IOException;

@Log4j2
public abstract class BasePage<T extends CustomLoadableComponent<T>> extends CustomLoadableComponent<T> {
    public BasePage(PropertyDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    protected abstract String getPageUrl() throws IOException;

    @Override
    protected T load() {
        return refreshPage();
    }

    @SuppressWarnings("unchecked")
    public T openPage() throws IOException {
        getDriver().get(getPageUrl());
        return (T) this;
    }

    protected void open(String url) {
        getDriver().get(url);
    }

    @SuppressWarnings("unchecked")
    protected T refreshPage() {
        log.info("Refresh " + this.getClass().getSimpleName() + " page, URL: '" + getDriver().getCurrentUrl() + "'");
        getDriver().navigate().refresh();
        return (T) this;
    }

    protected String getUrlFromProperty(UrlPropertyReader.Page page) throws IOException {
        return new UrlPropertyReader().getPageUrl(page).getUrl();
    }
}
