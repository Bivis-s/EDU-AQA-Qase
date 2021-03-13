package pageobjects;

import enums.UrlPageName;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import setups.CustomLoadableComponent;
import utils.readers.UrlPropertyReader;

@Log4j2
public abstract class BasePage<T extends CustomLoadableComponent<T>> extends CustomLoadableComponent<T> {
    public BasePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    protected abstract String getPageUrl();

    @Override
    protected T load() {
        return refreshPage();
    }

    public T openPage() {
        getDriver().get(getPageUrl());
        return get();
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

    protected String getUrlFromProperty(UrlPageName page) {
        return new UrlPropertyReader().getPageUrl(page).getUrl();
    }
}
